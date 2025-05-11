package lt.esdc.shapes.observer;

import lt.esdc.shapes.entity.Shape;
import lt.esdc.shapes.storage.Warehouse;

public class WarehouseUpdater implements ShapeObserver {
    private final Warehouse warehouse = Warehouse.getInstance();

    @Override
    public void onShapeChanged(long id, Shape shape) {
        warehouse.update(id, shape.calculateArea(), shape.calculatePerimeter());
    }
}