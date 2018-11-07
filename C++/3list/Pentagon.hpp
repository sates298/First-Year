/*
@author: Stanisław Woźniak
@title: pentagon.hpp
*/
#ifndef PENTAGON
#define PENTAGON
#include "Figure.hpp"

class Pentagon: public Figure{

  private:
    double side;

  public:
    Pentagon(double side);
    double field();
    double perimeter();
};
#endif
