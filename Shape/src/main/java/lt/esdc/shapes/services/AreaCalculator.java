package lt.esdc.shapes.services;

import lt.esdc.shapes.entity.Shape;
import lt.esdc.shapes.exception.OvalProjectException;

public interface AreaCalculator {
    double calculateArea(Shape shape) throws OvalProjectException;
}
