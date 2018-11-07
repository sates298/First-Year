#ifndef TREE_H
#define TREE_H

#include<cstdlib>
#include "branch.h"

using namespace std;

template <typename T>
class Tree{

  Branch<T> *root;
  int t;

public:

  //Tree();
  void setK(int k){
    root = NULL;
    if(2%k == 0){
      t = k/2 + 1;
    }else{
      t = (k+1)/2;
    }
  }
  void print(){
    if(root != NULL){
       char tab[2] = {'-','>'};
       root->print(tab);
    }
  }
  Branch<T>* search(T v){
    return (root == NULL)? NULL : root->search(v);
  }
  void insert(T v){
    if(root == NULL){
      root = new Branch<T>(t, true);
      root->values[0] = v;
      root->n = 1;
    }
    else {
      if(root->n == 2*t - 1){
        Branch<T> *s = new Branch<T>(t, false);
        s->C[0] = root;
        s->splitChild(0, root);
        int i = 0;
        if(s->values[0] < v){
  	  i++;
        }
        s->C[i]->insertNonFull(v);
        root = s;
      }
      else{
        root->insertNonFull(v);
      }
    }
  }
  void remove(T v){

    if(!root){
      cout<< "The tree is empty!" << '\n';
      return;
    }
    root->remove(v);

    if(root->n == 0){
      Branch<T> *temp = root;
      if(root->leaf){
        root = NULL;
      }
      else{
        root = root->C[0];
      }

      delete temp;
    }
    return;
  }
};

#endif
