package edu.gonzaga;

import java.util.ArrayList;
import java.util.Scanner;

public class Hand {
    private ArrayList<Card> cardList;
    private Integer handLimit;
    private boolean validHand;
    private Integer numCardsToCure;

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
        this.cardList = new ArrayList<Card>();
    }

    /**
     * EVC for a Hand object - sets the limit to 7, declares it a valid hand to start, and takes in the number of cards of one color that will be required to cure 
     * @param cardsToCure 
     * @author Izzy T
     */
    public Hand(Integer cardsToCure) {
        handLimit = 7; // 7 basic/event cards allowed in a Hand 
        validHand = true;
        numCardsToCure = cardsToCure; 
        this.cardList = new ArrayList<Card>();
    }

    /**
     * Sets the amount of cards of one color that this Hand needs in order to cure that color
     * @param amtOfCards how many cards this Hand will need to cure 
     * @author Izzy T
     */
    public void setNumCardsToCure(Integer amtOfCards) {
        numCardsToCure = amtOfCards;
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

    /**
     * Adds one card from the deck into the player's hand 
     * NOTE: Deck Card = basic cards and event cards
     * @param deck the Deck that the player is drawing from (draws from top of it) 
     * @author Izzy T
     */
    public void drawDeckCard(Deck deck) {
        // draw from the back of the Deck array list 
        Card newCard = deck.drawCard();
        this.addCard(newCard);
    }

    /**
     * Searches player's hand for a specific city card 
     * @param city the city whose corresponding card we are searching for in the Hand 
     * @return the Card object that has represenst that city IF it exists in the player's hand, otherwise null
     * @author Izzy T
     */
    public BasicCard searchHandForCity(City city) {
        ArrayList<BasicCard> basicArray = getBasicCardsInHand();
        for (int i = 0; i < basicArray.size(); i++) {
            if(basicArray.get(i).getCity().equals(city)) {
                return basicArray.get(i);
            }
        }
        return null;
    }

    /**
     * Inserts a card into the Hand 
     * @param card
     * @author Izzy T
     */
    public void addCard(Card card) {
        cardList.add(card);
    }

    /**
     * FInds all the basic cards in the hand, so that basic card-specific functions can be done 
     * @return array of only the BasicCard types in the hand 
     * @author Izzy T
     */
    public ArrayList<BasicCard> getBasicCardsInHand() {
        ArrayList<BasicCard> basicArray = new ArrayList<BasicCard>();
        for (Integer i = 0; i < cardList.size(); i++) {
            if (cardList.get(i).getCardType().equals("BasicCard")) {
                BasicCard basicCard = (BasicCard) cardList.get(i); // type casting card to basic card type so I can add it to array for Basic Cards
                basicArray.add(basicCard);
            }
        }
        return basicArray;
    }


    /**
     * Removes the first instance of a card from a hand (if it exists)
     * PASSES TESTS
     * @param card the card that the player wants to remove from their hand - using generic type so that it works for basic and event cards
     * @author Izzy T
     */
    public void discard(Card card) {

        if (!cardList.contains(card)) {
            System.out.println("This card is not in your hand");
        }
        else {
            System.out.println("The card " + card.getCardName() + " was discarded.");
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
            if (cardList.get(i).getCardType().equals("BasicCard")) { 
                BasicCard basicCard = (BasicCard) cardList.get(i); // type casting card to basic card type so I can access its Color
                // then, if that card's color is the same as the color of interest, increment numCardsOfColor
                if (basicCard.getColor() == cardColor) {
                    numCardsOfColor++;
                }
            }
        }
        if (numCardsOfColor >= numCardsToCure) {
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

    /**
     * Returns the number of cards that is needed to make a cure.
     * 
     * @return An integer of the number of cards to cure
     * @author Aiden T
     */
    public Integer getNumCardsToCure() {
        return this.numCardsToCure;
    }
}
