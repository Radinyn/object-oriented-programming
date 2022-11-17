package agh.ics.oop;

import java.util.*;

public class GrassField extends AbstractWorldMap {
    private Map<Vector2d, Grass> grass = new HashMap<Vector2d, Grass>();

    GrassField(int grassCount) {
        int limit = (int) Math.sqrt(grassCount * 10);

        List<Vector2d> positions = new ArrayList<>();

        for (int i = 0; i <= limit; i++)
            for (int j = 0; j <= limit; j++)
                positions.add(new Vector2d(i, j));

        Utils.shuffle(positions);

        for (int i = 0; i < grassCount; i++)
            grass.put(positions.get(i), new Grass(positions.get(i)));
    }

    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);

        int x_low = -2, y_low = -2;
        int x_high = 2, y_high = 2;

        for (Vector2d pos : this.animals.keySet()) {
            x_high = Math.max(x_high, pos.x);
            y_high = Math.max(y_high, pos.y);
            x_low = Math.min(x_low, pos.x);
            y_low = Math.min(y_low, pos.y);
        }

        for (Vector2d pos : this.grass.keySet()) {
            x_high = Math.max(x_high, pos.x);
            y_high = Math.max(y_high, pos.y);
            x_low = Math.min(x_low, pos.x);
            y_low = Math.min(y_low, pos.y);
        }

        return visualizer.draw(new Vector2d(x_low, y_low), new Vector2d(x_high, y_high));
    }

    public boolean canMoveTo(Vector2d position) {
        Object obj = this.objectAt(position);
        return obj == null || obj instanceof Grass;
    }

    public Object objectAt(Vector2d position) {
        if (animals.containsKey(position))
            return animals.get(position);

        if (grass.containsKey(position))
            return grass.get(position);

        return null;
    }
}
