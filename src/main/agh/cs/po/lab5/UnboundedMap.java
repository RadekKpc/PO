package agh.cs.po.lab5;

import agh.cs.po.lab2.MoveDirection;
import agh.cs.po.lab2.Vector2d;
import agh.cs.po.lab3.Animal;
import agh.cs.po.lab4.IWorldMap;
import agh.cs.po.lab4.MapVisualizer;

import java.util.List;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class UnboundedMap extends AbstractWorldMap implements IWorldMap  {

    public UnboundedMap(List<RockStack> stacks){
        for(IMapElement e : stacks){
            place(e);
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }



}
