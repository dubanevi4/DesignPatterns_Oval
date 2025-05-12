package lt.esdc.shapes.service.impl;

import lt.esdc.shapes.entity.Oval;
import lt.esdc.shapes.service.AreaCalculator;

public class OvalAreaCalculator implements AreaCalculator<Oval> {

    @Override
    public double calculateArea(Oval oval) {
        double a = OvalAxisCalculator.getSemiMajorAxis(oval.getPoint1(), oval.getPoint2());
        double b = OvalAxisCalculator.getSemiMinorAxis(oval.getPoint1(), oval.getPoint2());
        return Math.PI * a * b;
    }
}


