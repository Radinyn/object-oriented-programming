package agh.ics.oop;

public class SimulationEngine implements IEngine {
    private Animal[] animals;
    private IWorldMap map;
    private MoveDirection[] directions;

    public SimulationEngine(MoveDirection[] directions, AbstractWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;
        this.animals = new Animal[positions.length];
        for (int i = 0; i < positions.length; i++) {
            animals[i] = new Animal(map, positions[i]);
            map.place(animals[i]);
            animals[i].addObserver(map);
        }
    }

    public void run() {
        for (int i = 0; i < directions.length; i++) {
            animals[i % animals.length].move(directions[i]);
            System.out.println(String.format("Animal %d, move: %s", i % animals.length, directions[i].toString()));
            System.out.println(map.toString());
        }
    }

    public String getLastFrame() {
        return map.toString();
    }
}
