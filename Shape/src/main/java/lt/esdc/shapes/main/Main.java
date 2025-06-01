package lt.esdc.shapes.main;

import lt.esdc.shapes.entity.Shape;
import lt.esdc.shapes.exception.OvalProjectException;
import lt.esdc.shapes.reader.InputDataReader;
import lt.esdc.shapes.repository.ShapeRepository;
import lt.esdc.shapes.specification.impl.ComparatorSpecification;
import lt.esdc.shapes.specification.impl.ComparatorSpecification.ComparisonType;
import lt.esdc.shapes.specification.util.ShapeComparators;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            processShapes();
        } catch (OvalProjectException e) {
            logger.error("Project error: {}", e.getMessage());
        }
    }

    private static void processShapes() throws OvalProjectException {
        // Load shapes from file
        try {
            InputDataReader.readAndStoreShapes("shapes.txt");
        } catch (IOException e) {
            throw new OvalProjectException(e.getMessage());
        }
        logger.info("Shapes loaded successfully");

        ShapeRepository repository = ShapeRepository.getInstance();
        List<Shape> allShapes = repository.getAll();

        if (allShapes.isEmpty()) {
            throw new OvalProjectException("No shapes found in repository");
        }

        // Example 1: Sort shapes by area using repository method
        logger.info("Shapes sorted by area:");
        repository.sortByArea()
                .forEach(shape -> logger.info("Shape ID: {}, Area: {}", 
                        shape.getID(), 
                        String.format("%.2f", shape.calculateArea())));

        // Example 2: Find shapes with area greater than 50
        logger.info("\nShapes with area greater than 50:");
        var largeAreaSpec = ComparatorSpecification.forArea(50.0, ComparisonType.GREATER_THAN);
        allShapes.stream()
                .filter(largeAreaSpec::isSatisfied)
                .forEach(shape -> logger.info("Shape ID: {}, Area: {}", 
                        shape.getID(), 
                        String.format("%.2f", shape.calculateArea())));

        // Example 3: Find shapes with perimeter between 20 and 30
        logger.info("\nShapes with perimeter between 20 and 30:");
        var minPerimeterSpec = ComparatorSpecification.forPerimeter(20.0, ComparisonType.GREATER_THAN_OR_EQUAL);
        var maxPerimeterSpec = ComparatorSpecification.forPerimeter(30.0, ComparisonType.LESS_THAN_OR_EQUAL);
        var perimeterRangeSpec = minPerimeterSpec.and(maxPerimeterSpec);

        allShapes.stream()
                .filter(perimeterRangeSpec::isSatisfied)
                .forEach(shape -> logger.info("Shape ID: {}, Perimeter: {}", 
                        shape.getID(), 
                        String.format("%.2f", shape.calculatePerimeter())));

        // Example 4: Sort by perimeter (using repository method) and filter by area > 40
        logger.info("\nLarge shapes (area > 40) sorted by perimeter:");
        var largeShapeSpec = ComparatorSpecification.forArea(40.0, ComparisonType.GREATER_THAN);
        
        repository.sortByPerimeter().stream()
                .filter(largeShapeSpec::isSatisfied)
                .forEach(shape -> logger.info("Shape ID: {}, Area: {}, Perimeter: {}", 
                        shape.getID(), 
                        String.format("%.2f", shape.calculateArea()),
                        String.format("%.2f", shape.calculatePerimeter())));

        // Example 5: Sort by ID and show all properties
        logger.info("\nShapes sorted by ID:");
        repository.sortById()
                .forEach(shape -> logger.info("Shape ID: {}, Area: {}, Perimeter: {}", 
                        shape.getID(), 
                        String.format("%.2f", shape.calculateArea()),
                        String.format("%.2f", shape.calculatePerimeter())));

        // Example 6: Find similar shapes using tolerance
        logger.info("\nFinding shapes with similar areas (tolerance: 0.1):");
        Shape referenceShape = allShapes.get(0);
        allShapes.stream()
                .filter(shape -> shape != referenceShape)
                .sorted(ShapeComparators.byAreaWithTolerance(0.1))
                .forEach(shape -> logger.info("Shape ID: {}, Area difference: {}", 
                        shape.getID(), 
                        String.format("%.2f", Math.abs(shape.calculateArea() - referenceShape.calculateArea()))));
    }
}