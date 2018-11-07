/*
@author Stanisław Woźniak
@title Rectangle.hpp
*/
#ifndef RECTANGLE
#define RECTANGLE
#include "Quadrangle.hpp"

class Rectangle: public Quadrangle{

  public:
    Rectangle(double side1, double side3);
    double field();
    double perimeter();
};
#endif
