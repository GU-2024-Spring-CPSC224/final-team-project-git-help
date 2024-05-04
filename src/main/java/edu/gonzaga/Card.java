package edu.gonzaga;

abstract class Card {
    
    private String cardName;

    /**
     * Helps distinuish between different card types.
     * 
     * @return The name of the class
     * @author Aiden T
     */
    public String getCardType(){
        return this.getClass().getSimpleName();
    }

    /**
     * Gets the name of the card
     * 
     * @return The name of the card
     * @Author Tony
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * Sets the name of the card
     * 
     * @param cardName The name of the card
     * @Author Tony
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}
