package agh.ics.oop;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class OptionParserTest {
        @Test
        public void Test_parsing() {
                MoveDirection[] input = OptionsParser.parse(new String[] {
                                "forward",
                                "backward",
                                "backward",
                                "f",
                                "b",
                                "r",
                                "r",
                                "r",
                                "l",
                                "l",
                                "right",
                                "left",
                                "left",
                });

                MoveDirection[] expected = new MoveDirection[] {
                                MoveDirection.FORWARD,
                                MoveDirection.BACKWARD,
                                MoveDirection.BACKWARD,
                                MoveDirection.FORWARD,
                                MoveDirection.BACKWARD,
                                MoveDirection.RIGHT,
                                MoveDirection.RIGHT,
                                MoveDirection.RIGHT,
                                MoveDirection.LEFT,
                                MoveDirection.LEFT,
                                MoveDirection.RIGHT,
                                MoveDirection.LEFT,
                                MoveDirection.LEFT,
                };
                assert Arrays.equals(input, expected);

        }

        @Test
        public void Test_exceptions() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                        OptionsParser.parse(new String[] { "a", "b" });
                });

                assert exception.getMessage().contains("a is an invalid MoveDirection string.");
        }
}
