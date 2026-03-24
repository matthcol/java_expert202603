package streaming;

import geo.CityAdapter;
import geo.CityFr;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import util.CsvTools;
import util.function.TriFunction;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.Collator;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DemoStreamCitiesCsv {

    static final Collator COMPARATOR_STRING_FR = Collator.getInstance(Locale.FRENCH);
    static List<CityFr> cities;

    @BeforeAll
    static void loadData(){
        try (
            var stream = CsvTools.streamFromCsv("src/test/resources/communes-france-2025.csv", ',', '"', 1, StandardCharsets.UTF_8)
        ) {
            cities = stream
                    //.peek(line -> System.out.println(Arrays.toString(line)))
                    //.map(line -> CityAdapter.cityFromArray(line))
                    .map(CityAdapter::cityFromArray)
                    // .skip(200)
                    // .limit(5)
                    .toList(); // shortcut Java 17
        } // auto-close
    }

    @Test
    void demo1(){
        cities.stream()
                .limit(5)
                .forEach(System.out::println);
    }

    @Test
    void demoCollectSet(){
        var citySet = cities.stream()
                .filter(city -> city.getDepartmentCode().equals("82"))
                .collect(Collectors.toSet());
        System.out.println(citySet);
        System.out.println(citySet.getClass());
    }

    @Test
    void demoCollectSortedSet(){
        var citySet = cities.stream()
                .filter(city -> city.getDepartmentCode().equals("82"))
                .map(city -> city.getName())
                .collect(Collectors.toCollection(() -> new TreeSet<>()));
        System.out.println(citySet);
        System.out.println(citySet.getClass());
    }

    @Test
    void demoCollectSortedSet2(){
        var citySet = cities.stream()
                .filter(city -> city.getDepartmentCode().equals("82"))
                .map(CityFr::getName)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(citySet);
        System.out.println(citySet.getClass());
    }

    static Stream<Comparator<CityFr>> comparatorSource(){
        return Stream.of(
                Comparator.comparing(CityFr::getPopulation),
                Comparator.comparing(CityFr::getName)
        );
    }

    static Stream<Arguments> comparatorLabelledSource(){
        return Stream.of(
                Arguments.of("by population asc", Comparator.comparing(CityFr::getPopulation)),
                Arguments.of("by name asc", Comparator.comparing(CityFr::getName)),
                Arguments.of(
                        "by departement asc, population asc",
                        Comparator.comparing(CityFr::getDepartmentCode)
                                .thenComparing(CityFr::getPopulation)
                ),
                Arguments.of(
                        "by department asc, population desc",
                        Comparator.comparing(CityFr::getDepartmentCode)
                                .thenComparing(CityFr::getPopulation, Comparator.reverseOrder())
                ),
                Arguments.of(
                        "by department asc, population desc, name",
                        Comparator.comparing(CityFr::getDepartmentCode)
                                .thenComparing(CityFr::getPopulation, Comparator.reverseOrder())
                                .thenComparing(CityFr::getName, String::compareToIgnoreCase)
                ),
                Arguments.of(
                        "by name",
                        Comparator.comparing(CityFr::getName, COMPARATOR_STRING_FR)
                )
        );
    }

    @ParameterizedTest(name="{0}")
    @MethodSource("comparatorLabelledSource")
    void demoCollectSortedSet3(String label, Comparator<CityFr> cityFrComparator) {
        // Comparator<CityFr> cityFrComparator = Comparator.comparing(CityFr::getPopulation);
        var citySet = cities.stream()
                .filter(city -> city.getDepartmentCode().equals("82"))
                .collect(Collectors.toCollection(() -> new TreeSet<>(cityFrComparator)));
        citySet.forEach(System.out::println);
    }

    @ParameterizedTest(name="{0}")
    @MethodSource("comparatorLabelledSource")
    void demoCollectSortedSet4(String label, Comparator<CityFr> cityFrComparator) {
        // Comparator<CityFr> cityFrComparator = Comparator.comparing(CityFr::getPopulation);
        int populationThreshold = 10_000;
        var citySet = cities.stream()
                .filter(city -> city.getPopulation() >= populationThreshold)
                .collect(Collectors.toCollection(() -> new TreeSet<>(cityFrComparator)));
        citySet.forEach(System.out::println);
    }

    @Test
    void demoComparator(){
        Comparator<String> compStr = (s1, s2) -> s1.length() - s2.length();
        Comparator<String> compStr2 = String::compareToIgnoreCase;

        // to avoid that:
        Comparator<CityFr> compCity = (city1, city2) -> {
            int res = city1.getName().compareTo(city2.getName());
            if (res == 0) res = city1.getDepartmentCode().compareTo(city2.getDepartmentCode());
            return res;
        };
        // do this:
        Comparator<CityFr> compCity2 = Comparator.comparing(CityFr::getName)
                .thenComparing(CityFr::getDepartmentCode);
    }

    @Test
    void demoFunctionalType(){
        Function<CityFr, String> f1 = city -> city.getName() + " " + city.getDepartmentCode();
        TriFunction<String, String, LocalDate, String> f2 =
                (s1, s2, d) -> MessageFormat.format(
                        "{0} - {1} at {2}",
                        s1, s2, d);
    }

    @Test
    void demoGroupBy(){
        Map<String, List<CityFr>> citiesByDept = cities.stream()
                .collect(Collectors.groupingBy(CityFr::getDepartmentCode));

        citiesByDept.forEach((dept, cityList) -> {
            System.out.println(MessageFormat.format(
                    "* département {0} ({1} villes)",
                    dept,
                    cityList.size()
            ));
            cityList.stream()
                    .limit(20)
                    .forEach(city -> System.out.println("\t - " + city.getName()));
        });
    }

    @Test
    void demoGroupBy2() {
        TreeMap<String, IntSummaryStatistics> populationStatsByDept = cities.stream()
                .collect(Collectors.groupingBy(
                        CityFr::getDepartmentCode,
                        TreeMap::new,
                        Collectors.summarizingInt(CityFr::getPopulation)
                ));
        populationStatsByDept.forEach(
                (dept, stats) -> System.out.println(
                        MessageFormat.format(
                                "dept {0} : {1}",
                                dept,
                                stats
                        ))
        );
    }

    // TODO: trouver les villes sans coordonnes GPS
}
