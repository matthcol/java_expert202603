package geometry;

public record Circle(
        String name,
        double radius,
        Point center
) implements Form, Mesurable2D {
    @Override
    public double perimeter() {
        return 0;
    }

    @Override
    public double surface() {
        return 0;
    }
}
