package memoFwinAdrian.lbs.vier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CoffeeTest {

    @Test
    void testToString() {
        Coffee coffeeA = new Coffee(0, "A", 100);
        Coffee coffeeB = new Coffee(1, "B", 150);
        Coffee coffeeC = new Coffee(2, "C", 50);

        assertAll(
                "Schulden string",
                () -> assertEquals("A € 1,--", coffeeA.toString()),
                () -> assertEquals("B € 1,50", coffeeB.toString()),
                () -> assertEquals("C € 0,50", coffeeC.toString())
        );
    }
}