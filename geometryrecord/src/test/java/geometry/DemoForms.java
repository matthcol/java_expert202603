package geometry;

import org.junit.jupiter.api.Test;

import java.util.List;

public class DemoForms {
    @Test
    void demoForms(){
        var pt = new Point("A", 3, 4);
        var c = new Circle("C", 12.5, pt);
        List<Form> forms = List.of(pt, c);
        forms.stream()
                .map(Form::name)
                .forEach(System.out::println);
    }
}
