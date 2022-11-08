package agh.ics.oop;
import java.util.Arrays;

public class RectangularMap implements IWorldMap {

    private int width;
    private int height;
    private Object[][] map;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new Object[height][width];

        for (int i = 0; i < height; i++)
            Arrays.fill(this.map[i], null);
    }

    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(new Vector2d(0, 0), new Vector2d(width-1, height-1));
    }

    public boolean canMoveTo(Vector2d position) {
        if (position.in_bound(new Vector2d(0, 0), new Vector2d(width-1, height-1)))
            return !this.isOccupied(position);
        return false;
    }

    public boolean place(Animal animal) {
        Vector2d pos = animal.getPosition();

        if (this.isOccupied(pos))
            return false;

        map[pos.y][pos.x] = animal;
        return true;
    }

    public boolean isOccupied(Vector2d position) {
        return this.objectAt(position) != null;
    }


    public Object objectAt(Vector2d position) {
        return map[position.y][position.x];
    }

    public void update(Animal animal) {
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                if (this.map[i][j] == animal)
                {
                    this.map[i][j] = null;
                    Vector2d v = animal.getPosition();
                    this.map[v.y][v.x] = animal;
                    return;
                }
            }
        }
    }
}
