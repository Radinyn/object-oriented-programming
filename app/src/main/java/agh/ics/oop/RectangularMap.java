package agh.ics.oop;

public class RectangularMap extends AbstractWorldMap {
    private int width;
    private int height;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(new Vector2d(0, 0), new Vector2d(width - 1, height - 1));
    }

    public boolean canMoveTo(Vector2d position) {
        if (position.in_bound(new Vector2d(0, 0), new Vector2d(width - 1, height - 1)))
            return !this.isOccupied(position);
        return false;
    }

    public Object objectAt(Vector2d position) {
        if (animals.containsKey(position))
            return animals.get(position);
        return null;
    }
}
