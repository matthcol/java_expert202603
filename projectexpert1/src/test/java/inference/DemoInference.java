package inference;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

public class DemoInference {

    @Test
    void demoInferenceGeneric(){
        // Java 7 : notation diamond
        List<String> cities = new ArrayList<>();
        Collections.addAll(cities, "Montauban", "Toulouse", "Pau");
        Collections.<String>addAll(cities, "Paris", "Marseille", "Lyon"); // explicit typing
        Map<String,List<Map<String, String>>> settings = new TreeMap<>();
        settings.put("users", new ArrayList<>());
        // Java 8 : inference with streams + lambda
        cities.forEach(city -> System.out.println(city.substring(0, 3)));
        // Java 11 : inference keyword 'var'
        for (var city: cities){
            System.out.println(city.toUpperCase());
        }
        for (var entry: settings.entrySet()){
            System.out.println(entry);
        }

        var aDate = LocalDate.of(2028, 2, 29);
        // Advice: do not use var with primitive
        short xs = 12;
        var xi = 12; // int
        var xl = 12L; // long
        var xf = 12F; // float
        var xd1 = 12D; // double
        var xdd2 = 12.0; // double
        var xdd3 = 12E-23; // double
    }

    @Test
    void demoInferenceGeneric2(){
        var cities = new ArrayList<String>();
        Collections.addAll(cities, "Montauban", "Toulouse", "Pau");
        var settings = new TreeMap<String,List<Map<String, String>>>();
        settings.put("users", new ArrayList<>());

        var city0 = cities.get(1).toUpperCase();

        var nbUsers = settings.get("users").size();
    }

    @Test
    void demoInferenceAutoboxing(){
        int x = 12;
        Integer xo = x; // new Integer(x) // Heap
        int x2 = xo; // xo.intValue()     // Stack

        List<Integer> numbers = new ArrayList<>();
        Collections.addAll(numbers, x, xo, x2);
        int sum = 0;
        for (int v: numbers){
            sum += v;
        }
    }
}
