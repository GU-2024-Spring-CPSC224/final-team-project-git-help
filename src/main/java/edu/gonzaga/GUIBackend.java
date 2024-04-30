package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//Figure out how to clear player's hand selection after they choose their cards
public class GUIBackend extends GUI{

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
            playerListLabel.setText("Players: " 
            + city.getPlayers().get(0).getName() 
            + " " + city.getPlayers().get(1).getName() + " " 
            + city.getPlayers().get(2).getName() + " " 
            + city.getPlayers().get(3).getName() + " " );
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

    public Integer directFlightButtonHandler(){
        
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
    }

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

    public void getDestinationCityDrive(String destinationCity, Game gameObject){
        
        JFrame destinationCityScreen = new JFrame("Destination City Selector");
        JLabel enterCity = new JLabel("Choose a City: ");
        JComboBox<String> citySelector = new JComboBox<String>();
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                destinationCityScreen.dispose();
            }
        });
        for(int i = 0; i < gameObject.getGameboard().getCurrentTurnPlayer().getPlayerLocation().getConnections().size(); i++){

            citySelector.addItem(gameObject.getGameboard().getCurrentTurnPlayer().getPlayerLocation().getConnections().get(i).getCityName());
        }
        destinationCityScreen.add(enterCity, BorderLayout.WEST);
        destinationCityScreen.add(citySelector, BorderLayout.CENTER);
        destinationCityScreen.add(enterButton, BorderLayout.SOUTH);
        
    }

    public void getDestinationCityDirectFlight(String destinationCity, Game gameObject){
        
        JFrame destinationCityScreen = new JFrame("Destination City Selector");
        destinationCityScreen.setLayout(new BorderLayout());
        JLabel enterCity = new JLabel("Choose a City: ");
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                destinationCityScreen.dispose();
            }
        });
        JComboBox<String> citySelector = new JComboBox<String>();
        for(int i = 0; i < gameObject.getGameboard().getCurrentTurnPlayer().getHand().getCardList().size(); i++){

            citySelector.addItem(gameObject.getGameboard().getCurrentTurnPlayer().getHand().getCardList().get(i).getCardName());
        }
        destinationCityScreen.add(enterCity, BorderLayout.WEST);
        destinationCityScreen.add(citySelector, BorderLayout.CENTER);
        destinationCityScreen.add(enterButton, BorderLayout.SOUTH);
        
    }

    public void getDestinationCityCharterFlight(String destinationCity, Game gameObject){
        
        JFrame destinationCityScreen = new JFrame("Destination City Selector");
        destinationCityScreen.setLayout(new BorderLayout());
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

            citySelector.addItem(gameObject.getGameboard().getCityList().get(i).getCityName());
        }
        destinationCityScreen.add(enterCity, BorderLayout.WEST);
        destinationCityScreen.add(citySelector, BorderLayout.CENTER);
        destinationCityScreen.add(enterButton, BorderLayout.SOUTH);
        
    }

    public void getDestinationCityShuttleFlight(String destinationCity, Game gameObject){
        
        JFrame destinationCityScreen = new JFrame("Destination City Selector");
        destinationCityScreen.setLayout(new BorderLayout());
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

            if(gameObject.getGameboard().getCityList().get(i).getResearchStation() == true){

                citySelector.addItem(gameObject.getGameboard().getCityList().get(i).getCityName());
            }
        }
        destinationCityScreen.add(enterCity, BorderLayout.WEST);
        destinationCityScreen.add(citySelector, BorderLayout.CENTER);
        destinationCityScreen.add(enterButton, BorderLayout.SOUTH);
        
    }
}
