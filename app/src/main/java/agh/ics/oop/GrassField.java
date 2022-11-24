package agh.ics.oop;

import java.util.*;

public class GrassField extends AbstractWorldMap {
    private Map<Vector2d, Grass> grass = new HashMap<Vector2d, Grass>();

    public GrassField(int grassCount) {
        int limit = (int) Math.sqrt(grassCount * 10);

        List<Vector2d> positions = new ArrayList<>();

        for (int i = 0; i <= limit; i++)
            for (int j = 0; j <= limit; j++)
                positions.add(new Vector2d(i, j));

        Utils.shuffle(positions);

        for (int i = 0; i < grassCount; i++) {
            grass.put(positions.get(i), new Grass(positions.get(i)));
            this.mapBoundary.addPosition(positions.get(i));
        }
    }

    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        Vector2d[] bounds = this.mapBoundary.getBounds();
        return visualizer.draw(bounds[0], bounds[1]);
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
