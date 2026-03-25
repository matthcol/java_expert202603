package validation;

import geo.CityFr;
import org.junit.jupiter.api.Test;
import util.annotations.Min;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DemoValidation {

    @Test
    void demoGetValidationAnnotation() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var city1 = CityFr.builder()
                .name("Montauban")
                .population(-1)
                .latitudeCentroid(12.4)
                .build();

        var city2 = CityFr.builder()
                .name("Montauban")
                .zipcode("82000")
                .inseeCode("82000")
                .departmentCode("82")
                .latitudeCentroid(12.4)
                .population(70_000)
                .build();

        var other = LocalDate.now();

        for (var object: List.of(city1, city2, other)){
            Class<?> objectType = object.getClass();
            System.out.println(" * " + object);
            System.out.println("\t- type : " + objectType);
            Annotation[] annotationsType = objectType.getAnnotations();
            System.out.println("\t- annotations : " + Arrays.toString(annotationsType));
            Field[] fieldsType = objectType.getDeclaredFields();
            System.out.println("\t- fields : ");
            for (Field field: fieldsType){
                System.out.println("\t\t. " + field);

                // get all annotations
                Annotation[] annotationsField = field.getAnnotations();
                System.out.println("\t\t\t# annotations : " + Arrays.toString(annotationsField));

                // get a specific annotation
                Optional<Min> optAnnotationMin = Optional.ofNullable(field.getAnnotation(Min.class));
                System.out.println("\t\t\t# annotation Min : " + optAnnotationMin);
                if (optAnnotationMin.isPresent()) {
                    Min min = optAnnotationMin.get();
                    System.out.println("\t\t\t\t~ annotation value: " + min.value());
                    String getterName = "get"
                            + field.getName().substring(0, 1).toUpperCase()
                            + field.getName().substring(1);
                    System.out.println("\t\t\t\t~ getter: " + getterName);
                    Method getterMethod = objectType.getMethod(getterName);
                    Object valueField = getterMethod.invoke(object);
                    int intValueField = (Integer) valueField;
                    boolean isValid = intValueField >= min.value();
                    System.out.println("\t\t\t\t~ object field value: " + intValueField);
                    System.out.println("\t\t\t\t~ valid : " + isValid);
                }
            }
        }
    }
}
