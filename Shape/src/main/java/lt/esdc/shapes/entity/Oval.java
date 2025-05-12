package lt.esdc.shapes.entity;

import lt.esdc.shapes.service.impl.OvalAreaCalculator;
import lt.esdc.shapes.service.impl.OvalPerimeterCalculator;

public class Oval extends Shape {
    private Point point1;
    private Point point2;

    public Oval(Point point1, Point point2){
        super(-1); //Set ID to -1 before validation
        if (point1 == null || point2 == null) {
            throw new IllegalArgumentException("Points cannot be null");
        }
        this.point1 = point1;
        this.point2 = point2;
    }

    public Point getPoint1(){
        return point1;
    }

    public Point getPoint2(){
        return point2;
    }

    @Override
    public double calculateArea() {
        return new OvalAreaCalculator().calculateArea(this);
    }

    @Override
    public double calculatePerimeter() {
        return new OvalPerimeterCalculator().calculatePerimeter(this);
    }

    @Override
    public String toString() {
        return String.format("Oval[%d: (%.2f,%.2f)-(%.2f,%.2f)]",
                getID(), point1.getX(), point1.getY(), point2.getX(), point2.getY());
    }
}
