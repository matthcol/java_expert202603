package geometry;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public final class WeightedPoint extends Point{
    private double weight;

    public WeightedPoint(String name, double x, double y, double weight) {
        super(name, x, y);
        this.weight = weight;
    }
}
