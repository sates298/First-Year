/*
@author Stanisław Woźniak
@title: Figure.hpp
*/
#ifndef FIGURE
#define FIGURE
using namespace std;

class Figure{

  public:
    virtual double field() =0;
    virtual double perimeter() =0;

};
#endif
