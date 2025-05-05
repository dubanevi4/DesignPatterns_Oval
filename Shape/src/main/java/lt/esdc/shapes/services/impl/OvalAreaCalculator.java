package lt.esdc.shapes.services.impl;

import lt.esdc.shapes.entity.Oval;
import lt.esdc.shapes.entity.Shape;
import lt.esdc.shapes.exception.OvalProjectException;
import lt.esdc.shapes.services.AreaCalculator;
import lt.esdc.shapes.util.OvalUtils;

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


