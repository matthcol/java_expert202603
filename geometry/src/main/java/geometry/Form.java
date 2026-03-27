package geometry;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public abstract sealed class Form permits Point, Circle, Polygon, Segment{

    private String name;

    public abstract void translate(double deltaX, double deltaY);
}
