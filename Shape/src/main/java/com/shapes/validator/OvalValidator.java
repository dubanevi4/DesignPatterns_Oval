package com.shapes.validator;

import com.shapes.entity.Oval;
import com.shapes.entity.Point;

public class OvalValidator implements Validator<Oval> {

    @Override
    public ValidationResult validate(Oval oval) {
        Point p1 = oval.getPoint1();
        Point p2 = oval.getPoint2();

        if (p1.getX() == p2.getX() || p1.getY() == p2.getY()) {
            return ValidationResult.invalid("Points must form a valid rectangle");
        }

        return ValidationResult.valid();
    }
}
