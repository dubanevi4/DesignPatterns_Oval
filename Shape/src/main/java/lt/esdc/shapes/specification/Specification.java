package lt.esdc.shapes.specification;

import java.util.Objects;

import lt.esdc.shapes.entity.Shape;
import lt.esdc.shapes.specification.impl.AndSpecification;
import lt.esdc.shapes.specification.impl.NotSpecification;
import lt.esdc.shapes.specification.impl.OrSpecification;

public abstract class Specification<T extends Shape> {
    public abstract boolean isSatisfiedBy(T item);

    public Specification<T> and(Specification<T> other) {
        return new AndSpecification<>(this, other);
    }

    public Specification<T> or(Specification<T> other) {
        return new OrSpecification<>(this, other);
    }

    public Specification<T> not() {
        return new NotSpecification<>(this);
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof Specification<?> that && this.toString().equals(that.toString()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.toString());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}