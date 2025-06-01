package lt.esdc.shapes.specification.impl;

import lt.esdc.shapes.entity.Shape;
import lt.esdc.shapes.specification.Specification;

import java.util.Comparator;
import java.util.function.Function;

/**
 * A specification that uses comparators to compare shape properties.
 * This allows for flexible comparison of any comparable property of shapes.
 *
 * @param <T> The type of shape to compare
 * @param <V> The type of value to compare (Double for area/perimeter, Long for ID, etc.)
 */
public class ComparatorSpecification<T extends Shape, V extends Comparable<V>> extends Specification<T> {
    private final Function<T, V> valueExtractor;
    private final V compareValue;
    private final ComparisonType comparisonType;

    public enum ComparisonType {
        LESS_THAN,
        LESS_THAN_OR_EQUAL,
        EQUAL,
        GREATER_THAN,
        GREATER_THAN_OR_EQUAL,
        NOT_EQUAL
    }

    public ComparatorSpecification(Function<T, V> valueExtractor, V compareValue, ComparisonType comparisonType) {
        this.valueExtractor = valueExtractor;
        this.compareValue = compareValue;
        this.comparisonType = comparisonType;
    }

    @Override
    public boolean isSatisfied(T item) {
        V itemValue = valueExtractor.apply(item);
        if (itemValue == null || compareValue == null) {
            return false;
        }

        int comparison = itemValue.compareTo(compareValue);
        
        return switch (comparisonType) {
            case LESS_THAN -> comparison < 0;
            case LESS_THAN_OR_EQUAL -> comparison <= 0;
            case EQUAL -> comparison == 0;
            case GREATER_THAN -> comparison > 0;
            case GREATER_THAN_OR_EQUAL -> comparison >= 0;
            case NOT_EQUAL -> comparison != 0;
        };
    }

    // Factory methods for common shape comparisons
    public static ComparatorSpecification<Shape, Double> forArea(double value, ComparisonType type) {
        return new ComparatorSpecification<>(Shape::calculateArea, value, type);
    }

    public static ComparatorSpecification<Shape, Double> forPerimeter(double value, ComparisonType type) {
        return new ComparatorSpecification<>(Shape::calculatePerimeter, value, type);
    }

    public static ComparatorSpecification<Shape, Long> forId(long value, ComparisonType type) {
        return new ComparatorSpecification<>(Shape::getID, value, type);
    }
} 