package lt.esdc.shapes.services;

import lt.esdc.shapes.entity.Shape;
import lt.esdc.shapes.exception.OvalProjectException;

public interface PerimeterCalculator {
    double calculatePerimeter(Shape shape) throws OvalProjectException ;
}
