package com.shapes.validator.impl;

import com.shapes.entity.Oval;
import com.shapes.entity.Point;
import com.shapes.util.OvalUtils;
import com.shapes.validator.Validator;

public class OvalValidator implements Validator<Oval> {

    @Override
    public ValidationResult validate(Oval oval) {
        Point p1 = oval.getPoint1();
        Point p2 = oval.getPoint2();

        // 1. Проверка совпадения точек
        if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
            return ValidationResult.invalid("Points must not be identical");
        }

        // 2. Проверка расположения точек
        if (p1.getX() == p2.getX() || p1.getY() == p2.getY()) {
            return ValidationResult.invalid("Points must not be aligned vertically or horizontally");
        }

        double a = OvalUtils.getSemiMajorAxis(p1, p2);
        double b = OvalUtils.getSemiMajorAxis(p1, p2);


        // 3. Проверка допустимых значений координат
        if (a <= 0 || b <= 0) {
            return ValidationResult.invalid("Semi-axes must be positive values");
        }

        return ValidationResult.valid();
    }
}
