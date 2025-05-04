package com.shapes.services.impl;

import com.shapes.entity.Oval;
import com.shapes.entity.Point;
import com.shapes.entity.Shape;
import com.shapes.exception.OvalProjectException;
import com.shapes.services.AreaCalculator;

public class OvalAreaCalculator implements AreaCalculator {

    @Override
    public double calculateArea(Shape shape) throws OvalProjectException {

        if (shape instanceof Oval) {
            Oval oval = (Oval) shape;
            double a = OvalUtils.getSemiMajorAxis(oval.getPoint1(), oval.getPoint2());
            double b = OvalUtils.getSemiMinorAxis(oval.getPoint1(), oval.getPoint2());
            return Math.PI * a * b;
        }
        throw new OvalProjectException("OvalAreaCalculator supports only Oval shapes");
    }
}


