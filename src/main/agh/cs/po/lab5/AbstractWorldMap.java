package agh.cs.po.lab5;

import agh.cs.po.lab2.MoveDirection;
import agh.cs.po.lab2.Vector2d;
import agh.cs.po.lab3.Animal;
import agh.cs.po.lab4.IWorldMap;
import agh.cs.po.lab4.MapVisualizer;
import agh.cs.po.lab7.IPositionChangeObserver;
import agh.cs.po.lab7.MapBoundary;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractWorldMap implements IWorldMap,IPositionChangeObserver {
    protected List<IMapElement> animals = new ArrayList<>();
    protected MapVisualizer v;
    protected Map<Vector2d, LinkedList<IMapElement>> elements = new HashMap<>();
    public MapBoundary boundary = new MapBoundary();

    public Vector2d getLowerLeft(){return boundary.getLowerLeft();}
    public Vector2d getUpperRight(){return boundary.getUpperRight();}

    public AbstractWorldMap(){
        v = new MapVisualizer(this);
    }

    public boolean place(IMapElement animal) {
        if(isOccupied(animal.getPosition())){
            throw new IllegalArgumentException("Field "+ animal.getPosition() +" is occupied");

        }
        if(canMoveTo(animal.getPosition())){
            animals.add(animal);
            boundary.addElement(animal.getPosition());
            if(elements.get(animal.getPosition()) == null){
                LinkedList<IMapElement> tmp = new LinkedList<IMapElement>();
                tmp.add(animal);
                elements.put(animal.getPosition(), tmp );
            }
            return true;
        }
        return false;
    }

    public void run(MoveDirection[] directions) {
        int size = (int) animals.stream().filter(a -> a.isMovable()).count();
        if(size == 0) return;
        List<IMapElement> el = animals.stream().filter(a -> a.isMovable()).collect(Collectors.toCollection(ArrayList::new));
        for( int i=0;i<directions.length;i++){
            IMapElement e = el.get(i%size);
            e.move(directions[i]);
        }
    }
    public String toString(){
        return v.draw(getLowerLeft(),getUpperRight());
    }
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;

    }

    public MapBoundary getBoundary(){
        return boundary;
    }

    public Object objectAt(Vector2d position) {
        return elements.get(position) == null ? null : elements.get(position).peek();

    }
    public boolean positionChanged(Vector2d oldPosition, Vector2d newPosition) {

        LinkedList<IMapElement> l = elements.get(oldPosition);
        IMapElement e;

        if (l != null) {
            e = elements.get(oldPosition).stream().filter(a -> a.isMovable()).findFirst().orElse(null);
        }
        else {
            return false;
        }
        if (e != null) {
            if(elements.get(newPosition) == null) {
                LinkedList<IMapElement> tmp = new LinkedList<IMapElement>();
                tmp.add(e);
                l.remove(e);
                elements.put(newPosition, tmp);
                return true;
            }
            else{
                l.remove(e);
                elements.get(newPosition).add(e);
                return true;
            }
        } else {
            return false;
        }

    }

}
