package geometry;

public sealed interface Form permits Point, Circle {
    String name();
}
