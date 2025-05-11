package lt.esdc.shapes.entity;

import lt.esdc.shapes.observer.ShapeObserver;

//basic class for all possible shapes

public abstract class Shape {
    private long id;
    private ShapeObserver observer;

    public Shape(long id) {
        this.id = id;
    }

    public long getID() {
        return this.id;
    }

    public void setID(long id) {
        this.id = id;
    }

    public void setObserver(ShapeObserver observer) {
        this.observer = observer;
    }

    protected void notifyObserver() {
        if (observer != null) observer.onShapeChanged(this.id, this);
    }

    public abstract double calculateArea();
    public abstract double calculatePerimeter();
}
