package agh.cs.po.lab3;
import agh.cs.po.lab2.MapDirection;
import agh.cs.po.lab2.MoveDirection;
import agh.cs.po.lab2.Vector2d;
import agh.cs.po.lab4.IWorldMap;
import agh.cs.po.lab4.RectangularMap;
import agh.cs.po.lab5.GrassField;
import agh.cs.po.lab5.RockStack;
import agh.cs.po.lab5.UnboundedMap;
import java.util.Scanner;
import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;
import static java.lang.System.setIn;
import static java.lang.System.setOut;

public class World {

    public static void main(String[] args) {

        try {
            List<RockStack>
                    stacks = new ArrayList<>();
            stacks.add(new RockStack(new Vector2d(-4, -4)));
            stacks.add(new RockStack(new Vector2d(7, 7)));
            stacks.add(new RockStack(new Vector2d(3, 6)));
            stacks.add(new RockStack(new Vector2d(2, 0)));

            MoveDirection[] directions = new OptionsParser().parse(args);
            IWorldMap map = new GrassField(15);
            Animal a = new Animal(map);
            map.place(new RockStack(new Vector2d(-1,-1)));
            map.place(new RockStack(new Vector2d(0,-1)));
            map.place(new RockStack(new Vector2d(1,-1)));
            map.place(new RockStack(new Vector2d(2,-1)));
            map.place(new RockStack(new Vector2d(3,-1)));
            map.place(new RockStack(new Vector2d(4,-1)));
            map.place(new RockStack(new Vector2d(5,-1)));
            map.place(new RockStack(new Vector2d(6,-1)));
            map.place(new RockStack(new Vector2d(7,0)));
            map.place(new RockStack(new Vector2d(8,0)));
            map.place(new RockStack(new Vector2d(9,1)));

            map.place(a);
            System.out.println(map);

            boolean exit = true;
            Scanner scanner = new Scanner(System.in);

            while(exit){
                String key = scanner.next();
                if(key.equals("e")){exit = false;}
                if(key.equals("w")){a.move(MoveDirection.FORWARD);}
                if(key.equals("s")){a.move(MoveDirection.BACKWARD);}
                if(key.equals("a")){a.move(MoveDirection.LEFT);}
                if(key.equals("d")){a.move(MoveDirection.RIGHT);}
                System.out.print("\033[H\033[2J");
                System.out.println(map);
            }

        }
        catch(IllegalArgumentException ex){
            System.out.println(ex);
            return;
        }

    }

}
