/*
@title: Main
@author: Stanisław Woźniak
@version: 1.0
*/

#include "RomArab.hpp"
#include "RomArabException.hpp"
#include<iostream>


int main(int argc, char* argv[]){
  char* p;

  if (argc == 1) {
    cout << "write something!" << endl;
  } else {
    int x;
    for (int i = 1; i< argc; i++) {
      strtol(argv[i], &p, 10);    //sprawdzanie czy jest intem
      if(*p == 0 ){
        try {
          x = atoi(argv[i]);
          cout << argv[i] << " - "  << RomArab::arabToRom(x) << endl;

        } catch (RomArabException ex) {
          cout << argv[i] << ex.printTwo();
        }
      } else {
        try {
          cout << argv[i] << " - " << RomArab::romToArab(argv[i]) << endl;
        } catch (RomArabException e) {
          cout << argv[i] << " - " <<  e.printOne();
        }
      }
    }
  }

  return 0;
}
