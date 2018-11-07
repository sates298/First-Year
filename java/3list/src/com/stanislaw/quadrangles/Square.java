package com.stanislaw.quadrangles;
/*
@author: Stanisław Woźniak
@version: 1.0
*/
public class Square extends Quadrangle {

    public Square(double side1) {
        super(side1, side1, side1, side1, 90);
    }

    @Override
    public double field() {
        return this.side1*this.side1;
    }

    @Override
    public double perimeter() {
        return 4*this.side1;
    }
}
