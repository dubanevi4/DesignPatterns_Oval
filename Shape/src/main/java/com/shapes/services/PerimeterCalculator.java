package com.shapes.services;

import com.shapes.entity.Shape;
import com.shapes.exception.OvalProjectException;

public interface PerimeterCalculator {
    double calculatePerimeter(Shape shape) throws OvalProjectException ;
}
