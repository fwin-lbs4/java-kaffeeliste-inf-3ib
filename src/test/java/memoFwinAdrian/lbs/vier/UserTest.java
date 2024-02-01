package memoFwinAdrian.lbs.vier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void getSchuldenString() {
        User userA = new User("A", 100, 0, "User", 0);
        User userB = new User("B", 150, 1, "User", 0);
        User userC = new User("C", -100, 2, "User", 0);
        User userD = new User("D", -150, 3, "User", 0);

        assertAll(
                "Schulden string",
                () -> assertEquals("Schulden: € 1,--", userA.getSchuldenString()),
                () -> assertEquals("Schulden: € 1,50", userB.getSchuldenString()),
                () -> assertEquals("Guthaben: € 1,--", userC.getSchuldenString()),
                () -> assertEquals("Guthaben: € 1,50", userD.getSchuldenString())
        );
    }
}