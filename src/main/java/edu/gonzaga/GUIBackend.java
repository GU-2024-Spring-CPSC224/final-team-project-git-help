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
        progressBar.setValue((city.getInfectionCubes().size()*25)); //TODO: will this update automatically?
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

    public Integer treatDiseaseButtonHandler() {
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
    public void getDestinationCityDrive(Game gameObject, JLabel actionCounter){
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

        // Handle selection of the city
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
    public void getDestinationCityDirectFlight(Game gameObject, JLabel actionCounter){

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

    public void getDestinationCityCharterFlight(String destinationCity, Game gameObject){
        
        for(int i = 0; i < gameObject.getGameboard().getCurrentTurnPlayer().getHand().getCardList().size(); i++){

            if(gameObject.getGameboard().getCurrentTurnPlayer().getPlayerLocation().getCityName().equals(gameObject.getGameboard().getCurrentTurnPlayer().getHand().getCardList().get(i).getCardName())){
                
                cardIsInHand = true;
            }
            else{

                cardIsInHand = false;
            }
        }
        if(cardIsInHand){

            for (int i = 0; i < playerNames.size(); i++) {
                JLabel player = new JLabel(playerNames.get(i), SwingConstants.CENTER);
                player.setFont(new Font(null, Font.PLAIN, 25));
                playerHandDisplay.add(player);

                // 7 cards
                for (int j = 0; j < 7; j++) {  // 7 for 7 cards maximum
                    JLabel tempLabel = new JLabel();
                    if (j < gameObject.getGameboard().getCurrentTurnPlayer().getHand().getCardList().size()) {
                        tempLabel = new JLabel(gameObject.getGameboard().getPlayer(i).getHand().getCardList().get(j).getCardName());
                    } 
                    else {
                        tempLabel = new JLabel();
                    }
                    playerHandDisplay.add(tempLabel);
                }

                playerHandDisplay.add(new Checkbox()); 

                // 7 checkboxes
                for (int j = 0; j < 7; j++) {

                    JRadioButton tempCheckBox = new JRadioButton();

                    if (i != gameObject.getGameboard().getCurrentTurnPlayerIndex()) {
                        tempCheckBox.setEnabled(false);
                    }
                    if (j >= gameObject.getGameboard().getCurrentTurnPlayer().getHand().getCardList().size()) {

                        tempCheckBox.setEnabled(false); 
                    } 
                    actionSelectionCards.add(tempCheckBox);
                    playerHandDisplay.add(tempCheckBox); 
                    for(int k = 0; k < gameObject.getGameboard().getCurrentTurnPlayer().getHand().getCardList().size(); k++){

                        if(actionSelectionCards.get(k).isSelected()){

                            gameObject.getGameboard().getCurrentTurnPlayer().getPlayerSelection().addCard(gameObject.getGameboard().getCurrentTurnPlayer().getHand().getCardList().get(k));
                            gameObject.getGameboard().getCurrentTurnPlayer().getHand().discard(gameObject.getGameboard().getCurrentTurnPlayer().getHand().getCardList().get(k));
                        }
                        else{

                            gameObject.getGameboard().getCurrentTurnPlayer().getPlayerSelection().discard(gameObject.getGameboard().getCurrentTurnPlayer().getHand().getCardList().get(k));
                            gameObject.getGameboard().getCurrentTurnPlayer().getHand().addCard(gameObject.getGameboard().getCurrentTurnPlayer().getHand().getCardList().get(k));
                        }
                    }
                }
            }

            setDestinationCity(gameObject, actionSelectionCards.get(0).getText());
        }
        else{

            JPanel illegalAction = new JPanel();
            JOptionPane.showMessageDialog(illegalAction, "Illegal Action", "Error", JOptionPane.ERROR_MESSAGE);
            playerHandDisplay.dispose();
        }
        
    }

    public void getDestinationCityShuttleFlight(String destinationCity, Game gameObject){
        
        JFrame destinationCityScreen = new JFrame("Destination City Selector");
        destinationCityScreen.setLayout(new BorderLayout());
        destinationCityScreen.setSize(300, 300);
        destinationCityScreen.setLocation(500,500);

        JLabel enterCity = new JLabel("Choose a City: ");
        JComboBox<String> citySelector = new JComboBox<String>();
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                destinationCityScreen.dispose();
            }
        });
        for(int i = 0; i < gameObject.getGameboard().getCityList().size(); i++){

            if(gameObject.getGameboard().getCityList().get(i).getResearchStation() == true && gameObject.getGameboard().getCityList().get(i).getCityName() != gameObject.getGameboard().getCurrentTurnPlayer().getPlayerLocation().getCityName()){

                citySelector.addItem(gameObject.getGameboard().getCityList().get(i).getCityName());
            }
        }
        if(citySelector.getItemCount() == 0){

            JPanel illegalAction = new JPanel();
            JOptionPane.showMessageDialog(illegalAction, "Illegal Action. There are no research stations to travel to.", "Error", JOptionPane.ERROR_MESSAGE);
            destinationCityScreen.dispose();
        }
        else{

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

    /**
     * Handler for treat disease button
     * When treatDiseaseButton is clicked, it checks for a disease cube. If that exists, it calls
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