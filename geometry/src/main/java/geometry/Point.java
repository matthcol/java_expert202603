package geometry;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public sealed class Point extends Form permits WeightedPoint{
    private double x;
    private double y;

    public Point(String name, double x, double y) {
        super(name);
        this.x = x;
        this.y = y;
    }

    @Override
    public void translate(double deltaX, double deltaY) {
        x += deltaX;
        y += deltaY;
    }

    public double distance(Point other){
        return Math.hypot(
                this.x - other.x,
                this.y - other.y
        );
    }
}
