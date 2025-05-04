package com.shapes.services.impl;
import com.shapes.entity.Oval;
import com.shapes.entity.Shape;
import com.shapes.services.PerimeterCalculator;

public class OvalPerimeterCalculator implements PerimeterCalculator {

    @Override
    public double calculatePerimeter(Shape shape) {
        if (shape instanceof Oval) {
            Oval oval = (Oval) shape;
            double a = OvalUtils.getSemiMajorAxis(oval.getPoint1(), oval.getPoint2());
            double b = OvalUtils.getSemiMinorAxis(oval.getPoint1(), oval.getPoint2());

            return Math.PI * (3 * (a + b) - Math.sqrt((3 * a + b) * (a + 3 * b)));
        }
        throw new IllegalArgumentException("OvalPerimeterCalculator supports only Oval shapes");
    }
}
