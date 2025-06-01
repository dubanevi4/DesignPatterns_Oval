package lt.esdc.shapes.specification.util;

import lt.esdc.shapes.entity.Shape;
import java.util.Comparator;

/**
 * Utility class providing common comparators for Shape objects.
 */
public final class ShapeComparators {
    private ShapeComparators() {
        // Prevent instantiation
    }

    /**
     * Comparator for comparing shapes by area
     */
    public static final Comparator<Shape> BY_AREA = 
        Comparator.comparingDouble(Shape::calculateArea);

    /**
     * Comparator for comparing shapes by perimeter
     */
    public static final Comparator<Shape> BY_PERIMETER = 
        Comparator.comparingDouble(Shape::calculatePerimeter);

    /**
     * Comparator for comparing shapes by ID
     */
    public static final Comparator<Shape> BY_ID = 
        Comparator.comparingLong(Shape::getID);

    /**
     * Creates a comparator that compares shapes by area within a tolerance
     * @param tolerance the maximum difference to consider areas equal
     * @return a comparator that compares shapes by area with tolerance
     */
    public static Comparator<Shape> byAreaWithTolerance(double tolerance) {
        return (s1, s2) -> {
            double diff = s1.calculateArea() - s2.calculateArea();
            if (Math.abs(diff) < tolerance) return 0;
            return Double.compare(s1.calculateArea(), s2.calculateArea());
        };
    }

    /**
     * Creates a comparator that compares shapes by perimeter within a tolerance
     * @param tolerance the maximum difference to consider perimeters equal
     * @return a comparator that compares shapes by perimeter with tolerance
     */
    public static Comparator<Shape> byPerimeterWithTolerance(double tolerance) {
        return (s1, s2) -> {
            double diff = s1.calculatePerimeter() - s2.calculatePerimeter();
            if (Math.abs(diff) < tolerance) return 0;
            return Double.compare(s1.calculatePerimeter(), s2.calculatePerimeter());
        };
    }

    /**
     * Returns a reversed version of the given shape comparator
     * @param comparator the comparator to reverse
     * @return the reversed comparator
     */
    public static Comparator<Shape> reversed(Comparator<Shape> comparator) {
        return comparator.reversed();
    }

    /**
     * Creates a composite comparator that uses multiple comparators in sequence
     * @param comparators the comparators to use in sequence
     * @return a composite comparator
     */
    public static Comparator<Shape> composite(Comparator<Shape>... comparators) {
        Comparator<Shape> result = comparators[0];
        for (int i = 1; i < comparators.length; i++) {
            result = result.thenComparing(comparators[i]);
        }
        return result;
    }
} 