package geometry;

// Record
// * pas d'héritage (final
// * non mutable
public record Point(
        String name,
        double x,
        double y
) implements Form {

    public double distance(Point other){
        return Math.hypot(
                this.x - other.x,
                this.y - other.y
        );
    }

    public static Point translate(Point point, double deltaX, double deltaY){
        return new Point(
                point.name,
                point.x + deltaX,
                point.y + deltaY
        );
    }
}
