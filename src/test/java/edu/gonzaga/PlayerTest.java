package edu.gonzaga;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//TODO: Needs gameboard to test most if not all functions

public class PlayerTest {
    /**
     * Tests that a player can be assigned a role
     * 
     * @author Aiden T
     */
    @Test
    void playerConstructor1() {
        String desiredValue = "Medic";

        City starterCity = new City(Color.BLUE, "Atlanta");
        Player testPlayer = new Player("Medic", 0, starterCity);

        Assertions.assertEquals(desiredValue, testPlayer.getRole());
    }

    /**
     * Tests that a player is defaulted with a name
     * 
     * @author Aiden T
     */
    @Test
    void playerConstructor2() {
        String desiredValue = "Anonymous Player";

        City starterCity = new City(Color.BLUE, "Atlanta");
        Player testPlayer = new Player("Medic", 0, starterCity);

        Assertions.assertEquals(desiredValue, testPlayer.getName());
    }
}
