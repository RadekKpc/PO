package agh.cs.po.lab7;

import agh.cs.po.lab2.Vector2d;
import agh.cs.po.lab5.IMapElement;

public interface IPositionChangeObserver {
    boolean positionChanged(Vector2d oldPosition, Vector2d newPosition);
}
