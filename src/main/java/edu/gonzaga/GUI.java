package edu.gonzaga;

import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class GUI {

    private final static Integer DEFAULT_CITY_BUTTON_WIDTH = 80;
    private final static Integer SQUISHED_CITY_BUTTON_WIDTH = 50;
    private final static Integer LONG_CITY_BUTTON_WIDTH = 100;
    private final static Integer DEFAULT_CITY_BUTTON_HEIGHT = 30;

    JPanel playerCreationScreen = new JPanel(new GridLayout(6,0));
    JLabel background = new JLabel(new ImageIcon("./src/main/java/edu/gonzaga/Pandemic_Gameboard.png"));

    JFrame pandemicGameFrame = new JFrame("Pandemic!");
    JFrame playerHandDisplay = new JFrame("Player Hands!");
    JFrame cityInfoDisplay;
    ArrayList<JButton> gameBoardButtons = new ArrayList<>();
    ArrayList<JButton> playerActionButtons = new ArrayList<>();
    ArrayList<JCheckBox> playerCards = new ArrayList<>();
    String difficultyLevel;
    ArrayList<String> playerNames = new ArrayList<>();
    ArrayList<String> playerRoles = new ArrayList<>();
    static GUIBackend backend = new GUIBackend();
    DocumentListener docListener;
    Game gameObject;

    public GUI(){

        difficultyLevel = "Easy";
        playerNames.add("");
        playerNames.add("");
        playerNames.add("");
        playerNames.add("");
    }
    public static void main(String[] args) {

        GUI game = new GUI();
       
        game.pandemicGameFrame.setVisible(true);
        game.pandemicGameFrame.setBounds(0, 0, 1100, 800);
        game.pandemicGameFrame.setLocation(225, 50);
        game.generateGameStartScreen();
 
    }

    private void generateGameStartScreen(){

        JPanel gameStartScreen = new JPanel();
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                gameStartScreen.setVisible(false);
                generatePlayerCreationScreen();
            }
        });

        gameStartScreen.setBackground(null);
        JLabel gameTitle = new JLabel("Pandemic!", SwingConstants.CENTER);
        gameTitle.setFont(new Font(null, 0, 125));
        startButton.setFont(new Font(null, 0, 75));
        gameStartScreen.setSize(1215, 700);
        gameStartScreen.setLayout(new BorderLayout());
        gameStartScreen.add(gameTitle, BorderLayout.CENTER);
        gameStartScreen.add(startButton, BorderLayout.SOUTH);
        startButton.setPreferredSize(new Dimension(200, 100));
        gameStartScreen.setVisible(true);
        pandemicGameFrame.add(gameStartScreen);
    }

    private void generatePlayerCreationScreen(){

        JLabel difficulty = new JLabel("Difficulty", SwingConstants.CENTER);
        difficulty.setFont(new Font(null, 0, 50));
        JLabel playerName = new JLabel("Players", SwingConstants.CENTER);
        playerName.setFont(new Font(null, 0, 50));

        ButtonGroup difficultyGroup = new ButtonGroup();
        JRadioButton easy = createDifficultyButton("Easy", difficultyGroup);
        JRadioButton medium = createDifficultyButton("Medium", difficultyGroup);
        JRadioButton hard = createDifficultyButton("Hard", difficultyGroup);
        JRadioButton veryHard = createDifficultyButton("COVID-19", difficultyGroup);

        JTextField player1NameInput = new JTextField();
        player1NameInput.setFont(new Font(null, 0, 20));
        JTextField player2NameInput = new JTextField();
        player2NameInput.setFont(new Font(null, 0, 20));
        JTextField player3NameInput = new JTextField();
        player3NameInput.setFont(new Font(null, 0, 20));
        JTextField player4NameInput = new JTextField();
        player4NameInput.setFont(new Font(null, 0, 20));
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

                String player1Name = player1NameInput.getText();
                playerNames.set(0, player1Name);
                String player2Name = player2NameInput.getText();
                playerNames.set(1, player2Name);
                String player3Name = player3NameInput.getText();
                playerNames.set(2, player3Name);
                String player4Name = player4NameInput.getText();
                playerNames.set(3, player4Name);
            }
        
            @Override
            public void removeUpdate(DocumentEvent e) {

                String player1Name = player1NameInput.getText();
                playerNames.set(0, player1Name);
                String player2Name = player2NameInput.getText();
                playerNames.set(1, player2Name);
                String player3Name = player3NameInput.getText();
                playerNames.set(2, player3Name);
                String player4Name = player4NameInput.getText();
                playerNames.set(3, player4Name);
            }
        
            @Override
            public void changedUpdate(DocumentEvent e) {
                
                String player1Name = player1NameInput.getText();
                playerNames.set(0, player1Name);
                String player2Name = player2NameInput.getText();
                playerNames.set(1, player2Name);
                String player3Name = player3NameInput.getText();
                playerNames.set(2, player3Name);
                String player4Name = player4NameInput.getText();
                playerNames.set(3, player4Name);
            }
        };
        player1NameInput.getDocument().addDocumentListener(documentListener);
        player2NameInput.getDocument().addDocumentListener(documentListener);
        player3NameInput.getDocument().addDocumentListener(documentListener);
        player4NameInput.getDocument().addDocumentListener(documentListener);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                playerCreationScreen.setVisible(false);
                generateGameStartScreen();
            }
        });
        JButton startButton = new JButton("Next");
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
        
                playerCreationScreen.setVisible(false);
                generateRoleSelectionScreen();
            }
        });

        startButton.setFont(new Font(null, 0, 50));
        backButton.setFont(new Font(null, 0, 50));
        playerCreationScreen.setSize(1215, 700);
        playerCreationScreen.add(difficulty);
        playerCreationScreen.add(playerName);
        playerCreationScreen.add(easy);
        playerCreationScreen.add(player1NameInput);
        playerCreationScreen.add(medium);
        playerCreationScreen.add(player2NameInput);
        playerCreationScreen.add(hard);
        playerCreationScreen.add(player3NameInput);
        playerCreationScreen.add(veryHard);
        playerCreationScreen.add(player4NameInput);
        playerCreationScreen.add(backButton);
        playerCreationScreen.add(startButton);
        pandemicGameFrame.add(playerCreationScreen);
        playerCreationScreen.setVisible(true);
        
    }

    private void generateRoleSelectionScreen(){

        JPanel roleSelectionScreen = new JPanel();
        JLabel empty1 =  new JLabel("");
        JLabel empty2 =  new JLabel("");
        JLabel empty3 =  new JLabel("");
        JLabel empty4 =  new JLabel("");
        JLabel player1 = new JLabel("");
        JLabel player2 = new JLabel("");
        JLabel player3 = new JLabel("");
        JLabel player4 = new JLabel("");
        JLabel roleSelectionTitle1 = new JLabel("Choose", SwingConstants.CENTER);
        roleSelectionTitle1.setFont(new Font(null, 0, 50));
        JLabel roleSelectionTitle2 = new JLabel("Role", SwingConstants.CENTER);
        roleSelectionTitle2.setFont(new Font(null, 0, 50));
        if(playerNames.get(0) == ""){

            player1 = new JLabel("Player 1", SwingConstants.CENTER);
            player1.setFont(new Font(null, 0, 50));
            playerNames.add(0,"Player 1");
        }
        else{

            player1 = new JLabel(playerNames.get(0), SwingConstants.CENTER);
            player1.setFont(new Font(null, 0, 50));
        }
        if(playerNames.get(1) == ""){

            player2 = new JLabel("Player 2", SwingConstants.CENTER);
            player2.setFont(new Font(null, 0, 50));
            playerNames.add(1,"Player 2");
        }
        else{

            player2 = new JLabel(playerNames.get(1), SwingConstants.CENTER);
            player2.setFont(new Font(null, 0, 50));
        }
        if(playerNames.get(2) == ""){

            player3 = new JLabel("Player 3", SwingConstants.CENTER);
            player3.setFont(new Font(null, 0, 50));
            playerNames.add(2,"Player 3");
        }
        else{

            player3 = new JLabel(playerNames.get(2), SwingConstants.CENTER);
            player3.setFont(new Font(null, 0, 50));
        }
        if(playerNames.get(3) == ""){

            player4 = new JLabel("Player 4", SwingConstants.CENTER);
            player4.setFont(new Font(null, 0, 50));
            playerNames.add(3, "Player 4");
        }
        else{

            player4 = new JLabel(playerNames.get(3), SwingConstants.CENTER);
            player4.setFont(new Font(null, 0, 50));
        }
        String roles[] = {"Dispatcher", "Operations Expert", "Medic", "Researcher", "Quarantine Specialist", "Scientist", "Contingency Planner"};
        JComboBox<String> roleSelection1 = new JComboBox<String>(roles);
        roleSelection1.setFont(new Font(null, 0, 15));
        roleSelection1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                String role1 = roleSelection1.getSelectedItem().toString();
                playerRoles.add(role1);
            }
        });
        JComboBox<String> roleSelection2 = new JComboBox<String>(roles);
        roleSelection2.setFont(new Font(null, 0, 15));
        roleSelection2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                String role2 = roleSelection2.getSelectedItem().toString();
                playerRoles.add(role2);
            }
        });
        JComboBox<String> roleSelection3 = new JComboBox<String>(roles);
        roleSelection3.setFont(new Font(null, 0, 15));
        roleSelection3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                String role3 = roleSelection3.getSelectedItem().toString();
                playerRoles.add(role3);
            }
        });
        JComboBox<String> roleSelection4 = new JComboBox<String>(roles);
        roleSelection4.setFont(new Font(null, 0, 15));
        roleSelection4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                String role4 = roleSelection4.getSelectedItem().toString();
                playerRoles.add(role4);
            }
        });
        JButton back = new JButton("Back");
        back.setFont(new Font(null, 0, 50));
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                roleSelectionScreen.setVisible(false);
                generatePlayerCreationScreen();
            }
        });
        JButton next = new JButton("Next");
        next.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                pandemicGameFrame.dispose();
                generateGameboardScreen(backend);
            }
        });
        next.setFont(new Font(null, 0, 50));
        roleSelectionScreen.setLayout(new GridLayout(4, 0));
        roleSelectionScreen.setSize(1215, 700);
        roleSelectionScreen.add(empty1);
        roleSelectionScreen.add(roleSelectionTitle1);
        roleSelectionScreen.add(roleSelectionTitle2);
        roleSelectionScreen.add(empty2);
        roleSelectionScreen.add(player1);
        roleSelectionScreen.add(player2);
        roleSelectionScreen.add(player3);
        roleSelectionScreen.add(player4);
        roleSelectionScreen.add(roleSelection1);
        roleSelectionScreen.add(roleSelection2);
        roleSelectionScreen.add(roleSelection3);
        roleSelectionScreen.add(roleSelection4);
        roleSelectionScreen.add(empty3);
        roleSelectionScreen.add(back);
        roleSelectionScreen.add(next);
        roleSelectionScreen.add(empty4);
        pandemicGameFrame.add(roleSelectionScreen);
        roleSelectionScreen.setVisible(true);
    }
    private void generateGameboardScreen(GUIBackend backend) {

        gameObject = new Game(playerRoles, playerNames, backend.getDifficulty());
        JFrame gameboard = new JFrame("Pandemic!");
        gameboard.setSize(1472, 908);
        gameboard.setContentPane(background);

        JButton playerHandDisplay = new JButton("Display Player Hands");
        playerHandDisplay.setSize(300, 40);
        playerHandDisplay.setLocation(0, 500);

        JPanel playerActionOptions = new JPanel(new GridLayout(3, 3));
        playerActionOptions.setSize(300, 300);
        playerActionOptions.setLocation(0, 550);

        JButton drive = new JButton("Drive");
        playerActionButtons.add(drive);
        drive.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                backend.driveButtonHandler();
            }
        });
        JButton directFlight = new JButton("Direct Flight");
        playerActionButtons.add(directFlight);
        directFlight.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                backend.directFlightButtonHandler();
            }
        });
        JButton shuttleFlight = new JButton("Shuttle Flight");
        playerActionButtons.add(shuttleFlight);
        shuttleFlight.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                backend.shuttleFlightButtonHandler();
            }
        });
        JButton charterFlight = new JButton("Charter Flight");
        playerActionButtons.add(charterFlight);
        charterFlight.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                backend.charterFlightButtonHandler();
            }
        });
        JButton buildResearchStation = new JButton("Build Research Station");
        playerActionButtons.add(buildResearchStation);
        buildResearchStation.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                backend.buildResearchStationButtonHandler();
            }
        });
        JButton giveKnowledge = new JButton("Give Knowledge");
        playerActionButtons.add(giveKnowledge);
        giveKnowledge.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                backend.giveKnowledgeButtonHandler();
            }
        });
        JButton getKnowledge = new JButton("Get Knowledge");
        playerActionButtons.add(getKnowledge);
        getKnowledge.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                backend.getKnowledgeButtonHandler();
            }
        });
        JButton treatDisease = new JButton("Treat Disease");
        playerActionButtons.add(treatDisease);
        treatDisease.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                backend.treatDiseaseButtonHandler();
            }
        });
        JButton discoverCure = new JButton("Discover Cure");
        playerActionButtons.add(discoverCure);
        discoverCure.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                backend.discoverCureButtonHandler();
            }
        });
        for(int i = 0; i < playerActionButtons.size(); i++){

            playerActionOptions.add(playerActionButtons.get(i));
            playerActionButtons.get(i).setFont(new Font(null, 0, 15));
        }
        gameboard.add(playerActionOptions);
        gameboard.add(playerHandDisplay);
        playerHandDisplay.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                generatePlayerHandDisplayScreen(gameObject);
            }
        });
        createCityButton("San Francisco", 143, 347, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Atlanta", 310, 367, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Los Angeles", 160, 367, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Mexico City", 244, 440, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Miami", 348, 408, SQUISHED_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Chicago", 295, 322, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Washington DC", 343, 341, LONG_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("New York", 356, 330, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Montreal", 357, 300, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Bogota", 354, 508, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Lima", 358, 582, SQUISHED_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Lima", 358, 582, SQUISHED_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("São Paulo", 475, 633, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Santiago", 369, 685, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Buenos Aires", 430, 687, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Madrid", 672, 333, SQUISHED_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Algiers", 702, 357, SQUISHED_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Paris", 700, 279, SQUISHED_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("London", 680, 259, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Essen", 740, 264, SQUISHED_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Milan", 730, 305, SQUISHED_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("St. Petersburg", 812, 196, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Moscow", 847, 229, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Istanbul", 806, 327, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Bogota", 354, 508, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Cairo", 826, 392, SQUISHED_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Baghdad", 876, 368, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Tehran", 918, 356, SQUISHED_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Riyadh", 902, 413, SQUISHED_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Khartoum", 825, 459, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Lagos", 710, 498, SQUISHED_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Kinshasa", 748, 545, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Johannesburg", 804, 646, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Karachi", 977, 413, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Delhi", 1032, 397, SQUISHED_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Mumbai", 1003, 441, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Chennai", 1034, 466, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Kolkata", 1072, 424, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Bangkok", 1125, 465, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Jakarta", 1153, 555, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Sydney", 1349, 687, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Ho Chi Minh City", 1153, 481, LONG_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Manila", 1215, 462, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Hong Kong", 1185, 424, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Taipei", 1217, 412, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Shanghai", 1216, 383, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Beijing", 1195, 333, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Seoul", 1242, 347, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Tokyo", 1297, 359, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        createCityButton("Osaka", 1278, 371, DEFAULT_CITY_BUTTON_WIDTH, DEFAULT_CITY_BUTTON_HEIGHT);
        
        for (JButton button : gameBoardButtons){

            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    for(int i = 0; i < gameObject.getGameboard().getCityList().size(); i++){

                        if(gameObject.getGameboard().getCityList().get(i).getCityName().equals(button.getText())){

                            backend.cityButtonHandler(gameObject.getGameboard().getCityList().get(i));

                        }
                    } 
                }
            });
        }
        background.setLayout(null);
        gameboard.setLayout(null);
        gameboard.setSize(1472, 908);
        gameboard.setVisible(true);
    }   
    private void generatePlayerHandDisplayScreen(Game gameObject) {

        JLabel player1 = new JLabel(playerNames.get(0), SwingConstants.CENTER);
        player1.setFont(new Font(null, 0, 25));
        JLabel player2 = new JLabel(playerNames.get(1), SwingConstants.CENTER);
        player2.setFont(new Font(null, 0, 25));
        JLabel player3 = new JLabel(playerNames.get(2), SwingConstants.CENTER);
        player3.setFont(new Font(null, 0, 25));
        JLabel player4 = new JLabel(playerNames.get(3), SwingConstants.CENTER);
        player4.setFont(new Font(null, 0, 25));
        JPanel emptyPanel1 = new JPanel();
        JPanel emptyPanel2 = new JPanel();
        JPanel emptyPanel3 = new JPanel();
        JPanel emptyPanel4 = new JPanel();
        JPanel player1Card1 = new JPanel();
        JCheckBox player1Card1Checkbox = new JCheckBox();
        JLabel player1Card1Label = new JLabel(gameObject.getGameboard().getCurrentTurnPlayer().getHand().getCardList().get(0).getCardType());
        playerCards.add(player1Card1Checkbox);
        JPanel player1Card2 = new JPanel();
        JCheckBox player1Card2Checkbox = new JCheckBox();
        JLabel player1Card2Label = new JLabel();
        playerCards.add(player1Card2Checkbox);
        JPanel player1Card3 = new JPanel();
        JCheckBox player1Card3Checkbox = new JCheckBox();
        JLabel player1Card3Label = new JLabel();
        playerCards.add(player1Card3Checkbox);
        JPanel player1Card4 = new JPanel();
        JCheckBox player1Card4Checkbox = new JCheckBox();
        JLabel player1Card4Label = new JLabel();
        playerCards.add(player1Card4Checkbox);
        JPanel player1Card5 = new JPanel();
        JCheckBox player1Card5Checkbox = new JCheckBox();
        JLabel player1Card5Label = new JLabel();
        playerCards.add(player1Card5Checkbox);
        JPanel player1Card6 = new JPanel();
        JCheckBox player1Card6Checkbox = new JCheckBox();
        JLabel player1Card6Label = new JLabel();
        playerCards.add(player1Card6Checkbox);
        JPanel player1Card7 = new JPanel();
        JCheckBox player1Card7Checkbox = new JCheckBox();
        JLabel player1Card7Label = new JLabel();
        playerCards.add(player1Card7Checkbox);
        JPanel player2Card1 = new JPanel();
        JCheckBox player2Card1Checkbox = new JCheckBox();
        JLabel player2Card1Label = new JLabel();
        playerCards.add(player2Card1Checkbox);
        JPanel player2Card2 = new JPanel();
        JCheckBox player2Card2Checkbox = new JCheckBox();
        JLabel player2Card2Label = new JLabel();
        playerCards.add(player2Card2Checkbox);
        JPanel player2Card3 = new JPanel();
        JCheckBox player2Card3Checkbox = new JCheckBox();
        JLabel player2Card3Label = new JLabel();
        playerCards.add(player2Card3Checkbox);
        JPanel player2Card4 = new JPanel();
        JCheckBox player2Card4Checkbox = new JCheckBox();
        JLabel player2Card4Label = new JLabel();
        playerCards.add(player2Card4Checkbox);
        JPanel player2Card5 = new JPanel();
        JCheckBox player2Card5Checkbox = new JCheckBox();
        JLabel player2Card5Label = new JLabel();
        playerCards.add(player2Card5Checkbox);
        JPanel player2Card6 = new JPanel();
        JCheckBox player2Card6Checkbox = new JCheckBox();
        JLabel player2Card6Label = new JLabel();
        playerCards.add(player2Card6Checkbox);
        JPanel player2Card7 = new JPanel();
        JCheckBox player2Card7Checkbox = new JCheckBox();
        JLabel player2Card7Label = new JLabel();
        playerCards.add(player2Card7Checkbox);
        JPanel player3Card1 = new JPanel();
        JCheckBox player3Card1Checkbox = new JCheckBox();
        JLabel player3Card1Label = new JLabel();
        playerCards.add(player3Card1Checkbox);
        JPanel player3Card2 = new JPanel();
        JCheckBox player3Card2Checkbox = new JCheckBox();
        JLabel player3Card2Label = new JLabel();
        playerCards.add(player3Card2Checkbox);
        JPanel player3Card3 = new JPanel();
        JCheckBox player3Card3Checkbox = new JCheckBox();
        JLabel player3Card3Label = new JLabel();
        playerCards.add(player3Card3Checkbox);
        JPanel player3Card4 = new JPanel();
        JCheckBox player3Card4Checkbox = new JCheckBox();
        JLabel player3Card4Label = new JLabel();
        playerCards.add(player3Card4Checkbox);
        JPanel player3Card5 = new JPanel();
        JCheckBox player3Card5Checkbox = new JCheckBox();
        JLabel player3Card5Label = new JLabel();
        playerCards.add(player3Card5Checkbox);
        JPanel player3Card6 = new JPanel();
        JCheckBox player3Card6Checkbox = new JCheckBox();
        JLabel player3Card6Label = new JLabel();
        playerCards.add(player3Card6Checkbox);
        JPanel player3Card7 = new JPanel();
        JCheckBox player3Card7Checkbox = new JCheckBox();
        JLabel player3Card7Label = new JLabel();
        playerCards.add(player3Card7Checkbox);
        JPanel player4Card1 = new JPanel();
        JCheckBox player4Card1Checkbox = new JCheckBox();
        JLabel player4Card1Label = new JLabel();
        playerCards.add(player4Card1Checkbox);
        JPanel player4Card2 = new JPanel();
        JCheckBox player4Card2Checkbox = new JCheckBox();
        JLabel player4Card2Label = new JLabel();
        playerCards.add(player4Card2Checkbox);
        JPanel player4Card3 = new JPanel();
        JCheckBox player4Card3Checkbox = new JCheckBox();
        JLabel player4Card3Label = new JLabel();
        playerCards.add(player4Card3Checkbox);
        JPanel player4Card4 = new JPanel();
        JCheckBox player4Card4Checkbox = new JCheckBox();
        JLabel player4Card4Label = new JLabel();
        playerCards.add(player4Card4Checkbox);
        JPanel player4Card5 = new JPanel();
        JCheckBox player4Card5Checkbox = new JCheckBox();
        JLabel player4Card5Label = new JLabel();
        playerCards.add(player4Card5Checkbox);
        JPanel player4Card6 = new JPanel();
        JCheckBox player4Card6Checkbox = new JCheckBox();
        JLabel player4Card6Label = new JLabel();
        playerCards.add(player4Card6Checkbox);
        JPanel player4Card7 = new JPanel();
        JCheckBox player4Card7Checkbox = new JCheckBox();
        JLabel player4Card7Label = new JLabel();
        playerCards.add(player4Card7Checkbox);
        playerHandDisplay.setSize(1215, 700);
        playerHandDisplay.setLayout(new GridLayout(0, 8));
        playerHandDisplay.add(player1);
        playerHandDisplay.add(player1Card1);
        playerHandDisplay.add(player1Card2);
        playerHandDisplay.add(player1Card3);
        playerHandDisplay.add(player1Card4);
        playerHandDisplay.add(player1Card5);
        playerHandDisplay.add(player1Card6);
        playerHandDisplay.add(player1Card7);
        playerHandDisplay.add(emptyPanel1);
        playerHandDisplay.add(player1Card1Checkbox);
        playerHandDisplay.add(player1Card2Checkbox);
        playerHandDisplay.add(player1Card3Checkbox);
        playerHandDisplay.add(player1Card4Checkbox);
        playerHandDisplay.add(player1Card5Checkbox);
        playerHandDisplay.add(player1Card6Checkbox);
        playerHandDisplay.add(player1Card7Checkbox);
        playerHandDisplay.add(player2);
        playerHandDisplay.add(player2Card1);
        playerHandDisplay.add(player2Card2);
        playerHandDisplay.add(player2Card3);
        playerHandDisplay.add(player2Card4);
        playerHandDisplay.add(player2Card5);
        playerHandDisplay.add(player2Card6);
        playerHandDisplay.add(player2Card7);
        playerHandDisplay.add(emptyPanel2);
        playerHandDisplay.add(player2Card1Checkbox);
        playerHandDisplay.add(player2Card2Checkbox);
        playerHandDisplay.add(player2Card3Checkbox);
        playerHandDisplay.add(player2Card4Checkbox);
        playerHandDisplay.add(player2Card5Checkbox);
        playerHandDisplay.add(player2Card6Checkbox);
        playerHandDisplay.add(player2Card7Checkbox);
        playerHandDisplay.add(player3);
        playerHandDisplay.add(player3Card1);
        playerHandDisplay.add(player3Card2);
        playerHandDisplay.add(player3Card3);
        playerHandDisplay.add(player3Card4);
        playerHandDisplay.add(player3Card5);
        playerHandDisplay.add(player3Card6);
        playerHandDisplay.add(player3Card7);
        playerHandDisplay.add(emptyPanel3);
        playerHandDisplay.add(player3Card1Checkbox);
        playerHandDisplay.add(player3Card2Checkbox);
        playerHandDisplay.add(player3Card3Checkbox);
        playerHandDisplay.add(player3Card4Checkbox);
        playerHandDisplay.add(player3Card5Checkbox);
        playerHandDisplay.add(player3Card6Checkbox);
        playerHandDisplay.add(player3Card7Checkbox);
        playerHandDisplay.add(player4);
        playerHandDisplay.add(player4Card1);
        playerHandDisplay.add(player4Card2);
        playerHandDisplay.add(player4Card3);
        playerHandDisplay.add(player4Card4);
        playerHandDisplay.add(player4Card5);
        playerHandDisplay.add(player4Card6);
        playerHandDisplay.add(player4Card7);
        playerHandDisplay.add(emptyPanel4);
        playerHandDisplay.add(player4Card1Checkbox);
        playerHandDisplay.add(player4Card2Checkbox);
        playerHandDisplay.add(player4Card3Checkbox);
        playerHandDisplay.add(player4Card4Checkbox);
        playerHandDisplay.add(player4Card5Checkbox);
        playerHandDisplay.add(player4Card6Checkbox);
        playerHandDisplay.add(player4Card7Checkbox);
        playerHandDisplay.setVisible(true);
    }

    /**
     * Creates a radio dial button selection for a new difficulty
     * 
     * @param label - The name of the difficulty
     * @param buttonGroup - The group of difficulty buttons (so only one can be selected)
     * @author Aiden T
     */
    private JRadioButton createDifficultyButton(String label, ButtonGroup buttonGroup) {
        JRadioButton difficulty = new JRadioButton(label);
        buttonGroup.add(difficulty);

        difficulty.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected difficulty: " + label);
                difficultyLevel = label;
            }
        });
        difficulty.setFont(new Font(null, 0, 50));

        return difficulty;
    }

    /**
     * Creates a button of a city at a location on the background image
     * 
     * @param cityName - The name of a city
     * @param coordinateX - The X coordinate of the city button
     * @param coordinateY - The Y coordinate of the city button
     * @param width - The width of the button
     * @param height - The height of the button
     * @author Aiden T
     */
    private void createCityButton(String cityName, Integer coordinateX, Integer coordinateY, Integer width, Integer height) {
        JButton city = new JButton(cityName);
        city.setLocation(coordinateX, coordinateY);
        city.setSize(width, height);
        city.setFont(new Font(null, 0, 8));

        background.add(city);
        gameBoardButtons.add(city);
    }
}