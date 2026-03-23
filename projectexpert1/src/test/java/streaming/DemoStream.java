package streaming;

import org.junit.jupiter.api.Test;
import util.PairStringInt;

import java.util.List;
import java.util.Map;

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
}
