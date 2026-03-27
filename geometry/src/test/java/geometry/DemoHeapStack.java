package geometry;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DemoHeapStack {

    void f(Point pt1){
        g(pt1);
        System.out.println("In f:" + pt1);
    }

    static void g(Point pt2){
        int x2 = 3;
        int y2 = 4;
        pt2.setX(x2);
        pt2.setY(y2);
        System.out.println("In f:" + pt2);
    } // depile pt2, x2, y2

    Point h(String name){
        Point pt0 = new Point(name, 0, 0);
        return pt0;
    } // depile: name + pt0, empile valeur pt0

    @Test
    void demoStackHeap(){
        Point pt = h("A");
        List<Point> points = new ArrayList<>();
        points.add(pt);
        f(pt);
    }

    @Test
    void demoAllocate(){

    }

}
