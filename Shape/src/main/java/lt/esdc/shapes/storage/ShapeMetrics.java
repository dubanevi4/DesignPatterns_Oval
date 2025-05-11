package lt.esdc.shapes.storage;

public record ShapeMetrics(double area, double perimeter) {
    @Override
    public String toString() {
        return String.format("ShapeMetrics[Area: %.2f, Perimeter: %.2f]", area, perimeter);
    }
}