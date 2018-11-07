#ifndef BRANCH_H
#define BRANCH_H

#include <iostream>
#include<cstring>
#include "tree.h"

using namespace std;

template <typename T>
class Branch{
public:
  T *values;
  int t;  //minimum degree
  Branch<T> **C; //child pointers
  int n; //number of values
  bool leaf;



  Branch(int k, bool isLeaf){
    leaf = isLeaf;
    t = k;

    values = new T[2*t - 1];
    C = new Branch<T> *[2*t];

    n=0;

  }


  void print(char s[]){
  
    cout << s;
    for(int i=0; i < n; i++){
      cout << values[i] << " ";
    }
    cout << endl;

    for(int i=0; i<n+1;i++){
      int j=0;
      char s1[256];
      while(s[j] != '\0'){
        s1[j] = s[j];
        j++;
      }
      s1[j] = '\0';
      if(C[i] != NULL){
        char s2[256] = {' ',' '};
        strcat(s2, s1);
        C[i]->print(s2);
      }
    }
  }


  Branch<T> *search(T v){

    int i=0;
    while(i<n && v > values[i]){
      i++;
    }

    if(values[i] == v){
      return this;
    }

    if(leaf){
      return NULL;
    }


    return C[i]->search(v);
  }


  int findValue(T v){
    int idx =0;
    while(idx<n && values[idx]<v){
      ++idx;
    }
    return idx;
  }


  void insertNonFull(T v){

    int i = n-1;

    if (leaf){

      while (i >= 0 && values[i] > v)
      {
        values[i+1] = values[i];
        i--;
      }

      values[i+1] = v;
      n = n+1;
    }
    else
    {

      while (i >= 0 && values[i] > v){
        i--;
      }

      if (C[i+1]->n == 2*t-1)
      {
        splitChild(i+1, C[i+1]);

        if (values[i+1] < v){
          i++;
        }
      }
      C[i+1]->insertNonFull(v);
    }

  }


  void splitChild(int i, Branch<T> *y){

    Branch<T> *z = new Branch<T>(y->t, y->leaf);
    z->n = t - 1;

    for (int j = 0; j < t-1; j++){
      z->values[j] = y->values[j+t];
    }

    if (y->leaf == false){
      for (int j = 0; j < t; j++){
        z->C[j] = y->C[j+t];
      }
    }

    y->n = t - 1;

    for (int j = n; j >= i+1; j--){
      C[j+1] = C[j];
    }

    C[i+1] = z;

    for (int j = n-1; j >= i; j--){
      values[j+1] = values[j];
    }

    values[i] = y->values[t-1];

    n = n + 1;
  }


  void remove(T v){
    int idx = findValue(v);

    if(idx < n && values[idx] == v){
      if(leaf){
        removeFromLeaf(idx);
      }
      else{
        removeFromNonLeaf(idx);
      }
    }
    else{
       if(leaf){
        cout << "The value " << v << " is does not exist in the tree\n";
        return;
      }

      bool flag = ((idx==n)? true : false);

      if(C[idx]->n < t){
        fill(idx);
      }
  
      if(flag && idx > n){
        C[idx - 1]->remove(v);
      }
      else{
        C[idx]->remove(v);
      }
    }
    return;
  }


  void removeFromLeaf(int idx){
    for(int i=idx+1; i<n; ++i){
      values[i-1] = values[i];
    }

    n--;
    return;
  }


  void removeFromNonLeaf(int idx){
    T v = values[idx];

    if(C[idx]->n >= t){
      T pred = getPred(idx);
      values[idx] = pred;
      C[idx]->remove(pred);
    }
    else if(C[idx+1]->n >= t){
      T succ = getSucc(idx);
      values[idx] = succ;
      C[idx+1] -> remove(succ);
    }
    else{
      merge(idx);
      C[idx]->remove(v);
    }
    return;
  }


  T getPred(int idx){
    Branch<T> *curr = C[idx];
    while(!curr->leaf){
      curr=curr->C[curr->n];
    }
    return curr->values[(curr->n)-1];
  }


  T getSucc(int idx){
    Branch<T> *curr=C[idx+1];
    while(!curr->leaf){
      curr = curr->C[0];
    }
    return curr->values[0];
  }


  void fill(int idx){

    if(idx!=0 && C[idx-1]->n >= t){
      borrowFromPrev(idx);
    }
    else if(idx!=n && C[idx+1]->n >=t){
      borrowFromNext(idx);
    }
    else{
      if(idx!=n){
        merge(idx);
      }
      else{
        merge(idx-1);
      }
    }
    return;
  }


  void borrowFromPrev(int idx){

    Branch<T> *child = C[idx];
    Branch<T> *sibling = C[idx-1];

    for(int i = child->n-1; i>=0; --i){
      child->values[i+1] = child->values[i];
    }

    if(!child->leaf){
      for(int i=child->n; i>=0; --i){
        child->C[i+1] = child->C[i];
      }
    }

    child->values[0] = values[idx-1];
 
    if(!leaf){
      child->C[0] = sibling->C[sibling->n];
    }

    values[idx-1] = sibling->values[sibling->n-1];

    child->n +=1;
    sibling->n -=1;

    return;
  }


  void borrowFromNext(int idx){

    Branch<T> *child = C[idx];
    Branch<T> *sibling = C[idx+1];

    child->values[child->n] = values[idx];

    if(!child->leaf){
      child->C[child->n +1] = sibling->C[0];
    }

    values[idx] = sibling->values[0];

    for(int i=1; i< sibling->n; ++i){
      sibling->values[i-1] = sibling->values[i];
    }

    if(!sibling->leaf){
      for(int i=1; i<=sibling->n; ++i){
        sibling->C[i-1] = sibling->C[i];
      }
    }

    child->n += 1;
    sibling->n -= 1;

    return;
  }


  void merge(int idx){

    Branch<T> *child = C[idx];
    Branch<T> *sibling = C[idx +1];

    child->values[t-1] = values[idx];

    for (int i=0; i<sibling->n; ++i){
      child->values[i+t] = sibling->values[i];
    }
    if (!child->leaf){
      for(int i=0; i<=sibling->n; ++i){
        child->C[i+t] = sibling->C[i];
      }
    }

    for (int i=idx+1; i<n; ++i){
      values[i-1] = values[i];
    }

    for (int i=idx+2; i<=n; ++i){
      C[i-1] = C[i];
    }
    child->n += sibling->n+1;
    n--;

    delete(sibling);
    return;
  }


  //friend class Tree;
};

#endif
