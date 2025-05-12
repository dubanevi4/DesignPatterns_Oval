package lt.esdc.shapes.specification.impl;

import lt.esdc.shapes.entity.Shape;
import lt.esdc.shapes.specification.Specification;

public class AreaSpecification extends Specification<Shape> {
    private final double minArea;

    public AreaSpecification(double minArea) {
        this.minArea = minArea;
    }

    @Override
    public boolean isSatisfied(Shape shape) {
        return shape.calculateArea() >= minArea;
    }
}