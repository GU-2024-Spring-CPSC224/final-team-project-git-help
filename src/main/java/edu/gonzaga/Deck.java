package edu.gonzaga;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    // attributes
    private ArrayList<Card> drawPile = new ArrayList<Card>();
    private ArrayList<Card> discardPile = new ArrayList<Card>();

    // methods
    /**
     * Shuffles the deck
     * @author Tony
     */
    private void shufflePile(ArrayList<Card> pile) {
        Collections.shuffle(pile);
    }


    public Deck(City[] cities) {
        // add 48 basic cards
        for (int i = 0; i < 48; i++) {
            drawPile.add(new BasicCard(cities[i % 48]));
        }
        // add 5 event cards
        for (int i = 0; i < 5; i++) {
            drawPile.add(new EventCard());
        }
        // add 5 epidemic cards
        // do something
        
        // shuffle the deck
        shufflePile(drawPile);
    }

    /**
     * Returns the draw pile
     * @return the draw pile
     * @author Tony
     */
    public ArrayList<Card> getDrawPile() {
        return this.drawPile;
    }

    /**
     * Returns the discard pile
     * @return the discard pile
     * @author Tony
     */
    public ArrayList<Card> getDiscardPile() {
        return this.discardPile;
    }

    /**
     * Draws the first card from the deck
     * @return the first card from the deck
     * 
     * @author Tony
     */
    public Card drawCard() {
        // if the draw pile is empty, shuffle the discard pile and put it on top of the draw pile
        if (drawPile.isEmpty()) {
            putShuffledDiscardPileOnTop();
        }
        // draw the top card
        return drawPile.remove(drawPile.size() - 1);
        
    }

    /**
     * Draw the bottom card of the deck
     * @return the bottom card of the deck
     * 
     * @author Tony
     */
    public Card drawBottomCard() {
        // if the draw pile is empty, shuffle the discard pile and put it on top of the draw pile
        if (drawPile.isEmpty()) {
            putShuffledDiscardPileOnTop();
        }
        // draw the bottom card
        return drawPile.remove(0);
    }

     /**
     * Draw the bottom card of the deck
     * @author Tony
     */
    public void putShuffledDiscardPileOnTop() {
        // Side note: Can we agree that the top of the card is the beginning of the list and the bottom is the end?
        shufflePile(discardPile); // NOTE: do we want to shuffle the discard pile before putting it on top?
        drawPile.addAll(discardPile);
        discardPile.clear();
    }
}
