package lt.esdc.shapes.entity;

public class Oval extends Shape {
    private final Point point1;
    private final Point point2;

    public Oval(Point point1, Point point2){
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
    public String toString() {
        return String.format("Oval[(%.2f,%.2f)-(%.2f,%.2f)]",
                point1.getX(), point1.getY(), point2.getX(), point2.getY());
    }
}
