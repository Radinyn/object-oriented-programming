package agh.ics.oop;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }

    public String toString() {
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

                if (newPosition.in_bound(new Vector2d(0, 0), new Vector2d(4, 4)))
                    this.position = newPosition;
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
