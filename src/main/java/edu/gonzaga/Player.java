package edu.gonzaga;

import java.util.ArrayList;

public class Player {
    private String playerName;
    private Integer actionCount;
    private Hand playerHand;
    private Hand playerSelection; 
    private String role;
    private City playerLocation;
    private static Gameboard gameboard;
    private static final Integer DEFAULT_ACTION_NUM = 4;

    /**
     * Initializes the Hand class, if the player is the research it sets the amount to cure to 4. Draws cards on initialization to create a starter hand.
     * 
     * @param firstDrawAmount - The amount of cards to draw before the game begins
     * @author Aiden T
     */
    private void setupHand(Integer firstDrawAmount, Deck playerDeck) {

        if (this.role == "Scientist") {
            this.playerHand = new Hand(4);
        } else {
            this.playerHand = new Hand();
        }

        for (int i = 0; i < firstDrawAmount; i++) {
            playerHand.drawDeckCard(playerDeck);
        }
    }

    /**
     * Moves a player from one city to another, if it's the medic and the cure is researched, they cure all cubes of that color by moving through.
     * 
     * @param destinationCity - The city the player wants to go to
     * @author Aiden T
     */
    private void movePlayer(City destinationCity) {
        this.playerLocation.removePlayer(this);
        this.playerLocation = destinationCity;
        destinationCity.addPlayer(this);

        if (this.role == "Medic") {

            for (Cure cure : gameboard.getCures()) {
                for (Color cube : this.playerLocation.getInfectionCubes()) {

                    if (cure.getStatus() > 0 && cure.getColor() == cube) {
                        this.playerLocation.removeInfectionCube(cube);
                    }
                }
            }
        }
    }

    /**
     * Constructor for the Player class 
     * 
     * @param role - A string of the name of the role EX: "Researcher", "Medic", etc.
     * @param playerName - A string of the player's name
     * @param firstDrawAmount - Number of cards to draw before the game starts
     * @param startingLocation - The location the player will start in
     */
    public Player(String role, String playerName, Integer firstDrawAmount, City startingLocation, Deck playerDeck) {

        this.playerName = playerName; 
        this.role = role;
        this.actionCount = DEFAULT_ACTION_NUM;
        this.playerLocation = startingLocation;
    
        startingLocation.addPlayer(this);
        setupHand(firstDrawAmount, playerDeck);
    }

    /**
     * Constructor for the Player class without a name
     * 
     * @param role - A string of the name of the role EX: "Researcher", "Medic", etc.
     * @param firstDrawAmount - Number of cards to draw before the game starts
     * @param startingLocation - The location the player will start in
     */
    public Player(String role, Integer firstDrawAmount, City startingLocation, Deck playerDeck){

        this.playerName = "Anonymous Player"; 
        this.role = role;
        this.actionCount = DEFAULT_ACTION_NUM;

        startingLocation.addPlayer(this);
        setupHand(firstDrawAmount, playerDeck);
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
     * Move from a city with a research station to any other city that has a research station.
     * 
     * @param selectedCity - The city the player wants to go to
     * @author Aiden T
     */
    private void charterFlight(City selectedCity) {
        BasicCard usedCard = this.playerHand.searchHandForCity(this.playerLocation);

        if (this.playerLocation != usedCard.getCity()) {
            System.err.println("!! ERROR: Player is attempting to charter flight without a card with a city of their current location !!");
            return;
        }

        movePlayer(selectedCity);
    }

    /**
     * Discard the City card that matches the city you are in to move to any city.
     * 
     * @param selection - The city the player wants to go to that has a research station
     * @author Aiden T
     */
    private void shuttleFlight(City selection){
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

    /**
     * Treats a disease cube of one color, or all disease cubes of one color in the player's city
     * 
     * @param color - The color of cube to remove
     * @author Aiden T
     */
    private void treatDisease(Color color){
        Cure selectedCure = null;

        for (Cure cure : gameboard.getCures()) {
            if (cure.getColor() == color) {
                selectedCure = cure;
            }
        }

        for (Color cube : this.playerLocation.getInfectionCubes()) {

            if (cube == color) {
                this.playerLocation.removeInfectionCube(color);
            }

            // If they're the medic or the cure is researched, it takes all cubes of that color from the city
            if (this.role != "Medic" && selectedCure.getStatus() == 0) { 
                break;
            }
        }
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

    /**
     * Since each card is different, it's up to the event card to prompt the player and do the event. Just calls it's functon
     * 
     * @param selectedCard - The card to play out
     * @author Aiden T
     */
    public void useEventCard(EventCard selectedCard){
        // selectedCard.playEvent();
        // TODO: Implement or remove this function
    }

    /**
     * Completes an action, requires the input from the player to select a certain "action type". Draws 2 cards if it's the last action the player can take.
     * 
     * @param actionType - 0 for drive, 1 for direct flight, 2 for shuttle flight, 3 for charter flight, 
     * 4 for build research station, 5 for give knowledge, 6 for take knowledge, 7 for treat disease, 8 for discover cure, 9 for forfeit action
     * @param cityResponse - Null otherwise, use for all movement related actions and building research stations
     * @param cardResponse - Null otherwise, use for movement that requires cards, and giving/taking knowledge
     * @param playerResponse - Null otherwise, use for giving/taking knowledge
     * @param colorResponse - Null otherwise, use for treating a disease
     * @param cardListResponse - Null otherwise, use for discovering a cure. Must be 4 or 5 cards.
     * 
     * @return True: If the player is out of actions and they're reset | False: If a regular action as taken or was unable to be taken.
     * @author Aiden T
     */
    public Boolean takeTurn(Integer actionType, City cityResponse, Card cardResponse, Player playerResponse, Color colorResponse, ArrayList<BasicCard> cardListResponse){

        if (this.actionCount != 0) {
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
            else if (actionType == 6 && cardResponse != null && playerResponse != null && cardResponse.getClass() == BasicCard.class) {
                getKnowledge((BasicCard)cardResponse, playerResponse);
            }
            else if (actionType == 7 && colorResponse != null) {
                treatDisease(colorResponse);
            }
            else if (actionType == 8 && cardListResponse.isEmpty() != true) {
                discoverCure(cardListResponse);
            }
            else {
                System.err.println("!! ERROR: There was an issue getting responses from the player !!");
                return false;
            }

            this.actionCount -= 1;
        }

        if (this.actionCount == 0) {
            this.actionCount = DEFAULT_ACTION_NUM;
            
            this.playerHand.drawDeckCard(gameboard.getPlayerDeck());
            this.playerHand.drawDeckCard(gameboard.getPlayerDeck());

            gameboard.endPlayerTurn();
            return true;
        }

        return false;
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

    /**
     * Returns the player's name
     * 
     * @return A string of the player's name
     * @author Aiden T
     */
    public String getName() {

        return this.playerName;
    }

    public Hand getPlayerSelection(){

        return this.playerSelection;
    }

    public Integer getActionCount(){
        return this.actionCount;
    }

    public City getPlayerLocation(){

        return this.playerLocation;
    }

    public void setActionNumber(Integer actionNumber){

        this.actionCount = actionNumber;
    }
}
