/*
@author: Stanisław Woźniak
@title: rectangle.cpp
*/
#include "Rectangle.hpp"

Rectangle::Rectangle(double side1, double side3){
  this -> side1 = side1;
  side2 = side1;
  this -> side3 = side3;
  side4 = side3;
  angle = 90;
}

double Rectangle::field(){
  return side1*side3;
}

double Rectangle::perimeter(){
  return 4*side1;
}
