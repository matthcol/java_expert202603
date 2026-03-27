package geometry;

public class Forms {

    /**
     * compute some integer code from a form object
     * @param form
     * @return code
     * @throws NullPointerException if form is null
     */
    public static int algoSealedLevel1(Form form){
        return switch (form){
            // case null -> -1;  // generates Objects.requiresNonNul(form)
            case Point pt -> 0; // covers WeigthedPoint
            case Circle c -> 1;
            case Polygon poly -> 2;
            // new feature Segment
            case Segment _ -> 3;
        };
    }

    public static int algoSealedLevel12(Form form) {
        return switch (form) {
            case WeightedPoint _  -> 0;
            case Circle _ -> 1;
            case Polygon _ -> 2;
            default -> -1;  // NB: throw NPE if null
//            case null, default -> -1;
        };
    }

    public static int algoSealedWithInterface(Form form) {
        return switch (form) {
            case Point _ -> 0;
            case Mesurable2D _ -> 1; // covers Polygon, Circle
            case Mesurable1D _ -> 2;
        };
    }

    public static int algoSealedWithInterface(Mesurable2D mesurable) {
        return switch (mesurable) {
            case Circle _ -> 0;
            case Polygon _ -> 1;
        };
    }
}
