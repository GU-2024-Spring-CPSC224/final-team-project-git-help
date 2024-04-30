package edu.gonzaga;
public class FlowControl {
    private Game game;
    private GUIBackend backend;

    public FlowControl(Game game, GUIBackend backend) {
        this.game = game;
        this.backend = backend;
    }

    public void runGame() {
        // if not win condition
        while(checkLoseCondition() == false && checkWinCondition() == false) {
            for (int i = 0; i < game.getGameboard().getPlayerList().size(); i++) {
                game.getGameboard().getPlayer(i).takeTurn(null, null, null, null, null, null);
            }
        }
    }

    /**
     * Checks if the game has been lost
     * 
     * @return true if the game has been lost, false otherwise
     * @Author Tony
     */
    private Boolean checkLoseCondition() {
        if (game.getGameboard().getOutbreakCount() >= 8 || 
            game.getGameboard().getPlayerDeck().getDrawPile().size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the game has been won
     * 
     * @return true if the game has been won, false otherwise
     * @Author Tony
     */
    private Boolean checkWinCondition() {
        if (game.getGameboard().checkAllCured() == true) {
            return true;
        }
        return false;
    }
}
