package agh.ics.oop;

import java.util.*;

public class Animal extends AbstractWorldMapElement {
    private MapDirection orientation = MapDirection.NORTH;
    private List<IPositionChangeObserver> observers = new ArrayList<>();
    private Vector2d position = new Vector2d(2, 2);
    private IWorldMap map;

    public Animal(IWorldMap map, Vector2d initial_position) {
        this.map = map;
        this.position = initial_position;
    }

    public Animal(IWorldMap map) {
        this.map = map;
        this.position = new Vector2d(2, 2);
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }

    public String toString() {
        return switch (this.orientation) {
            case NORTH -> "↑";
            case EAST -> "→";
            case WEST -> "←";
            case SOUTH -> "↓";
        };
    }

    public String getImagePath() {
        return switch (this.orientation) {
            case NORTH -> "src/main/resources/up.png";
            case EAST -> "src/main/resources/right.png";
            case WEST -> "src/main/resources/left.png";
            case SOUTH -> "src/main/resources/down.png";
        };
    }

    public String describe() {
        return String.format("Animal(position: %s, direction: %s)", this.position, this.orientation);
    }

    public Animal move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD, BACKWARD -> {
                Vector2d unitVector = this.orientation.toUnitVector();
                if (direction == MoveDirection.BACKWARD)
                    unitVector = unitVector.opposite();
                Vector2d newPosition = this.position.add(unitVector);

                if (this.map.canMoveTo(newPosition)) {
                    this.onPositionChanged(this.position, newPosition);
                    this.position = newPosition;
                }
            }
        }

        return this;
    }

    public Animal move(MoveDirection[] directions) {
        for (MoveDirection direction : directions)
            this.move(direction);

        return this;
    }

    public boolean isAt(Vector2d other_position) {
        return this.position.equals(other_position);
    }

    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    public void onPositionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver observer : observers) {
            observer.positionChanged(oldPosition, newPosition);
        }
    }

}
