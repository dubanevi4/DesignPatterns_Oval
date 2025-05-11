package lt.esdc.shapes.services.impl;
import lt.esdc.shapes.entity.Oval;
import lt.esdc.shapes.services.PerimeterCalculator;

public class OvalPerimeterCalculator implements PerimeterCalculator<Oval> {

    @Override
    public double calculatePerimeter(Oval oval) {
        double a = OvalAxisCalculator.getSemiMajorAxis(oval.getPoint1(), oval.getPoint2());
        double b = OvalAxisCalculator.getSemiMinorAxis(oval.getPoint1(), oval.getPoint2());
        return Math.PI * (3 * (a + b) - Math.sqrt((3 * a + b) * (a + 3 * b)));
    }
}
