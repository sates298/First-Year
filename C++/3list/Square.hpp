/*
@author Stanisław Woźniak
@title: Square.hpp
*/
#ifndef SQUARE
#define SQUARE
#include "Quadrangle.hpp"

class Square: public Quadrangle{

  public:
    Square(double side);
    double field();
    double perimeter();
};
#endif
