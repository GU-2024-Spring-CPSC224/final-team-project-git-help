package edu.gonzaga;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import java.awt.*;
import java.io.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.GridLayout;

public class GUI {

    JFrame pandemicGameFrame = new JFrame("Pandemic!");
    ArrayList<JPanel> gameScenes = new ArrayList<JPanel>();

    public static void main(String args[]) {

        GUI game = new GUI();
        game.generateGUI();
        game.pandemicGameFrame.setSize(1215, 700);
        //game.pandemicGameFrame.setLayout(new BorderLayout());
        game.pandemicGameFrame.add(game.gameScenes.get(0));
        game.pandemicGameFrame.setVisible(true);
    }

    private void generateGUI(){

        generateGameStartScreen();
       //generatePlayerCreationScreen();
       //generateGameboardScreen();
      //generateRoleSelectionScreen();
      //generatePlayerHandDisplayScreen();
    }
    private void generateGameStartScreen(){

        JPanel gameStartScreen = new JPanel();
        JButton startButton = new JButton("Start");
        gameStartScreen.setBackground(java.awt.Color.GREEN);
        JLabel gameTitle = new JLabel("Pandemic!", SwingConstants.CENTER);
        gameTitle.setFont(new Font(null, 0, 125));
        startButton.setFont(new Font(null, 0, 75));
        gameStartScreen.setSize(1215, 700);
        gameStartScreen.setLayout(new BorderLayout());
        gameStartScreen.add(gameTitle, BorderLayout.CENTER);
        gameStartScreen.add(startButton, BorderLayout.SOUTH);
        startButton.setPreferredSize(new Dimension(200, 100));
        gameScenes.add(gameStartScreen);
    }
    private void generatePlayerCreationScreen(){

        JPanel playerCreationScreen = new JPanel();
        JLabel difficulty = new JLabel("Difficulty");
        JLabel playerName = new JLabel("Players");
        JLabel empty =  new JLabel("");
        JCheckBox easy = new JCheckBox("Easy");
        JCheckBox medium = new JCheckBox("Medium");
        JCheckBox hard = new JCheckBox("Hard");
        JCheckBox veryHard = new JCheckBox("COVID-19");
        JTextField player1NameInput = new JTextField();
        JTextField player2NameInput = new JTextField();
        JTextField player3NameInput = new JTextField();
        JTextField player4NameInput = new JTextField();
        JButton startButton = new JButton("Next");
        playerCreationScreen.setSize(1215, 700);
        playerCreationScreen.setLayout(new GridLayout(6, 3));
        playerCreationScreen.add(difficulty, 0,0);
        playerCreationScreen.add(empty, 0,1);
        playerCreationScreen.add(playerName, 0,2);
        playerCreationScreen.add(easy, 1,0);
        playerCreationScreen.add(medium, 2,0);
        playerCreationScreen.add(hard, 3,0);
        playerCreationScreen.add(veryHard, 4,0);
        playerCreationScreen.add(empty, 0,1);
        playerCreationScreen.add(empty, 1,1);
        playerCreationScreen.add(empty, 2,1);
        playerCreationScreen.add(empty, 3,1);
        playerCreationScreen.add(empty, 3,1);
        playerCreationScreen.add(player1NameInput, 1,2);
        playerCreationScreen.add(player2NameInput, 2,2);
        playerCreationScreen.add(player3NameInput, 3,2);
        playerCreationScreen.add(player4NameInput, 4,2);
        playerCreationScreen.add(empty, 5,0);
        playerCreationScreen.add(startButton, 5,1);
        playerCreationScreen.add(empty, 5,2);
        playerCreationScreen.setVisible(true);
        gameScenes.add(playerCreationScreen);
        
    }
    private void generateRoleSelectionScreen(){

        JPanel roleSelectionScreen = new JPanel();
        JLabel empty =  new JLabel("");
        JLabel roleSelectionTitle1 = new JLabel("Choose");
        JLabel roleSelectionTitle2 = new JLabel("Role");
        JLabel player = new JLabel("Player");
        String roles[] = {"Dispatcher", "Operations Expert", "Medic", "Researcher", "Quarantine Specialist", "Scientist", "Contingency Planner"};
        JComboBox<String> roleSelection = new JComboBox<String>(roles);
        JButton next = new JButton("Next");
        JButton back = new JButton("Back");
        roleSelectionScreen.setLayout(new GridLayout(4, 4));
        roleSelectionScreen.setSize(1215, 700);
        roleSelectionScreen.add(empty, 0,0);
        roleSelectionScreen.add(roleSelectionTitle1, 0,1);
        roleSelectionScreen.add(roleSelectionTitle2, 0,2);
        //roleSelectionScreen.add(empty, 0,3);
        roleSelectionScreen.add(player, 1,0);
        roleSelectionScreen.add(player, 1,1);
        roleSelectionScreen.add(player, 1,2);
        roleSelectionScreen.add(player, 1,3);
        roleSelectionScreen.add(roleSelection, 2,0);
        roleSelectionScreen.add(roleSelection, 2,1);
        roleSelectionScreen.add(roleSelection, 2,2);
        roleSelectionScreen.add(roleSelection, 2,3);
        roleSelectionScreen.add(empty, 3,0);
        roleSelectionScreen.add(next, 3,2);
        roleSelectionScreen.add(back, 3,1);
        roleSelectionScreen.add(empty, 3,3);
        roleSelectionScreen.setVisible(true);
        gameScenes.add(roleSelectionScreen);
    }
    private void generateGameboardScreen(){

        JPanel gameboard = new JPanel();
        gameboard.setSize(1215, 700);
        gameScenes.add(gameboard);
    }
    private void generatePlayerHandDisplayScreen(){

        JFrame playerHandDisplay = new JFrame();
        JLabel player = new JLabel("Player");
        playerHandDisplay.setSize(1215, 700);
        playerHandDisplay.setLayout(new GridLayout(4, 8));
        playerHandDisplay.add(player, 0,0);
        playerHandDisplay.add(player, 1,0);
        playerHandDisplay.add(player, 2,0);
        playerHandDisplay.add(player, 3,0);
        playerHandDisplay.setVisible(true);
    }
}
 