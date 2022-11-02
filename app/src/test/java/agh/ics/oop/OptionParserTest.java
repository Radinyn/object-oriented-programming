package agh.ics.oop;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class OptionParserTest {
    @Test
    public void Test_next() {
        MoveDirection[] input = OptionsParser.parse(new String[] {
            "forward",
            "backward",
            "backward",
            "f",
            "b",
            "r",
            """
                According to all known laws
                of aviation,
                there is no way a bee
                should be able to fly.
             """,
            "r",
            "r",
            "l",
            "l",
            "right",
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
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
}
