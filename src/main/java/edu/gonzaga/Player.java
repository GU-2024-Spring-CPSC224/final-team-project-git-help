package edu.gonzaga;

import java.util.ArrayList;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class Player {
    
    private String playerName;
    private Integer actionCount;
    private Hand playerHand;
    private String role;
    private City playerLocation;
    private static Gameboard gameboard;

    /**
     * Initializes the Hand class, if the player is the research it sets the amount to cure to 4. Draws cards on initialization to create a starter hand.
     * 
     * @param firstDrawAmount - The amount of cards to draw before the game begins
     * @author Aiden T
     */
    private void setupHand(Integer firstDrawAmount) {

        if (this.role == "Scientist") {
            this.playerHand = new Hand(4);
        } else {
            this.playerHand = new Hand();
        }

        for (int i = 0; i < firstDrawAmount; i++) {
            playerHand.drawDeckCard(gameboard.getPlayerDeck());
        }
    }

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

    /**
     * Constructor for the Player class 
     * 
     * @param role - A string of the name of the role EX: "Researcher", "Medic", etc.
     * @param playerName - A string of the player's name
     * @param firstDrawAmount - Number of cards to draw before the game starts
     * @param startingLocation - The location the player will start in
     */
    public Player(String role, String playerName, Integer firstDrawAmount, City startingLocation) {

        this.playerName = playerName;
        this.role = role;
        this.actionCount = 4;
        this.playerLocation = startingLocation;
    
        startingLocation.addPlayer(this);
        setupHand(firstDrawAmount);
    }

    /**
     * Constructor for the Player class without a name
     * 
     * @param role - A string of the name of the role EX: "Researcher", "Medic", etc.
     * @param firstDrawAmount - Number of cards to draw before the game starts
     * @param startingLocation - The location the player will start in
     */
    public Player(String role, Integer firstDrawAmount, City startingLocation){

        this.playerName = "Anonymous Player"; 
        this.role = role;
        this.actionCount = 4;

        startingLocation.addPlayer(this);
        setupHand(firstDrawAmount);
    }

    /**
     * This function should be called before constructing any Player objects 
     * 
     * @param board - The boardgame object this class is instantiated in
     */
    public static void setupPlayerClass(Gameboard board) {
        gameboard = board;
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
        BasicCard cardUsed = this.playerHand.searchHandForCity(selection);
        
        if (cardUsed == null) {
            System.err.println("!! ERROR: Player attempted to use a card that doesn't exist in their hand !!");
            return;
        }

        movePlayer(selection);
        this.playerHand.discard(cardUsed);
    }

    /**
     * Moves from a city that matches the discarded card, to any city on the map.
     * 
     * @param selectedCity - The city the player wants to go to
     * @author Aiden T
     */
    private void shuttleFlight(City selectedCity) {
        BasicCard usedCard = this.playerHand.searchHandForCity(this.playerLocation);

        if (this.playerLocation != usedCard.getCity()) {
            System.err.println("!! ERROR: Player is attempting to shuttle flight without a card with a city of their current location !!");
            return;
        }

        movePlayer(selectedCity);
        this.playerHand.discard(usedCard);
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
     * Transfers a card from the player to another if they're the researcher, or if they're in the same city as the card. Both players must be in the same city.
     * 
     * @param cardSelection - The card they'd like to give
     * @param playerSelection - The player they're giving a card to
     * @author Aiden T
     */
    private void giveKnowledge(BasicCard cardSelection, Player playerSelection){
        if (this.playerLocation.getPlayers().contains(playerSelection) == false) {
            System.err.println("!! ERROR: Player is attempting to give a card to a player in another city !!");
            return;
        } else if (this.playerHand.searchHandForCity(cardSelection.getCity()) == null) {
            System.err.println("!! ERROR: Player is attempting to give a card they do not possess !!");
            return;
        } else if (this.role != "Researcher" && cardSelection.getCity() != this.playerLocation) {
            System.err.println("!! ERROR: Player is attempting to give a card while not in the represented city !!");
            return;
        }

        this.playerHand.discard(cardSelection);
        playerSelection.getHand().addCard(cardSelection);
    }

    /**
     * Transfers a card from the player to another if the other is the researcher, or if they're in the same city as the card. Both players must be in the same city.
     * 
     * @param cardSelection - The card they'd like to take
     * @param playerSelection - The player they're taking a card from
     * @author Aiden T
     */
    private void getKnowledge(BasicCard cardSelection, Player playerSelection) {
        if (this.playerLocation.getPlayers().contains(playerSelection) == false) {
            System.err.println("!! ERROR: Player is attempting to take a card from a player in another city !!");
            return;
        } else if (playerSelection.getHand().searchHandForCity(cardSelection.getCity()) == null) {
            System.err.println("!! ERROR: Player is attempting to take a card that the other player does not possess !!");
            return;
        } else if (this.role != "Researcher" && cardSelection.getCity() != this.playerLocation) {
            System.err.println("!! ERROR: Player is attempting to take a card while not in the represented city !!");
            return;
        }
        
        playerSelection.getHand().discard(cardSelection);
        this.playerHand.addCard(cardSelection);
    }


    private void treatDisease(){
        
    }

    /**
     * Given 4 or 5 cards, the player cures one of the diseases. This function has many checks to make sure that this is done in a valid way.
     * 
     * @param selectedCards - The cards the player has selected to be discarded to make the cure
     * @author Aiden T
     */
    private void discoverCure(ArrayList<BasicCard> selectedCards){
        Color cureColor = null;
        Cure cureObject = null;

        if (this.playerHand.checkCanCure(selectedCards.get(0).getColor()) == false) {
            System.err.println("!! ERROR: Player is attempting to cure while not having the number of cards needed !!");
            return;
        } else if (this.playerHand.getNumCardsToCure() != selectedCards.size()) {
            System.err.println("!! ERROR: Player is attempting to cure without the correct amount of cards !!");
            return;
        }

        cureColor = selectedCards.get(0).getColor();

        for (Cure cure : gameboard.getCures()) { // Getting disease object while checking it hasn't already been cured

            if (cure.getColor() == cureColor) {
                if (cure.getStatus() > 0) {
                    System.err.println("!! ERROR: Player is attempting to cure a disease that has already been cured !!");
                    return;
                }

                cureObject = cure; 
            }
        }

        for (BasicCard card : selectedCards) { // Discarding cards while double checking they're all the same color
            if (card.getColor() != cureColor) {
                System.err.println("!! ERROR: Selected cards for curing are not all the same color !!");
                return;
            }

            this.playerHand.discard(card);
        }
        
        cureObject.cure();
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
            else if (actionType == 2 && cityResponse != null) {
                shuttleFlight(cityResponse);
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

    /**
     * Returns the player's hand
     * 
     * @return A hand object the player has
     * @author Aiden T
     */
    public Hand getHand() {
        return this.playerHand;
    }
}
