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

    /**
     * Tests that the Hand for players is set up correctly (non-scientist)
     */

    /**
     * Tests that a player can move locations 
     */

    /**
     * Tests that a medic can get rid of a cube simply by moving to that city when that city's color is cured 
     */

    /**
     * Tests that a player can move between connected cities 
     */

    /**
      * Tests that a player can't move between cities that are not connected
     */

    /**
     * Tests that a player can go directly to an unconnected city when they have the card for that city 
     */

    /**
     * Tests that a player in a city with a research station can charter to an unconnected city with a research station
     */

    /**
     * Tests that a player in a city without a research station cannot charter to an unconnected city with a research station
     */

    /**
     * Tests that a player in a city with a research station cannot charter to an unconnected city without a research station
     */ 

    /**
     * Tests that a player can transfer a card if they are in that card's city 
     */

    /**
     * Tests that a Researcher can transfer a card if they are not in that card's city
     */

    /**
     * Tests that a player cannot transfer a card if they are not in that card's city
     */

    /**
     * Tests that a player cannot transfer a card if they are not in that card's city
     */

    /**
     * Tests that a player cannot transfer a card to another player that is not in that card's city
     */

    // TODO: implement above 3 tests for receiving knowledge as well

    /**
     * Tests that a player can remove a cube from the city they land on (not cured)
     */

    /**
     * Tests that a medic can remove all cubes from the city they land on (not cured)
     */
    
     /**
      * Tests that a player can remove all cubes from the city they land on (cured)
      */

    /**
     * Tests that a player can cure a disease if they have all 5 cards 
     */

    /**
     * Tests that a Scientist can cure a disease if they have all 4 cards 
     */

     /**
     * Tests that the Hand for players is set up correctly (scientist)
     */


    /**
     * Tests that Take Response #1 works 
     */

     /**
     * Tests that Take Response #2 works 
     */

    /**
     * Tests that Take Response #3 works 
     */

     /**
     * Tests that Take Response #4 works 
     */

     /**
     * Tests that Take Response #5 works 
     */

    /**
     * Tests that Take Response #6 works 
     */

     /**
     * Tests that Take Response #7 works 
     */

     /**
     * Tests that Take Response #8 works 
     */
}
