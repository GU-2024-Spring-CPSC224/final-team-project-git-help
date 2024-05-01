package edu.gonzaga;

import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

//Give and get knowledge
//Discover cure

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
    ArrayList<JPanel> introScreenPanels = new ArrayList<>();
    ArrayList<JButton> gameBoardButtons = new ArrayList<>();
    ArrayList<JButton> playerActionButtons = new ArrayList<>();
    ArrayList<JCheckBox> playerCards = new ArrayList<>();
    ArrayList<JCheckBox> playerChoices = new ArrayList<>();
    ArrayList<JRadioButton> actionSelectionCards = new ArrayList<>();
    String difficultyLevel;
    ArrayList<String> playerNames = new ArrayList<>();
    ArrayList<String> playerRoles = new ArrayList<>();
    static GUIBackend backend = new GUIBackend();
    DocumentListener docListener;
    Game gameObject;
    City destinationCity = null;
    Boolean cardIsInHand;

    public GUI(){
        // Default values
        difficultyLevel = "Easy";
        playerNames.add("");
        playerNames.add("");
        playerNames.add("");
        playerNames.add("");

        playerRoles.add("");
        playerRoles.add("");
        playerRoles.add("");
        playerRoles.add("");
    }
    public static void main(String[] args) {

        GUI game = new GUI();
       
        game.pandemicGameFrame.setVisible(true);
        game.pandemicGameFrame.setBounds(0, 0, 1100, 800);
        game.pandemicGameFrame.setLocation(225, 50);
        game.pandemicGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.generateGameStartScreen();
 
    }

    private void generateGameStartScreen(){

        JPanel gameStartScreen = new JPanel();
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                if(introScreenPanels.size() > 1){

                    gameStartScreen.setVisible(false);
                    pandemicGameFrame.add(introScreenPanels.get(1));
                    introScreenPanels.get(1).setVisible(true);
                }
                else{

                    gameStartScreen.setVisible(false);
                    generatePlayerCreationScreen();
                }
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
        introScreenPanels.add(gameStartScreen);
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
        introScreenPanels.add(playerCreationScreen);
        playerCreationScreen.setVisible(true);
        
    }

    private void generateRoleSelectionScreen(){
        // TODO: needs refactoring 
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
            playerNames.set(0,"Player 1");
        }
        else{

            player1 = new JLabel(playerNames.get(0), SwingConstants.CENTER);
            player1.setFont(new Font(null, 0, 50));
        }
        if(playerNames.get(1) == ""){

            player2 = new JLabel("Player 2", SwingConstants.CENTER);
            player2.setFont(new Font(null, 0, 50));
            playerNames.set(1,"Player 2");
        }
        else{

            player2 = new JLabel(playerNames.get(1), SwingConstants.CENTER);
            player2.setFont(new Font(null, 0, 50));
        }
        if(playerNames.get(2) == ""){

            player3 = new JLabel("Player 3", SwingConstants.CENTER);
            player3.setFont(new Font(null, 0, 50));
            playerNames.set(2,"Player 3");
        }
        else{

            player3 = new JLabel(playerNames.get(2), SwingConstants.CENTER);
            player3.setFont(new Font(null, 0, 50));
        }
        if(playerNames.get(3) == ""){

            player4 = new JLabel("Player 4", SwingConstants.CENTER);
            player4.setFont(new Font(null, 0, 50));
            playerNames.set(3, "Player 4");
        }
        else{

            player4 = new JLabel(playerNames.get(3), SwingConstants.CENTER);
            player4.setFont(new Font(null, 0, 50));
        }
        String roles[] = {"", "Dispatcher", "Operations Expert", "Medic", "Researcher", "Quarantine Specialist", "Scientist", "Contingency Planner"};
        JComboBox<String> roleSelection1 = new JComboBox<String>(roles);
        roleSelection1.setFont(new Font(null, 0, 15));
        roleSelection1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                String role1 = roleSelection1.getSelectedItem().toString();
                if (!role1.equals("")) { // "" is the default value
                    playerRoles.set(0, role1);
                }
            }
        });
        JComboBox<String> roleSelection2 = new JComboBox<String>(roles);
        roleSelection2.setFont(new Font(null, 0, 15));
        roleSelection2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                String role2 = roleSelection2.getSelectedItem().toString();
                if (!role2.equals("")) { // "" is the default value
                    playerRoles.set(1, role2);
                }
            }
        });
        JComboBox<String> roleSelection3 = new JComboBox<String>(roles);
        roleSelection3.setFont(new Font(null, 0, 15));
        roleSelection3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                String role3 = roleSelection3.getSelectedItem().toString();
                if (!role3.equals("")) { // "" is the default value
                    playerRoles.set(2, role3);
                }
            }
        });
        JComboBox<String> roleSelection4 = new JComboBox<String>(roles);
        roleSelection4.setFont(new Font(null, 0, 15));
        roleSelection4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                String role4 = roleSelection4.getSelectedItem().toString();
                if (!role4.equals("")) { // "" is the default value
                    playerRoles.set(3, role4);
                }
            }
        });
        JButton back = new JButton("Back");
        back.setFont(new Font(null, 0, 50));
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                roleSelectionScreen.setVisible(false);
                pandemicGameFrame.add(introScreenPanels.get(1));
                introScreenPanels.get(1).setVisible(true);
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
        introScreenPanels.add(roleSelectionScreen);
        roleSelectionScreen.setVisible(true);
    }

    /**
     * refresh action counter after each action
     * @param game
     * @param label
     * @author Tony
     */
    protected void refreshActionCounter(Game game, JLabel label) {
        label.setText("Actions Remaining: " + game.getGameboard().getCurrentTurnPlayer().getActionCount().toString()); 
        label.revalidate();
        label.repaint();         
    }


    private void generateGameboardScreen(GUIBackend backend) {
        // Create Players
        System.out.println(playerNames.toString());
        System.out.println(playerRoles.toString());
        gameObject = new Game(playerNames, playerRoles, backend.getDifficulty());

        // Set up the gameboard 
        JFrame gameboard = new JFrame("Pandemic!");
        gameboard.setResizable(false);
        gameboard.setSize(1472, 908);
        gameboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameboard.setContentPane(background);

        JButton playerHandDisplay = new JButton("Display Player Hands");
        playerHandDisplay.setSize(300, 40);
        playerHandDisplay.setLocation(0, 500);

        JPanel playerActionOptions = new JPanel(new GridLayout(3, 3));
        playerActionOptions.setSize(300, 300);
        playerActionOptions.setLocation(0, 550);

        // Set up the player turn info
        JPanel playerTurnInfo = new JPanel(new GridLayout(2, 0));
        playerTurnInfo.setSize(350, 100);
        playerTurnInfo.setLocation(0, 0);
        JLabel playerName = new JLabel("Current Player: " 
        + gameObject.getGameboard().getCurrentTurnPlayer().getName(), SwingConstants.CENTER);
        playerTurnInfo.add(playerName);
        JLabel playerActionNumber = new JLabel("Actions Remaining: " 
        + gameObject.getGameboard().getCurrentTurnPlayer().getActionCount(), SwingConstants.CENTER);
        playerTurnInfo.add(playerActionNumber);
        gameboard.add(playerTurnInfo);

        // Handler for Drive feature
        JButton drive = new JButton("Drive");
        playerActionButtons.add(drive); 
        drive.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                backend.doDrive(gameObject, playerActionNumber);       
            }
        });

        // Handler for Direct Flight
        JButton directFlight = new JButton("Direct Flight");
        playerActionButtons.add(directFlight);
        directFlight.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                backend.doDirectFlight(gameObject, playerActionNumber);
            }
        });

        // Handler for Shuttle Flight
        JButton shuttleFlight = new JButton("Shuttle Flight"); 
        playerActionButtons.add(shuttleFlight);
        shuttleFlight.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //backend.shuttleFlightButtonHandler();
                backend.doShuttleFlight(destinationCity, gameObject, playerActionNumber);
            }
        });

        // Handler for Charter Flight
        JButton charterFlight = new JButton("Charter Flight");
        playerActionButtons.add(charterFlight);
        charterFlight.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //backend.charterFlightButtonHandler();
                backend.doCharterFlight(destinationCity, gameObject, playerActionNumber);
            }
        });

        // Handler for Build Research Station
        JButton buildResearchStation = new JButton("Build Research Station");
        playerActionButtons.add(buildResearchStation);
        buildResearchStation.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                backend.buildResearchStationHandler(gameObject, playerActionNumber);
                generatePlayerHandDisplayScreen(gameObject);
            }
        });

        // Handler for Give Knowledge
        JButton giveKnowledge = new JButton("Give Knowledge");
        playerActionButtons.add(giveKnowledge);
        giveKnowledge.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //backend.giveKnowledgeButtonHandler();
                playerActionNumber.setText("Actions Remaining: "+ gameObject.getGameboard().getCurrentTurnPlayer().getActionCount().toString());       
            }
        });

        // Handler for Get Knowledge
        JButton getKnowledge = new JButton("Get Knowledge");
        playerActionButtons.add(getKnowledge);
        getKnowledge.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //backend.getKnowledgeButtonHandler();
                playerActionNumber.setText("Actions Remaining: " + gameObject.getGameboard().getCurrentTurnPlayer().getActionCount().toString());       
            }
        });

        // Handler for Treat Disease
        JButton treatDisease = new JButton("Treat Disease");
        playerActionButtons.add(treatDisease);
        treatDisease.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //backend.treatDiseaseButtonHandler();
                playerActionNumber.setText("Actions Remaining: " + gameObject.getGameboard().getCurrentTurnPlayer().getActionCount().toString());       
            }
        });

        // Handler for Discover Cure
        JButton discoverCure = new JButton("Discover Cure");
        playerActionButtons.add(discoverCure);
        discoverCure.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                backend.cureDiseaseHandler(gameObject, playerActionNumber);  
                generatePlayerHandDisplayScreen(gameObject);
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

        // Handler for Forfeit Turn
        JButton forfeitTurn = new JButton("Forfeit Turn");
        forfeitTurn.setSize(100, 50);
        forfeitTurn.setLocation(1300, 0);
        gameboard.add(forfeitTurn);
        forfeitTurn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {

                backend.forfeitTurnHandler(gameObject);
                gameObject.getGameboard().getCurrentTurnPlayer().takeTurn(0, null, null, null, null, null);
                refreshActionCounter(gameObject, playerActionNumber);  

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
        
        for(JButton button : gameBoardButtons){
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
        gameboard.setVisible(true);

        FlowControl flowControl = new FlowControl(gameObject, backend);
        // flowControl.runGame();
        // TODO: Add win/lose screen
    }   

    public void generatePlayerHandDisplayScreen(Game gameObject) {
        playerHandDisplay.getContentPane().removeAll();
        playerHandDisplay.setSize(1215, 700);
        playerHandDisplay.setLayout(new GridLayout(0, 8));

        createCardScreen(playerHandDisplay);
        playerHandDisplay.setVisible(true);
    }

    /**
     * Creates the card screen for the player hand display
     * 
     * @param playerHandDisplay - The JFrame to display the player hands on
     */
    public void createCardScreen(JFrame playerHandDisplay) {
        System.out.println("Creating the Card Screen");
        
        for (int i = 0; i < gameObject.getGameboard().getPlayerList().size(); i++) {
            JLabel player = new JLabel(playerNames.get(i), SwingConstants.CENTER);
            player.setFont(new Font(null, Font.PLAIN, 25));
            playerHandDisplay.add(player);

            ArrayList<JLabel> cardLabels = new ArrayList<JLabel>();

            // 7 cards
            for (int j = 0; j < 7; j++) {  // 7 for 7 cards maximum
                JLabel tempLabel = new JLabel();
                if (j < gameObject.getGameboard().getPlayer(i).getHand().getCardList().size()) {
                    tempLabel = new JLabel(gameObject.getGameboard().getPlayer(i).getHand().getCardList().get(j).getCardName());
                } 
                else {
                    tempLabel = new JLabel();
                }

                cardLabels.add(tempLabel);
                playerHandDisplay.add(tempLabel);
            }

            // Add an empty panel for spacing
            JCheckBox playerCheckBox = new JCheckBox();
            playerHandDisplay.add(playerCheckBox);
            playerChoices.add(playerCheckBox);
            
            // 7 checkboxes
            for (int j = 0; j < 7; j++) {
                JRadioButton tempRadioBox = new JRadioButton();

                final Integer index = j;

                tempRadioBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String cityCard = cardLabels.get(index).getText();
                        Player currentPlayer = gameObject.getGameboard().getCurrentTurnPlayer();

                        if (tempRadioBox.isSelected()) {
                            backend.setSelectedCard(currentPlayer, cityCard, true);
                        } else {
                            backend.setSelectedCard(currentPlayer, cityCard, false);
                        }
                        
                    }
                });

                if (i != gameObject.getGameboard().getCurrentTurnPlayerIndex()) {
                    tempRadioBox.setEnabled(false);
                }
                if (j >= gameObject.getGameboard().getCurrentTurnPlayer().getHand().getCardList().size()) { 
                    tempRadioBox.setEnabled(false);
                }

                actionSelectionCards.add(tempRadioBox);
                playerHandDisplay.add(tempRadioBox);
            }
        }
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