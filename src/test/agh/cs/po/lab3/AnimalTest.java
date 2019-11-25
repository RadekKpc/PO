package agh.cs.po.lab3;

import agh.cs.po.lab2.MoveDirection;
import agh.cs.po.lab2.Vector2d;
import agh.cs.po.lab5.GrassField;
import org.junit.Before;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    Animal a;
    @Before
    void setup(){
        GrassField m = new GrassField(0);
        a = new Animal(m,new Vector2d(2,2));
    }
    @Test
    void testToString() {
        setup();
        assertEquals("N",a.toString());
        assertEquals(new Vector2d(2,2), a.getPosition());
        a.move(MoveDirection.BACKWARD);
        assertEquals("N",a.toString());
        assertEquals(new Vector2d(2,1), a.getPosition());

    }

    @Test
    void moveRight() {
        setup();
        //RIGHT

        assertEquals("N", a.toString());
        a.move(MoveDirection.BACKWARD);
        assertEquals("N", a.toString());
        assertEquals(new Vector2d(2,1), a.getPosition());
        a.move(MoveDirection.FORWARD);
        assertEquals("N", a.toString());
        assertEquals(new Vector2d(2,2), a.getPosition());

        a.move(MoveDirection.RIGHT);
        assertEquals("E", a.toString());
        assertEquals(new Vector2d(2,2), a.getPosition());
        a.move(MoveDirection.BACKWARD);
        assertEquals("E", a.toString());
        assertEquals(new Vector2d(1,2), a.getPosition());
        a.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(2,2), a.getPosition());
        assertEquals("E", a.toString());

        a.move(MoveDirection.RIGHT);
        assertEquals("S", a.toString());
        assertEquals(new Vector2d(2,2), a.getPosition());
        a.move(MoveDirection.BACKWARD);
        assertEquals("S", a.toString());
        assertEquals(new Vector2d(2,3), a.getPosition());
        a.move(MoveDirection.FORWARD);
        assertEquals("S", a.toString());
        assertEquals(new Vector2d(2,2), a.getPosition());


        a.move(MoveDirection.RIGHT);
        assertEquals("W", a.toString());
        assertEquals(new Vector2d(2,2), a.getPosition());
        a.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(3,2), a.getPosition());
        assertEquals("W", a.toString());
        a.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(2,2), a.getPosition());
        assertEquals("W", a.toString());

    }
    @Test
    void moveLeft() {
        //LEFT
        setup();
        a.move(MoveDirection.LEFT);
        assertEquals(new Vector2d(2,2), a.getPosition());
        assertEquals("W", a.toString());
        a.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(3,2), a.getPosition());
        assertEquals("W", a.toString());
        a.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(2,2), a.getPosition());
        assertEquals("W", a.toString());

        a.move(MoveDirection.LEFT);
        assertEquals(new Vector2d(2,2), a.getPosition());
        assertEquals("S", a.toString());
        a.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(2,3), a.getPosition());
        assertEquals("S", a.toString());
        a.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(2,2), a.getPosition());
        assertEquals("S", a.toString());

        a.move(MoveDirection.LEFT);
        assertEquals(new Vector2d(2,2), a.getPosition());
        assertEquals("E", a.toString());
        a.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(1,2), a.getPosition());
        assertEquals("E", a.toString());
        a.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(2,2), a.getPosition());
        assertEquals("E", a.toString());

        a.move(MoveDirection.LEFT);
        assertEquals(new Vector2d(2,2), a.getPosition());
        assertEquals("N", a.toString());
        a.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(2,1), a.getPosition());
        assertEquals("N", a.toString());
        a.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(2,2), a.getPosition());
        assertEquals("N", a.toString());
    }


}