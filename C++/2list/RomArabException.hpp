/*
@title: head of Exception
@author: Stanisław Woźniak
@version: 1.0
*/
#include<iostream>

using namespace std;

class RomArabException: public exception{  //Exception

  public:
    const char* printOne() const throw();
    const char* printTwo() const throw();
};
