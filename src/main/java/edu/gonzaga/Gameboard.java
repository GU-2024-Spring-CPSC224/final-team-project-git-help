package edu.gonzaga;

import java.util.ArrayList;
import java.beans.PropertyChangeListener;

public class Gameboard {
    
    private ArrayList<Cure> cureList;
    private ArrayList<City> cityList; 
    private Deck playerDeck;
    private Deck infectionDeck;
    private Integer numOfResearchStations;
    private boolean canBuildResearchStation;
    private final static Integer MAX_RESEARCH_STATIONS = 6;


    public Gameboard(ArrayList<City> newCityList, Deck newPlayerDeck, Deck newInfectionDeck) {
        this.cityList = newCityList;
        this.playerDeck = newPlayerDeck;
        this.infectionDeck = newInfectionDeck;
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
     * Gets a list of the cures for each color
     * 
     * @return An ArrayList of 4 cures
     * @author Aiden T
     */
    public ArrayList<Cure> getCures() {
        return this.cureList;
    }


}
