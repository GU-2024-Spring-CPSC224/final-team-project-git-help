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

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}
