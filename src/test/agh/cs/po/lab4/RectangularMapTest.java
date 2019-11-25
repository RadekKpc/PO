package agh.cs.po.lab4;

import agh.cs.po.lab2.MoveDirection;
import agh.cs.po.lab2.Vector2d;
import agh.cs.po.lab3.Animal;
import agh.cs.po.lab3.OptionsParser;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    RectangularMap map = new RectangularMap(10,10);
    Animal a;

    @Before
    void setup(){
        map = new RectangularMap(10,10);
        a= new Animal(map,new Vector2d(1,1));
    }
    @Test
    void isOccupied() {
        for(int i=0;i<map.getUpperRight().y;i++) {
            for (int j = 0; j < map.getUpperRight().y; j++) {
                assertFalse(map.isOccupied(new Vector2d(i, j)));
                map.place(new Animal(map, new Vector2d(i, j)));
                assertTrue(map.isOccupied(new Vector2d(i, j)));
            }
        }
    }

    @Test
    void objectAt() {
        for(int i=0;i<map.getUpperRight().y;i++) {
            for (int j = 0; j < map.getUpperRight().y; j++) {
                assertNull(map.objectAt(new Vector2d(i, j)));
                Animal n  = new Animal(map, new Vector2d(i, j));
                map.place(n);
                assertTrue(map.objectAt(new Vector2d(i, j)).equals(n));
            }
        }
    }

    @Test
    void canMoveToOutOfTheMap(){
        assertFalse(map.canMoveTo(new Vector2d(-1,3)));
        assertFalse(map.canMoveTo(new Vector2d(3,-1)));
        assertFalse(map.canMoveTo(new Vector2d(10,5)));
        assertFalse(map.canMoveTo(new Vector2d(5,10)));
        assertFalse(map.canMoveTo(new Vector2d(-1,-1)));
        assertFalse(map.canMoveTo(new Vector2d(10,10)));
    }

    @Test
    void canMoveToAtOccupied(){
        map.place(new Animal(map,new Vector2d(1,2)));
        assertFalse(map.canMoveTo(new Vector2d(1,2)));
    }
    @Test
    void canMoveToFree(){
        for(int i=0;i<map.getUpperRight().y;i++) {
            for (int j = 0; j < map.getUpperRight().y; j++) {
                assertTrue(map.canMoveTo(new Vector2d(i, j)));
            }
        }

    }

    @Test
    void place() {
        assertTrue(map.place(new Animal(map,new Vector2d(2,2))));
        assertFalse(map.place(new Animal(map,new Vector2d(-1,2))));
        assertTrue(map.place(new Animal(map,new Vector2d(2,3))));
    }
    //Dopisac testy
    @Test
    void runForward() {
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
    void integrationTests(){
        String[] args = {"f","b","r","l","f","f"    ,"r","r","f","f","f","f",   "f","f","f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 10);

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