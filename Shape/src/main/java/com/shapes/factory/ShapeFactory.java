package com.shapes.factory;

import com.shapes.entity.Oval;
import com.shapes.entity.Point;
import com.shapes.entity.Shape;
import com.shapes.exception.OvalProjectException;
import com.shapes.validator.impl.OvalValidator;
import com.shapes.validator.impl.ValidationResult;
import com.shapes.validator.Validator;

public class ShapeFactory {
    public enum ShapeType {
        OVAL
    }

    private static final Validator<Oval> ovalValidator = new OvalValidator();

    public static Shape createShape(ShapeType type, Point point1, Point point2) throws OvalProjectException {
        switch (type) {
            case OVAL:
                Oval oval = new Oval(point1, point2);
                ValidationResult validation = ovalValidator.validate(oval);
                if (!validation.isValid()) {
                    throw new OvalProjectException(validation.getErrorMessage());
                }
                return oval;
            default:
                throw new OvalProjectException("Illegal shape type :" + type);
        }
    }
}