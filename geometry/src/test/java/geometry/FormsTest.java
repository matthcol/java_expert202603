package geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class FormsTest {

    static Stream<Arguments> formSource(){
        return Stream.of(
              //  Arguments.of(null, -1), // if case present
                Arguments.of(new Point(), 0),
                Arguments.of(new WeightedPoint(), 0),
                Arguments.of(new Circle(), 1),
                Arguments.of(new Polygon(), 2)
        );
    }

    @ParameterizedTest
    @MethodSource("formSource")
    void testAlgoSealedLevel1_ok(Form f, int expectedCode){
        int actualCode = Forms.algoSealedLevel1(f);
        Assertions.assertEquals(expectedCode, actualCode);
    }

    @Test
    void testAlgoSealedLevel1_ko(){
        NullPointerException npe = Assertions.assertThrows(
                NullPointerException.class,
                () -> Forms.algoSealedLevel1(null)
        );
        // TODO: assertions with exception object (message, ...)
    }

    @Test
    void testAlgoSealedLevel1_2_ko(){
        Assertions.assertThrows(
                NullPointerException.class,
                () -> Forms.algoSealedLevel12(null)
        );
    }
}