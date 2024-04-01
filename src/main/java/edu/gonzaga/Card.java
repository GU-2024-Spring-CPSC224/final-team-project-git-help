package edu.gonzaga;

abstract class Card {

    /**
     * Helps distinuish between different card types.
     * 
     * @return The name of the class
     * @author Aiden T
     */
    public String getCardType(){
        return this.getClass().getSimpleName();
    }
}
