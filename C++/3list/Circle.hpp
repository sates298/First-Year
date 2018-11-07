/*
@author: Stanisław Woźniak
@title: Circle.hpp
*/
#ifndef CIRCLE
#define CIRCLE
#include "Figure.hpp"

class Circle: public Figure{

  private:
    double radius;

  public:
    Circle(double radius);
    double field();
    double perimeter();

};
#endif
