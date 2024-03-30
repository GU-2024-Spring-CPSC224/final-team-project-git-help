package edu.gonzaga;

import java.util.ArrayList;

public class Player {
    
    String playerName;
    Integer actionCount;
    ArrayList<Hand> playerHand;
    String role;

    public Player(String playerName, String role){

        this.playerName = playerName;
        this.role = role;
        this.actionCount = 4;
        playerHand = new ArrayList<>();
    }

    public Player(String role){

        this.playerName = "Anonymous Player"; 
        this.role = role;
        this.actionCount = 4;
        playerHand = new ArrayList<>();
    }

    private void drive(){

    }

    private void directFlight(){
        
    }

    private void shuttleFlight(){
        
    }

    private void charterFlight(){
        
    }

    private void buildResearchStation(){
        
    }

    private void giveKnowledge(){
        
    }

    private void getKnowledge(){
        
    }

    private void treatDisease(){
        
    }

    private void discoverCure(){
        
    }

    public void useEventCard(){
        
    }

    public void takeTurn(){

    }

    public String getRole(){

        return role;
    }
}
