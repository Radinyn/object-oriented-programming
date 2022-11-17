package agh.ics.oop;

import java.util.*;

public class Utils {
    public static Random rng = new Random();

    public static void setSeed(int seed) {
        rng = new Random(seed);
    }

    public static int randint(int min, int max) {
        return (int) ((rng.nextDouble() * (max - min)) + min);
    }

    public static void shuffle(List<?> collection) {
        Collections.shuffle(collection, rng);
    }
}
