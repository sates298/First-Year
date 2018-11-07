/*
@title: Exception
@author: Stanisław Woźniak
@version: 1.0
*/
#include "RomArabException.hpp"

using namespace std;

const char* RomArabException::printOne() const throw(){
 return " - It's not roman number\n";
}

const char* RomArabException::printTwo() const throw(){
 return " - wrong number\n";
}
