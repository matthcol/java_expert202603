package geometry;

public class Forms {

    public static int codeFromForm(Form form){
        return switch (form){
            case Point(_, double x, double y) when x > 0 -> (int) (x + y);
            case Point _ -> 1;
            case Mesurable2D _ -> 0;
        };
    }
}
