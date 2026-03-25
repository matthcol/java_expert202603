package validation.genrics;

import geo.CityFr;
import org.junit.jupiter.api.Test;
import util.generic.GenericTool;

import java.util.List;

public class DemoGenerics {

    @Test
    void demoGenerics(){
        List<CityFr> list1 = List.of(
                CityFr.builder()
                        .name("Pau")
                        .build(),
                CityFr.builder()
                        .name("Montauban")
                        .build()
        );
        List<String> list2 = List.of("Toulouse", "Paris");
        List<Double> list3 = List.of(12.3, 25.4, 34.5);
        List<Number> list4 = List.of(12.3, 25.4, 12, 14L, (short) 4);

        for (CityFr city: list1) System.out.println(city.getName());
        for (String city: list2) System.out.println(city.toUpperCase());

        Object object1 = list1;

        List<CityFr> list1bis = GenericTool.toList(object1, CityFr.class);
        System.out.println(list1bis);

        List<Number> list3bis = GenericTool.toList(list3, Number.class);
        List<Double> list4bis = GenericTool.toList(list4, Double.class);
    }
}
