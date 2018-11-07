/*
@author: Stanisław Woźniak
@title: circle.hpp
*/
#include "Circle.hpp"
#include <cmath>


Circle::Circle(double radius){
  this -> radius = radius;
}

double Circle::field(){
  return radius*radius*M_PI;
}

double Circle::perimeter(){
  return radius*2*M_PI;
}

