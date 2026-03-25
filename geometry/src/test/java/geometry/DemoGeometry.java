package geometry;

import org.junit.jupiter.api.Test;

import java.util.*;

public class DemoGeometry {

    @Test
    void demoGeo(){
        List<Form> forms = new ArrayList<>();
        // Form f = new Form();  // forbidden
        var pointA = new Point("A", 12.25, 13.5);
        var wpointB = new WeightedPoint("B", 15.25, 17.5, 10.0);
        var pointD = new Point("D", 20.5, 30.75);
        var circleC = new Circle("C", 5.0, pointA);
        var triangleABD = new Polygon("ABD", List.of(pointA, wpointB, pointD));
        var triangleBDA = new Polygon("BDA", wpointB, pointD, pointA);
        Deque<Point> pointQueue = new ArrayDeque<>();
        Collections.addAll(pointQueue, pointD, pointA, wpointB);
        var triangleDAB = new Polygon("DAB", pointQueue);

        Collections.addAll(forms, pointA, wpointB, circleC, triangleABD, triangleBDA, triangleDAB);
        System.out.println(forms);
        forms.forEach(f -> {
            System.out.println("* " + f.getName());
            System.out.println("\t - full description: " + f); // call .toString()
        });

        double d1 = pointA.distance(wpointB);
        double d2 = wpointB.distance(pointA);
        System.out.println("distances:" +  d1 + " / " + d2);

        forms.forEach(f -> f.translate(-1.0, 1.0));
        forms.forEach(System.out::println);
    }
}
