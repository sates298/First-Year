/*
@author Stanisław Woźniak
@title: Square.cpp
*/
#include "Square.hpp"

Square::Square(double side){
  side1 = side;
  side2 = side;
  side3 = side;
  side4 = side;
  angle = 90;
}

double Square::field(){
  return side1*side1;
}

double Square::perimeter(){
  return 4*side1;
}


