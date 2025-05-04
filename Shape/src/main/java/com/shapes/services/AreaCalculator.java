package com.shapes.services;

import com.shapes.entity.Shape;
import com.shapes.exception.OvalProjectException;

public interface AreaCalculator {
    double calculateArea(Shape shape) throws OvalProjectException;
}
