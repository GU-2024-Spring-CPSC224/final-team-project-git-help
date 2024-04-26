package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
public class GUIBackend extends GUI{
    
    private void createNewPlayers(){

        for(int i = 0; i < playerNames.size(); i++){

            Player newPlayer = new Player(playerNames.get(i), playerRoles.get(i));

        }
    }

    private String getDifficulty(){

        return this.difficultyLevel;
    }

    private void cityButtonHandler(City city) {
        
        JFrame cityInfoDisplay = new JFrame(city.getCityName());
        cityInfoDisplay.setLayout(new BorderLayout());
        cityInfoDisplay.setSize(450, 450);
        JPanel cityProgressBar = new JPanel();
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue((city.getInfectionCubes().size()*25));
        cityProgressBar.add(progressBar, SwingConstants.VERTICAL);
        cityInfoDisplay.add(cityProgressBar, BorderLayout.EAST);
        JPanel researchStation = new JPanel();
        JLabel researchStationLabel = new JLabel();
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
            cityInfoDisplay.add(playerList, BorderLayout.SOUTH);
        }
        else{

            cityInfoDisplay.add(new JLabel("No Players"), BorderLayout.SOUTH);
        }

        cityInfoDisplay.setVisible(true);
    }

}
