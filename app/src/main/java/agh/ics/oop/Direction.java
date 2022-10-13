package agh.ics.oop;

public enum Direction {

    FORWARD,
    BACKWARD,
    RIGHT,
    LEFT,
    INVALID;

    public static Direction convertStringToDirection(String dir) {
        return switch (dir) {
            case "f" -> FORWARD;
            case "b" -> BACKWARD;
            case "r" -> RIGHT;
            case "l" -> LEFT;
            default  -> INVALID;
        };
    }

    public static String toText(Direction dir) {
        return switch(dir) {
            case FORWARD  -> "Zwierzak idzie do przodu";
            case BACKWARD -> "Zwierzak idzie do tyłu";
            case RIGHT    -> "Zwierzak idzie w prawo";
            case LEFT     -> "Zwierzak idzie w lewo";
            default       -> "Nieznana komenda";
        };
    }
}
