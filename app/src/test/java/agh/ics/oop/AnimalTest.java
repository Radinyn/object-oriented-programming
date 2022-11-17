package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
    @Test
    public void orientationTest() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(map);

        animal.move(MoveDirection.RIGHT);
        assertEquals("Animal(position: (2,2), direction: Wschód)", animal.describe());
        animal.move(MoveDirection.FORWARD);
        assertEquals("Animal(position: (3,2), direction: Wschód)", animal.describe());
        animal.move(MoveDirection.LEFT);
        assertNotEquals("Animal(position: (4,2), direction: Zachód)", animal.describe());
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.BACKWARD);
        assertNotEquals("Animal(position: (1,1), direction: Północ)", animal.describe());
    }

    @Test
    public void positionTest() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(map);

        animal.move(MoveDirection.LEFT);
        assertEquals("Animal(position: (2,2), direction: Zachód)", animal.describe());
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        assertEquals("Animal(position: (4,2), direction: Zachód)", animal.describe());
        animal.move(MoveDirection.BACKWARD);
        assertEquals("Animal(position: (4,2), direction: Zachód)", animal.describe());
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.BACKWARD);
        assertNotEquals("Animal(position: (4,4), direction: Wschód)", animal.describe());
    }

    @Test
    public void outsideTheMapTest() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(map);

        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        assertEquals("Animal(position: (2,4), direction: Północ)", animal.describe());
        animal.move(MoveDirection.RIGHT);
        assertEquals("Animal(position: (2,4), direction: Wschód)", animal.describe());
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        assertNotEquals("Animal(position: (2,3), direction: Północ)", animal.describe());
        animal.move(MoveDirection.BACKWARD);
        assertNotEquals("Animal(position: (2,4), direction: Północ)", animal.describe());
    }

}
