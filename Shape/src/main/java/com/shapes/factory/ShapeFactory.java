package com.shapes.factory;

import com.shapes.Oval;
import com.shapes.Point;
import com.shapes.Shape;
import com.shapes.validator.OvalValidator;
import com.shapes.validator.ValidationResult;
import com.shapes.validator.Validator;

public class ShapeFactory {
    public enum ShapeType {
        OVAL
    }

    private static final Validator<Oval> ovalValidator = new OvalValidator();

    public static Shape createShape(ShapeType type, Point point1, Point point2) {
        switch (type) {
            case OVAL:
                Oval oval = new Oval(point1, point2);
                ValidationResult validation = ovalValidator.validate(oval);
                if (!validation.isValid()) {
                    throw new IllegalArgumentException(validation.getErrorMessage());
                }
                return oval;
            default:
                throw new IllegalArgumentException("Unknown shape type: " + type);
        }
    }
}