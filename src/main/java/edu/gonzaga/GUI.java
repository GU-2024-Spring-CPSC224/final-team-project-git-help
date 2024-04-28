package edu.gonzaga;

import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

public class GUI {

    JFrame pandemicGameFrame = new JFrame("Pandemic!");
    JFrame playerHandDisplay = new JFrame("Player Hands!");
    JFrame cityInfoDisplay;
    ArrayList<JPanel> gameScenes = new ArrayList<JPanel>();
    ArrayList<JButton> gameBoardButtons = new ArrayList<JButton>();
    ArrayList<JButton> startScreenButton = new ArrayList<JButton>();
    ArrayList<JButton> playerActionButtons = new ArrayList<JButton>();
    String difficultyLevel = "";
    ArrayList<String> playerNames = new ArrayList<String>();
    ArrayList<String> playerRoles = new ArrayList<String>();
    GUIBackend backend = new GUIBackend();

    public GUI(){

        difficultyLevel = "Easy";
        playerNames.add("");
        playerNames.add("");
        playerNames.add("");
        playerNames.add("");
    }
    public static void main(String args[]) {

        
        GUI game = new GUI();
        game.pandemicGameFrame.setSize(1472, 908);
        game.pandemicGameFrame.setLayout(new BorderLayout());
        game.pandemicGameFrame.setVisible(true);
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
        pandemicGameFrame.add(gameStartScreen);
        gameStartScreen.setVisible(true);
       
    }

    private void generatePlayerCreationScreen(){

        JPanel playerCreationScreen = new JPanel(new GridLayout(6,0));
        JLabel difficulty = new JLabel("Difficulty", SwingConstants.CENTER);
        difficulty.setFont(new Font(null, 0, 50));
        JLabel playerName = new JLabel("Players", SwingConstants.CENTER);
        playerName.setFont(new Font(null, 0, 50));
        JCheckBox easy = new JCheckBox("Easy");
        easy.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                difficultyLevel = "Easy";
            }
        });
        easy.setFont(new Font(null, 0, 50));
        JCheckBox medium = new JCheckBox("Medium"); 
        medium.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                difficultyLevel = "Medium";
            }
        });   
        medium.setFont(new Font(null, 0, 50));
        JCheckBox hard = new JCheckBox("Hard");
        hard.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                difficultyLevel = "Hard";
            }
        });
        hard.setFont(new Font(null, 0, 50));
        JCheckBox veryHard = new JCheckBox("COVID-19");
        veryHard.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                difficultyLevel = "COVID-19";
            }
        });
        veryHard.setFont(new Font(null, 0, 50));
        JTextField player1NameInput = new JTextField();
        player1NameInput.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                String player1Name = player1NameInput.getText();
                playerNames.add(player1Name);
            }
        });
        player1NameInput.setFont(new Font(null, 0, 20));
        JTextField player2NameInput = new JTextField();
        player2NameInput.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                String player2Name = player2NameInput.getText();
                playerNames.add(player2Name);
            }
        });
        player2NameInput.setFont(new Font(null, 0, 20));
        JTextField player3NameInput = new JTextField();
        player3NameInput.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                String player3Name = player3NameInput.getText();
                playerNames.add(player3Name);
            }
        });
        player3NameInput.setFont(new Font(null, 0, 20));
        JTextField player4NameInput = new JTextField();
        player4NameInput.setFont(new Font(null, 0, 20));
        player4NameInput.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                String player4Name = player4NameInput.getText();
                playerNames.add(player4Name);
            }
        });
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
        }
        else{

            player1 = new JLabel(playerNames.get(0), SwingConstants.CENTER);
            player1.setFont(new Font(null, 0, 50));
        }
        if(playerNames.get(1) == ""){

            player2 = new JLabel("Player 2", SwingConstants.CENTER);
            player2.setFont(new Font(null, 0, 50));
        }
        else{

            player2 = new JLabel(playerNames.get(1), SwingConstants.CENTER);
            player2.setFont(new Font(null, 0, 50));
        }
        if(playerNames.get(2) == ""){

            player3 = new JLabel("Player 3", SwingConstants.CENTER);
            player3.setFont(new Font(null, 0, 50));
        }
        else{

            player3 = new JLabel(playerNames.get(2), SwingConstants.CENTER);
            player3.setFont(new Font(null, 0, 50));
        }
        if(playerNames.get(3) == ""){

            player4 = new JLabel("Player 3", SwingConstants.CENTER);
            player4.setFont(new Font(null, 0, 50));
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

        JFrame gameboard = new JFrame("Pandemic!");
        gameboard.setSize(1472, 908);
        JLabel background = new JLabel(new ImageIcon("./src/main/java/edu/gonzaga/Pandemic_Gameboard.png"));
        gameboard.setContentPane(background);
        background.setSize(1472, 908);
        JButton playerHandDisplay = new JButton("Display Player Hands");
        playerHandDisplay.setSize(300, 300);
        playerHandDisplay.setLocation(200, 400);
        JPanel playerActionOptions = new JPanel(new GridLayout(3, 3));
        playerActionOptions.setSize(300, 300);
        playerActionOptions.setLocation(200, 770);
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
               
                generatePlayerHandDisplayScreen();
            }
        });
        JButton sanFrancisco = new JButton("San Francisco");
        sanFrancisco.setLocation(143, 347);
        sanFrancisco.setSize(80, 30);
        sanFrancisco.setFont(new Font(null, 0, 8));
        JButton atlanta = new JButton("Atlanta");
        atlanta.setLocation(310, 367);
        atlanta.setSize(80, 30);
        atlanta.setFont(new Font(null, 0, 8));
        JButton lasAngeles = new JButton("Las Angeles");
        lasAngeles.setLocation(160, 367);
        lasAngeles.setSize(80, 30);
        lasAngeles.setFont(new Font(null, 0, 8));
        JButton mexicoCity = new JButton("Mexico City");
        mexicoCity.setLocation(244, 440);
        mexicoCity.setSize(80, 30);
        mexicoCity.setFont(new Font(null, 0, 8));
        JButton miami = new JButton("Miami");
        miami.setLocation(348, 408);
        miami.setSize(50, 30);
        miami.setFont(new Font(null, 0, 8));
        JButton chicago = new JButton("Chicago");
        chicago.setLocation(295, 322);
        chicago.setSize(80, 30);
        chicago.setFont(new Font(null, 0, 8));
        JButton washingtonDC = new JButton("Washington DC");
        washingtonDC.setLocation(343, 341);
        washingtonDC.setSize(100, 30);
        washingtonDC.setFont(new Font(null, 0, 8));
        JButton newYork = new JButton("New York");
        newYork.setLocation(356, 330);
        newYork.setSize(80, 30);
        newYork.setFont(new Font(null, 0, 8));
        JButton montreal = new JButton("Montreal");
        montreal.setLocation(357, 300);
        montreal.setSize(80, 30);
        montreal.setFont(new Font(null, 0, 8));
        JButton bogota = new JButton("Bogota");
        bogota.setLocation(354, 508);
        bogota.setSize(80, 30);
        bogota.setFont(new Font(null, 0, 8));
        JButton lima = new JButton("Lima");
        lima.setLocation(358, 582);
        lima.setSize(50, 30);
        lima.setFont(new Font(null, 0, 8));
        JButton saoPaulo = new JButton("São Paulo");
        saoPaulo.setLocation(475, 633);
        saoPaulo.setSize(80, 30);
        saoPaulo.setFont(new Font(null, 0, 8));
        JButton santiago = new JButton("Santiago");
        santiago.setLocation(369, 685);
        santiago.setSize(80, 30);
        santiago.setFont(new Font(null, 0, 8));
        JButton buenosAires = new JButton("Buenos Aires");
        buenosAires.setLocation(430, 687);
        buenosAires.setSize(80, 30);
        buenosAires.setFont(new Font(null, 0, 8));
        JButton madrid = new JButton("Madrid");
        madrid.setLocation(672, 333);
        madrid.setSize(50, 30);
        madrid.setFont(new Font(null, 0, 8));
        JButton algiers = new JButton("Algiers");
        algiers.setLocation(702, 357);
        algiers.setSize(50, 30);
        algiers.setFont(new Font(null, 0, 8));
        JButton paris = new JButton("Paris");
        paris.setLocation(700, 279);
        paris.setSize(50, 30);
        paris.setFont(new Font(null, 0, 8));
        JButton london = new JButton("London");
        london.setLocation(680, 259);
        london.setSize(80, 30);
        london.setFont(new Font(null, 0, 8));
        JButton essen = new JButton("Essen");
        essen.setLocation(740, 264);
        essen.setSize(50, 30);
        essen.setFont(new Font(null, 0, 8));
        JButton milan = new JButton("Milan");
        milan.setLocation(730, 305);
        milan.setSize(50, 30);
        milan.setFont(new Font(null, 0, 8));
        JButton stPetersburg = new JButton("St. Petersburg");
        stPetersburg.setLocation(812, 196);
        stPetersburg.setSize(80, 30);
        stPetersburg.setFont(new Font(null, 0, 8));
        JButton moscow = new JButton("Moscow");
        moscow.setLocation(847, 229);
        moscow.setSize(80, 30);
        moscow.setFont(new Font(null, 0, 8));
        JButton istanbul = new JButton("Istanbul");
        istanbul.setLocation(806, 327);
        istanbul.setSize(80, 30);
        istanbul.setFont(new Font(null, 0, 8));
        JButton cairo = new JButton("Cairo");
        cairo.setLocation(826, 392);
        cairo.setSize(50, 30);
        cairo.setFont(new Font(null, 0, 8));
        JButton baghdad = new JButton("Baghdad");
        baghdad.setLocation(876, 368);
        baghdad.setSize(80, 30);
        baghdad.setFont(new Font(null, 0, 8));
        JButton tehran = new JButton("Tehran");
        tehran.setLocation(918, 356);
        tehran.setSize(50, 30);
        tehran.setFont(new Font(null, 0, 8));
        JButton riyadh = new JButton("Riyadh");
        riyadh.setLocation(902, 413);
        riyadh.setSize(50, 30);
        riyadh.setFont(new Font(null, 0, 8));
        JButton khartoum = new JButton("Khartoum");
        khartoum.setLocation(825, 459);
        khartoum.setSize(80, 30);
        khartoum.setFont(new Font(null, 0, 8));
        JButton lagos = new JButton("Lagos");
        lagos.setLocation(710, 498);
        lagos.setSize(50, 30);
        lagos.setFont(new Font(null, 0, 8));
        JButton kinshasa = new JButton("Kinshasa");
        kinshasa.setLocation(748, 545);
        kinshasa.setSize(80, 30);
        kinshasa.setFont(new Font(null, 0, 8));
        JButton johannesburg = new JButton("Johannesburg");
        johannesburg.setLocation(804, 646);
        johannesburg.setSize(80, 30);
        johannesburg.setFont(new Font(null, 0, 8));
        JButton karachi = new JButton("Karachi");
        karachi.setLocation(977, 413);
        karachi.setSize(80, 30);
        karachi.setFont(new Font(null, 0, 8));
        JButton delhi = new JButton("Delhi");
        delhi.setLocation(1032, 397);
        delhi.setSize(50, 30);
        delhi.setFont(new Font(null, 0, 8));
        JButton mumbai = new JButton("Mumbai");
        mumbai.setLocation(1003, 441);
        mumbai.setSize(80, 30);
        mumbai.setFont(new Font(null, 0, 8));
        JButton chennai = new JButton("Chennai");
        chennai.setLocation(1034, 466);
        chennai.setSize(80, 30);
        chennai.setFont(new Font(null, 0, 8));
        JButton kolkata = new JButton("Kolkata");
        kolkata.setLocation(1072, 424);
        kolkata.setSize(80, 30);
        kolkata.setFont(new Font(null, 0, 8));
        JButton bangkok = new JButton("Bangkok");
        bangkok.setLocation(1125, 465);
        bangkok.setSize(80, 30);
        bangkok.setFont(new Font(null, 0, 8));
        JButton jakarta = new JButton("Jakarta");
        jakarta.setLocation(1153, 555);
        jakarta.setSize(80, 30);
        jakarta.setFont(new Font(null, 0, 8));
        JButton sydney = new JButton("Sydney");
        sydney.setLocation(1349, 687);
        sydney.setSize(80, 30);
        sydney.setFont(new Font(null, 0, 8));
        JButton hoChiMinhCity = new JButton("Ho Chi Minh City");
        hoChiMinhCity.setLocation(1153, 481);
        hoChiMinhCity.setSize(100, 30);
        hoChiMinhCity.setFont(new Font(null, 0, 8));
        JButton manila = new JButton("Manila");
        manila.setLocation(1215, 462);
        manila.setSize(80, 30);
        manila.setFont(new Font(null, 0, 8));
        JButton hongKong = new JButton("Hong Kong");
        hongKong.setLocation(1185, 424);
        hongKong.setSize(80, 30);
        hongKong.setFont(new Font(null, 0, 8));
        JButton taipei = new JButton("Taipei");
        taipei.setLocation(1217, 412);
        taipei.setSize(80, 30);
        taipei.setFont(new Font(null, 0, 8));
        JButton shanghai = new JButton("Shanghai");
        shanghai.setLocation(1216, 383);
        shanghai.setSize(80, 30);
        shanghai.setFont(new Font(null, 0, 8));
        JButton beijing = new JButton("Beijing");
        beijing.setLocation(1195, 333);
        beijing.setSize(80, 30);
        beijing.setFont(new Font(null, 0, 8));
        JButton seoul = new JButton("Seoul");
        seoul.setLocation(1242, 347);
        seoul.setSize(80, 30);
        seoul.setFont(new Font(null, 0, 8));
        JButton tokyo = new JButton("Tokyo");
        tokyo.setLocation(1297, 359);
        tokyo.setSize(80, 30);
        tokyo.setFont(new Font(null, 0, 8));
        JButton osaka = new JButton("Osaka");
        osaka.setLocation(1278, 371);
        osaka.setSize(80, 30);
        osaka.setFont(new Font(null, 0, 8));
        background.add(sanFrancisco);
        background.add(atlanta);
        background.add(lasAngeles);
        background.add(mexicoCity);
        background.add(miami);
        background.add(chicago);
        background.add(washingtonDC);
        background.add(newYork);
        background.add(montreal);
        background.add(bogota);
        background.add(lima);
        background.add(saoPaulo);
        background.add(santiago);
        background.add(buenosAires);
        background.add(madrid);
        background.add(algiers);
        background.add(paris);
        background.add(london);
        background.add(essen);
        background.add(milan);
        background.add(stPetersburg);
        background.add(moscow);
        background.add(istanbul);
        background.add(cairo);
        background.add(baghdad);
        background.add(tehran);
        background.add(riyadh);
        background.add(khartoum);
        background.add(lagos);
        background.add(kinshasa);
        background.add(johannesburg);
        background.add(karachi);
        background.add(delhi);
        background.add(mumbai);
        background.add(chennai);
        background.add(kolkata);
        background.add(bangkok);
        background.add(jakarta);
        background.add(sydney);
        background.add(hoChiMinhCity);
        background.add(manila);
        background.add(hongKong);
        background.add(taipei);
        background.add(shanghai);
        background.add(beijing);
        background.add(seoul);
        background.add(tokyo);
        background.add(osaka);
        gameBoardButtons.add(sanFrancisco);
        gameBoardButtons.add(atlanta);
        gameBoardButtons.add(lasAngeles);
        gameBoardButtons.add(mexicoCity);
        gameBoardButtons.add(miami);
        gameBoardButtons.add(chicago);
        gameBoardButtons.add(washingtonDC);
        gameBoardButtons.add(newYork);
        gameBoardButtons.add(montreal);
        gameBoardButtons.add(bogota);
        gameBoardButtons.add(lima);
        gameBoardButtons.add(saoPaulo);
        gameBoardButtons.add(santiago);
        gameBoardButtons.add(buenosAires);
        gameBoardButtons.add(madrid);
        gameBoardButtons.add(algiers);
        gameBoardButtons.add(paris);
        gameBoardButtons.add(london);
        gameBoardButtons.add(essen);
        gameBoardButtons.add(milan);
        gameBoardButtons.add(stPetersburg);
        gameBoardButtons.add(moscow);
        gameBoardButtons.add(istanbul);
        gameBoardButtons.add(cairo);
        gameBoardButtons.add(baghdad);
        gameBoardButtons.add(tehran);
        gameBoardButtons.add(riyadh);
        gameBoardButtons.add(khartoum);
        gameBoardButtons.add(lagos);
        gameBoardButtons.add(kinshasa);
        gameBoardButtons.add(johannesburg);
        gameBoardButtons.add(karachi);
        gameBoardButtons.add(delhi);
        gameBoardButtons.add(mumbai);
        gameBoardButtons.add(chennai);
        gameBoardButtons.add(kolkata);
        gameBoardButtons.add(bangkok);
        gameBoardButtons.add(jakarta);
        gameBoardButtons.add(sydney);
        gameBoardButtons.add(hoChiMinhCity);
        gameBoardButtons.add(manila);
        gameBoardButtons.add(hongKong);
        gameBoardButtons.add(taipei);
        gameBoardButtons.add(shanghai);
        gameBoardButtons.add(beijing);
        gameBoardButtons.add(seoul);
        gameBoardButtons.add(tokyo);
        gameBoardButtons.add(osaka);
        background.setLayout(null);
        gameboard.setLayout(null);
        gameboard.setSize(1472, 908);
        gameboard.setVisible(true);
    }   
    private void generatePlayerHandDisplayScreen(){

        JLabel player1 = new JLabel(playerNames.get(0), SwingConstants.CENTER);
        player1.setFont(new Font(null, 0, 50));
        JLabel player2 = new JLabel(playerNames.get(1), SwingConstants.CENTER);
        player2.setFont(new Font(null, 0, 50));
        JLabel player3 = new JLabel(playerNames.get(2), SwingConstants.CENTER);
        player3.setFont(new Font(null, 0, 50));
        JLabel player4 = new JLabel(playerNames.get(3), SwingConstants.CENTER);
        JPanel player1Card1 = new JPanel();
        JPanel player1Card2 = new JPanel();
        JPanel player1Card3 = new JPanel();
        JPanel player1Card4 = new JPanel();
        JPanel player1Card5 = new JPanel();
        JPanel player1Card6 = new JPanel();
        JPanel player1Card7 = new JPanel();
        JPanel player2Card1 = new JPanel();
        JPanel player2Card2 = new JPanel();
        JPanel player2Card3 = new JPanel();
        JPanel player2Card4 = new JPanel();
        JPanel player2Card5 = new JPanel();
        JPanel player2Card6 = new JPanel();
        JPanel player2Card7 = new JPanel();
        JPanel player3Card1 = new JPanel();
        JPanel player3Card2 = new JPanel();
        JPanel player3Card3 = new JPanel();
        JPanel player3Card4 = new JPanel();
        JPanel player3Card5 = new JPanel();
        JPanel player3Card6 = new JPanel();
        JPanel player3Card7 = new JPanel();
        JPanel player4Card1 = new JPanel();
        JPanel player4Card2 = new JPanel();
        JPanel player4Card3 = new JPanel();
        JPanel player4Card4 = new JPanel();
        JPanel player4Card5 = new JPanel();
        JPanel player4Card6 = new JPanel();
        JPanel player4Card7 = new JPanel();
        player4.setFont(new Font(null, 0, 50));
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
        playerHandDisplay.add(player2);
        playerHandDisplay.add(player2Card1);
        playerHandDisplay.add(player2Card2);
        playerHandDisplay.add(player2Card3);
        playerHandDisplay.add(player2Card4);
        playerHandDisplay.add(player2Card5);
        playerHandDisplay.add(player2Card6);
        playerHandDisplay.add(player2Card7);
        playerHandDisplay.add(player3);
        playerHandDisplay.add(player3Card1);
        playerHandDisplay.add(player3Card2);
        playerHandDisplay.add(player3Card3);
        playerHandDisplay.add(player3Card4);
        playerHandDisplay.add(player3Card5);
        playerHandDisplay.add(player3Card6);
        playerHandDisplay.add(player3Card7);
        playerHandDisplay.add(player4);
        playerHandDisplay.add(player4Card1);
        playerHandDisplay.add(player4Card2);
        playerHandDisplay.add(player4Card3);
        playerHandDisplay.add(player4Card4);
        playerHandDisplay.add(player4Card5);
        playerHandDisplay.add(player4Card6);
        playerHandDisplay.add(player4Card7);
        playerHandDisplay.setVisible(true);
    }

}