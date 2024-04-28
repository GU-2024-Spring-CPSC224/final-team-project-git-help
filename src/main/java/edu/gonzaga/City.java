package edu.gonzaga;

import java.util.ArrayList;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class City {
    private ArrayList<Player> playerList = new ArrayList<Player>();
    private ArrayList<City> connections = new ArrayList<City>();
    private ArrayList<Color> infectionCubes = new ArrayList<Color>();
    private Color cityColor;
    private String cityName;
    private Boolean researchStation;
    private final static Boolean DEFAULT_RESEARCH_STATION_STATUS = false;
    private final static Integer MAX_CUBES = 3;


    /**
     * Causes an outbreak in the city, which spreads infection to each connecting city which may cause more outbreaks.
     * 
     * @return The number of outbreaks in total that have occurred (including itself)
     * @author Aiden T
     */
    private Integer outbreak() {
        Integer outbreakCount = 1;

        for (int i = 0; i < this.connections.size(); i++) {
            Integer newOutbreak = connections.get(i).addInfectionCube(cityColor);
            outbreakCount += newOutbreak;
        }

        return outbreakCount;
    }

    public City(Color color, String name) {
        this.cityColor = color;
        this.cityName = name;
        this.researchStation = DEFAULT_RESEARCH_STATION_STATUS;
    }

    public City(Color color, String name, ArrayList<City> connections) {
        this.connections = connections;
        this.cityColor = color;
        this.cityName = name;
        this.researchStation = DEFAULT_RESEARCH_STATION_STATUS;
    }

    public City(Color color, String name, Boolean researchStation) {
        this.cityColor = color;
        this.cityName = name;
        this.researchStation = researchStation;
    }

    /**
     * Creates a connection between this city and another, updates the connections list for both cities.
     * 
     * @param city - The city to create a connection with
     * @author Aiden T
     */
    public void createConnection(City city) {
        if (this.connections.contains(city)) {
            System.err.println("!! Attempt to create a connection with a city it's already connected to !!");
            return;
        }

        this.connections.add(city);
        city.connections.add(this);
    }

    /**
     * Searches for the connections to see if another city object is connected to the city object
     * 
     * @param city - the city to search for
     * @return true if the city is a connection, false if not.
     * @author Aiden T
     */
    public Boolean findConnection(City city) {
        Boolean validConnection = false;

        for (int i = 0; i < this.connections.size(); i++) {
            if (connections.get(i) == city) {
                validConnection = true;
                break;
            }
        }

        return validConnection;
    }

    /**
     * Adds a player to this city
     * 
     * @param player - the player to add
     * @author Aiden T
     */
    public void addPlayer(Player player) {
        this.playerList.add(player);
    }

    /**
     * Removes a player from this city
     * 
     * @param player - The player to remove
     * @author Aiden T
     */
    public void removePlayer(Player player) {
        this.playerList.remove(player);
    }

    /**
     * Gets each player in the city
     * 
     * @return A list of all players currently in the city
     * @author Aiden T
     */
    public ArrayList<Player> getPlayers() {
        return this.playerList;
    }

    /**
     * Adds an infection cube that is the color of the city
     * 
     * @return The number of outbreaks caused by the addition of a cube.
     * @author Aiden T
     */
    public Integer addInfectionCube() {
        return addInfectionCube(cityColor);
    }

    /**
     * Adds a specified color of infection cube to the city.
     * 
     * @param color - The color of the cube
     * @return The number of outbreaks caused by the addition of a cube.
     * @author Aiden T
     */
    public Integer addInfectionCube(Color color) {
        Integer outbreakCount = 0;
        this.infectionCubes.add(color);

        if (this.infectionCubes.size() == (MAX_CUBES + 1)) {

            outbreakCount = this.outbreak();
            
        } else if (this.infectionCubes.size() > (MAX_CUBES + 1)){
            this.infectionCubes.remove(color); // If an outbreak causes another outbreak, it will exceed 4 which isn't possible so remove it.
        } 
        
        return outbreakCount;
    }

    /**
     * Removes an infection cube from a city if it exists.
     * 
     * @author Aiden T
     */
    public void removeInfectionCube(Color color) {
        Boolean result = this.infectionCubes.remove(color);

        if (result != true) {
            System.err.println("!! Attempt to remove an infection cube that doesn't exist !!");
        }
    }

    /**
     * Gives a list of all infection cubes on top of the city.
     * 
     * @return an ArrayList with Colors that represent the infection cubes
     * @author Aiden T
     */
    public ArrayList<Color> getInfectionCubes() {
        return this.infectionCubes;
    }

    /**
     * If the city doesn't have a research station, it adds one to it.
     * 
     * @author Aiden T
     */
    public void addResearchStation() {

        if (this.researchStation == true) {
            System.err.println("!! Attempt to add a research station to a city that already has one !!");
        } else {
            this.researchStation = true;
        }
    }


    /**
     * If the city has a research station, it removes it.
     * 
     * @author Aiden T
     */
    public void removeResearchStation() {

        if (this.researchStation == false) {
            System.err.println("!! Attempt to remove a research station that doesn't exist !!");
        } else {
            this.researchStation = false;
        }
    }

    /**
     * Returns whether a research station exists or not
     * 
     * @return true if there's a research station, false if there's not
     * @author Aiden T
     */
    public Boolean getResearchStation() {
        return this.researchStation;
    }

    /**
     * Gets the color of the disease the city will create in the game.
     * 
     * @return The color of the city
     * @author Aiden T
     */
    public Color getColor() {
        return this.cityColor;
    }

    /**
     * Must be called after an outbreak occurs, it cleans up the number of cubes to 3 in each city that has 4 or more.
     * 
     * @author Aiden T
     */
    public void outbreakCleanup() {
        if (this.getInfectionCubes().size() > MAX_CUBES) {
            removeInfectionCube(cityColor);
        }

        for (City city : this.connections) {
            if (city.getInfectionCubes().size() > MAX_CUBES) {
                city.removeInfectionCube(this.cityColor);
                city.outbreakCleanup();
            }
        }
    }

    /**
     * Gets the name of the city
     * 
     * @return The name of the city object
     * @author Aiden T
     */
    public String getCityName() {
        return this.cityName;
    }
    public boolean getResearchStation(){
        
        return this.researchStation;
    }
}
