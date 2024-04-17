package edu.gonzaga;

import java.util.ArrayList;

public class Gameboard {
    
    private ArrayList<Cure> cureList;
    private ArrayList<City> cityList; 
    private Deck playerDeck;
    private Deck infectionDeck;

    public Gameboard(ArrayList<City> newCityList, Deck newPlayerDeck, Deck newInfectionDeck) {
        this.cityList = newCityList;
        this.playerDeck = newPlayerDeck;
        this.infectionDeck = newInfectionDeck;
    }


    // Need a function for finding all cities with a research station, and making sure they don't exceed 6.
    // Note from Izzy: I think we should implement an MVC style thing so that Gameboard is notified everytime a research station is added/removed

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
