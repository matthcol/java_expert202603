package geometry;

import lombok.Getter;

// type object qui hérite de Enum
// - toString
// - conversion String <-> Enum (name)
// - conversion int <-> Enum (ordinal)
// - comparable : ordre déclaration
@Getter
public enum PolygonCategory {
    // valeurs litterales = 3 instances

    // version initiale:
    //    CONCAVE, CONVEXE, CROISE

    CONCAVE(3) {
        @Override
        public void displayWithNote() {
            System.out.println("concave is quite good");
        }
    },
    CONVEXE(5) {
        @Override
        public void displayWithNote() {
            System.out.println("convexe is the best");
        }
    },
    CROISE(0) {
        @Override
        public void displayWithNote() {
            System.out.println("croisé, on veut éviter");
        }
    };

    PolygonCategory(double note) {
        this.note = note;
    }

    private double note;

    public abstract void displayWithNote();
}
