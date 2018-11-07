package com.stanislaw.quadrangles;
/*
@author: Stanisław Woźniak
@version: 1.0
*/
public class Diamond extends Quadrangle {

    public Diamond(double side1, double angle) {
        super(side1, side1, side1, side1, angle);
    }

    @Override
    public double field() {
        return this.side1*this.side1*Math.sin(Math.toRadians(this.angle));
    }

    @Override
    public double perimeter() {
        return 4*this.side1;
    }
}
