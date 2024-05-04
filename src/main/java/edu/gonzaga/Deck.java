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
     * Constructor for a deck of purely city (a.k.a basic) cards - Infection deck uses this constructor
     * @param cityList an array of City objects
     * @return a Deck object
     * 
     * @author Tony
     * @author Aiden T
     */
    public Deck(ArrayList<City> cityList) {
        // add 48 basic cards
        initializeBasicCards(cityList);
        
        // shuffle the deck
        shufflePile(drawPile);
    }

    /**
     * Constructor for a deck of player cards - adds in event and epidemic cards
     * @param cityList - An ArrayList of all cities
     * @param epidemicCount - The number of epidemic cards to add to the deck
     * 
     * @author Tony
     * @author Aiden T
     */
    public Deck(ArrayList<City> cityList, Integer epidemicCount) {
        // add cards to player deck
        initializeBasicCards(cityList);

        // adds epidemic cards to the discard pile so players can draw first, then add this back to the main deck and
        for (int i = 0; i < epidemicCount; i++) {
            discardPile.add(new EpidemicCard());
            // initializeEventCards(); // comment out since there is no code to handle this yet
        }
        
        // shuffle the deck
        shufflePile(drawPile);
    }

    /**
     * After startup, epidemic cards of the player deck are in the discard pile. This shuffles them back into the player deck.
     * 
     * @author Aiden T
     */
    public void shuffleEpidemicCardsIn() {
        // Insert epidemic cards back into the player deck
        this.putShuffledDiscardPileOnTop();
        this.shufflePile(this.drawPile);
    }

    /**
     * Initialize EventCards. Note that the event cards are not shuffled into the deck until the game starts.
     * @return a Deck object
     * 
     * @Author Tony
     */
    private void initializeEventCards() {
        // add 5 event cards
        List <String> eventNames = List.of("Airlift", "Government Grant", "One Quiet Night", "Resilient Population", "Forecast");

        for (String name : eventNames) {
            discardPile.add(new EventCard(name));
        }
    }

    /**
     * Initializes the city cards based off of what cities are passed in and adds them to the draw pile
     * @param cityList
     * @author Izzy T
     */
    private void initializeBasicCards(ArrayList<City> cityList) {
        for (City city : cityList) {
            drawPile.add(new BasicCard(city));
        }
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
     * Adds a card to the draw pile
     * @param card a Card object
     * @return void
     * 
     * @Author Tony
     */
    public void addCardToDrawPile(Card card) {
        drawPile.add(card);
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
        // if the draw pile is empty, game ends
        // if (drawPile.isEmpty()) {
            //TODO: when Game class is created, make this an end-game case
        // }
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
        // if the draw pile is empty, game ends
        // if (drawPile.isEmpty()) {
            //TODO: when Game class is created, make this an end-game case
        // }
        // draw the bottom card
        return drawPile.remove(0);
    }

    /**
     * Draw the bottom card of the deck
     * 
     * @author Tony
     */
    public void putShuffledDiscardPileOnTop() {
        shufflePile(discardPile); 
        drawPile.addAll(discardPile);
        discardPile.clear();
    }
}
