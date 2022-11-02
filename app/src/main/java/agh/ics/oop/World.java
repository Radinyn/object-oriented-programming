package agh.ics.oop;
import java.util.stream.*;

public class World {
    public static void main(String[] args) {
        System.out.println("--- START ---");
        Stream.of(args)
            .filter(dir -> dir.matches("[fblr]"))
            .map(dir -> MoveDirection.fromString(dir))
            .map(dir -> MoveDirection.toText(dir))
            .forEach(dir -> System.out.println(dir));
        System.out.println("--- STOP ---");

        // Vector2d position1 = new Vector2d(1,2);
        // System.out.println(position1);
        // Vector2d position2 = new Vector2d(-2,1);
        // System.out.println(position2);
        // System.out.println(position1.add(position2));

        System.out.println(
            MapDirection.EAST
                .next()
                .next()
                .previous()
                .toUnitVector()
                .toString()
        );
    }
}
