package edu.gonzaga;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Gameboard {
    
    private ArrayList<Cure> cureList;
    private ArrayList<City> cityList; 
    private ArrayList<Player> playerList;
    private Queue<Integer> infectionRate;
    private Deck playerDeck; // just the city and event cards
    private Deck infectionDeck; // just the infection cards
    private Deck totalDeck; // all of the cards
    private Integer numOfResearchStations;
    private boolean canBuildResearchStation;
    private final static Integer MAX_RESEARCH_STATIONS = 6;


    public Gameboard(ArrayList<City> newCityList, ArrayList<Cure> newCureList, ArrayList<Player> newPlayerList) {
        this.cityList = newCityList;
        this.cureList = newCureList;
        this.playerList = newPlayerList;
        this.canBuildResearchStation = true;
        this.totalDeck = new Deck(newCityList);
        this.infectionRate = new LinkedList<Integer>();
        setInfectionQueue();
    }

    /**
     * Creates the player card deck to draw from, based on the cities in the game 
     * might be important for testing, not used in main functions because all cards are created in the mainDeck
     */
    public void setPlayerDeck() {
        playerDeck = new Deck(cityList);
    }

    /**
     * Helper function for setting all of the values in the Queue-style linked list for keeping track of current infection rates
     * @author Izzy T
     */
    private void setInfectionQueue() {
        infectionRate.add(2);
        infectionRate.add(2);
        infectionRate.add(2);
        infectionRate.add(3);
        infectionRate.add(3);
        infectionRate.add(4);
        infectionRate.add(4);
    }

    /**
     * Determines if you can build more research stations - must have less than the maximum (6) number
     * @author Izzy T
     */
    public void setCanBuildResearchStation() {
        this.canBuildResearchStation = (this.numOfResearchStations < MAX_RESEARCH_STATIONS);
    }

    /**
     * Returns if you can build more research stations
     * @return true if you can build research stations, false if you have reached the max
     * @author Izzy T
     */
    public boolean getCanBuildResearchStation() {
        setCanBuildResearchStation();
        return this.canBuildResearchStation;
    }

    /**
     * Builds research station in passed in City IF there are less than the max number of research stations already built; increases counter of research stations by 1
     * @param city the City that you want to build a research station on
     * @author Izzy T
     */
    public void buildResearchStation(City city) {
        if (canBuildResearchStation) {
            city.addResearchStation();
            numOfResearchStations++;
        }
        else {
            System.out.println("You have exceeded the amount of research stations that can be built. Please remove one before building another.");
        }
    }

    /**
     * Removes a research station from a city, and decreases the counter for research stations by 1
     * @param city the City that you want to remove a research station from
     * @author Izzy T
     */
    public void removeResearchStation(City city) {
        city.removeResearchStation();;
        numOfResearchStations--;
    }

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
     * Gets the deck of infection cards
     * 
     * @return A deck of the Infection cards
     * @author Izzy T
     */
    public Deck getInfectionDeck() {
        return this.infectionDeck;
    }

    /**
     * Gets the current infection rate on the board (stored in a queue)
     * @return current infection rate 
     * @author Izzy T
     */
    public Integer getCurrentInfectionRate() {
        return infectionRate.peek();
    }

    /**
     * Pops value off of the top of the queue and next infection rate becomes the top one
     * Used during an epidemic 
     * @author Izzy T
     */
    public void changeInfectionRate() {
        infectionRate.remove();
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
