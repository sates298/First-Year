/*
@author: Stanisław Woźniak
@title: diamond.cpp
*/

#include "Diamond.hpp"
#include <cmath>

Diamond::Diamond(double side, double angle){
  side1 = side;
  side2 = side;
  side3 = side;
  side4 = side;
  this -> angle = angle;
}

double Diamond::field(){
  return sin((angle*M_PI)/180)*side1*side1;
}

double Diamond::perimeter(){
  return 4*side1;
}

