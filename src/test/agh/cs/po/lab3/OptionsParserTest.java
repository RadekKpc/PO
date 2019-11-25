package agh.cs.po.lab3;

import agh.cs.po.lab2.MapDirection;
import agh.cs.po.lab2.MoveDirection;
import agh.cs.po.lab2.Vector2d;
import agh.cs.po.lab4.RectangularMap;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OptionsParserTest {

    @Test
    void parse() {

        MoveDirection[] t1 = {
                MoveDirection.BACKWARD,
                MoveDirection.BACKWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.RIGHT,
                MoveDirection.LEFT,
                MoveDirection.LEFT};
        String[] t2 = {
                "b",
                "backward",
                "f",
                "forward",
                "r",
                "right",
                "l",
                "left"};

        assertEquals(true, Arrays.equals(t1,OptionsParser.parse(t2)));

        //EMPTY
        MoveDirection[] t3 = {};
        String[] t4 = {};
        assertEquals(true, Arrays.equals(t3,OptionsParser.parse(t4)));

        //VALID STRINGS

        MoveDirection[] t5 = {
                MoveDirection.BACKWARD,
                MoveDirection.BACKWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.RIGHT,
                MoveDirection.LEFT,
                MoveDirection.LEFT};
        String[] t6 = {
                "b",
                "backward",
                "f",
                "forward",
                "r",
                "right",
                "l",
                "left"};

        assertEquals(true, Arrays.equals(t5,OptionsParser.parse(t6)));

    }

    @Test
    void integrationTests()
    {

        RectangularMap m = new RectangularMap(10,10);
        Animal animal = new Animal(m,new Vector2d(2,2));
        String args[] = {"r", "l", "r", "l", "l", "l","b","b","f"};
        MoveDirection moves[] = OptionsParser.parse(args);

        MapDirection e[] = {
                MapDirection.EAST,
                MapDirection.NORTH,
                MapDirection.EAST,
                MapDirection.NORTH,
                MapDirection.WEST,
                MapDirection.SOUTH,
                MapDirection.SOUTH,
                MapDirection.SOUTH,
                MapDirection.SOUTH
        };

        for (int i = 0; i < moves.length; i++) {
            animal.move(moves[i]);
            assertEquals(animal.direction, e[i]);
        }
    }
}