package agh.ics.oop;

import java.util.stream.*;

public class OptionsParser {
    public static MoveDirection[] parse(String[] input) {
        return Stream.of(input)
                .filter(dir -> dir.matches("(f)|(forward)|(b)|(backward)|(l)|(left)|(r)|(right)"))
                .map(dir -> OptionsParser.dirToString(dir))
                .toArray(MoveDirection[]::new);
    }

    private static MoveDirection dirToString(String dir) {
        return switch (dir) {
            case "f", "forward" -> MoveDirection.FORWARD;
            case "b", "backward" -> MoveDirection.BACKWARD;
            case "r", "right" -> MoveDirection.RIGHT;
            case "l", "left" -> MoveDirection.LEFT;
            default -> throw new IllegalArgumentException("Invalid MoveDirection string.");
        };
    }
}
