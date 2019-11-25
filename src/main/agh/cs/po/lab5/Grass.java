package agh.cs.po.lab5;

import agh.cs.po.lab2.MoveDirection;
import agh.cs.po.lab2.Vector2d;

public class Grass extends AbstractWorldMapElement implements IMapElement{


    public Grass(Vector2d position) {
        super(position);
    }

    @Override
    public boolean isMovable() {
        return false;
}

    @Override
    public void move(MoveDirection d) {
    }

    @Override
    public String toString(){
        return "*";
    }
}
