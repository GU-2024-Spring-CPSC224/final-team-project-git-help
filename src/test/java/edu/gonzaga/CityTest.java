package edu.gonzaga;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CityTest {
    /**
     * Tests that a city can be constructed
     * 
     * @author Aiden T
     */
    @Test
    void cityConstructor1() {
        Boolean desiredValue = true;
        City testCity = new City(Color.BLUE, "Los Angeles", true);

        Assertions.assertEquals(desiredValue, testCity.getResearchStation());
    }

    /**
     * Tests that a city can be connected
     * 
     * @author Aiden T
     */
    @Test
    void cityConnection1() {
        Boolean desiredValue = true;

        City testCity1 = new City(Color.BLUE, "Los Angeles", true);
        City testCity2 = new City(Color.BLACK, "San Francisco");

        testCity1.createConnection(testCity2);

        Assertions.assertEquals(desiredValue, testCity1.findConnection(testCity2));
    }

    /**
     * Tests that a city can be connected and it works both ways
     * 
     * @author Aiden T
     */
    @Test
    void cityConnection2() {
        Boolean desiredValue = true;

        City testCity1 = new City(Color.BLUE, "Los Angeles", true);
        City testCity2 = new City(Color.BLACK, "San Francisco");

        testCity1.createConnection(testCity2);

        Assertions.assertEquals(desiredValue, testCity2.findConnection(testCity1));
    }

    /**
     * Tests that a city won't falsely be connected
     * 
     * @author Aiden T
     */
    @Test
    void cityConnection3() {
        Boolean desiredValue = false;

        City testCity1 = new City(Color.BLUE, "Los Angeles", true);
        City testCity2 = new City(Color.BLACK, "San Francisco");


        Assertions.assertEquals(desiredValue, testCity2.findConnection(testCity1));
    }

    /**
     * Tests that a city can have cubes added
     * 
     * @author Aiden T
     */
    @Test
    void cityInfection1() {
        ArrayList<Color> desiredValue = new ArrayList<Color>();
        desiredValue.add(Color.BLACK);

        City testCity1 = new City(Color.BLUE, "Los Angeles", true);
        testCity1.addInfectionCube(Color.BLACK);

        Assertions.assertEquals(desiredValue, testCity1.getInfectionCubes());
    }

    /**
     * Tests that a city can have cubes of multiple colors added
     * 
     * @author Aiden T
     */
    @Test
    void cityInfection2() {
        ArrayList<Color> desiredValue = new ArrayList<Color>();
        desiredValue.add(Color.BLUE);
        desiredValue.add(Color.BLUE);
        desiredValue.add(Color.BLACK);

        City testCity1 = new City(Color.BLUE, "Los Angeles", true);
        testCity1.addInfectionCube(Color.BLUE);
        testCity1.addInfectionCube(Color.BLUE);
        testCity1.addInfectionCube(Color.BLACK);

        Assertions.assertEquals(desiredValue, testCity1.getInfectionCubes());
    }

    /**
     * Tests that a city can remove cubes
     * 
     * @author Aiden T
     */
    @Test
    void cityRemoveInfection1() {
        ArrayList<Color> desiredValue = new ArrayList<Color>();
        desiredValue.add(Color.BLUE);

        City testCity1 = new City(Color.BLUE, "Los Angeles", true);
        testCity1.addInfectionCube(Color.BLUE);
        testCity1.addInfectionCube(Color.BLUE);
        testCity1.removeInfectionCube(Color.BLUE);

        Assertions.assertEquals(desiredValue, testCity1.getInfectionCubes());
    }

    /**
     * Tests that a city can remove cubes of multiple colors
     * 
     * @author Aiden T
     */
    @Test
    void cityRemoveInfection2() {
        ArrayList<Color> desiredValue = new ArrayList<Color>();
        desiredValue.add(Color.BLUE);
        desiredValue.add(Color.BLUE);

        City testCity1 = new City(Color.BLUE, "Los Angeles", true);
        testCity1.addInfectionCube(Color.BLUE);
        testCity1.addInfectionCube(Color.BLUE);
        testCity1.removeInfectionCube(Color.BLACK);

        Assertions.assertEquals(desiredValue, testCity1.getInfectionCubes());
    }

    /**
     * Tests that a city can remove a research station
     * 
     * @author Aiden T
     */
    @Test
    void cityResearchStation1() {
        Boolean desiredValue = false;

        City testCity1 = new City(Color.BLUE, "Los Angeles", true);
        testCity1.removeResearchStation();

        Assertions.assertEquals(desiredValue, testCity1.getResearchStation());
    }

    /**
     * Tests that a city can't double add a research station
     * 
     * @author Aiden T
     */
    @Test
    void cityResearchStation2() {
        Boolean desiredValue = true;

        City testCity1 = new City(Color.BLUE, "Los Angeles", true);
        testCity1.addResearchStation();

        Assertions.assertEquals(desiredValue, testCity1.getResearchStation());
    }

    /**
     * Tests that a city can't double remove a research station
     * 
     * @author Aiden T
     */
    @Test
    void cityResearchStation3() {
        Boolean desiredValue = false;

        City testCity1 = new City(Color.BLUE, "Los Angeles");
        testCity1.removeResearchStation();

        Assertions.assertEquals(desiredValue, testCity1.getResearchStation());
    }

    /**
     * Tests that a city can add a research station
     * 
     * @author Aiden T
     */
    @Test
    void cityResearchStation4() {
        Boolean desiredValue = true;

        City testCity1 = new City(Color.BLUE, "Los Angeles");
        testCity1.addResearchStation();

        Assertions.assertEquals(desiredValue, testCity1.getResearchStation());
    }

    /**
     * Tests that a city can outbreak once properly
     * 
     * @author Aiden T
     */
    @Test
    void outbreakTest() {
        Integer desiredValue = 1;

        City testCity1 = new City(Color.BLUE, "Los Angeles");
        City testCity2 = new City(Color.BLACK, "San Francisco");
        
        testCity1.createConnection(testCity2);

        for (int i = 0; i < 3; i++) {
            testCity1.addInfectionCube();
        }

        Integer result = testCity1.addInfectionCube();

        Assertions.assertEquals(desiredValue, result);
    }

    /**
     * Tests that a city will only have 3 cubes after outbreaking
     * 
     * @author Aiden T
     */
    @Test
    void outbreakTest2() {
        ArrayList<Color> desiredValue = new ArrayList<Color>();
        desiredValue.add(Color.BLUE);
        desiredValue.add(Color.BLUE);
        desiredValue.add(Color.BLUE);

        City testCity1 = new City(Color.BLUE, "Los Angeles");
        City testCity2 = new City(Color.BLACK, "San Francisco");
        
        testCity1.createConnection(testCity2);

        for (int i = 0; i < 4; i++) {
            testCity1.addInfectionCube();
        }

        testCity1.outbreakCleanup();

        Assertions.assertEquals(desiredValue, testCity1.getInfectionCubes());
    }

    /**
     * Tests that multiple cities can outbreak properly
     * 
     * @author Aiden T
     */
    @Test
    void outbreakTest3() {
        Integer desiredValue = 4;

        City testCity1 = new City(Color.BLUE, "Los Angeles");
        City testCity2 = new City(Color.BLACK, "San Francisco");
        City testCity3 = new City(Color.RED, "Mysterious City");
        City testCity4 = new City(Color.YELLOW, "Beach City");
        
        testCity1.createConnection(testCity2);
        testCity1.createConnection(testCity3);
        testCity1.createConnection(testCity4);
        testCity3.createConnection(testCity4);

        for (int i = 0; i < 3; i++) {
            testCity1.addInfectionCube();
            testCity2.addInfectionCube();
            testCity3.addInfectionCube();
            testCity4.addInfectionCube();
        }

        Integer result = testCity1.addInfectionCube();
        testCity1.outbreakCleanup();

        Assertions.assertEquals(desiredValue, result);
    }
}
