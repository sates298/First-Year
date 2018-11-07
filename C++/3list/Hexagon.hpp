/*
@author Stanisław Woźniak
@title hexagon.hpp
*/
#ifndef HEXAGON
#define HEXAGON
#include "Figure.hpp"

class Hexagon: public Figure{

  private:
    double side;

  public:
    Hexagon(double side);
    double field();
    double perimeter();

};
#endif
