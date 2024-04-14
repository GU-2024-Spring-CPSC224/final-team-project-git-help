package edu.gonzaga;

public class EpdemicCard extends Card {
    
    public void triggerEmpidemic() {
        // increase the infection rate
        increaseInfectionRate();

        // infect
        infect();

        // intensify
        intensify();
    }

    private void infect() {
        // infect cities
    }

    private void intensify() {
        // shuffle the discard pile and add it to the draw pile
    }

    private void increaseInfectionRate() {
        // increase the infection rate
    }
}
