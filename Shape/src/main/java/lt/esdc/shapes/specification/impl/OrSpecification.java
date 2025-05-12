package lt.esdc.shapes.specification.impl;

import lt.esdc.shapes.entity.Shape;
import lt.esdc.shapes.specification.Specification;

public class OrSpecification<T extends Shape> extends Specification<T> {
    private final Specification<T> spec1;
    private final Specification<T> spec2;

    public OrSpecification(Specification<T> spec1, Specification<T> spec2) {
        this.spec1 = spec1;
        this.spec2 = spec2;
    }

    @Override
    public boolean isSatisfied(T item) {
        return spec1.isSatisfied(item) || spec2.isSatisfied(item);
    }
}