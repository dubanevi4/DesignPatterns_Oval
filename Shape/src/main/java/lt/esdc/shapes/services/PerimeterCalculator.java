package lt.esdc.shapes.services;

import lt.esdc.shapes.entity.Shape;
import lt.esdc.shapes.exception.OvalProjectException;

public interface PerimeterCalculator<T extends Shape> {
    double calculatePerimeter(T shape) throws OvalProjectException ;
}
