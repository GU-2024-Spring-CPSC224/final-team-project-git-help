package edu.gonzaga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    // attributes
    private ArrayList<Card> drawPile = new ArrayList<Card>();
    private ArrayList<Card> discardPile = new ArrayList<Card>();

    // methods
    /**
     * Shuffles the deck
     * @param pile an array of Card objects
     * @return void
     * 
     * @author Tony
     */
    private void shufflePile(ArrayList<Card> pile) {
        Collections.shuffle(pile);
    }

    /**
     * Constructor for the Deck class
     * @param cities an array of City objects
     * @return a Deck object
     * 
     * @author Tony
     */
    public Deck() {
        // add 48 basic cards
        initializeBasicCards();

        // add 5 event cards
        initializeEventCards();

        // add 6 epidemic cards
        for (int i = 0; i < 6; i++) {
            drawPile.add(new EpdemicCard());
        }
        
        // shuffle the deck
        shufflePile(drawPile);
    }

    /**
     * Initialize EventCards
     * @return a Deck object
     * 
     * @Author Tony
     */
    private void initializeEventCards() {
        // add 5 event cards
        List <String> eventNames = List.of("Airlift", "Government Grant", "One Quiet Night", "Resilient Population", "Forecast");

        for (String name : eventNames) {
            drawPile.add(new EventCard(name));
        }
    }

    private void initializeBasicCards() {
        // Should somehow find a way to connect to the actual City class
        // add 48 basic cards
        
    }

    /**
     * Returns the draw pile
     * @return the draw pile
     * 
     * @author Tony
     */
    public ArrayList<Card> getDrawPile() {
        return this.drawPile;
    }

    /**
     * Returns the discard pile
     * @return the discard pile
     * 
     * @author Tony
     */
    public ArrayList<Card> getDiscardPile() {
        return this.discardPile;
    }

    /**
     * Discards a card
     * @param card a Card object
     * @return void
     * 
     * @author Tony
     */
    public void discardCard(Card card) {
        discardPile.add(card);
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
     * 
     * @author Tony
     */
    public void putShuffledDiscardPileOnTop() {
        // Side note: Can we agree that the top of the card is the beginning of the list and the bottom is the end?
        shufflePile(discardPile); // NOTE: do we want to shuffle the discard pile before putting it on top?
        drawPile.addAll(discardPile);
        discardPile.clear();
    }
}
