package geometry;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@ToString
@NoArgsConstructor
@Getter
@Setter
public final class Polygon extends Form implements Mesurable2D{
    private List<Point> vertices = new ArrayList<>();

    public Polygon(String name, Collection<? extends Point> vertices) {
        super(name);
        this.vertices.addAll(vertices);
    }

    public Polygon(String name, Point... vertices){
        this(name, Arrays.asList(vertices));
    }

    @Override
    public void translate(double deltaX, double deltaY) {

    }

    @Override
    public double perimeter() {
        return 0;
    }

    @Override
    public double surface() {
        return 0;
    }
}
