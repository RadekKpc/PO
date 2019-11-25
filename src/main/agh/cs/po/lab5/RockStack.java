package agh.cs.po.lab5;

import agh.cs.po.lab2.MoveDirection;
import agh.cs.po.lab2.Vector2d;

public class RockStack extends AbstractWorldMapElement implements IMapElement{

    public RockStack(Vector2d position){
        super(position);
    }

    @Override
    public String toString(){
        return "X";
    }

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public void move(MoveDirection d) {
    }

}
