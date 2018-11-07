package com.stanislaw.moreFigures;
/*
@author: Stanisław Woźniak
@version: 1.0
*/
import com.stanislaw.core.FigureImpl;

public class Pentagon extends FigureImpl {

    private double side;

    public Pentagon(double side) {
        this.side = side;
    }

    @Override
    public double field() {
        return this.side*this.side*1.25*Math.tan(Math.toRadians(54));
    }

    @Override
    public double perimeter() {
        return 5*this.side;
    }
}
