package lt.esdc.shapes.storage;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Warehouse {
    private static final Logger logger = LogManager.getLogger(Warehouse.class);
    private static final Warehouse INSTANCE = new Warehouse();
    private final Map<Long, ShapeMetrics> storage = new HashMap<>();

    private Warehouse() {}

    public static Warehouse getInstance() {
        return INSTANCE;
    }

    public void update(long id, double area, double perimeter) {
        storage.put(id, new ShapeMetrics(area, perimeter));
        logger.info("Updated shape ID {}: Area = {}, Perimeter = {}", id, area, perimeter);
    }

    public ShapeMetrics getMetrics(long id) {
        return storage.get(id);
    }
}
