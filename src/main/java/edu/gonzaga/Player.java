package edu.gonzaga;

import java.util.ArrayList;

public class Player {
    
    private String playerName;
    private Integer actionCount;
    private Hand playerHand;
    private String role;
    private City playerLocation;

    /**
     * Moves a player from one city to another
     * 
     * @param destinationCity - The city the player wants to go to
     * @author Aiden T
     */
    private void movePlayer(City destinationCity) {
        this.playerLocation.removePlayer(this);
        this.playerLocation = destinationCity;
        destinationCity.addPlayer(this);
    }

    public Player(City startingLocation, String role, String playerName){

        this.playerName = playerName;
        this.role = role;
        this.actionCount = 4;
        this.playerLocation = startingLocation;
    
        startingLocation.addPlayer(this);
        playerHand = new Hand();
    }

    public Player(City startingLocation, String role){

        this.playerName = "Anonymous Player"; 
        this.role = role;
        this.actionCount = 4;
        playerHand = new Hand();
    }

    /**
     * Moves a player from one city to an adjacent city. Checks if the input is a valid connection.
     * 
     * @param destinationCity - The adjacent city the player wants to go to.
     * @author Aiden T
     */
    private void drive(City selection){
        if (this.playerLocation.findConnection(selection) == false) {
            System.err.println("!! ERROR: Attempt to drive to a city that isn't connected to " + this.playerLocation.getCityName() + " !!");
            return;
        }

        movePlayer(selection);
    }

    /**
     * Moves a player from one city to a city they have a card of. Checks if the input is valid first.
     * 
     * @param selection - The city that matches the card that will be discarded
     * @author Aiden T
     */
    private void directFlight(City selection) {
        //TODO: check card is in hand
        movePlayer(selection);
        //TODO: Discard that card
    }

    /**
     * Moves from a city that matches the discarded card, to any city on the map.
     * 
     * @param selectedCard - The card that matches the current city the player is in
     * @param selectedCity - The city the player wants to go to
     * @author Aiden T
     */
    private void shuttleFlight(BasicCard selectedCard, City selectedCity) {
        if (this.playerLocation != selectedCard.getCity()) {
            System.err.println(" !! ERROR: Player is attempting to shuttle flight while not in the city that matches the card !!");
            return;
        }

        //TODO: Discard selected card
        movePlayer(selectedCity);
    }

    /**
     * Moves from a city with a research station, to another city with a research stations
     * 
     * @param selection - The city the player wants to go to that has a research station
     * @author Aiden T
     */
    private void charterFlight(City selection){
        if (this.playerLocation.getResearchStation() == false) {
            System.err.println("!! ERROR: Player is attempting to charter flight from a city that doesn't have a research station !!");
            return;
        }
        if (selection.getResearchStation() == false) {
            System.err.println("!! ERROR: Player is attempting to charter flight to a city that doesn't have a research station !!");
            return;
        }

        movePlayer(selection);
    }

    /**
     * Builds a research station in the city the player is currently in
     * 
     * @author Aiden T
     */
    private void buildResearchStation(){
        //TODO: Check if there's 6 research stations, if there is then player should be prompted to move a research station 
        if (this.playerLocation.getResearchStation() == true) {
            System.err.println("!! ERROR: Player is attempting to build a research station on a city that already has one !!");
            return;
        }
        else {
            this.playerLocation.addResearchStation();
        }
    }

    /**
     * 
     */
    private void giveKnowledge(BasicCard cardSelection, Player playerSelection){
        
    }

    private void getKnowledge(){
        
    }

    private void treatDisease(){
        
    }

    private void discoverCure(){
        
    }

    public void useEventCard(){
        
    }

    public void takeTurn(){
        Integer actions = 4;
        Integer actionType = -1;
        City cityResponse = null;
        Card cardResponse = null;
        Player playerResponse = null;

        while (actions > 0) {
            // outcome, response = getOutcome();
            //TODO: GUI needs to be connected to here. Depending on which buttons are clicked do different things

            if (actionType == 0 && cityResponse != null) { 
                drive(cityResponse);
            } 
            else if (actionType == 1 && cityResponse != null) {
                directFlight(cityResponse);
            } 
            else if (actionType == 2 && cityResponse != null && cardResponse != null && cardResponse.getClass() == BasicCard.class) {
                shuttleFlight((BasicCard)cardResponse, cityResponse);
            } 
            else if (actionType == 3 && cityResponse != null) {
                charterFlight(cityResponse);
            } 
            else if (actionType == 4) {
                buildResearchStation();
            } 
            else if (actionType == 5 && cardResponse != null && playerResponse != null && cardResponse.getClass() == BasicCard.class) {
                giveKnowledge((BasicCard)cardResponse, playerResponse);
            }
            else {
                System.err.println("!! ERROR: There was an issue getting responses from the player !!");
                continue;
            }

            actions -= 1;
        }

        


    }

    /**
     * Returns the player's role
     * 
     * @return A string of the player's role
     * @author Aiden T
     */
    public String getRole(){
        return this.role;
    }
}
