package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIBackend extends GUI{
    
   /*  private void createNewPlayers(){

        for(int i = 0; i < playerNames.size(); i++){

            Player newPlayer = new Player(playerNames.get(i), playerRoles.get(i));

        }
    } */

    public String getDifficulty(){

        return this.difficultyLevel;
    }

    public void cityButtonHandler(City city) {
        
        cityInfoDisplay = new JFrame(city.getCityName());
        cityInfoDisplay.setLayout(new BorderLayout());
        cityInfoDisplay.setSize(450, 450);
        JPanel cityProgressBar = new JPanel();
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue((city.getInfectionCubes().size()*25));
        cityProgressBar.add(progressBar, SwingConstants.VERTICAL);
        cityInfoDisplay.add(cityProgressBar, BorderLayout.EAST);
        JPanel researchStation = new JPanel();
        JLabel researchStationLabel = new JLabel();
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
        cityInfoDisplay.add(researchStation, BorderLayout.WEST);

        if(city.getPlayers().size() != 0){
            
            JPanel playerList = new JPanel();
            JLabel playerListLabel = new JLabel();
            playerListLabel.setText("Players: " + city.getPlayers().toString() + "|");
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

}