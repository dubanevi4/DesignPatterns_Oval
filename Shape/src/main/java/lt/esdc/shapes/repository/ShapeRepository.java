package lt.esdc.shapes.repository;

import lt.esdc.shapes.entity.Shape;
import lt.esdc.shapes.exception.OvalProjectException;
import lt.esdc.shapes.observer.WarehouseUpdater;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShapeRepository {
    private static final Logger logger = LogManager.getLogger(ShapeRepository.class);
    private static final ShapeRepository INSTANCE = new ShapeRepository();
    private final Map<Long, Shape> storage = new HashMap<>();
    private final WarehouseUpdater observer = new WarehouseUpdater();

    private ShapeRepository() {}

    public static ShapeRepository getInstance() {
        return INSTANCE;
    }

    public void save(Shape shape) throws OvalProjectException {
        if (shape == null) {
            throw new OvalProjectException("Cannot save null shape");
        }
        shape.setObserver(observer);
        storage.put(shape.getID(), shape);
        logger.info("Saved shape with id: {}", shape.getID());
    }

    public Shape getById(long id) throws OvalProjectException {
        Shape shape = storage.get(id);
        if (shape == null) {
            throw new OvalProjectException("Shape with id " + id + " not found");
        }
        return shape;
    }

    /**
     * Returns all shapes stored in the repository
     * @return List of all shapes
     * @throws OvalProjectException if there's an error accessing the storage
     */
    public List<Shape> getAll() throws OvalProjectException {
        try {
            return new ArrayList<>(storage.values());
        } catch (Exception e) {
            throw new OvalProjectException("Error retrieving shapes from storage: " + e.getMessage());
        }
    }

    /**
     * Sort shapes by area
     * @return List of shapes sorted by area
     * @throws OvalProjectException if there's an error during sorting
     */
    public List<Shape> sortByArea() throws OvalProjectException {
        try {
            return storage.values().stream()
                    .sorted(Comparator.comparingDouble(Shape::calculateArea))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new OvalProjectException("Error sorting shapes by area: " + e.getMessage());
        }
    }

    /**
     * Sort shapes by perimeter
     * @return List of shapes sorted by perimeter
     * @throws OvalProjectException if there's an error during sorting
     */
    public List<Shape> sortByPerimeter() throws OvalProjectException {
        try {
            return storage.values().stream()
                    .sorted(Comparator.comparingDouble(Shape::calculatePerimeter))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new OvalProjectException("Error sorting shapes by perimeter: " + e.getMessage());
        }
    }

    /**
     * Sort shapes by ID
     * @return List of shapes sorted by ID
     * @throws OvalProjectException if there's an error during sorting
     */
    public List<Shape> sortById() throws OvalProjectException {
        try {
            return storage.values().stream()
                    .sorted(Comparator.comparingLong(Shape::getID))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new OvalProjectException("Error sorting shapes by ID: " + e.getMessage());
        }
    }
}