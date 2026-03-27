package geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
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

    @Test
    void demoPolygonPoint() {
        Point ptA = new Point("A", 1.0, 2.0);
        Point ptB = new Point("B", 3.0, 4.0);
        Point ptC = new Point("C", 5.0, 6.0);
        Deque<Point> pointQueue = new ArrayDeque<>();
        Collections.addAll(pointQueue, ptB, ptC, ptA);
        Polygon triangleABC = new Polygon("ABC", ptA, ptB, ptC);
        Polygon triangleBCA = new Polygon("BCA", pointQueue);

        pointQueue.addAll(List.of(new WeightedPoint(), new WeightedPoint()));
        // pointQueue.addAll(List.of(new Point(), new Circle(), new Polygon())); // error
        List<Form> forms = new ArrayList<>();
        Collections.addAll(forms, new Point(), new WeightedPoint());

        Comparator<Point> comparatorPoint = Comparator.comparing(Point::getX);
        Comparator<WeightedPoint> comparatorWeightePoint = Comparator.comparing(WeightedPoint::getWeight);
        Comparator<Form> comparatorForm = Comparator.comparing(Form::getName);
        Comparator<Object> comparatorObject = Comparator.comparing(Object::hashCode);

        List<Point> pointList = new ArrayList<>(pointQueue);
        Collections.sort(pointList, comparatorPoint); // OK : =
        System.out.println(pointList);
        // Collections.sort(pointList, comparatorWeightePoint); // KO: extends
        Collections.sort(pointList, comparatorForm); // OK : super
        System.out.println(pointList);
        Collections.sort(pointList, comparatorObject); // OK : super
        System.out.println(pointList);

        pointList.stream()
                .map(Form::getName)
                .forEach(System.out::println);
    }

    @Test
    void demoPolygonWeightedPoint() {
        WeightedPoint ptA = new WeightedPoint("A", 1.0, 2.0, 1.0);
        WeightedPoint ptB = new WeightedPoint("B", 3.0, 4.0, 2.0);
        WeightedPoint ptC = new WeightedPoint("C", 5.0, 6.0, 3.0);
        Deque<WeightedPoint> pointQueue = new ArrayDeque<>();
        Collections.addAll(pointQueue, ptB, ptC, ptA);
        Polygon triangleABC = new Polygon("ABC", ptA, ptB, ptC);
        Polygon triangleBCA = new Polygon("BCA", pointQueue);
    }

    @Test
    void demoIsInstanceInterface(){
        Point ptA = new Point("A", 1.0, 2.0);
        Circle c = new Circle("C", 12.5, ptA);
//        Assertions.assertFalse(ptA instanceof Mesurable2D); // Warning : always False or Error if sealed class
//        Assertions.assertFalse(ptA instanceof Serializable); // Warning : always False or Error if sealed class
        List<Form> forms = List.of(ptA, c);
        forms.stream()
                .filter(f -> f instanceof  Mesurable2D)
                .forEach(System.out::println);
    }
}
