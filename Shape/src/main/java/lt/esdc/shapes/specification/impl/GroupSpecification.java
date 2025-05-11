package lt.esdc.shapes.specification.impl;

import lt.esdc.shapes.entity.Shape;
import lt.esdc.shapes.specification.Specification;

public class GroupSpecification extends Specification<Shape> {
    private final Class<? extends Shape> shapeType;

    public GroupSpecification(Class<? extends Shape> shapeType) {
        this.shapeType = shapeType;
    }

    @Override
    public boolean isSatisfiedBy(Shape shape) {
        return shape.getClass().equals(shapeType);
    }
}