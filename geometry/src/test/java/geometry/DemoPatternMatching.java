package geometry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DoublePair;
import util.StreamUtil;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    @Test
    void demoInstanceOfJava1_11(){
        for (Form f: this.forms){
            if (f instanceof Point){ // Point and subclasses
                Point pt = (Point) f;
                System.out.println(MessageFormat.format(
                        " - {0} : x = {1}, y = {2}",
                        f.getName(),
                        pt.getX(),
                        pt.getY()
                ));
            }
        }
    }

    @Test
    void demoTestGetClass(){
        for (Form f: this.forms){
            if (f.getClass() == Point.class){ // Point and subclasses
                Point pt = (Point) f;
                System.out.println(MessageFormat.format(
                        " - {0} : x = {1}, y = {2}",
                        f.getName(),
                        pt.getX(),
                        pt.getY()
                ));
            }
        }
    }

    @Test
    void demoInstanceOfJava17(){ // pattern matching
        for (Form f: this.forms){
            if (f instanceof Point pt){ // Point and subclasses
                System.out.println(MessageFormat.format(
                        " - {0} : x = {1}, y = {2}",
                        f.getName(),
                        pt.getX(),
                        pt.getY()
                ));
            }
        }
    }

    // calculer le X moyen de tous les points
    @Test
    void demoAverageX(){
        int nbPoint = 0;
        double averageX = 0.0;
        for (var f: forms){
            if (f instanceof Point pt){
                nbPoint++;
                averageX += pt.getX();
            }
        }
        averageX = nbPoint > 0 ? averageX / nbPoint : Double.NaN;
        System.out.println("Average X:" + averageX);
    }

    @Test
    void demoAverageXStream(){
        var optionalAverageX = forms.stream()
                .filter(f -> f instanceof  Point)
                .map(f -> (Point) f)
                .mapToDouble(Point::getX)
//                .filter(x -> x > 10_000.0)
                .average();
        optionalAverageX.ifPresentOrElse(
                avgX -> System.out.println("Average X: " + avgX),
                () -> System.out.println("No points to compute average X")
        );
    }

    @Test
    void demoReduce(){
        var result = forms.stream()
                .filter(f -> f instanceof  Point)
                .map(f -> (Point) f)
                .mapToDouble(Point::getX)
                .reduce(0.0, (a, x) -> a + x);
        System.out.println(result);
    }

    @Test
    void demoCollect(){
        var result = forms.parallelStream()
                .filter(f -> f instanceof  Point)
                .map(f -> (Point) f)
                .mapToDouble(Point::getX)
                .collect(
                        () -> DoublePair.of(0.0, 0.0), // initialisation
                        (pair, x) -> { // accumulation
                            pair.setFirst(pair.getFirst() + 1); // count
                            pair.setSecond(pair.getSecond() + x); // sum
                        },
                        (pair1, pair2) -> { // combiner (if //)
                            pair1.setFirst(pair1.getFirst() + pair2.getFirst());
                            pair1.setSecond(pair1.getSecond() + pair2.getSecond());
                        }
                );
        double averageX = result.getFirst() > 0.0
                ? result.getSecond() / result.getFirst()
                : Double.NaN;
        System.out.println(result);
        System.out.println(averageX);
    }

    // calculer la surface totale de toutes les formes mesurables 2D
    @Test
    void demoTotalSurface(){
        var totalSurface = forms.stream()
                .filter(f -> f instanceof Mesurable2D)
                .map(f -> (Mesurable2D) f)
                .mapToDouble(Mesurable2D::surface)
                .sum();
        System.out.println("Surface: " + totalSurface);
    }

    @Test
    void demoTotalSurface2(){
        var streamMesurable2D = StreamUtil.filterByType(forms.stream(), Mesurable2D.class);
        var totalSurface = streamMesurable2D.mapToDouble(Mesurable2D::surface)
                .sum();
        System.out.println("Surface: " + totalSurface);
    }

    @Test
    void demoSwitchCasePatternMatchingJava21(){
        for (var form: forms){
            System.out.print(" * " + form.getName() + " : ");
            switch (form){
                case Point point -> // Point + WeightedPoint
                        System.out.println("Point : x = " + point.getX());
                case Circle circle -> System.out.println("Circle: r = " +circle.getRadius());
                default -> System.out.println("unknown");
            }
        }
    }

    @Test
    void demoSwitchCasePatternMatchingJava21b(){
        for (var form: forms){
            System.out.print(" * " + form.getName() + " : ");
            switch (form){
                case WeightedPoint wp -> // this case before Point !!
                        System.out.println("WeightedPoint : weight = " + wp.getWeight());
                case Point point -> // Point only
                        System.out.println("Point : x = " + point.getX());
                case Circle circle -> System.out.println("Circle: r = " +circle.getRadius());
                default -> System.out.println("unknown");
            }
        }
    }

    @Test
    void demoSwitchCasePatternMatchingJava21c() {
        for (var form : forms) {
            System.out.print(" * " + form.getName() + " : ");
            switch (form) {
                case WeightedPoint wp -> // this case before Point !!
                        System.out.println("WeightedPoint : weight = " + wp.getWeight());
                case Point point when point.getX() >= 4 -> // Point with x >= 4
                        System.out.println("Point big x: x = " + point.getX());
                case Point point -> // other points
                        System.out.println("Point small x: x = " + point.getX());
                case Circle _, Polygon _ -> System.out.println("Circle | Polygon");
                    // Java 25 : variable _
                case null, default -> System.out.println("unknown");
            }
        }
    }

    @Test
    void demoSwitchCasePatternMatchingJava21value(){
        for (var form: forms){
            System.out.print(" * " + form.getName() + " : ");
            double indicator = switch (form){
                case WeightedPoint wp ->  wp.getWeight();
                case Point point when point.getX() >= 4 -> point.getX();
                case Point point -> point.getY();
                case Circle circle -> circle.getRadius();
                default -> Double.NaN;
            };
            System.out.println(indicator);
        }

        // sealed class, records

    }

    @Test
    void demoMapCategory(){
        EnumMap<PolygonCategory, List<Polygon>> result =
                StreamUtil.filterByType(forms.stream(), Polygon.class)
                .collect(Collectors.groupingBy(
                        Polygon::getPolygonCategory,
                        () -> new EnumMap<>(PolygonCategory.class),
                        Collectors.toList()
                ));

        for (var entry: result.entrySet()){
            System.out.println("* Categorie : " + entry.getKey());
            for (var polygon: entry.getValue()){
                System.out.println("\t - " + polygon);
                System.out.println("\t\t NB: cat = " + polygon.getPolygonCategory());

            }
        }
        System.out.println();

        result.forEach(
                (category, polygons) -> {
                    System.out.println("* Categorie : " + category);
                    polygons.forEach(polygon -> {
                        System.out.println("\t - " + polygon);
                        System.out.println("\t\t NB: cat = " + polygon.getPolygonCategory());
                    });
                }
        );
    }


    @Test
    void demoParallStream(){
        Collector<String, ?, TreeSet<String>> collector = Collectors.toCollection(TreeSet::new);
        Collector<Mesurable2D, ?, DoubleSummaryStatistics> collector2 =
                Collectors.summarizingDouble(Mesurable2D::surface);

        var names = forms.parallelStream()
                .map(Form::getName)
                .collect(collector);

        System.out.println(names);
        System.out.println(collector.characteristics()); // IDENTITY_FINISH
        System.out.println(collector2.characteristics()); // IDENTITY_FINISH
    }



}
