package com.shapes.services.impl;

import com.shapes.entity.Oval;
import com.shapes.entity.Point;
import com.shapes.entity.Shape;
import com.shapes.services.AreaCalculator;

public class OvalAreaCalculator implements AreaCalculator {

    @Override
    public double calculateArea(Shape shape) {
//        validateShape(shape);
//        Oval oval = (Oval) shape;
//
//        if (!(shape instanceof Oval)) {
//            throw new IllegalArgumentException("OvalAreaCalculator supports only Oval shapes");
//        }
        Oval oval = (Oval) shape;

        double a = getSemiMajorAxis(oval.getPoint1(), oval.getPoint2());
        double b = getSemiMinorAxis(oval.getPoint1(), oval.getPoint2());

        return Math.PI * a * b;
    }

    private double getSemiMajorAxis(Point point1, Point point2) {
        return Math.abs(point2.getX() - point1.getX()) / 2.0;
    }

    private double getSemiMinorAxis(Point point1, Point point2) {
        return Math.abs(point2.getY() - point1.getY()) / 2.0;
    }
}


