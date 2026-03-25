package geometry;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Circle extends Form implements Mesurable2D{
    private double radius;
    private Point center;

    public Circle(String name, double radius, Point center) {
        super(name);
        this.radius = radius;
        this.center = center;
    }

    @Override
    public void translate(double deltaX, double deltaY) {
        center.translate(deltaX, deltaY);
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI;
    }

    @Override
    public double surface() {
        return Math.PI * radius * radius;
    }
}
