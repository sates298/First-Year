package com.stanislaw.moreFigures;
/*
@author: Stanisław Woźniak
@version: 1.0
*/
import com.stanislaw.core.FigureImpl;

public class Circle extends FigureImpl {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double field() {
        return this.radius*this.radius*Math.PI;
    }

    @Override
    public double perimeter() {
        return 2*Math.PI*this.radius;
    }
}
