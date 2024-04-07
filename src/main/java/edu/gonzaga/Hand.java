package edu.gonzaga;

import java.util.ArrayList;
import java.util.Scanner;

public class Hand {
    private ArrayList<Card> cardList;
    private Integer handLimit;
    private boolean validHand;
    private Integer numCardsToCure;
    private Card playerCard; // change to type PlayerCard when that class is created

    /**
     * Checks if Hand object follows limit, and sets it to resulting value of t/f
     * @returns true if hand has less or equal number of objects as the limit, or false otherwise
     * @author Izzy T 
     */
    public boolean doesHandFollowLimit() { 
        if (cardList.size() > handLimit) {
            validHand = false;
        }
        else {
            validHand = true;
        }
        return validHand;
    }

    /**
     * DVC for a Hand object - sets the limit to 7, declares it a valid hand to start 
     * 
     * @author Izzy T
     */
    public Hand() {
        handLimit = 7; // 7 basic/event cards allowed in a Hand 
        validHand = true;
        numCardsToCure = 5; // 5 cards of a color to cure cities of that color 
    }

    /**
     * Prints contents of Hand 
     * @author Izzy T
     */
    public void printHand() {
        for (Integer i = 0; i < cardList.size(); i++) {
            System.out.println(i + ": " + cardList.get(i));
        }
    }
 
    /**
     * Removes the first instance of a card from a hand (if it exists)
     * PASSES TESTS
     * @param card the card that the player wants to remove from their hand - using generic type so that it works for basic and event cards
     * @author Izzy T
     */
    public void setCardList(ArrayList<Card> newCardList) {
        this.cardList = newCardList;
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
            System.out.println("The card " + card.getCardType() + " was discarded.");
            cardList.remove(card);
        }
    }

    /**
     * Checks if there are five cards of the desired color in a players hand - aka # of cards 
     * PASSES TESTS
     * @param cardColor the color of the city cards that we want to check for 
     * @author Izzy T
     */
    public boolean checkCanCure(Color cardColor) {
        Integer numCardsOfColor = 0;
        for (Integer i = 0; i < cardList.size(); i++) {
            if (cardList.get(i).getCardType().equals("BasicCard")) { // BasicCard is a stand-in for now, not sure what Card.getCardType() returns for a BasicCard type 
                BasicCard basicCard = (BasicCard) cardList.get(i); // type casting card to basic card type so I can access its Color
                // then, if that card's color is the same as the color of interest, increment numCardsOfColor
                if (basicCard.getColor() == cardColor) {
                    numCardsOfColor++;
                }
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

    /**
     * Restricts number of cards in a Hand - if card # exceeds max num allowed, then it requests one card be discarded until doesHandFollowLimit() is true
     * 
     * @author Izzy T
     */
    public void makeHandValid(Scanner userInput) {
        int targetVal; // index of card list to delete a card from 
        while (!this.doesHandFollowLimit()) {
            System.out.println("Your hand exceeds the maximum card amount of " + handLimit + ". What card would you like to get rid of? ");    
            printHand();
            // if the user input is a number
            if (userInput.hasNextInt()) {
                Integer input = userInput.nextInt();
                // if the user input is a number that IS a valid index
                 if (input < cardList.size()) {
                    targetVal = input;
                    discard(cardList.get(targetVal));
                }
            }
            else {
                System.out.println("Invalid index. ");  
                userInput.next(); // allows scanner to take in new input 
            }
        }
    }
}
