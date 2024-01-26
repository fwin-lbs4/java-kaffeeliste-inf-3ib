package memoFwinAdrian.lbs.vier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
class DatenbankTest {

    @Test
    void getUserInfos() {
        int userId = 1;

        Datenbank datenbank = new Datenbank();
        User user = datenbank.getUserInfos(userId);

        assertNotNull(user);
        assertEquals(userId, user.getIdUser());
    }

    @Test
    void getAllUserInfos() {
        Datenbank datenbank = new Datenbank();
        List<User> users = datenbank.getAllUserInfos();

        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

    @Test
    void getAllCoffees() {
        Datenbank datenbank = new Datenbank();
        List<Coffee> coffees = datenbank.getAllCoffees();

        assertNotNull(coffees);
        assertTrue(coffees.size() > 0);
    }

    @Test
    void addSchulden() {
        int userId = 1;
        Datenbank datenbank = new Datenbank();
        User user = datenbank.getUserInfos(userId);

        int alteSchulden = user.getSchulden();

        int schuldenToAdd = 50;
        datenbank.addSchulden(user, schuldenToAdd);

        int neueSchulden = user.getSchulden();

        assertEquals(alteSchulden + schuldenToAdd, neueSchulden);
    }
}
