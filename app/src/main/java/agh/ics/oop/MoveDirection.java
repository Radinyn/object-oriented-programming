package agh.ics.oop;

public enum MoveDirection {

    FORWARD,
    BACKWARD,
    RIGHT,
    LEFT;

    public static String toText(MoveDirection dir) {
        return switch (dir) {
            case FORWARD -> "Zwierzak idzie do przodu";
            case BACKWARD -> "Zwierzak idzie do tyłu";
            case RIGHT -> "Zwierzak idzie w prawo";
            case LEFT -> "Zwierzak idzie w lewo";
            default -> "Nieznana komenda";
        };
    }
}