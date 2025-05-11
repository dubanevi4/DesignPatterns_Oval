package lt.esdc.shapes.repository;

import lt.esdc.shapes.entity.Shape;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShapeRepository {
    private static final Logger logger = LogManager.getLogger(ShapeRepository.class);
    private static final ShapeRepository INSTANCE = new ShapeRepository();
    private final Map<Long, Shape> storage = new HashMap<>();

    private ShapeRepository() {}

    public static ShapeRepository getInstance() {
        return INSTANCE;
    }

    public void save(Shape shape) {
        storage.put(shape.getID(), shape);
        logger.info("Saved shape with id: {}", shape.getID());
    }

    public Shape getById(long id) {
        Shape shape = storage.get(id);
        if (shape == null) {
            logger.warn("Shape with id {} not found", id);
        }
        return shape;
    }

    // Sort by Area
    public List<Shape> sortByArea() {
        return storage.values().stream()
                .sorted(Comparator.comparingDouble(Shape::calculateArea))
                .collect(Collectors.toList());
    }

    // Sort by Perimeter
    public List<Shape> sortByPerimeter() {
        return storage.values().stream()
                .sorted(Comparator.comparingDouble(Shape::calculatePerimeter))
                .collect(Collectors.toList());
    }

    // Sort by ID
    public List<Shape> sortById() {
        return storage.values().stream()
                .sorted(Comparator.comparingLong(Shape::getID))
                .collect(Collectors.toList());
    }
}