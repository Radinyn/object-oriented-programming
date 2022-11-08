package agh.ics.oop;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);
    private IWorldMap map;

    public Animal(IWorldMap map, Vector2d initial_position) {
        this.map = map;
        this.position = initial_position;
    }

    public Animal(IWorldMap map) {
        this.map = map;
        this.position = new Vector2d(2,2);
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }

    public String toString() {
        return switch(this.orientation) {
            case NORTH -> "N";
            case EAST  -> "E";
            case WEST  -> "W";
            case SOUTH -> "S";
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

                if (this.map.canMoveTo(newPosition))
                {
                    this.position = newPosition;
                    this.map.update(this);
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

}
