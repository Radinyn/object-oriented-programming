package agh.ics.oop;

import java.util.*;

public class MapBoundary implements IPositionChangeObserver {
    private SortedSet<Vector2d> xSet = new TreeSet<>(Comparator.comparingInt(p -> p.x));
    private SortedSet<Vector2d> ySet = new TreeSet<>(Comparator.comparingInt(p -> p.y));

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        this.removePosition(oldPosition);
        this.addPosition(newPosition);
    }

    public void addPosition(Vector2d position) {
        xSet.add(position);
        ySet.add(position);
    }

    public void removePosition(Vector2d position) {
        xSet.remove(position);
        ySet.remove(position);
    }

    public Vector2d getUpperRight() {
        return xSet.last().upperRight(ySet.last());
    }

    public Vector2d getLowerLeft() {
        return xSet.first().lowerLeft(ySet.first());
    }

    public Vector2d[] getBounds() {
        return new Vector2d[] { this.getLowerLeft(), this.getUpperRight() };
    }
}
