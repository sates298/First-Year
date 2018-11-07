package com.stanislaw.moreFigures;
/*
@author: Stanisław Woźniak
@version: 1.0
*/
import com.stanislaw.core.FigureImpl;

public class Hexagon extends FigureImpl {

    private double side;

    public Hexagon(double side) {
        this.side = side;
    }

    @Override
    public double field() {
        return 6* this.side*this.side*0.25*Math.sqrt(3);
    }

    @Override
    public double perimeter() {
        return 6*this.side;
    }
}
