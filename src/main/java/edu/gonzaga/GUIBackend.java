package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUIBackend extends GUI{

    private ArrayList<Card> selectedCards = new ArrayList<Card>();

    /**
     * get the level of difficulty
     * @return String
     * 
     * @Author Kylie
     */
    public String getDifficulty(){
        return this.difficultyLevel;
    }

    /**
     * handler for city buttons
     * @param city object
     * 
     * @Author Kylie
     */
    public void cityButtonHandler(City city) {
        cityInfoDisplay = new JFrame(city.getCityName());
        cityInfoDisplay.setLayout(new BorderLayout());
        cityInfoDisplay.setSize(450, 450);
        JPanel cityProgressBar = new JPanel(new BorderLayout());
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setOrientation(JProgressBar.VERTICAL);
        progressBar.setValue((city.getInfectionCubes().size()*25));
        cityProgressBar.add(progressBar, BorderLayout.CENTER);
        cityInfoDisplay.add(cityProgressBar, BorderLayout.EAST);
        JPanel researchStation = new JPanel();
        JLabel researchStationLabel = new JLabel(" ", SwingConstants.CENTER);
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                exitButtonHandlder();
            }
        });

        // hanlder for recognizing the research station
        cityInfoDisplay.add(exitButton, BorderLayout.SOUTH);
        if(city.getResearchStation() == true){
            researchStationLabel.setText("Research Station");
            researchStation.add(researchStationLabel);
        }
        else{

            researchStationLabel.setText("No Research Station");
            researchStation.add(researchStationLabel);
        }

        // handler for recognizing the players in the city
        cityInfoDisplay.add(researchStation, BorderLayout.CENTER);
        if(city.getPlayers().size() != 0){
            JPanel playerList = new JPanel();
            JLabel playerListLabel = new JLabel(" ", SwingConstants.CENTER);

            String playerDisplay = "Players: ";
            Boolean firstTime = true;

            for (Player player : city.getPlayers()) {
                if (firstTime == true) {
                    firstTime = false;
                } 
                else {
                    playerDisplay += ", ";
                }
                playerDisplay += player.getName();
            }

            playerListLabel.setText(playerDisplay);
            playerList.add(playerListLabel);
            cityInfoDisplay.add(playerList, BorderLayout.NORTH);
        }
        else{
            cityInfoDisplay.add(new JLabel("No Players"), BorderLayout.NORTH);
        }
        cityInfoDisplay.setVisible(true);
    }

    /**
     * Handler for the player hand display
     * 
     * @Author Kylie
     */
    private void exitButtonHandlder(){
        cityInfoDisplay.dispose();
    }

    /**
     * Handler for the player hand display
     * @param gameObject the game object
     * @Author Kylie
     */
    public void playerCardCheckBoxHandler(Game gameObject){
        gameObject.getGameboard().getCurrentTurnPlayer().getPlayerSelection().getCardList().clear();
        for(int i = 0; i < playerCards.size(); i++){
            if(playerCards.get(i).isSelected()){
                gameObject.getGameboard().getCurrentTurnPlayer().getPlayerSelection().addCard(gameObject.getGameboard().getCurrentTurnPlayer().getHand().getCardList().get(i));
            }
        }
    } 

    /**
     * Print out the player names
     * @param gameObject the game object
     * @Author Kylie
     */
    public void printPlayerNames(ArrayList<String> playerNames){
        System.out.println("Player Names: " + playerNames);
    }

    /**
     * Handler for Drive feature
     * @param gameObject the game object
     * @param actionCounter the action counter label
     * 
     * @author Tony
     * @author Kylie
     */
    public void doDrive(Game gameObject, JLabel actionCounter){
        // Initialize the destination city selection screen
        JFrame destinationCityScreen = new JFrame("Destination City Selector");
        destinationCityScreen.setSize(500, 200);
        JLabel enterCity = new JLabel("Choose a City: ");
        JComboBox<String> citySelector = new JComboBox<String>();

        // Add the valid cities to the dropdown
        // Player can only drive to cities that are connected to their current city
        citySelector.addItem("");
        for(int i = 0; i < gameObject.getGameboard().getCurrentTurnPlayer().getPlayerLocation().getConnections().size(); i++){
            citySelector.addItem(gameObject.getGameboard().getCurrentTurnPlayer().getPlayerLocation().getConnections().get(i).getCityName());
        }

        // Handle selcection of the city
        citySelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDestinationCity(gameObject, citySelector.getSelectedItem().toString());
            }
        });

        // Handle the feature
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                destinationCityScreen.dispose();
                Player currPlayer = gameObject.getGameboard().getCurrentTurnPlayer();
                currPlayer.takeTurn(0, destinationCity, null, null, null, null);
                refreshActionCounter(gameObject, actionCounter);
            }
        });
        
        // Screen logistics
        destinationCityScreen.add(enterCity, BorderLayout.WEST);
        destinationCityScreen.add(citySelector, BorderLayout.CENTER);
        destinationCityScreen.add(enterButton, BorderLayout.SOUTH);
        destinationCityScreen.setVisible(true);
    }

    /**
     * Backend for direct flight. Automatically finds what cards the player has selected and passes it to the player object to take it's turn.
     * 
     * @param gameObject the game object
     * @author Aiden T
     * @author Kylie
     */
    public void doDirectFlight(Game gameObject, JLabel actionCounter){
        Player currentPlayer = gameObject.getGameboard().getCurrentTurnPlayer();

        if (selectedCards.size() != 0) {
            BasicCard selectedCard = (BasicCard)selectedCards.get(0);

            System.out.println("Selected card is " + selectedCard.getCardName());
            if (selectedCard != null) {
                currentPlayer.takeTurn(1, selectedCard.getCity(), null, null, null, null);
                refreshActionCounter(gameObject, actionCounter);
                this.discardSelectedCards();
            }
        }
    }

    /**
     * Backend for charter flight.
     * @param destinationCity the city the player wants to fly to
     * @param gameObject the game object
     * @param actionCounter the action counter label
     * 
     * @Author Aiden T
     * @Author Kylie
     */
    public void doCharterFlight(City destinationCity, Game gameObject, JLabel actionCounter){
        // set up the card selection screen
        JFrame destinationCityScreen = new JFrame("Destination City Selector");
        destinationCityScreen.setLayout(new BorderLayout());
        destinationCityScreen.setSize(300, 300);
        destinationCityScreen.setLocation(500,500);

        JLabel enterCity = new JLabel("Choose a City: ");
        JComboBox<String> citySelector = new JComboBox<String>();
        JButton enterButton = new JButton("Enter");

        Player currentPlayer = gameObject.getGameboard().getCurrentTurnPlayer();
        ArrayList<City> cityList = gameObject.getGameboard().getCityList();

        // Creates a button in this new window that takes the turn
        enterButton.addActionListener(new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent e) {        
                destinationCityScreen.dispose();
                City destination = gameObject.getCity((String)citySelector.getSelectedItem());

                Player currPlayer = gameObject.getGameboard().getCurrentTurnPlayer();
                currPlayer.takeTurn(3, destination, null, null, null, null);
                refreshActionCounter(gameObject, actionCounter);
            }
        });

        for(int i = 0; i < cityList.size(); i++){
            if(cityList.get(i).getCityName() != currentPlayer.getPlayerLocation().getCityName()) {
                citySelector.addItem(cityList.get(i).getCityName());
            }
        }
        
        for(int i = 0; i < currentPlayer.getHand().getCardList().size(); i++){
            if(currentPlayer.getHand().searchHandForCity(currentPlayer.getPlayerLocation()) != null) {
                cardIsInHand = true;
            } else {
                cardIsInHand = false;
            }
        }

        System.out.println(citySelector.getItemCount());
        if(citySelector.getItemCount() == 0 || cardIsInHand == false){
            System.out.println(currentPlayer.getPlayerLocation().getCityName());
            JPanel illegalAction = new JPanel();
            JOptionPane.showMessageDialog(illegalAction, "Illegal Action. You don't have the card that matches the city you're in.", "Error", JOptionPane.ERROR_MESSAGE);
            destinationCityScreen.dispose();
        } 
        else{
            destinationCityScreen.add(enterCity, BorderLayout.NORTH);
            destinationCityScreen.add(citySelector, BorderLayout.CENTER);
            destinationCityScreen.add(enterButton, BorderLayout.SOUTH);
            destinationCityScreen.setVisible(true);
        }
    }

    /**
     * Backend for shuttle flight.
     * @param destinationCity the city the player wants to fly to
     * @param gameObject the game object
     * @param actionCounter the action counter label
     * 
     * @Author Kylie
     * @Author Aiden T
     */
    public void doShuttleFlight(City destinationCity, Game gameObject, JLabel actionCounter){
        JFrame destinationCityScreen = new JFrame("Destination City Selector");
        destinationCityScreen.setLayout(new BorderLayout());
        destinationCityScreen.setSize(300, 300);
        destinationCityScreen.setLocation(500,500);

        JLabel enterCity = new JLabel("Choose a City: ");
        JComboBox<String> citySelector = new JComboBox<String>();
        JButton enterButton = new JButton("Enter");

        Player currentPlayer = gameObject.getGameboard().getCurrentTurnPlayer();
        ArrayList<City> cityList = gameObject.getGameboard().getCityList();

        // Creates a button in this new window that takes the turn
        enterButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                destinationCityScreen.dispose();
                setDestinationCity(gameObject, (String)citySelector.getSelectedItem());

                Player currPlayer = gameObject.getGameboard().getCurrentTurnPlayer();
                currPlayer.takeTurn(2, destinationCity, null, null, null, null);
                refreshActionCounter(gameObject, actionCounter);
            }
        });

        // Gathering potential cities to fly to. Player must be in a city with a research station to use this action.
        if (currentPlayer.getPlayerLocation().getResearchStation() == true) {
            for(int i = 0; i < cityList.size(); i++){

                if(cityList.get(i).getResearchStation() == true && (cityList.get(i).getCityName() != currentPlayer.getPlayerLocation().getCityName())) {
                    citySelector.addItem(cityList.get(i).getCityName());
                }
            }
        }
        
        // handler for error message
        if(citySelector.getItemCount() == 0){
            JPanel illegalAction = new JPanel();
            JOptionPane.showMessageDialog(illegalAction, "Illegal Action. There are no research stations to travel to.", "Error", JOptionPane.ERROR_MESSAGE);
            destinationCityScreen.dispose();
        } else{
            destinationCityScreen.add(enterCity, BorderLayout.NORTH);
            destinationCityScreen.add(citySelector, BorderLayout.CENTER);
            destinationCityScreen.add(enterButton, BorderLayout.SOUTH);
            destinationCityScreen.setVisible(true);
        }
    }

    /**
     * Handler for build research station button
     * @param game the game object
     * @param actionCounter the action counter label
     * 
     * @Author Tony
     * @Author Kylie
     */
    public void buildResearchStationHandler(Game game, JLabel actionCounter) {
        Player currentPlayer = game.getGameboard().getCurrentTurnPlayer();
        City currentCity = currentPlayer.getPlayerLocation();
        Boolean hasCityCard = false;

        // TODO: Check if we have exceeded 6 stations
        // Check if player has the card of the current city to build research station
        for (int i = 0; i < currentPlayer.getHand().getCardList().size(); i++) {
            if (currentPlayer.getHand().getCardList().get(i).getCardName() == currentCity.getCityName()){
                hasCityCard = true;
            }
        }

        if (hasCityCard == true) {
            if (selectedCards.size() != 0) {
                BasicCard selectedCard = (BasicCard)selectedCards.get(0);
    
                System.out.println("Selected card is " + selectedCard.getCardName());
                if (selectedCard != null) {
                    currentPlayer.takeTurn(4, selectedCard.getCity(), null, null, null, null);
                    refreshActionCounter(game, actionCounter);
                    this.discardSelectedCards();
                }
            }
        }
        else {
            JPanel illegalAction = new JPanel();
            JOptionPane.showMessageDialog(illegalAction, "You don't have the city card to build a research station here", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Handler for get knowledge feature
     * @param gameObject the game object
     * @param actionCounter the action counter label
     * 
     * @Author Kylie
     */
    public void getKnowledgeHandler(Game gameObject, JLabel actionCounter){
        // Set up the player selection screen
        JFrame playerSelectorScreen = new JFrame("Get Knowledge");
        playerSelectorScreen.setSize(500, 200);
        JLabel enterPlayer = new JLabel("Choose a player to get knowledge from: ");
        Player currentPlayer = gameObject.getGameboard().getCurrentTurnPlayer();
        JComboBox<String> playerSelector = new JComboBox<String>();
        JButton enterButton = new JButton("Enter");
        JButton nextButton  = new JButton("Next");
        enterButton.addActionListener(new ActionListener() {
            
        @Override
        public void actionPerformed(ActionEvent e) {
                refreshActionCounter(gameObject, actionCounter);
                currentPlayer.takeTurn(6, null, selectedCards.get(0), gameObject.getGameboard().getPlayerObject(playerSelector.getSelectedItem().toString()), null, null);
                playerSelectorScreen.dispose();
            }
        });
        
        for(int i = 0; i < currentPlayer.getPlayerLocation().getPlayers().size(); i++){
            if(currentPlayer.getPlayerLocation().getPlayers().get(i).getName() != gameObject.getGameboard().getCurrentTurnPlayer().getName()){
                playerSelector.addItem("");
                playerSelector.addItem(currentPlayer.getPlayerLocation().getPlayers().get(i).getName());
            }
        }

        // screen logistics
        playerSelectorScreen.add(enterPlayer, BorderLayout.NORTH);
        playerSelectorScreen.add(playerSelector, BorderLayout.CENTER);
        enterButton.setEnabled(false);
        playerSelectorScreen.add(enterButton, BorderLayout.EAST);
        playerSelectorScreen.add(nextButton, BorderLayout.WEST);
        playerSelectorScreen.setVisible(true);

        // logic flow
        String tempSelection = playerSelector.getSelectedItem().toString(); 
        nextButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected player: " + playerSelector.getSelectedItem().toString());
                for(int i = 0; i < gameObject.getGameboard().getPlayerObject(tempSelection).getHand().getCardList().size(); i++){
                    if(gameObject.getGameboard().getPlayerObject(playerSelector.getSelectedItem().toString()).getPlayerLocation().getCityName().equals(gameObject.getGameboard().getPlayerObject(playerSelector.getSelectedItem().toString()).getHand().getCardList().get(i).getCardName())){                        
                        System.out.println("Card is in Hand");
                        cardIsInHand = true;
                        selectedCards.add(gameObject.getGameboard().getCurrentTurnPlayer().getHand().getCardList().get(i));
                        gameObject.getGameboard().getCurrentTurnPlayer().getHand().getCardList().remove(gameObject.getGameboard().getCurrentTurnPlayer().getHand().getCardList().get(i));
                        nextButton.setEnabled(false);
                        enterButton.setEnabled(true);
                        return;
                    }
                    else{
                        System.out.println("Card is not in Hand");
                        cardIsInHand = false;
                    }
                }
                System.out.println(cardIsInHand);
                if(!cardIsInHand){
                    System.out.println("ERROR!!!!!");
                    JPanel illegalAction = new JPanel();
                    JOptionPane.showMessageDialog(illegalAction, "Selected player does not have the correct card in their hand.", "Error", JOptionPane.ERROR_MESSAGE);
                    playerHandDisplay.dispose();
                }   
            }
        });
    
    }

    /**
     * Handler for give knowledge feature
     * @param gameObject the game object
     * @param actionCounter the action counter label
     * 
     * @Author Kylie
     */
    public void giveKnowldegeHandler(Game gameObject, JLabel actionCounter){
        // Set up the player selection screen
        JFrame playerSelectorScreen = new JFrame("Give Knowledge");
        playerSelectorScreen.setSize(500, 200);
        JLabel enterPlayer = new JLabel("Choose a player to give knowledge to: ");
        Player currentPlayer = gameObject.getGameboard().getCurrentTurnPlayer();
        JComboBox<String> playerSelector = new JComboBox<String>();
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new ActionListener() {
            
        @Override
        public void actionPerformed(ActionEvent e) {
                refreshActionCounter(gameObject, actionCounter);
                currentPlayer.takeTurn(5, null, selectedCards.get(0), gameObject.getGameboard().getPlayerObject(playerSelector.getSelectedItem().toString()), null, null);
                playerSelectorScreen.dispose();
            }
        });

        // logic flow
        for(int i = 0; i < gameObject.getGameboard().getCurrentTurnPlayer().getHand().getCardList().size(); i++){
            if(gameObject.getGameboard().getCurrentTurnPlayer().getPlayerLocation().getCityName().equals(gameObject.getGameboard().getCurrentTurnPlayer().getHand().getCardList().get(i).getCardName())){
                cardIsInHand = true;
                selectedCards.add(gameObject.getGameboard().getCurrentTurnPlayer().getHand().getCardList().get(i));
                gameObject.getGameboard().getCurrentTurnPlayer().getHand().getCardList().remove(gameObject.getGameboard().getCurrentTurnPlayer().getHand().getCardList().get(i));
            }
            else{
                cardIsInHand = false;
            }
        }

        if(cardIsInHand){
            for(int i = 0; i < currentPlayer.getPlayerLocation().getPlayers().size(); i++){
                if(currentPlayer.getPlayerLocation().getPlayers().get(i).getName() != gameObject.getGameboard().getCurrentTurnPlayer().getName()){
                    playerSelector.addItem("");
                    playerSelector.addItem(currentPlayer.getPlayerLocation().getPlayers().get(i).getName());
                }
            }

            // screen logistics
            playerSelectorScreen.add(enterPlayer, BorderLayout.NORTH);
            playerSelectorScreen.add(playerSelector, BorderLayout.CENTER);
            playerSelectorScreen.add(enterButton, BorderLayout.SOUTH);
            playerSelectorScreen.setVisible(true);
            
        }
        else{
            JPanel illegalAction = new JPanel();
            JOptionPane.showMessageDialog(illegalAction, "You do not posses the proper card to give knowledge.", "Error", JOptionPane.ERROR_MESSAGE);
            playerHandDisplay.dispose();
        }   
    }

    /**
     * Handler for treat disease button
     * When treatDiseaseButton is clicked, it checks for a disease cube. If that exists, it calls
     * @param gameObject the game object
     * @param actionCounter the action counter label
     * 
     * @Author Izzy T
     */
    public void treatDiseaseButtonHandler(Game gameObject, JLabel actionCounter){
        // initialize cube selection
        JFrame cubeColorScreen = new JFrame("Color Cube Selector");
        cubeColorScreen.setSize(500, 200);
        JLabel enterColor = new JLabel("Choose a Disease Color to Target: ");
        JComboBox<String> colorSelector = new JComboBox<String>();

        ArrayList<Color> infectionCubes = gameObject.getGameboard().getCurrentTurnPlayer().getPlayerLocation().getInfectionCubes();
        colorSelector.addItem("");
        // add all of the cube colors to the dropdown 
        for(int i = 0; i < infectionCubes.size(); i++){
            colorSelector.addItem(infectionCubes.get(i).name());
        }
        
        // Handle selection of cube color 
        colorSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String colorName = (String)colorSelector.getSelectedItem();
                targetColor = Color.valueOf(colorName);
            }
        });

        // Handle the feature
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cubeColorScreen.dispose();
                Player currPlayer = gameObject.getGameboard().getCurrentTurnPlayer();
                currPlayer.takeTurn(7, null, null, null, targetColor, null);
                refreshActionCounter(gameObject, actionCounter);
                
            }
        });
        
        // Screen logistics
        cubeColorScreen.add(enterColor, BorderLayout.WEST);
        cubeColorScreen.add(colorSelector, BorderLayout.CENTER);
        cubeColorScreen.add(enterButton, BorderLayout.SOUTH);
        cubeColorScreen.setVisible(true);
        
    }

    /**
     * Handler for cure disease button
     * @param gameObject the game object
     * @param actionCounter the action counter label
     * 
     * @Author Aiden
     */
    public void cureDiseaseHandler (Game gameObject, JLabel actionCounter){
        Player currentPlayer = gameObject.getGameboard().getCurrentTurnPlayer();
        Integer numCardsToCure = currentPlayer.getHand().getNumCardsToCure();

        // Check if the player has enough cards to cure the disease
        if (selectedCards.size() != numCardsToCure) {
            System.out.println(selectedCards);
            JPanel illegalAction = new JPanel();
            JOptionPane.showMessageDialog(illegalAction, "Number of cards selected is not equal to " + numCardsToCure, "Error", JOptionPane.ERROR_MESSAGE);
            discardSelectedCards();
            return;
        }
    
        Boolean firstTime = true;
        Color selectedColor = null;
        ArrayList<BasicCard> basicCardList = new ArrayList<BasicCard>();

        // Check if all the cards are of the same color
        for (int i = 0; i < selectedCards.size(); i++) {
            BasicCard selectedCard = (BasicCard) selectedCards.get(i);

            if (firstTime == true) {
                selectedColor = selectedCard.getColor();
                firstTime = false;
            } 
            else {
                if (selectedCard.getColor() != selectedColor) {
                    JPanel illegalAction = new JPanel();
                    JOptionPane.showMessageDialog(illegalAction, "Not all cards are of the same color: " + selectedColor, "Error", JOptionPane.ERROR_MESSAGE);
                    discardSelectedCards();
                    return;
                }
            }
            basicCardList.add(selectedCard);
        }

        // Cure the disease
        currentPlayer.takeTurn(8, null, null, null, null, basicCardList);
        refreshActionCounter(gameObject, actionCounter);
        discardSelectedCards();
    }

    /**
     * Handler for the forfeit turn button
     * @param gameObject the game object
     * @param actionCounter the action counter label
     * 
     * @Author Kylie
     */
    public void forfeitTurnHandler(Game gameObject, JLabel actionCounter){
        refreshActionCounter(gameObject, actionCounter);
    }

    /**
     * Handler for the game over screen
     * @param gameObject the game object
     * 
     * @Author Kylie
     */
    public void gameOverScreen(Game gameObject){
        JFrame gameOverScreen = new JFrame("Game Over");
        gameOverScreen.setLayout(new BorderLayout());
        gameOverScreen.setSize(500,500);
        gameOverScreen.setLocation(200,200);
        gameOverScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel gameOver = new JLabel("Game Over!", SwingConstants.CENTER);
        gameOver.setFont(new Font(null, 0, 75));
        gameOverScreen.add(gameOver, BorderLayout.CENTER);
        gameOverScreen.setVisible(true);
    }

    /**
     * Handler for the winning screen
     * @param gameObject the game object
     * 
     * @Author Kylie
     */
    public void winningScreen(Game gameObject){
        JFrame winningScreen = new JFrame("You Win!");
        winningScreen.setLayout(new BorderLayout());
        winningScreen.setSize(500,500);
        winningScreen.setLocation(200,200);
        winningScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel winning = new JLabel("You Win!", SwingConstants.CENTER);
        winning.setFont(new Font(null, 0, 60));
        winningScreen.add(winning, BorderLayout.CENTER);
        winningScreen.setVisible(true);
    }

    /**
     * Set the destination city
     * @param gameObject the game object
     * @param destinationCity the name of the destination city
     * 
     * @Author Tony
     */
    private void setDestinationCity(Game game, String destinationCity) {
        this.destinationCity = game.getCity(destinationCity);
    }

    /**
     * Adds a card to the selected cards array
     * 
     * @param currentPlayer - The player that's currently taking a turn.
     * @param cityName - The name of the city to add to the selected cards
     * @param selected - True if the card is being selected, false if it's being deselected.
     * @author Aiden T
     */
    public void setSelectedCard(Player currentPlayer, String cityName, Boolean selected) {
        if(selected != true) {
            for (int i = 0; i < selectedCards.size(); i++) {
                if (selectedCards.get(i).getCardName() == cityName) {
                    selectedCards.remove(i);
                }
            }
        }

        for (Card card : currentPlayer.getHand().getCardList()) {
            if (card.getCardName() == cityName && selectedCards.indexOf(card) == -1) {
                selectedCards.add(card);
                return;
            }
        }

        System.err.println("!! ERROR: Unable to find the card to select !!");
    }

    /**
     * Removes all cards in the selectedCards variable so that we don't accidentally access cards that are out of the player's hands.
     * 
     * @author Aiden T
     */
    public void discardSelectedCards() {
        selectedCards.clear();
    }

    /**
     * Displays the infected cities in the GUI
     * @param gameObject the game object
     * 
     * @Author Kylie
     */
    public void displayInfectedCities(Game game) {
        JFrame infectedCities = new JFrame("Infected Cities");
        infectedCities.setLayout(new BorderLayout());
        infectedCities.setSize(500,150);
        infectedCities.setLocation(200,200);
        JLabel infected = new JLabel();
        game.getGameboard().addInfectedCities();
        infected.setText(game.getGameboard().getInfectedCities().toString());
        infected.setFont(new Font(null, 0, 15));
        infectedCities.add(infected, BorderLayout.CENTER);
        infectedCities.setVisible(true);

    }
}