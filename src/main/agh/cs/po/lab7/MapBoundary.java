package agh.cs.po.lab7;
import agh.cs.po.lab2.Vector2d;
import java.util.*;

public class MapBoundary implements IPositionChangeObserver {

    private SortedSet<Vector2d> sortedX;
    private SortedSet<Vector2d> sortedY;

    public MapBoundary() {
        sortedY = new TreeSet<>(new IMapElementComparatorY());
        sortedX = new TreeSet<>(new IMapElementComparatorX());
    }

    public void addElement(Vector2d v) {
        sortedX.add(v);
        sortedY.add(v);
    }


    public Vector2d getUpperRight() {
        return new Vector2d(sortedX.last().x,sortedY.last().y);
    }

    public Vector2d getLowerLeft() {
        return new Vector2d(sortedX.first().x,sortedY.first().y);
    }

    @Override
    public boolean positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        sortedX.remove(oldPosition);
        sortedY.remove(oldPosition);
        sortedX.add(newPosition);
        sortedY.add(newPosition);
        return true;
    }

}