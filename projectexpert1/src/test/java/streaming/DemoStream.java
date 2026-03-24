package streaming;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import util.PairStringInt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class DemoStream {

    @Test
    void demoStreamString(){
        // Java 11 : List.of, Map.of, Set.of (immutable collections)
        var cities = List.of(
                "Amiens", "Pau", "Montauban",
                "Toulouse", "Paris", "annecy",
                "angoulême", "Arras"
        );
        // cities.add("Marseille");  // .UnsupportedOperationException

        cities.stream()                               // Stream<String>
                .map(city -> city.toUpperCase())      // Function<String, R> : String -> R (R = String)
                .filter(city -> city.startsWith("A")) // Predicate<String> : String -> boolean
                .forEach(city -> System.out.println(city));  // Consumer<String> : String -> void

        cities.stream()
                .map(city -> city.length())
                .filter(nbLetter -> nbLetter > 5)
                .forEach(nbLetter -> System.out.println(nbLetter));

        cities.stream()
                .peek(city -> System.out.println("Source: " + city))
                .map(city -> new PairStringInt(city, city.length()))
                .peek(city -> System.out.println("City + Nb letters: " + city))
                .filter(pairCityNb -> pairCityNb.second() > 5)
                .forEach(pairStringInt -> System.out.println(
                        "City + Nb letters (filtered): " + pairStringInt)
                );

        cities.stream()
                .filter(city -> city.length() > 5)
                .map(city -> city.toUpperCase())
                .sorted()  // !!! collection intermédiaire: n*log(n)
                .forEach(city -> System.out.println(city));
    }


    @Test
    void demoStreamStringSimplified(){
        // Java 11 : List.of, Map.of, Set.of (immutable collections)
        var cities = List.of(
                "Amiens", "Pau", "Montauban",
                "Toulouse", "Paris", "annecy",
                "angoulême", "Arras"
        );
        // cities.add("Marseille");  // .UnsupportedOperationException

        cities.stream()                               // Stream<String>
                .map(String::toLowerCase)      // Function<String, R> : String -> R (R = String)
                .filter(city -> city.startsWith("A")) // Predicate<String> : String -> boolean
                .forEach(System.out::println);  // Consumer<String> : String -> void

        cities.stream()
                .mapToInt(String::length)
                .filter(nbLetter -> nbLetter > 5)
                .forEach(System.out::println);

        cities.stream()
                .peek(city -> System.out.println("Source: " + city))
                .map(city -> new PairStringInt(city, city.length()))
                .peek(city -> System.out.println("City + Nb letters: " + city))
                .filter(pairCityNb -> pairCityNb.second() > 5)
                .forEach(pairStringInt -> System.out.println(
                        "City + Nb letters (filtered): " + pairStringInt)
                );

        cities.stream()
                .filter(city -> city.length() > 5)
                .map(String::toUpperCase)
                .sorted()  // !!! collection intermédiaire: n*log(n)
                .forEach(System.out::println);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings={"Poitiers"})
    void demoNumericReduce(String intrus){
        var cities = List.of(
                "Amiens", "Pau", "Montauban",
                "Toulouse", "Paris", "annecy",
                "angoulême", "Arras"
        );
        var cities2 = new ArrayList<>(cities);
        cities2.add(intrus);
        var totalLetters = cities2.stream()
                .filter(Objects::nonNull)  // bad idea to store null in a List
                .mapToInt(String::length)
                .filter(l -> l > 5)
                .sum();
    }

    @Test
    void demoStreamOf(){
        Stream.of(
                "Amiens", "Pau", "Montauban",
                "Toulouse", "Paris", "annecy",
                "angoulême", "Arras"
        )
                .filter(city -> city.length() > 5)
                .map(String::toUpperCase)
                .forEach(System.out::println);
        ;
    }

}
