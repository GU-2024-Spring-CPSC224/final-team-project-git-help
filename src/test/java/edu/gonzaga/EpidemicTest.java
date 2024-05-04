package edu.gonzaga;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class EpidemicTest {
    @Test
    /**
     * Tests that the epidemic card is removed from the draw pile
     * 
     * @Author Tony
     */
    public void testRemoveEpidemicCard() {        
        City newCity1 = new City(Color.BLACK, "Madrid");
        City newCity2 = new City(Color.YELLOW, "Portugal");
        City newCity3 = new City(Color.YELLOW, "San Francisco");
        City newCity4 = new City(Color.YELLOW, "San Diego");
        City newCity5 = new City(Color.YELLOW, "Spokane");

        ArrayList<City> cityList = new ArrayList<City>();
        cityList.add(newCity1);
        cityList.add(newCity2);
        cityList.add(newCity3);
        cityList.add(newCity4);
        cityList.add(newCity5);

        Deck currentDeck = new Deck(cityList, 1);
        currentDeck.addCardToDrawPile(new EpidemicCard());

        // Simulate drawing the epidemic card
        Card drawnCard = currentDeck.drawCard(); // should be the epidemic card
        currentDeck.putShuffledDiscardPileOnTop();

        assertFalse(currentDeck.getDrawPile().contains(drawnCard));
    }
}
