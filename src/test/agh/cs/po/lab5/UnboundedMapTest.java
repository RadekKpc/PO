package agh.cs.po.lab5;

import agh.cs.po.lab2.MoveDirection;
import agh.cs.po.lab2.Vector2d;
import agh.cs.po.lab3.Animal;
import agh.cs.po.lab3.OptionsParser;
import agh.cs.po.lab4.IWorldMap;
import agh.cs.po.lab4.RectangularMap;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UnboundedMapTest {

    UnboundedMap map;
    Animal a;

    @Before
    void setup(){
        List<RockStack> stacks = new ArrayList<>();
        stacks.add(new RockStack(new Vector2d(-4,-4)));
        stacks.add(new RockStack(new Vector2d(7,7)));
        stacks.add(new RockStack(new Vector2d(3,6)));
        stacks.add(new RockStack(new Vector2d(2,0)));
        map = new UnboundedMap(stacks);
        a = new Animal(map,new Vector2d(1,1));
    }
    @Test
    void isOccupied() {
        setup();
        for(int i=20;i<30;i++) {
            for (int j = 0; j < 10; j++) {
                assertFalse(map.isOccupied(new Vector2d(i, j)));
                map.place(new Animal(map, new Vector2d(i, j)));
                assertTrue(map.isOccupied(new Vector2d(i, j)));
            }
        }
    }

    @Test
    void isOccupiedRockStack(){
        setup();
        assertTrue(map.isOccupied(new Vector2d(-4, -4)));
        assertTrue(map.isOccupied(new Vector2d(7, 7)));
        assertTrue(map.isOccupied(new Vector2d(3, 6)));
        assertTrue(map.isOccupied(new Vector2d(2, 0)));
    }

    @Test
    void objectAt() {
        setup();
        for(int i=10;i<30;i++) {
            for (int j = 0; j < 16; j++) {
                assertNull(map.objectAt(new Vector2d(i, j)));
                Animal n  = new Animal(map, new Vector2d(i, j));
                map.place(n);
                assertTrue(map.objectAt(new Vector2d(i, j)).equals(n));
            }
        }
    }


    @Test
    void canMoveToAtOccupied(){
        setup();
        assertFalse(map.canMoveTo(new Vector2d(7,7)));
    }
    @Test
    void canMoveToFree(){
        setup();
        for(int i=10;i<20;i++) {
            for (int j = 0; j < 10; j++) {
                assertTrue(map.canMoveTo(new Vector2d(i, j)));
            }
        }

    }

    @Test
    void place() {
        setup();
        assertTrue(map.place(new Animal(map,new Vector2d(2,2))));
        assertTrue(map.place(new Animal(map,new Vector2d(-1,2))));
        assertTrue(map.place(new Animal(map,new Vector2d(2,3))));
    }

    @Test
    void runForward() {
        setup();
        Animal[] animals  = new Animal[5];
        for(int i =0;i<animals.length;i++){
            animals[i] = new Animal(map,new Vector2d(i+1,i+1));
            map.place(animals[i]);
        }

        String[] args = {"f","f","f","f","f"};
        MoveDirection[] directions = new OptionsParser().parse(args);

        map.run(directions);
        for(int i=0;i<animals.length;i++){
            assertEquals(new Vector2d(i+1,i+2),animals[i].getPosition());
        }
    }
    @Test
    void runBackward() {
        setup();
        Animal[] animals  = new Animal[5];
        for(int i =0;i<animals.length;i++){
            animals[i] = new Animal(map,new Vector2d(i+1,i+1));
            map.place(animals[i]);
        }

        String[] args = {"b","b","b","b","b"};
        MoveDirection[] directions = new OptionsParser().parse(args);

        map.run(directions);
        for(int i=0;i<animals.length;i++){
            assertEquals(new Vector2d(i+1,i),animals[i].getPosition());
        }
    }
    @Test
    void runLeft() {
        setup();
        Animal[] animals  = new Animal[5];
        for(int i =0;i<animals.length;i++){
            animals[i] = new Animal(map,new Vector2d(i+1,i+1));
            map.place(animals[i]);
        }

        String[] args = {"l","l","l","l","l","f","f","f","f","f"};
        MoveDirection[] directions = new OptionsParser().parse(args);

        map.run(directions);
        for(int i=0;i<animals.length;i++){
            assertEquals(new Vector2d(i,i+1),animals[i].getPosition());
        }
    }
    @Test
    void runRight() {
        setup();
        Animal[] animals  = new Animal[5];
        for(int i =0;i<animals.length;i++){
            animals[i] = new Animal(map,new Vector2d(i+1,i+1));
            map.place(animals[i]);
        }

        String[] args = {"r","r","r","r","r","f","f","f","f","f"};
        MoveDirection[] directions = new OptionsParser().parse(args);

        map.run(directions);
        for(int i=0;i<animals.length;i++){
            assertEquals(new Vector2d(i+2,i+1),animals[i].getPosition());
        }
    }    @Test
    void runCollison() {
        setup();
        Animal[] animals  = new Animal[2];
        for(int i =0;i<animals.length;i++){
            animals[i] = new Animal(map,new Vector2d(i+1,i+1));
            map.place(animals[i]);
        }

        String[] args = {"f","l","r","f","f","f","f","f","f","f"};
        MoveDirection[] directions = new OptionsParser().parse(args);

        map.run(directions);
        assertEquals(new Vector2d(1,2),animals[0].getPosition());
        assertEquals(new Vector2d(2,2),animals[1].getPosition());
    }
    @Test
    void runStackColision(){
        setup();
        map.place(a);
        String[] args = {"r","f","r","f","f","f","f","f","f","f"};
        MoveDirection[] directions = new OptionsParser().parse(args);

        map.run(directions);

        assertEquals(new Vector2d(2,1),a.getPosition());

    }
    @Test
    void integrationTests(){
        setup();
        String[] args = {"f","b","r","l","f","f"    ,"r","r","f","f","f","f",   "f","f","f"};
        MoveDirection[] directions = new OptionsParser().parse(args);

        Animal[] animals  = new Animal[6];
        for(int i =0;i<animals.length;i++){
            animals[i] = new Animal(map,new Vector2d(i+1,i+1));
            map.place(animals[i]);
        }
        map.run(directions);

        Vector2d[] expected = new Vector2d[animals.length];
        expected[0] = new Vector2d(2,2);
        expected[1]= new Vector2d(3,1);
        expected[2] = new Vector2d(5,3);
        expected[3]= new Vector2d(3,4);
        expected[4]= new Vector2d(5,7);
        expected[5]= new Vector2d(6,8);

        for(int i=0;i<animals.length;i++){
            assertEquals(expected[i],animals[i].getPosition());
            assertEquals(animals[i],map.objectAt(expected[i]));
        }
    }
}