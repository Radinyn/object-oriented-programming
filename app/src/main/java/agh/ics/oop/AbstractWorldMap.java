package agh.ics.oop;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Map<Vector2d, Animal> animals = new HashMap<Vector2d, Animal>();
    protected MapBoundary mapBoundary = new MapBoundary();

    public boolean isOccupied(Vector2d position) {
        return this.objectAt(position) != null;
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = animals.get(oldPosition);
        animals.remove(oldPosition);
        animals.put(newPosition, animal);
        mapBoundary.positionChanged(oldPosition, newPosition);
    }

    public boolean place(Animal animal) {
        Vector2d pos = animal.getPosition();

        if (!this.canMoveTo(pos))
            throw new IllegalArgumentException("Animal cannot be placed at " + pos.toString());

        animals.put(pos, animal);
        mapBoundary.addPosition(pos);
        return true;
    }

    public Vector2d[] getBounds() {
        return mapBoundary.getBounds();
    }

}
