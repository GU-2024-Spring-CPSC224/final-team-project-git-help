package edu.gonzaga;

public class BasicCard extends Card{
    
    private City city;

    /**
     * Sets 
     * @param newCity
     * @author Izzy T
     */
    public BasicCard(City newCity) {
        this.city = newCity;
        setCardName(newCity.getCityName());
    }

    /**
     * Gets the color of the card
     * 
     * @return The color of the city this card represents
     * @author Aiden T
     */
    public Color getColor(){
        return city.getColor();
    }

    /**
     * Gets the city object
     * 
     * @return The city object this card represents
     * @author Aiden T
     */
    public City getCity(){
        return this.city;
    }

    /**
     * Gets the name of the card
     * 
     * @return The name of the city this card represents
     * @author Aiden T
     */
    public String getCityName(){
        return this.city.getCityName();
    }
}
