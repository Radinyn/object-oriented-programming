package agh.ics.oop;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimulationTest {
    @Test
    public void mainTest() {
        MoveDirection[] directions = OptionsParser.parse("f b r l f f r r f f f f f f f f".split(" "));
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
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

        MoveDirection[] directions = OptionsParser.parse("f b r l f f r r f f f f f f f f".split(" "));
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        String output = engine.getLastFrame();

        assertEquals(output.strip(), """
            y\\x -2-1 0 1 2 3 4 5 6 7 8 9
            10: -------------------------
             9: | | |*| | | | | | | | | |
             8: | | | | | | |*| | | | | |
             7: | | | | | |N| | | | | | |
             6: | | | | | | | | |*| | |*|
             5: | | | | | | | | | | | | |
             4: | | | |*| | | | | | |*| |
             3: | | | | |*| | | | | | | |
             2: | | | | | | | |*| |*| | |
             1: | | |*| | | | | | | | | |
             0: | | | | | | | | | | | | |
            -1: | | | | | | | | | | | | |
            -2: | | | | | |S| | | | | | |
            -3: -------------------------
           """.strip());
    }
}


