package agh.ics.oop;
import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap {
    protected List<Animal> animals = new ArrayList<>();

    public boolean isOccupied(Vector2d position) {
        return this.objectAt(position) != null;
    }

}
