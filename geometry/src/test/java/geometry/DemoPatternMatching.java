package geometry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DemoPatternMatching {

    List<Form> forms;

    @BeforeEach
    void initData(){
        var pointA = new Point("A", 1.0, 6.0);
        var pointB = new Point("B", 3.0, 1.0);
        var pointC = new Point("C", 7.0, 2.0);
        var pointD = new Point("D", 4.0, 4.0);
        var pointE = new Point("E", 8.0, 5.0);
        var wPointF = new WeightedPoint("F", 4.5, 12.75, 2.5);
        var wPointG = new WeightedPoint("G", 7.5, 8.75, 5.0);
        var wPointH = new WeightedPoint("H", 4.5, 8.75, 7.5);
        var circleC1 = new Circle("C1", 5.0, pointA);
        var circleC2 = new Circle("C2", 10.0, pointA);
        var circleC3 = new Circle("C3", 5.0, pointB);
        var circleC4 = new Circle("C3", 10.0, pointB);
        var triangleABC = new Polygon("ABC", pointA, pointB, pointC);
        var pentagon = new Polygon("ABCDE", pointA, pointB, pointC, pointD, pointE);
        var triangleFGH = new Polygon("FGH", wPointF, wPointG, wPointH);
        forms = new ArrayList<>();
        Collections.addAll(forms,
                pointA, pointB, pointC, pointD, pointE,
                wPointF, wPointG, wPointH,
                circleC1, circleC2, circleC3, circleC4,
                triangleABC, triangleFGH, pentagon
        );
        Collections.shuffle(forms);
    }

    @Test
    void iterateForms(){
        forms.forEach(System.out::println);
    }
}
