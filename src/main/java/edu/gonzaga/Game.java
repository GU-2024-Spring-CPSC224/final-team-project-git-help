package edu.gonzaga;

import java.util.ArrayList;
import java.util.List;

public class Game {

    Gameboard gameboard;

    /**
     * Creates a gameboard object with the cities and players initialized.
     * 
     * @author Aiden T
     */
    public Game(ArrayList<Player> playerList, String difficulty) {
        ArrayList<Cure> cureList = createCureList();
        ArrayList<City> cityList = createCityList();

        this.gameboard = new Gameboard(cityList, cureList, playerList, difficulty);
    }

    /**
     * Creates an ArrayList of the 4 cures to start the game
     * 
     * @return 4 cures of the 4 different colors in order - red, blue, yellow, and black
     * @author Aiden T
     */
    private ArrayList<Cure> createCureList() {
        Cure redCure = new Cure(Color.RED);
        Cure blueCure = new Cure(Color.BLUE);
        Cure yellowCure = new Cure(Color.YELLOW);
        Cure blackCure = new Cure(Color.BLACK);

        ArrayList<Cure> cureList = new ArrayList<Cure>();
        cureList.add(redCure);
        cureList.add(blueCure);
        cureList.add(yellowCure);
        cureList.add(blackCure);

        return cureList;
    }

    /**
     * Creates a single city and adds it to the list of cities
     * 
     * @param cityList - The list of cities
     * @param color - Color of the newly-created city
     * @param name - A string of the name of the city
     * @return The city object
     * @author Aiden T
     */
    private City createCity(ArrayList<City> cityList, Color color, String name) {
        City newCity = new City(color, name);
        cityList.add(newCity);

        return newCity;
    }

    /**
     * A default-value constructor function that creates a city, adds it to the list of cities, and connects it to other specified cities.
     * 
     * @param cityList - The list of cities
     * @param color - Color of the newly-created city
     * @param name - A string of the name of the city
     * @param connection1 - A city to connect to, null otherwise
     * @param connection2 - A city to connect to, null otherwise
     * @param connection3 - A city to connect to, null otherwise
     * @return The city object
     * @author Aiden T
     */
    private City createCity(ArrayList<City> cityList, Color color, String name, City connection1, City connection2, City connection3) {
        City newCity = createCity(cityList, color, name);
        
        if (connection1 != null) {
            newCity.createConnection(connection1);
        } 
        if (connection2 != null) {
            newCity.createConnection(connection2);
        } 
        if (connection3 != null) {
            newCity.createConnection(connection3);
        } 

        return newCity;
    }

    /**
     * Creates a city with a research station, and adds it to the list of cities.
     * 
     * @param cityList - The list of cities
     * @param color - Color of the newly-created city
     * @param name - A string of the name of the city
     * @param researchStation - True if the city should have a research station
     * @return The city object
     * @author Aiden T
     */
    private City createCity(ArrayList<City> cityList, Color color, String name, Boolean researchStation) {
        City newCity = new City(color, name, researchStation);
        cityList.add(newCity);

        return newCity;
    }

    /**
     * Creates 49 cities of all 4 colors, and connects them all up like on the game board.
     * 
     * @return A complete list of cities that are all set up to reflect the board
     * @author Aiden T
     */
    private ArrayList<City> createCityList() {
        ArrayList<City> cityList = new ArrayList<City>();

        // Blue cities
        City atlanta = createCity(cityList, Color.BLUE, "Atlanta", true);
        City chicago = createCity(cityList, Color.BLUE, "Chicago", atlanta, null, null);
        City sanFrancisco = createCity(cityList, Color.BLUE, "San Francisco", chicago, null, null);
        City montreal = createCity(cityList, Color.BLUE, "Montreal", chicago, null, null);
        City newYork = createCity(cityList, Color.BLUE, "New York", montreal, null, null);
        City washington = createCity(cityList, Color.BLUE, "Washington", montreal, newYork, atlanta);
        City london = createCity(cityList, Color.BLUE, "London", newYork, null, null);
        City madrid = createCity(cityList, Color.BLUE, "Madrid", newYork, london, null);
        City paris = createCity(cityList, Color.BLUE, "Paris", madrid, london, null);
        City essen = createCity(cityList, Color.BLUE, "Essen", paris, london, null);
        City milan = createCity(cityList, Color.BLUE, "Milan", paris, essen, null);
        City saintPetersburg = createCity(cityList, Color.BLUE, "Saint Petersburg", essen, null, null);

        // Yellow cities
        City losAngeles = createCity(cityList, Color.YELLOW, "Los Angeles", sanFrancisco, chicago, null);
        City mexicoCity = createCity(cityList, Color.YELLOW, "Mexico City", losAngeles, chicago, null);
        City miami = createCity(cityList, Color.YELLOW, "Miami", atlanta, washington, mexicoCity);
        City lima = createCity(cityList, Color.YELLOW, "Lima", mexicoCity, null, null);
        City bogota = createCity(cityList, Color.YELLOW, "Bogota", mexicoCity, miami, lima);
        City santiago = createCity(cityList, Color.YELLOW, "Santiago", lima, null, null);
        City buenosAires = createCity(cityList, Color.YELLOW, "Buenos Aires", bogota, null, null);
        City saoPaulo = createCity(cityList, Color.YELLOW, "Sao Paulo", bogota, buenosAires, null);
        City lagos = createCity(cityList, Color.YELLOW, "Lagos", saoPaulo, null, null);
        City kinshasa = createCity(cityList, Color.YELLOW, "Kinshasa", lagos, null, null);
        City johannesburg = createCity(cityList, Color.YELLOW, "Johannesburg", kinshasa, null, null);
        City khartoum = createCity(cityList, Color.YELLOW, "Khartoum", lagos, kinshasa, johannesburg);

        // Black cities
        City algiers = createCity(cityList, Color.BLACK, "Algiers", madrid, paris, null);
        City istanbul = createCity(cityList, Color.BLACK, "Istanbul", algiers, milan, saintPetersburg);
        City cairo = createCity(cityList, Color.BLACK, "Cairo", algiers, istanbul, khartoum);
        City baghdad = createCity(cityList, Color.BLACK, "Baghdad", cairo, istanbul, null);
        City moscow = createCity(cityList, Color.BLACK, "Moscow", istanbul, saintPetersburg, null);
        City tehran = createCity(cityList, Color.BLACK, "Tehran", moscow, baghdad, null);
        City riyadh = createCity(cityList, Color.BLACK, "Riyadh", cairo, baghdad, null);
        City karachi = createCity(cityList, Color.BLACK, "Karachi", riyadh, baghdad, tehran);
        City delhi = createCity(cityList, Color.BLACK, "Delhi", karachi, tehran, null);
        City mumbai = createCity(cityList, Color.BLACK, "Mumbai", karachi, delhi, null);
        City chennai = createCity(cityList, Color.BLACK, "Chennai", mumbai, delhi, null);
        City kolkata = createCity(cityList, Color.BLACK, "Kolkata", delhi, chennai, null);

        // Red cities
        City bangkok = createCity(cityList, Color.RED, "Bangkok", chennai, kolkata, null);
        City hongKong = createCity(cityList, Color.RED, "Hong Kong", kolkata, bangkok, null);
        City jakarta = createCity(cityList, Color.RED, "Jakarta", chennai, bangkok, null);
        City hoChiMinhCity = createCity(cityList, Color.RED, "Ho Chi Minh City", jakarta, bangkok, hongKong);
        City manila = createCity(cityList, Color.RED, "Manila", hongKong, hoChiMinhCity, sanFrancisco);
        City taipei = createCity(cityList, Color.RED, "Taipei", hongKong, manila, null);
        City shanghai = createCity(cityList, Color.RED, "Shanghai", hongKong, taipei, null);
        City beijing = createCity(cityList, Color.RED, "Beijing", shanghai, null, null);
        City seoul = createCity(cityList, Color.RED, "Seoul", beijing, shanghai, null);
        City tokyo = createCity(cityList, Color.RED, "Tokyo", shanghai, seoul, sanFrancisco);
        City osaka = createCity(cityList, Color.RED, "Osaka", taipei, tokyo, null);
        City sydney = createCity(cityList, Color.RED, "Sydney", jakarta, manila, losAngeles);

        return cityList;
    }
}
