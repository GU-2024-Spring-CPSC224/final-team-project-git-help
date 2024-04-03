package edu.gonzaga;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HandTest {
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
}
