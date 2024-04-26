package edu.gonzaga;

public class GUIBackend extends GUI{
    
    private void createNewPlayers(){

        for(int i = 0; i < playerNames.size(); i++){

            Player newPlayer = new Player(playerNames.get(i), playerRoles.get(i));

        }
    }

    private String getDifficulty(){

        return this.difficultyLevel;
    }

    private Integer getActionNumber(){

        return this.actionNumber;
    }
}
