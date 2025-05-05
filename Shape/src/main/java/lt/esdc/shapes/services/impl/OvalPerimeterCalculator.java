package lt.esdc.shapes.services.impl;
import lt.esdc.shapes.entity.Oval;
import lt.esdc.shapes.entity.Shape;
import lt.esdc.shapes.exception.OvalProjectException;
import lt.esdc.shapes.services.PerimeterCalculator;
import lt.esdc.shapes.util.OvalUtils;

public class OvalPerimeterCalculator implements PerimeterCalculator {

    @Override
    public double calculatePerimeter(Shape shape) throws OvalProjectException {
        if (shape instanceof Oval) {
            Oval oval = (Oval) shape;
            double a = OvalUtils.getSemiMajorAxis(oval.getPoint1(), oval.getPoint2());
            double b = OvalUtils.getSemiMinorAxis(oval.getPoint1(), oval.getPoint2());

            return Math.PI * (3 * (a + b) - Math.sqrt((3 * a + b) * (a + 3 * b)));
        }
        throw new OvalProjectException("OvalPerimeterCalculator supports only Oval shapes");
    }
}
