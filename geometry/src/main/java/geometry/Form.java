package geometry;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public abstract class Form {

    private String name;

    public abstract void translate(double deltaX, double deltaY);
}
