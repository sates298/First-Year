
/*
@author: Stanisław Woźniak
@title: main
*/

#include "Figure.hpp"
#include "Circle.hpp"
#include "Square.hpp"
#include "Rectangle.hpp"
#include "Diamond.hpp"
#include "Pentagon.hpp"
#include "Hexagon.hpp"
#include "FigureException.hpp"
#include "Figure.hpp"
#include <vector>
#include <memory>
#include <iostream>

int main(int argc, char** argv) {
  if(argc == 1){
    cout << "write something!" << endl;
  } else {
    try{
      string::size_type p;
      string temp;
      string firstArg = argv[1];
      int index = 2;
      vector<shared_ptr<Figure>> figures;
      for (int i = 0; i < firstArg.length(); i++) {

        if (firstArg[i] == 'c') {
          for(int j =0; j<5; j++){
            temp = argv[index+j];
	    stod(temp, &p);
            if(p != temp.length()){
              FigureException exc;
              throw exc;
            }
          }
          if(stod(argv[index + 4]) >=180 || stod(argv[index + 4]) <0){
            FigureException exn;
	    throw exn;
          }
          if (stod(argv[index]) == stod(argv[index + 1]) && stod(argv[index]) == stod(argv[index + 2]) && stod(argv[index]) == stod(argv[index + 3])){
            index = index + 4;
            if (stod(argv[index]) == 90) {
              Square* square = new Square(stod(argv[index - 4]));
	      figures.emplace_back(square);
	      //cout << "Square: field = " << square->field() << "; parimeter = " << square->perimeter() << endl;
              index++;
            } else if (stod(argv[index]) != 90 && stod(argv[index]) > 0 && stod(argv[index]) < 180) {
              Diamond* diamond = new Diamond(stod(argv[index - 4]), stod(argv[index]));
              figures.emplace_back(diamond);
              //cout << "Diamond: field = " << diamond->field() << "; parimeter = " << diamond->perimeter() << endl;
              index++;
            }
          } else if (stod(argv[index]) == stod(argv[index + 1]) && stod(argv[index + 2]) == stod(argv[index + 3]) && stod(argv[index + 4]) == 90) {
            Rectangle* rectangle = new Rectangle(stod(argv[index]), stod(argv[index + 3]));
	    figures.emplace_back(rectangle);
            //cout << "Rectangle: field = " << rectangle->field() << "; parimeter = " << rectangle->perimeter() << endl;
            index = index + 5;
          } else {
            FigureException f;
	    throw f;
          }
        } else if (firstArg[i] == 'o') {
	  temp = argv[index];
          stod(temp, &p);
          if(p != temp.length()){
            FigureException exo;
            throw exo;
          }
          Circle* circle = new Circle(stod(argv[index]));
	  figures.emplace_back(circle);
          //cout << "Circle: field = " << circle->field() << "; parimeter = " << circle->perimeter() << endl;
          index++;

        } else if (firstArg[i] == 'p') {
          temp = argv[index];
          stod(argv[index], &p);
          if(p != temp.length()){
            FigureException exp;
            throw exp;
          }
          Pentagon* pentagon = new Pentagon(stod(argv[index]));
	  figures.emplace_back(pentagon);
          //cout << "Pentagon: field = " << pentagon->field() << "; parimeter = " << pentagon->perimeter() << endl;
          index++;
        } else if (firstArg[i] == 's') {
          temp = argv[index];
          stod(argv[index], &p);
          if(p != temp.length()){
            FigureException exs;
            throw exs;
          }
          Hexagon* hexagon = new Hexagon(stod(argv[index]));
	  figures.emplace_back(hexagon);
          //cout << "Hexagon: field = " << hexagon->field() << "; parimeter = " <<  hexagon->perimeter() << endl;
          index++;
        } else {
          FigureException exa;
          throw exa;
        }
	for(int i=0; i<firstArg.length(); i++){
	  cout << i << "nazwa: field = " << figures[i]->field() << "; parimeter = " << figures[i]->perimeter() << endl;
        }

      }
    } catch (...) {
      cout << "Wrong data!" << endl;
    }
  }

  return 0;
}

