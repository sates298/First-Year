package com.stanislaw;
/*
@author: Stanisław Woźniak
@version: 1.0
*/

import com.stanislaw.core.FigureException;
import com.stanislaw.core.FigureImpl;
import com.stanislaw.moreFigures.Circle;
import com.stanislaw.moreFigures.Hexagon;
import com.stanislaw.moreFigures.Pentagon;
import com.stanislaw.quadrangles.Diamond;
import com.stanislaw.quadrangles.Rectangle;
import com.stanislaw.quadrangles.Square;

public class Main {

    public static void main(String[] args) {
        try {

            String firstArg = args[0];
            int index = 1;
            FigureImpl[] figures = new FigureImpl[firstArg.length()];
            for (int i = 0; i < firstArg.length(); i++) {

                if (firstArg.charAt(i) == 'c') {                        //checking character
                    if (args[index].equals(args[index + 1]) &&               //checking sides
                            args[index].equals(args[index + 2]) && args[index].equals(args[index + 2])
                            && args[index].equals(args[index + 3])) {
                        index = index + 4;

                        if (Double.parseDouble(args[index]) == 90) {                            //checking angle
                            Square square = new Square(Double.parseDouble(args[index - 4]));
                            figures[i] = square;/*
                            System.out.println("Square: field = " + square.field()
                                    + "; parimeter = " + square.perimeter());*/

                            index++;
                        } else if (Double.parseDouble(args[index]) < 180 &&
                                Double.parseDouble(args[index]) > 0) {
                            Diamond diamond = new Diamond(Double.parseDouble(args[index - 4]),
                                    Double.parseDouble(args[index]));
                            figures[i] = diamond;
                            /*System.out.println("Diamond: field = " + diamond.field()
                                    + "; parimeter = " + diamond.perimeter());*/
                            index++;
                        }
                    } else if (args[index].equals(args[index + 1]) &&               //checking sides
                            args[index + 2].equals(args[index + 3]) &&
                            Double.parseDouble(args[index + 4]) == 90) {                //checking angle
                        Rectangle rectangle = new Rectangle(Double.parseDouble(args[index]),
                                Double.parseDouble(args[index + 3]));
                        figures[i] = rectangle;
                        /*System.out.println("Rectangle: field = " + rectangle.field()
                                + "; parimeter = " + rectangle.perimeter());*/
                        index = index + 5;
                    } else {
                        throw new FigureException();
                    }
                } else if (firstArg.charAt(i) == 'o') {                     //checking character
                    Circle circle = new Circle(Double.parseDouble(args[index]));
                    figures[i] = circle;
                   /* System.out.println("Circle: field = " + circle.field()
                            + "; parimeter = " + circle.perimeter());*/

                    index++;

                } else if (firstArg.charAt(i) == 'p') {                         //checking character
                    Pentagon pentagon = new Pentagon(Double.parseDouble(args[index]));
                    figures[i] = pentagon;
                /*    System.out.println("Pentagon: field = " + pentagon.field()
                            + "; parimeter = " + pentagon.perimeter());*/
                    index++;
                } else if (firstArg.charAt(i) == 's') {                         //checking character
                    Hexagon hexagon = new Hexagon(Double.parseDouble(args[index]));
                    figures[i] = hexagon;
                    /*System.out.println("Hexagon: field = " + hexagon.field()
                            + "; parimeter = " + hexagon.perimeter());
*/
                    index++;
                } else {
                    throw new FigureException();
                }
            }
            for (FigureImpl f : figures) {
                System.out.println(f.getClass().getName() + ": field = " + f.field()
                        + "; parimeter = " + f.perimeter());
            }
        } catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("Write something!!");
        } catch (FigureException | NumberFormatException e) {
            System.out.println("Wrong data!");
        }
    }
}
