/*
@author: Stanisław Woźniak
@title: diamond.hpp
*/
#include "Quadrangle.hpp"

class Diamond: public Quadrangle{

  public:
    Diamond(double side, double angle);
    double field();
    double perimeter();
};
