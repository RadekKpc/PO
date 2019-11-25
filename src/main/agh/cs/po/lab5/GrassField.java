package agh.cs.po.lab5;

import agh.cs.po.lab2.MoveDirection;
import agh.cs.po.lab2.Vector2d;
import agh.cs.po.lab3.Animal;
import agh.cs.po.lab4.IWorldMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class GrassField extends AbstractWorldMap implements IWorldMap {

    protected int how_many_grass;
    public GrassField(int h){
        how_many_grass = h;
        int i =0;
        while(i!=h) {

            int x = (int) (Math.random() * (int) Math.sqrt(10 * h));
            int y = (int) (Math.random() * (int) Math.sqrt(10 * h));

            Vector2d pos = new Vector2d(x, y);
            if (!isOccupied(pos)){
                place(new Grass(pos));
                i++;
            }
        }

    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if(objectAt(position)==null)return true;
        else
        {
            if(objectAt(position) instanceof Grass)
                return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        Queue<IMapElement> el = elements.get(position);
        if(el == null) return null;
        if(el.size() == 1) return el.peek();
        return el.stream().filter(a -> a.isMovable()).findFirst().orElse(el.peek());

    }

}
