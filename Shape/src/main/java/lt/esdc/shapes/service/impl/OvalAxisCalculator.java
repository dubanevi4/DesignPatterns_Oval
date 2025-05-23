package lt.esdc.shapes.service.impl;

import lt.esdc.shapes.entity.Point;

public class OvalAxisCalculator {

    public static double getSemiMajorAxis(Point point1, Point point2) {
        return Math.abs(point2.getX() - point1.getX()) / 2.0;
    }

    public static double getSemiMinorAxis(Point point1, Point point2) {
        return Math.abs(point2.getY() - point1.getY()) / 2.0;
    }
}
