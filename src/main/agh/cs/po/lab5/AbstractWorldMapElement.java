package agh.cs.po.lab5;

import agh.cs.po.lab2.Vector2d;

public abstract class AbstractWorldMapElement{

    protected Vector2d position;

    public AbstractWorldMapElement(Vector2d position){
        this.position = position;
    }

    public Vector2d getPosition(){
        return this.position;
    }

}
