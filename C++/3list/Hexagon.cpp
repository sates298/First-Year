#include "Hexagon.hpp"
#include <cmath>

Hexagon::Hexagon(double side){
  this -> side = side;
}

double Hexagon::field(){
  return side*side*sqrt(3)*1.5;
}

double Hexagon::perimeter(){
  return 6*side;
}
