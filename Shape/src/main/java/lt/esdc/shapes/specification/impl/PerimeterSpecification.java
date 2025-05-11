package lt.esdc.shapes.specification.impl;

import lt.esdc.shapes.entity.Shape;
import lt.esdc.shapes.specification.Specification;

public class PerimeterSpecification extends Specification<Shape> {
    private final double maxPerimeter;

    public PerimeterSpecification(double maxPerimeter) {
        this.maxPerimeter = maxPerimeter;
    }

    @Override
    public boolean isSatisfiedBy(Shape shape) {
        return shape.calculatePerimeter() <= maxPerimeter;
    }
}