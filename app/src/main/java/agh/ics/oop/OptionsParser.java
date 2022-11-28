package agh.ics.oop;

import java.util.List;

public class OptionsParser {
    public static MoveDirection[] parse(List<String> input) {
        return input.stream()
                .map(dir -> OptionsParser.dirToString(dir))
                .toArray(MoveDirection[]::new);
    }

    private static MoveDirection dirToString(String dir) {
        return switch (dir) {
            case "f", "forward" -> MoveDirection.FORWARD;
            case "b", "backward" -> MoveDirection.BACKWARD;
            case "r", "right" -> MoveDirection.RIGHT;
            case "l", "left" -> MoveDirection.LEFT;
            default -> throw new IllegalArgumentException(dir + " is an invalid MoveDirection string.");
        };
    }
}
