package edu.gonzaga;

public class EventCard extends Card{
    private String name;

    /**
     * Constructor for the EventCard class
     * @param name the name of the event card
     * @return an EventCard object
     * 
     * @author Tony
     */
    public EventCard(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the event card
     * 
     * @return the name of the event card
     * @Author Tony
     */
    public String getName() {
        return name;
    }
    
    /**
     * Plays the event card
     * 
     * @param card the event card to be played
     * @return void
     * @Author Tony
     */
    public void playEvent(EventCard card) {
        switch (card.getName()) {
            case "Airlift":
                playAirlift();
                break;
            case "Government Grant":
                playGovernmentGrant();
                break;
            case "One Quiet Night":
                playOneQuietNight();
                break;
            case "Resilient Population":
                playResilientPopulation();
                break;
            case "Forecast":
                playForecast();
                break;
            default:
                break;
        }
    }

    private void playAirlift() {
        // move any one pawn to any city
    }

    private void playGovernmentGrant() {
        // add a research station to any city
    }

    private void playOneQuietNight() {
        // skip the next infect cities step
    }

    private void playResilientPopulation() {
        // remove any one card in the infection discard pile from the game
    }

    private void playForecast() {
        // draw, look at, and rearrange the top 6 cards of the infection deck
    }
}
