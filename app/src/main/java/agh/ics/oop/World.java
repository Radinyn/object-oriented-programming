package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        Animal animal = new Animal();
        System.out.println("--- START ---");

        MoveDirection[] parsed = OptionsParser.parse(args);
        for (MoveDirection direction : parsed)
        {
            animal.move(direction);
            System.out.println(animal.toString());
        }
        
        System.out.println("--- STOP ---");
    }
}
