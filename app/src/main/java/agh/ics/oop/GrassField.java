package agh.ics.oop;
import java.util.*;

public class GrassField extends AbstractWorldMap {
    private List<Grass> grass = new ArrayList<Grass>();

    GrassField(int grassCount) {
        int limit = (int) Math.sqrt(grassCount*10);
        
        for (int i = 0; i < grassCount; i++)
        {
            int x = Utils.randint(0, limit);
            int y = Utils.randint(0, limit);

            grass.add(new Grass(new Vector2d(x, y)));
        }
    }

    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);

        int x_low = -2, y_low = -2;
        int x_high = 2, y_high = 2;

        for (Animal animal : this.animals)
        {
            Vector2d pos = animal.getPosition();
            x_high = Math.max(x_high, pos.x);
            y_high = Math.max(y_high, pos.y);
            x_low = Math.min(x_low, pos.x);
            y_low = Math.min(y_low, pos.y);
        }

        for (Grass g : this.grass)
        {
            Vector2d pos = g.getPosition();
            x_high = Math.max(x_high, pos.x);
            y_high = Math.max(y_high, pos.y);
            x_low = Math.min(x_low, pos.x);
            y_low = Math.min(y_low, pos.y);
        }


        return visualizer.draw(new Vector2d(x_low, y_low), new Vector2d(x_high, y_high));
    }

    public boolean canMoveTo(Vector2d position) {
        return !this.isOccupied(position);
    }

    public boolean place(Animal animal) {
        Vector2d pos = animal.getPosition();

        if (this.isOccupied(pos))
            return false;


        animals.add(animal);
        return true;
    }

    public Object objectAt(Vector2d position) {

        for (Animal animal : this.animals)
        {
            if (animal.getPosition().equals(position))
                return animal;
        }

        for (Grass g : this.grass)
        {
            if (g.getPosition().equals(position))
                return g;
        }

        return null;
    }
}
