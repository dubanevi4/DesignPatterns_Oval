package lt.esdc.shapes.specification.impl;

import lt.esdc.shapes.entity.Shape;
import lt.esdc.shapes.specification.Specification;

public class NotSpecification<T extends Shape> extends Specification<T> {
    private final Specification<T> spec;

    public NotSpecification(Specification<T> spec) {
        this.spec = spec;
    }

    @Override
    public boolean isSatisfiedBy(T item) {
        return !spec.isSatisfiedBy(item);
    }
}