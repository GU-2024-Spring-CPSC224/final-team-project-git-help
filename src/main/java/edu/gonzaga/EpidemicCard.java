package edu.gonzaga;

import java.util.ArrayList;

public class EpidemicCard extends Card {
    
    /**
     * Constructor for the EpidemicCard class
     * @return an EpidemicCard object
     * 
     * @Author Tony
     */
    public void triggerEpidemic(Gameboard board) {
        // increase the infection rate
        increaseInfectionRate(board);

        // infect
        infect(board);

        // intensify
        intensify(board);
    }

    /**
     * Increases the infection rate
     * 
     * @param board the gameboard
     * @return void
     * @Author Tony
     */
    private void increaseInfectionRate(Gameboard board) {
        board.changeInfectionRate();
    }

    /**
     * Infects cities
     * 
     * @param board the gameboard
     * @return void
     * @Author Tony
     */
    private void infect(Gameboard board) {
        // draw cards
        ArrayList<Color> eradicatedColors = board.getEradicatedColors();
        BasicCard cityCard = (BasicCard)board.getInfectionDeck().drawBottomCard();
        City tempCity = cityCard.getCity();

        // infect the city
        if (!eradicatedColors.contains(tempCity.getColor())){
            for (int i = 0; i < 3; i++) {  // i < 3 because epidemic cards only add 3 cubes
                Integer numOutbreaks = tempCity.addInfectionCube();
                board.setOutbreakCount(numOutbreaks);
                tempCity.outbreakCleanup();
            }
        }

        // discard the card
        board.getInfectionDeck().discardCard(cityCard);
    }

    /**
     * Intensifies the game
     * Shuffle the discard pile and add it to the draw pile
     * 
     * @param board the gameboard
     * @return void
     * @Author Tony
     */
    private void intensify(Gameboard board) {
        board.getInfectionDeck().putShuffledDiscardPileOnTop();
    }

    // TODO: remove the epidemic card from the game to prevent it from being drawn again
}
