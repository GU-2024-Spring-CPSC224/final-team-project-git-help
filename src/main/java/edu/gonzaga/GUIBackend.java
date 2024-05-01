package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//Figure out how to clear player's hand selection after they choose their cards
public class GUIBackend extends GUI{

    private ArrayList<Card> selectedCards = new ArrayList<Card>();

    public String getDifficulty(){

        return this.difficultyLevel;
    }

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
        cityInfoDisplay.add(exitButton, BorderLayout.SOUTH);
        if(city.getResearchStation() == true){

            researchStationLabel.setText("Research Station");
            researchStation.add(researchStationLabel);
        }
        else{

            researchStationLabel.setText("No Research Station");
            researchStation.add(researchStationLabel);
        }
        cityInfoDisplay.add(researchStation, BorderLayout.CENTER);

        if(city.getPlayers().size() != 0){
            
            JPanel playerList = new JPanel();
            JLabel playerListLabel = new JLabel(" ", SwingConstants.CENTER);

            String playerDisplay = "Players: ";
            Boolean firstTime = true;

            for (Player player : city.getPlayers()) {
                if (firstTime == true) {
                    firstTime = false;
                } else {
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

    private void exitButtonHandlder(){

        cityInfoDisplay.dispose();

    }

    public Integer driveButtonHandler(){
        
        return 0;
    }

   /*  public Integer directFlightButtonHandler(){
        
        return 1;
    }

    public Integer shuttleFlightButtonHandler(){
        
        return 2;
    }

    public Integer charterFlightButtonHandler(){
        
        return 3;   
    }

    public Integer buildResearchStationButtonHandler(){
        
        return 4;
    }

    public Integer giveKnowledgeButtonHandler(){

        return 5;
    }

    public Integer getKnowledgeButtonHandler(){

        return 6;
    }
    public Integer treatDiseaseButtonHandler(){

        return 7;
    }

    public Integer discoverCureButtonHandler(){

        return 8;
    } */

    public void playerCardCheckBoxHandler(Game gameObject){

        gameObject.getGameboard().getCurrentTurnPlayer().getPlayerSelection().getCardList().clear();

        for(int i = 0; i < playerCards.size(); i++){

            if(playerCards.get(i).isSelected()){
                
                gameObject.getGameboard().getCurrentTurnPlayer().getPlayerSelection().addCard(gameObject.getGameboard().getCurrentTurnPlayer().getHand().getCardList().get(i));
            }
        }
    } 

    public void printPlayerNames(ArrayList<String> playerNames){

        System.out.println("Player Names: " + playerNames);
    }

    /**
     * Handler for Drive feature
     * @param gameObject
     * @param actionCounter
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
     * @param gameObject
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

        System.out.println(currentPlayer.getActionCount());
    }

    public void doCharterFlight(City destinationCity, Game gameObject, JLabel actionCounter){
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
        } else{

            destinationCityScreen.add(enterCity, BorderLayout.NORTH);
            destinationCityScreen.add(citySelector, BorderLayout.CENTER);
            destinationCityScreen.add(enterButton, BorderLayout.SOUTH);
            destinationCityScreen.setVisible(true);
        }
        
    }

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

    public void getKnowledgeHandler(Game gameObject){

        /* Player currentPlayer = gameObject.getGameboard().getCurrentTurnPlayer();

        if (selectedCards.size() != 0) {
            BasicCard selectedCard = (BasicCard)selectedCards.get(0);

            System.out.println("Selected card is " + selectedCard);

            if (selectedCard != null) {
                currentPlayer.takeTurn(1, selectedCard.getCity(), null, null, null, null);
                this.discardSelectedCards();
            }
        }

        System.out.println(currentPlayer.getActionNumber()); */

    }

    public void giveKnowldegeHandler(Game gameObject){
        
    }

    public void treatDiseaseHandler(Game gameObject){
        
    }

    public void cureDiseaseHandler(Game gameObject){

    }

    public void forfeitTurnHandler(Game gameObject){

        gameObject.getGameboard().getCurrentTurnPlayer().setActionNumber(0);
    }

    public void gameOverScreen(Game gameObject){

        JFrame gameOverScreen = new JFrame("Game Over");
        gameOverScreen.setLayout(new BorderLayout());
        JLabel gameOver = new JLabel("Game Over!");
        gameOverScreen.add(gameOver, BorderLayout.CENTER);
    }

    public void winningScreen(Game gameObject){

        JFrame winningScreen = new JFrame("You Win!");
        winningScreen.setLayout(new BorderLayout());
        JLabel winning = new JLabel("You Win!");
        winningScreen.add(winning, BorderLayout.CENTER);
    }

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
            if (card.getCardName() == cityName) {
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
        for (int i = 0; i < selectedCards.size(); i++) {
            selectedCards.remove(selectedCards.get(i));
        }
    }
}