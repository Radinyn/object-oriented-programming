package agh.ics.oop;

import java.util.*;

public class SimulationEngine implements IEngine, Runnable {
    private Animal[] animals;
    private IWorldMap map;
    private MoveDirection[] directions;
    private int stepNumber;
    private List<IStepObserver> observers;

    public SimulationEngine(AbstractWorldMap map, Vector2d[] positions) {
        this(new MoveDirection[] {}, map, positions);
    }

    public SimulationEngine(MoveDirection[] directions, AbstractWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;
        this.stepNumber = 0;
        this.observers = new ArrayList<>();
        this.animals = new Animal[positions.length];
        for (int i = 0; i < positions.length; i++) {
            animals[i] = new Animal(map, positions[i]);
            map.place(animals[i]);
            animals[i].addObserver(map);
        }
    }

    public void run() {
        for (int i = 0; i < directions.length; i++)
            this.step();
    }

    public void step() {
        if (stepNumber >= directions.length)
            return;

        animals[stepNumber % animals.length].move(directions[stepNumber]);
        System.out.println(
                String.format("Animal %d, move: %s", stepNumber % animals.length, directions[stepNumber].toString()));
        System.out.println(map.toString());
        stepNumber++;

        for (var observer : observers)
            observer.onStep();
    }

    public String getLastFrame() {
        return map.toString();
    }

    public void addObserver(IStepObserver observer) {
        observers.add(observer);
    }

    public void setDirections(MoveDirection[] directions) {
        this.directions = directions.clone();
    }
}
