package lt.esdc.shapes.specification.impl;

import lt.esdc.shapes.entity.Shape;
import lt.esdc.shapes.specification.Specification;
import java.util.Comparator;

/**
 * A specification that uses a Comparator to compare shapes with a reference shape.
 */
public class ComparatorBasedSpecification extends Specification<Shape> {
    private final Shape referenceShape;
    private final Comparator<Shape> comparator;
    private final ComparisonType comparisonType;

    public enum ComparisonType {
        LESS_THAN,
        LESS_THAN_OR_EQUAL,
        EQUAL,
        GREATER_THAN,
        GREATER_THAN_OR_EQUAL,
        NOT_EQUAL
    }

    public ComparatorBasedSpecification(Shape referenceShape, Comparator<Shape> comparator, ComparisonType comparisonType) {
        this.referenceShape = referenceShape;
        this.comparator = comparator;
        this.comparisonType = comparisonType;
    }

    @Override
    public boolean isSatisfied(Shape item) {
        int comparison = comparator.compare(item, referenceShape);
        
        return switch (comparisonType) {
            case LESS_THAN -> comparison < 0;
            case LESS_THAN_OR_EQUAL -> comparison <= 0;
            case EQUAL -> comparison == 0;
            case GREATER_THAN -> comparison > 0;
            case GREATER_THAN_OR_EQUAL -> comparison >= 0;
            case NOT_EQUAL -> comparison != 0;
        };
    }
} 