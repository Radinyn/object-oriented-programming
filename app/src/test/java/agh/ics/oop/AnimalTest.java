package agh.ics.oop;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {

    @Test
    public void orientationTest() {

        Animal animal = new Animal();

        animal.move(MoveDirection.RIGHT);
        assertEquals("Animal(position: (2,2), direction: Wschód)", animal.toString());
        animal.move(MoveDirection.FORWARD);
        assertEquals("Animal(position: (3,2), direction: Wschód)", animal.toString());
        animal.move(MoveDirection.LEFT);
        assertNotEquals("Animal(position: (4,2), direction: Zachód)", animal.toString());
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.BACKWARD);
        assertNotEquals("Animal(position: (1,1), direction: Północ)", animal.toString());
    }

    @Test
    public void positionTest() {

        Animal animal = new Animal();

        animal.move(MoveDirection.LEFT);
        assertEquals("Animal(position: (2,2), direction: Zachód)", animal.toString());
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        assertEquals("Animal(position: (4,2), direction: Zachód)", animal.toString());
        animal.move(MoveDirection.BACKWARD);
        assertEquals("Animal(position: (4,2), direction: Zachód)", animal.toString());
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.BACKWARD);
        assertNotEquals("Animal(position: (4,4), direction: Wschód)", animal.toString());
    }

    Animal animalOutsideTheMap = new Animal();

    @Test
    public void outsideTheMapTest() {

        Animal animal = new Animal();

        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        assertEquals("Animal(position: (2,4), direction: Północ)", animal.toString());
        animal.move(MoveDirection.RIGHT);
        assertEquals("Animal(position: (2,4), direction: Wschód)", animal.toString());
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        assertNotEquals("Animal(position: (2,3), direction: Północ)", animal.toString());
        animal.move(MoveDirection.BACKWARD);
        assertNotEquals("Animal(position: (2,4), direction: Północ)", animal.toString());
    }

}
