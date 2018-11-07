package com.stanislaw.quadrangles;

/*
@author: Stanisław Woźniak
@version: 1.0
*/
public class Rectangle extends Quadrangle {

    public Rectangle(double side1, double side3) {
        super(side1, side1, side3, side3, 90);
    }

    @Override
    public double field() {
        return this.side1 * this.side3;
    }

    @Override
    public double perimeter() {
        return this.side1 * 2 + this.side3 * 2;
    }

}
