/*
@author: Stanisław Woźniak
@title: pentagon.cpp
*/

#include "Pentagon.hpp"
#include <cmath>

Pentagon::Pentagon(double side){
  this -> side = side;
}

double Pentagon::field(){
  return side*side*1.25*tan((54*M_PI)/180);
}

double Pentagon::perimeter(){
  return 5*side;
}
