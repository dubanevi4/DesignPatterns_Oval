package lt.esdc.shapes.repository;

import lt.esdc.shapes.entity.Shape;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ShapeRepository {
    private static final Logger logger = LogManager.getLogger(ShapeRepository.class);
    private static final ShapeRepository INSTANCE = new ShapeRepository();
    private final ConcurrentHashMap<Long, Shape> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    private ShapeRepository() {}

    public static ShapeRepository getInstance() {
        return INSTANCE;
    }

    public long save(Shape shape) {
        long id = idGenerator.getAndIncrement();
        storage.put(id, shape);
        logger.info("Saved shape with id: {}", id);
        return id;
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
