package lt.esdc.shapes.specification;

import java.util.Objects;

import lt.esdc.shapes.entity.Shape;
import lt.esdc.shapes.specification.impl.AndSpecification;
import lt.esdc.shapes.specification.impl.NotSpecification;
import lt.esdc.shapes.specification.impl.OrSpecification;

/**
 * Specification pattern implementation for Shape objects.
 * This class provides a way to create complex business rules by combining simple specifications.
 * 
 * @param <T> Type of Shape this specification can evaluate
 */
public abstract class Specification<T extends Shape> {
    /**
     * Evaluates if the given item satisfies this specification.
     *
     * @param item The shape to evaluate
     * @return true if the shape satisfies the specification, false otherwise
     */
    public abstract boolean isSatisfied(T item);

    /**
     * Combines this specification with another using logical AND.
     *
     * @param other The specification to combine with
     * @return A new specification that is satisfied only if both specifications are satisfied
     */
    public Specification<T> and(Specification<T> other) {
        return new AndSpecification<>(this, other);
    }

    /**
     * Combines this specification with another using logical OR.
     *
     * @param other The specification to combine with
     * @return A new specification that is satisfied if either specification is satisfied
     */
    public Specification<T> or(Specification<T> other) {
        return new OrSpecification<>(this, other);
    }

    /**
     * Creates the logical NOT of this specification.
     *
     * @return A new specification that is satisfied when this specification is not satisfied
     */
    public Specification<T> not() {
        return new NotSpecification<>(this);
    }
}