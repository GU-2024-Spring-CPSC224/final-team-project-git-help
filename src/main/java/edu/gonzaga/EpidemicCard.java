package edu.gonzaga;

public class EpidemicCard extends Card {
    
    /**
     * Constructor for the EpidemicCard class
     * @return an EpidemicCard object
     * 
     * @Author Tony
     */
    public void triggerEmpidemic(Gameboard board) {
        // increase the infection rate
        increaseInfectionRate(board);

        // infect
        infect(board);

        // intensify
        intensify(board);
    }

    /**
     * Infects cities
     * 
     * @param board the gameboard
     * @return void
     * @Author Tony
     */
    private void infect(Gameboard board) {
        // infect cities
        board.takeInfectionTurn();
    }

    /**
     * Intensifies the game
     * 
     * @param board the gameboard
     * @return void
     * @Author Tony
     */
    private void intensify(Gameboard board) {
        // shuffle the discard pile and add it to the draw pile
        board.getPlayerDeck().putShuffledDiscardPileOnTop();
    }

    /**
     * Increases the infection rate
     * 
     * @param board the gameboard
     * @return void
     * @Author Tony
     */
    private void increaseInfectionRate(Gameboard board) {
        // increase the infection rate
        board.changeInfectionRate();
    }
}
