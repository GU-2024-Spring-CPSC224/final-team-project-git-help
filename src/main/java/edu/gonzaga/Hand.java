package edu.gonzaga;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cardList;
    private Integer handLimit;
    private boolean validHand;
    private Integer numCardsToCure;

    private void checkHandFollowsLimit() { // CLARIFY WITH AIDEN on what this is supposed to do
        if (cardList.size() > handLimit) {
            validHand = false;
        }
    }

    /**
     * EVC for a Hand object - sets the limit to 7, declares it a valid hand to start 
     * 
     * @author Izzy T
     */
    public Hand() {
        handLimit = 7; // 7 basic/event cards allowed in a Hand 
        validHand = true;
        numCardsToCure = 5; // 5 cards of a color to cure cities of that color 
    }

    public void drawPlayerCard(Player player) {
        // does not affect the action card limit - might need to increase handLimit to 8 to account for one player card?
        // feel like this should be moved to Player, or put in a new class called PlayerCard 
        
    }
    
    /**
     * Removes the first instance of a card from a hand (if it exists)
     * 
     * @param card the card that the player wants to remove from their hand - using generic type so that it works for basic and event cards
     * @author Izzy T
     */
    public void discard(Card card) {
        if (!cardList.contains(card)) {
            System.out.println("This card is not in your hand");
        }
        else {
            cardList.remove(card);
            System.out.println("The card " + card.getCardType() + " was discarded.");
        }
    }

    /**
     * Checks if there are five cards of the desired color in a players hand - aka # of cards 
     * 
     * @param cardColor the color of the city card we want to check for 
     * @author Izzy T
     */
    public boolean checkCanCure(Color cardColor) {
        Integer numCardsOfColor = 0;
        for (Integer i = 0; i < cardList.size(); i++) {
            if (cardList.get(i).getCardType() == "BasicCard") { // BasicCard is a stand-in for now, not sure what Card.getCardType returns for a BasicCard type 
                
                // need to convert card to BasicCard type in order to get its color 
                // then, if that card's color is the same as the color of interest, increment numCardsOfColor
            }
        }
        if (numCardsOfColor >= 5) {
            return true;
        }
        return false;
    }

    /**
     * Accessor for the private member variable, cardList, in Hand 
     * 
     * @return the list of cards in a Hand, of type ArrayList<Card>
     * @author Izzy T
     */
    public ArrayList<Card> getCardList() {
        return this.cardList;
    }
}
