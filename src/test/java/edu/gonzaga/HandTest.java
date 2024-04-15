package edu.gonzaga;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HandTest {
    /**
     * Tests if Hand.checkCanCure() will recognize a hand of 5 (minimum) cards of one color in order to cure that color
     * @author Izzy T
     */
    @Test
    void testCheckCanCure() {
        City newCity1 = new City(Color.YELLOW, "Madrid");
        BasicCard cityCard1 = new BasicCard(newCity1);

        City newCity2 = new City(Color.YELLOW, "Portugal");
        BasicCard cityCard2 = new BasicCard(newCity2);

        City newCity3 = new City(Color.YELLOW, "San Francisco");
        BasicCard cityCard3 = new BasicCard(newCity3);

        City newCity4 = new City(Color.YELLOW, "San Diego");
        BasicCard cityCard4 = new BasicCard(newCity4);

        City newCity5 = new City(Color.YELLOW, "Spokane");
        BasicCard cityCard5 = new BasicCard(newCity5);

        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.add(cityCard1);
        cardList.add(cityCard2);
        cardList.add(cityCard3);
        cardList.add(cityCard4);
        cardList.add(cityCard5);

        Hand newHand = new Hand();
        newHand.setCardList(cardList);

        boolean canCure = newHand.checkCanCure(Color.YELLOW);

        assertEquals(true, canCure);
    }

    /**
     * Tests if Hand.checkCanCure() will recognize a hand of 4 of one color and 1 of another color as a hand that does NOT cure the first color
     * @author Izzy T
     */
    @Test
    void testCheckCanCureFail() {
        City newCity1 = new City(Color.BLACK, "Madrid");
        BasicCard cityCard1 = new BasicCard(newCity1);

        City newCity2 = new City(Color.YELLOW, "Portugal");
        BasicCard cityCard2 = new BasicCard(newCity2);

        City newCity3 = new City(Color.YELLOW, "San Francisco");
        BasicCard cityCard3 = new BasicCard(newCity3);

        City newCity4 = new City(Color.YELLOW, "San Diego");
        BasicCard cityCard4 = new BasicCard(newCity4);

        City newCity5 = new City(Color.YELLOW, "Spokane");
        BasicCard cityCard5 = new BasicCard(newCity5);

        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.add(cityCard1);
        cardList.add(cityCard2);
        cardList.add(cityCard3);
        cardList.add(cityCard4);
        cardList.add(cityCard5);

        Hand newHand = new Hand();
        newHand.setCardList(cardList);

        boolean canCure = newHand.checkCanCure(Color.YELLOW);

        assertEquals(false, canCure);
    }

    /**
     * Tests if Hand.checkCanCure() will recognize a hand of 5 (minimum) cards of one color and one of another color, in order to see that it will cure the first color and disreagrd the second color
     * @author Izzy T
     */
    @Test
    void testCheckCanCure2() {
        City newCity1 = new City(Color.BLACK, "Madrid");
        BasicCard cityCard1 = new BasicCard(newCity1);

        City newCity2 = new City(Color.YELLOW, "Portugal");
        BasicCard cityCard2 = new BasicCard(newCity2);

        City newCity3 = new City(Color.YELLOW, "San Francisco");
        BasicCard cityCard3 = new BasicCard(newCity3);

        City newCity4 = new City(Color.YELLOW, "San Diego");
        BasicCard cityCard4 = new BasicCard(newCity4);

        City newCity5 = new City(Color.YELLOW, "Spokane");
        BasicCard cityCard5 = new BasicCard(newCity5);

        City newCity6 = new City(Color.YELLOW, "Seattle");
        BasicCard cityCard6 = new BasicCard(newCity6);

        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.add(cityCard1);
        cardList.add(cityCard2);
        cardList.add(cityCard3);
        cardList.add(cityCard4);
        cardList.add(cityCard5);
        cardList.add(cityCard6);

        Hand newHand = new Hand();
        newHand.setCardList(cardList);

        boolean canCure = newHand.checkCanCure(Color.YELLOW);

        assertEquals(true, canCure);
    }

    /**
     * Tests if Hand.discard works 
     * @author Izzy T
     */
    @Test
    void testDiscard() {
        // code to create a robust card list:
        City newCity1 = new City(Color.BLACK, "Madrid");
        BasicCard cityCard1 = new BasicCard(newCity1);

        City newCity2 = new City(Color.YELLOW, "Portugal");
        BasicCard cityCard2 = new BasicCard(newCity2);

        City newCity3 = new City(Color.YELLOW, "San Francisco");
        BasicCard cityCard3 = new BasicCard(newCity3);

        City newCity4 = new City(Color.YELLOW, "San Diego");
        BasicCard cityCard4 = new BasicCard(newCity4);

        City newCity5 = new City(Color.YELLOW, "Spokane");
        BasicCard cityCard5 = new BasicCard(newCity5);

        City newCity6 = new City(Color.YELLOW, "Seattle");
        BasicCard cityCard6 = new BasicCard(newCity6);

        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.add(cityCard1);
        cardList.add(cityCard2);
        cardList.add(cityCard3);
        cardList.add(cityCard4);
        cardList.add(cityCard5);
        cardList.add(cityCard6);

        Hand newHand = new Hand();
        newHand.setCardList(cardList);

        newHand.discard(cityCard5);

        ArrayList<Card> expectedList = new ArrayList<Card>();
        expectedList.add(cityCard1);
        expectedList.add(cityCard2);
        expectedList.add(cityCard3);
        expectedList.add(cityCard4);
        expectedList.add(cityCard6);

        assertEquals(expectedList, cardList);
    }

    /**
     * Tests if Hand.discard works when the card inputted doesn't exist  
     * @author Izzy T
    */
    @Test
    void testDiscard2() {
        // code to create a robust card list:
        City newCity1 = new City(Color.BLACK, "Madrid");
        BasicCard cityCard1 = new BasicCard(newCity1);

        City newCity2 = new City(Color.YELLOW, "Portugal");
        BasicCard cityCard2 = new BasicCard(newCity2);

        City newCity3 = new City(Color.YELLOW, "San Francisco");
        BasicCard cityCard3 = new BasicCard(newCity3);

        City newCity4 = new City(Color.YELLOW, "San Diego");
        BasicCard cityCard4 = new BasicCard(newCity4);

        City newCity5 = new City(Color.YELLOW, "Spokane");
        BasicCard cityCard5 = new BasicCard(newCity5);

        City newCity6 = new City(Color.YELLOW, "Seattle");
        BasicCard cityCard6 = new BasicCard(newCity6);

        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.add(cityCard1);
        cardList.add(cityCard2);
        cardList.add(cityCard3);
        cardList.add(cityCard4);
        cardList.add(cityCard5);
        // don't add cityCard6

        Hand newHand = new Hand();
        newHand.setCardList(cardList);

        newHand.discard(cityCard6);

        ArrayList<Card> expectedList = new ArrayList<Card>();
        expectedList.add(cityCard1);
        expectedList.add(cityCard2);
        expectedList.add(cityCard3);
        expectedList.add(cityCard4);
        expectedList.add(cityCard5);

        assertEquals(expectedList, cardList);
    }

    /**
     * Tests if makeHandValid() will correctly have user discard cards until card limit is obeyed (tested with one card)
     * @author Izzy T
     */
    @Test
    void testMakeHandValid() {
        // code to create a robust card list:
        City newCity1 = new City(Color.BLACK, "Madrid");
        BasicCard cityCard1 = new BasicCard(newCity1);

        City newCity2 = new City(Color.YELLOW, "Portugal");
        BasicCard cityCard2 = new BasicCard(newCity2);

        City newCity3 = new City(Color.YELLOW, "San Francisco");
        BasicCard cityCard3 = new BasicCard(newCity3);

        City newCity4 = new City(Color.YELLOW, "San Diego");
        BasicCard cityCard4 = new BasicCard(newCity4);

        City newCity5 = new City(Color.YELLOW, "Spokane");
        BasicCard cityCard5 = new BasicCard(newCity5);

        City newCity6 = new City(Color.YELLOW, "Seattle");
        BasicCard cityCard6 = new BasicCard(newCity6);

        City newCity7 = new City(Color.BLACK, "Seattle");
        BasicCard cityCard7 = new BasicCard(newCity7);

        City newCity8 = new City(Color.BLUE, "Seattle");
        BasicCard cityCard8 = new BasicCard(newCity8);

        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.add(cityCard1);
        cardList.add(cityCard2);
        cardList.add(cityCard3);
        cardList.add(cityCard4);
        cardList.add(cityCard5);
        cardList.add(cityCard6);
        cardList.add(cityCard7);
        cardList.add(cityCard8);

        Hand newHand = new Hand();
        newHand.setCardList(cardList);

        String inputData = "2"; // index 2
        
        // comvert string data to an InputStream for Scanner
        InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
        System.setIn(inputStream);
        Scanner newScanner = new Scanner(System.in);

        newHand.makeHandValid(newScanner);

        ArrayList<Card> expectedList = new ArrayList<Card>();
        expectedList.add(cityCard1);
        expectedList.add(cityCard2);
        // removed card3, in index 2
        expectedList.add(cityCard4);
        expectedList.add(cityCard5);
        expectedList.add(cityCard6);
        expectedList.add(cityCard7);
        expectedList.add(cityCard8);

        assertEquals(expectedList, newHand.getCardList());
    }

    /**
     * Tests if addCard works
     * @author Izzy T
     */
    @Test
    void testAddCard() {
        // code to create a robust card list:
        City newCity1 = new City(Color.BLACK, "Madrid");
        BasicCard cityCard1 = new BasicCard(newCity1);

        City newCity2 = new City(Color.YELLOW, "Portugal");
        BasicCard cityCard2 = new BasicCard(newCity2);

        City newCity3 = new City(Color.YELLOW, "San Francisco");
        BasicCard cityCard3 = new BasicCard(newCity3);

        City newCity4 = new City(Color.YELLOW, "San Diego");
        BasicCard cityCard4 = new BasicCard(newCity4);

        City newCity5 = new City(Color.YELLOW, "Spokane");
        BasicCard cityCard5 = new BasicCard(newCity5);

        City newCity6 = new City(Color.YELLOW, "Seattle");
        BasicCard cityCard6 = new BasicCard(newCity6);

        City newCity7 = new City(Color.BLUE, "Kyoto");
        BasicCard cityCard7 = new BasicCard(newCity7);

        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.add(cityCard1);
        cardList.add(cityCard2);
        cardList.add(cityCard3);
        cardList.add(cityCard4);
        cardList.add(cityCard5);
        cardList.add(cityCard6);

        Hand newHand = new Hand();
        newHand.setCardList(cardList);

        newHand.addCard(cityCard7);

        ArrayList<Card> expectedList = new ArrayList<Card>();
        expectedList.add(cityCard1);
        expectedList.add(cityCard2);
        expectedList.add(cityCard3);
        expectedList.add(cityCard4);
        expectedList.add(cityCard5);
        expectedList.add(cityCard6);
        expectedList.add(cityCard7);

        assertEquals(expectedList, cardList);
    }

    /**
     * Tests if getBasicCardsInHand works
     * @author Izzy T
     */
    @Test
    void testGetBasicCard() {
        // code to create a robust card list:
        City newCity1 = new City(Color.BLACK, "Madrid");
        BasicCard cityCard1 = new BasicCard(newCity1);

        City newCity2 = new City(Color.YELLOW, "Portugal");
        BasicCard cityCard2 = new BasicCard(newCity2);

        City newCity3 = new City(Color.YELLOW, "San Francisco");
        BasicCard cityCard3 = new BasicCard(newCity3);

        City newCity4 = new City(Color.YELLOW, "San Diego");
        BasicCard cityCard4 = new BasicCard(newCity4);

        City newCity5 = new City(Color.YELLOW, "Spokane");
        BasicCard cityCard5 = new BasicCard(newCity5);

        City newCity6 = new City(Color.YELLOW, "Seattle");
        BasicCard cityCard6 = new BasicCard(newCity6);

        EventCard eventCard1 = new EventCard("Do Stuff");

        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.add(cityCard1);
        cardList.add(cityCard2);
        cardList.add(cityCard3);
        cardList.add(cityCard4);
        cardList.add(cityCard5);
        cardList.add(cityCard6);
        cardList.add(eventCard1);


        Hand newHand = new Hand();
        newHand.setCardList(cardList);

        ArrayList<BasicCard> newHandCards = newHand.getBasicCardsInHand(); // should remove eventCard1 from cardList
        
        ArrayList<Card> expectedList = new ArrayList<Card>();
        expectedList.add(cityCard1);
        expectedList.add(cityCard2);
        expectedList.add(cityCard3);
        expectedList.add(cityCard4);
        expectedList.add(cityCard5);
        expectedList.add(cityCard6);

        assertEquals(expectedList, newHandCards);
    }

    /**
     * Tests if searchHandForCity works when the card is there
     * @author Izzy T
     */
    @Test
    void testSearchHandForCity() {
        // code to create a robust card list:
        City newCity1 = new City(Color.BLACK, "Madrid");
        BasicCard cityCard1 = new BasicCard(newCity1);

        City newCity2 = new City(Color.YELLOW, "Portugal");
        BasicCard cityCard2 = new BasicCard(newCity2);

        City newCity3 = new City(Color.YELLOW, "San Francisco");
        BasicCard cityCard3 = new BasicCard(newCity3);

        City newCity4 = new City(Color.YELLOW, "San Diego");
        BasicCard cityCard4 = new BasicCard(newCity4);

        City newCity5 = new City(Color.YELLOW, "Spokane");
        BasicCard cityCard5 = new BasicCard(newCity5);

        City newCity6 = new City(Color.YELLOW, "Seattle");
        BasicCard cityCard6 = new BasicCard(newCity6);

        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.add(cityCard1);
        cardList.add(cityCard2);
        cardList.add(cityCard3);
        cardList.add(cityCard4);
        cardList.add(cityCard5);
        cardList.add(cityCard6);

        Hand newHand = new Hand();
        newHand.setCardList(cardList);

        BasicCard foundCard = newHand.searchHandForCity(newCity3);

        assertEquals(foundCard, cityCard3);
    }

        /**
     * Tests if searchHandForCity works when the card is not there
     * @author Izzy T
     */
    @Test
    void testSearchHandForCityFail() {
        // code to create a robust card list:
        City newCity1 = new City(Color.BLACK, "Madrid");
        BasicCard cityCard1 = new BasicCard(newCity1);

        City newCity2 = new City(Color.YELLOW, "Portugal");
        BasicCard cityCard2 = new BasicCard(newCity2);

        City newCity3 = new City(Color.YELLOW, "San Francisco");
        BasicCard cityCard3 = new BasicCard(newCity3);

        City newCity4 = new City(Color.YELLOW, "San Diego");
        BasicCard cityCard4 = new BasicCard(newCity4);

        City newCity5 = new City(Color.YELLOW, "Spokane");
        BasicCard cityCard5 = new BasicCard(newCity5);

        City newCity6 = new City(Color.YELLOW, "Seattle");
        BasicCard cityCard6 = new BasicCard(newCity6);

        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.add(cityCard1);
        cardList.add(cityCard2);
        //did not add cityCard3
        cardList.add(cityCard4);
        cardList.add(cityCard5);
        cardList.add(cityCard6);

        Hand newHand = new Hand();
        newHand.setCardList(cardList);

        BasicCard foundCard = newHand.searchHandForCity(newCity3);

        assertEquals(null, foundCard);
    }
}


