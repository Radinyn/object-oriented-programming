package agh.ics.oop;
import java.util.stream.*;

public class World {
    public static void main(String[] args) {
        System.out.println("--- START ---");
        Stream.of(args)
            .map(dir -> Direction.convertStringToDirection(dir))
            .map(dir -> Direction.toText(dir))
            .forEach(dir -> System.out.println(dir));
        System.out.println("--- STOP ---");
    }
}
