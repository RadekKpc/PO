package agh.cs.po.lab0;
import static java.lang.System.out;
import java.util.*;
import agh.cs.po.lab1.Direction;
import agh.cs.po.lab2.Vector2d;
import agh.cs.po.lab5.GrassField;
import agh.cs.po.lab5.IMapElement;
import agh.cs.po.lab5.RockStack;

public class World {
    public static void main(String[] args) {
        Integer a = 200;
        Integer b = 200;
        if (a.equals(b)) {
            out.println("Wartości są równe");
        } else {
            out.println("Wartości nie są równe");
        }

        Map<Vector2d, IMapElement> elements = new HashMap<>();
        RockStack y = new RockStack(new Vector2d(1,1));
        RockStack r = new RockStack(new Vector2d(1,1));

        elements.put(r.getPosition(),r);
        elements.put(y.getPosition(),y);
        System.out.println(elements.get(new Vector2d(1,1)) == r);
        System.out.println(elements.get(new Vector2d(1,1)) == y);
    }
}
