package agh.ics.oop;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Map<Vector2d, Animal> animals = new HashMap<Vector2d, Animal>();

    public boolean isOccupied(Vector2d position) {
        return this.objectAt(position) != null;
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = animals.get(oldPosition);
        animals.remove(oldPosition);
        animals.put(newPosition, animal);
    }

    public boolean place(Animal animal) {
        Vector2d pos = animal.getPosition();

        if (this.isOccupied(pos))
            return false;

        animals.put(pos, animal);
        return true;
    }

}
