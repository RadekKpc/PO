package agh.cs.po.lab3;
import agh.cs.po.lab2.MapDirection;
import agh.cs.po.lab2.MoveDirection;
import agh.cs.po.lab2.Vector2d;
import agh.cs.po.lab4.IWorldMap;
import agh.cs.po.lab4.RectangularMap;
import agh.cs.po.lab5.AbstractWorldMap;
import agh.cs.po.lab5.AbstractWorldMapElement;
import agh.cs.po.lab5.IMapElement;
import agh.cs.po.lab7.IPositionChangeObserver;

import java.util.ArrayList;

public class Animal extends AbstractWorldMapElement implements IMapElement{
    public MapDirection direction;
    public IWorldMap map;
    public ArrayList<IPositionChangeObserver> observerlist = new ArrayList<>();

    public Animal() {
        super(new Vector2d(2,2));
        this.direction = MapDirection.NORTH;

    }

    public Animal(IWorldMap map){
        super(new Vector2d(2, 2));
        this.direction = MapDirection.NORTH;
        this.map = map;
        addObserver((IPositionChangeObserver) map);
        addObserver(map.getBoundary());
    }
    public Animal(IWorldMap map, Vector2d initialPosition){
        super(initialPosition);
        this.direction = MapDirection.NORTH;
        this.map = map;
        addObserver((IPositionChangeObserver) map);
        addObserver(map.getBoundary());
    }

    @Override
    public String toString(){
        return this.direction.toString();
    }

    public void move(MoveDirection d){

        switch(d){
            case LEFT:
                this.direction = this.direction.previous();
                return;
            case RIGHT:
                this.direction = this.direction.next();
                return;
            case FORWARD:
                if(map.canMoveTo(position.add(direction.toUnitVector()))){
                    Vector2d old = new Vector2d(this.getPosition().x,this.getPosition().y);
                    this.position = position.add(direction.toUnitVector());
                    this.positionChanged(old,this.position);
                }
                return;
            case BACKWARD:
                if(map.canMoveTo(position.subtract(direction.toUnitVector()))){
                    Vector2d old = new Vector2d(this.getPosition().x,this.getPosition().y);
                    position = position.subtract(direction.toUnitVector());
                    this.positionChanged(old,this.position);
                }
                return;
                //no need default
        }
    }

    @Override
    public boolean isMovable() {
        return true;
    }

    void addObserver(IPositionChangeObserver observer){
        observerlist.add(observer);
    }
    void removeObserver(IPositionChangeObserver observer){
        observerlist.remove(observer);
    }
    void positionChanged(Vector2d old,Vector2d n){

        observerlist.stream().forEach(l -> l.positionChanged(old,n));
    }


}
