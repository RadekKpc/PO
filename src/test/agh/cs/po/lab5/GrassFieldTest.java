package agh.cs.po.lab5;

import agh.cs.po.lab2.MoveDirection;
import agh.cs.po.lab2.Vector2d;
import agh.cs.po.lab3.Animal;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {
    GrassField map;
    Animal a;

    @Before
    void setup(){
        map = new GrassField(10);
        a = new Animal(map,new Vector2d(1,1));
    }
    @Test
    void canMoveToGrass() {
        setup();
        map.place(new Grass(new Vector2d(1,2)));
        map.place(a);
        a.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(1,2),a.getPosition());
    }
    @Test
    void canMoveToAtOccupied(){
        map = new GrassField(0);
        map.place(new Animal(map,new Vector2d(1,2)));
        assertFalse(map.canMoveTo(new Vector2d(1,2)));
    }
    @Test
    void canMoveToFree(){
        setup();
        for(int i=0;i<map.getUpperRight().y;i++) {
            for (int j = 0; j < map.getUpperRight().y; j++) {
                assertTrue(map.canMoveTo(new Vector2d(i, j)));
            }
        }

    }
    @Test
    void objectAt() {
        GrassField map = new GrassField(0);
        map.place(new Grass(new Vector2d(7,7)));
        map.place(new Grass(new Vector2d(0,0)));
        a = new Animal(map,new Vector2d(1,1));
        Grass g =  new Grass(new Vector2d(1,2));
        map.place(g);
        assertEquals(map.objectAt(new Vector2d(1,2)),g);
        map.place(a);
        assertEquals(map.objectAt(new Vector2d(1,1)),a);
        map.run(new MoveDirection[]{MoveDirection.FORWARD} );
        assertEquals(map.objectAt(new Vector2d(1,2)),a);
        map.run(new MoveDirection[]{MoveDirection.FORWARD} );
        assertEquals(map.objectAt(new Vector2d(1,2)),g);

    }
}