
#include <iostream>
#include <cstring>
#include "tree.h"
using namespace std;

int main(){

  bool exit = false;
  char type;
  int k;
  int valueI;
  double valueD;
  string valueS;
  
  cout << "choose type(default int): d - double, s - string, i - integer: ";
  cin >> type;

  cout << "enter k: ";
  cin >> k;
  Tree<double> treed; // = Tree<double>();
  Tree<int> treeI; //= Tree<int>();
  Tree<string> trees; // = Tree<string>();

  if(type == 'd'){
    treed.setK(k);
  }else if(type == 's'){
    trees.setK(k);
  }else{
    treeI.setK(k);
  }

  for(;;){
    if(exit){
      break;
    }
    cout << "i = insert\n" << "d = delete\n" << "p = print\n" << "m = insert many\n"<< "s = search\n"  << "x = exit\n";
    char choice;
    cin >> choice;
    switch(choice){

      case 'i':
        cout << "enter inserting value: ";
	if(type == 'd'){
          cin >> valueD;
          treed.insert(valueD);
        }else if(type == 's'){
	  cin >> valueS;
          trees.insert(valueS);
        }else{
	  cin >> valueI;
          treeI.insert(valueI);
        }
        break;
      case 'd':
        cout << "enter deleting value: ";
	if(type == 'd'){
          cin >> valueD;
          treed.remove(valueD);
        }else if(type == 's'){
	  cin >> valueS;
          trees.remove(valueS);
        }else{
	  cin >> valueI;
          treeI.remove(valueI);
        }
        break;
      case 'p':
        if(type == 'd'){
          treed.print();
        }else if(type == 's'){
          trees.print();
        }else{
          treeI.print();
        }
        cout <<'\n';
        break;
      case 'm':
        int howmany;
        cout << "enter how many values do you want insert: ";
        cin >> howmany;
        for(int i=0; i<howmany; i++){
          cout << "enter inserting value: ";
          if(type == 'd'){
            cin >> valueD;
            treed.insert(valueD);
          }else if(type == 's'){
	    cin >> valueS;
            trees.insert(valueS);
          }else{
	    cin >> valueI;
            treeI.insert(valueI);
          }
        }
        break;
      case 's':
        bool isHere;
        cout << "enter searching value: ";
        if(type == 'd'){
          cin >> valueD;
          isHere = (treed.search(valueD)!=NULL);
        }else if(type == 's'){
	  cin >> valueS;
          isHere = (trees.search(valueS)!=NULL);
        }else{
	  cin >> valueI;
          isHere = (treeI.search(valueI)!=NULL);
        }
        if(isHere){
          cout << "it is here\n";
	}else{
	  cout << "it is not here\n";
 	}
        break;
      case 'x':
        exit = true;
        break;
    }
  }

  cout << "Have a nice day \n";

  return 0;
}
