package lt.esdc.shapes.repository;

import lt.esdc.shapes.entity.Shape;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

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

    public void clear() {
        storage.clear();
        logger.info("Repository cleared");
    }
}