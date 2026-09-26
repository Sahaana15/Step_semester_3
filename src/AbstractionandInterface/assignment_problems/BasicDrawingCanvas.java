package AbstractionandInterface.assignment_problems;
abstract class Shape {

    private static int counter = 1000;
    private final String shapeId;

    public Shape() {
        counter++;
        shapeId = "SH-" + counter;
    }

    public abstract double calculateArea();

    public void scale(double factor) {
        System.out.println("Scaled by factor: " + factor);
    }

    public void scale(double xFactor, double yFactor) {
        scale(xFactor);
        System.out.println("Y factor: " + yFactor);
    }

    public String getShapeId() {
        return shapeId;
    }
}

class CircleShape extends Shape {

    private double radius;

    public CircleShape(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void scale(double factor) {
        radius = radius * factor;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        radius = radius * xFactor;
    }
}

class SquareShape extends Shape {

    private double side;

    public SquareShape(double side) {
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public void scale(double factor) {
        side = side * factor;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        side = side * xFactor;
    }
}

public class BasicDrawingCanvas {

    static void printArea(Shape s) {
        System.out.println("Area: " + s.calculateArea());
    }

    public static void main(String[] args) {

        CircleShape c = new CircleShape(5.0);
        System.out.println("Circle Area: " + c.calculateArea());

        SquareShape sq = new SquareShape(4.0);
        System.out.println("Square Area: " + sq.calculateArea());

        sq.scale(2.0);
        System.out.println("Scaled Square Area: " + sq.calculateArea());

        printArea(c);

        System.out.println("Circle ID: " + c.getShapeId());
        System.out.println("Square ID: " + sq.getShapeId());
    }
}
