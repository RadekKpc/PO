package agh.cs.po.lab4;
import agh.cs.po.lab2.MoveDirection;
import agh.cs.po.lab2.Vector2d;
import agh.cs.po.lab3.Animal;
import agh.cs.po.lab5.AbstractWorldMap;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap implements IWorldMap{
    Vector2d lowerLeft2;
    Vector2d upperRight2;
    public RectangularMap(int width, int height) {
        upperRight2 = new Vector2d(width-1,height-1);
        lowerLeft2 = new Vector2d(0,0);
    }

    public Vector2d getLowerLeft(){return  lowerLeft2;}
    public  Vector2d getUpperRight(){return upperRight2;}

    @Override
    public boolean canMoveTo(Vector2d position) {
        if(!position.precedes(upperRight2) || !position.follows(lowerLeft2))
            return false;
        if(isOccupied(position))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return v.draw(lowerLeft2,upperRight2);
    }


}
