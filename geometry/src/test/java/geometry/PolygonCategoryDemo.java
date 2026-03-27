package geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Objects;
import java.util.SortedMap;

import static org.junit.jupiter.api.Assertions.*;

class PolygonCategoryDemo {

    @Test
    void demo1(){
        PolygonCategory category = PolygonCategory.CONCAVE;
        System.out.println("toString: " + category);
        System.out.println("name: " + category.name());
        System.out.println("ordinal: " + category.ordinal());
    }

    @ParameterizedTest
    @EnumSource(PolygonCategory.class)
    void demo2(PolygonCategory category){
        System.out.println("toString: " + category);
        System.out.println("name: " + category.name());
        System.out.println("ordinal: " + category.ordinal());
        System.out.println("note: " + category.getNote());
        category.displayWithNote();
    }

    @ParameterizedTest
    @ValueSource(strings = {"CONCAVE", "CONVEXE", "CROISE"})
    void demoParse_ok(String value){
        PolygonCategory category = PolygonCategory.valueOf(value);
        System.out.println(category);
    }

    @ParameterizedTest
    @ValueSource(strings = {"concave", "Convexe", "toto"})
    // @NullSource => java.lang.NullPointerException
    void demoParse_ko(String value){
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> PolygonCategory.valueOf(value)
        );
        assertEquals(
                "No enum constant geometry.PolygonCategory."+value,
                ex.getMessage()
        );
    }

    @ParameterizedTest
    @ValueSource(ints={0, 1, 2})
    void demoFromOrdinal(int ordinal){
        PolygonCategory category = PolygonCategory.values()[ordinal];
        System.out.println(category);
    }

    @ParameterizedTest
    @ValueSource(ints={-1, 3})
    void demoFromOrdinal_ko(int ordinal){
        assertThrows(
                ArrayIndexOutOfBoundsException.class,
                () -> {
                    PolygonCategory cat = PolygonCategory.values()[ordinal];
                }
        );
    }


    @Test
    void demoEquals(){
        // NB : instances uniques !
        PolygonCategory c1 = PolygonCategory.CONVEXE;
        PolygonCategory c2 = PolygonCategory.CONVEXE;
        PolygonCategory c3 = PolygonCategory.CONCAVE;
        assertTrue(c1 == c2);
        assertTrue(c1.equals(c2));
        assertTrue(Objects.equals(c1, c2));
    }

    @Test
    void demoIterate(){
        for (PolygonCategory category: PolygonCategory.values()){
            System.out.println(category);
        }

        Arrays.stream(PolygonCategory.values())
                .filter(cat -> cat != PolygonCategory.CONVEXE)
                .forEach(System.out::println);
    }

    @ParameterizedTest
    @EnumSource(PolygonCategory.class)
    @NullSource
    void demoSwithCase(PolygonCategory category){
        switch (category){
            case CONVEXE -> System.out.println("Good geometry");
            case CONCAVE -> System.out.println("Aie, j'ai pris un coup");
            case CROISE -> System.out.println("pas bon");
            case null -> System.out.println("no category");
        }
    }
}