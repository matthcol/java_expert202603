package geometry;

public sealed interface Mesurable2D permits Circle, Polygon {
    double perimeter();
    double surface();
}
