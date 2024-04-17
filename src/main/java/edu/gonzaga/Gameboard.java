package edu.gonzaga;

import java.util.ArrayList;

public class Gameboard {
    
    private ArrayList<Cure> cureList;
    private Deck playerDeck;
    private Deck infectionDeck;

    public Gameboard() {

    }


    // Need a function for finding all cities with a research station, and making sure they don't exceed 6.

    /**
     * Gets the deck of player cards
     * 
     * @return A deck of BasicCards, EpidemicCards, and EventCards
     * @author Aiden T
     */
    public Deck getPlayerDeck() {
        return this.playerDeck;
    }

    /**
     * Gets a list of the cures for each color
     * 
     * @return An ArrayList of 4 cures
     * @author Aiden T
     */
    public ArrayList<Cure> getCures() {
        return this.cureList;
    }


}
