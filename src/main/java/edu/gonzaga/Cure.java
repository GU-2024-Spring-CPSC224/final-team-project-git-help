package edu.gonzaga;

public class Cure {
    private Color color;
    private Boolean cured;
    private Boolean eradicated;
    private static final Boolean DEFAULT_VALUE = false;

    public Cure(Color color) {
        this.color = color;
        this.cured = DEFAULT_VALUE;
        this.eradicated = DEFAULT_VALUE;
    }

    /**
     * Gets the status of the curing process for a disease
     * 
     * @return 2 for eradication, 1 for cured, and 0 for uncured
     * @author Aiden T
     */
    public Integer getStatus() {
        if (this.eradicated) {
            return 2;
        } else if (this.cured) {
            return 1;
        } else {
            return 0;
        }
    }
    
    /**
     * Sets the disease to be cured
     * 
     * @author Aiden T
     */
    public void cure() {
        if (this.cured == true) {
            System.err.println("!! Attempt to cure a disease that's already cured !!");
            return;
        }

        this.cured = true;
    }

    /**
     * Sets the disease to be eradicated
     * 
     * @author Aiden T
     */
    public void eradicate() {
        if (this.eradicated == true) {
            System.err.println("!! Attempt to eradicate a disease that's already eradicated !!");
            return;
        } else if (this.cured != true) {
            System.err.println("!! Attempt to eradicate a disease that hasn't been cured !!");
            return;
        }

        this.eradicated = true;
    }

    /**
     * Gets the color of the cure
     * 
     * @return the color of disease the cure represents
     * @author Aiden T
     */
    public Color getColor() {
        return this.color;
    }
}
