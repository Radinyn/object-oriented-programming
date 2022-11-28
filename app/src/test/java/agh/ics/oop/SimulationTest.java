package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class SimulationTest {
    @Test
    public void mainTest() {
        MoveDirection[] directions = OptionsParser.parse(Arrays.asList("f b r l f f r r f f f f f f f f".split(" ")));
        AbstractWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2, 2), new Vector2d(3, 4) };
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        String output = engine.getLastFrame();

        assertEquals(output.strip(), """
                  y\\x  0 1 2 3 4 5 6 7 8 9
                  5: ---------------------
                  4: | | | |N| | | | | | |
                  3: | | | | | | | | | | |
                  2: | | | | | | | | | | |
                  1: | | | | | | | | | | |
                  0: | | |S| | | | | | | |
                 -1: ---------------------
                """.strip());
    }

    @Test
    public void grassTest() {
        Utils.setSeed(0xBADC0DE);

        MoveDirection[] directions = OptionsParser.parse(Arrays.asList("f b r l f f r r f f f f f f f f".split(" ")));
        AbstractWorldMap map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2, 2), new Vector2d(3, 4) };
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        String output = engine.getLastFrame();

        assertEquals(output.strip(), """
                y\\x -2-1 0 1 2 3 4 5 6 7 8 9
                 11: -------------------------
                 10: | | | | | | |*| | | | | |
                  9: | | | | | | | | | | | | |
                  8: | | | | | | | | |*| | |*|
                  7: | | | | | |N| | | | | | |
                  6: | | | | | | | | | | | |*|
                  5: | | | | | | | | | | | | |
                  4: | | | | | | | | |*| | | |
                  3: | | |*| | | | | | | | | |
                  2: | | | |*| | | |*| | | |*|
                  1: | | |*| | | | | | | | | |
                  0: | | | | | | | | | | | | |
                 -1: | | | | |S| | | | | | | |
                 -2: | | | | | | | | | | | | |
                 -3: -------------------------
                    """.strip());
    }

    @Test
    public void collisionTest() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            MoveDirection[] directions = OptionsParser
                    .parse(Arrays.asList("f b r l f f r r f f f f f f f f".split(" ")));
            AbstractWorldMap map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2, 2), new Vector2d(2, 2) };
            SimulationEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
        });

        assert exception.getMessage().contains("Animal cannot be placed at (2,2)");
    }
}
