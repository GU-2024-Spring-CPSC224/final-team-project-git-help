package edu.gonzaga;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CityTest {
    @Test
    void cityConstructor1() {
        Boolean desiredValue = true;
        City testCity = new City(Color.BLUE, "Los Angeles", true);

        Assertions.assertEquals(testCity.getResearchStation(), desiredValue);
    }

    @Test
    void cityConnection1() {
        Boolean desiredValue = true;

        City testCity1 = new City(Color.BLUE, "Los Angeles", true);
        City testCity2 = new City(Color.BLACK, "San Francisco");

        testCity1.createConnection(testCity2);

        Assertions.assertEquals(testCity1.findConnection(testCity2), desiredValue);
    }

    @Test
    void cityConnection2() {
        Boolean desiredValue = true;

        City testCity1 = new City(Color.BLUE, "Los Angeles", true);
        City testCity2 = new City(Color.BLACK, "San Francisco");

        testCity1.createConnection(testCity2);

        Assertions.assertEquals(testCity2.findConnection(testCity1), desiredValue);
    }

    @Test
    void cityConnection3() {
        Boolean desiredValue = false;

        City testCity1 = new City(Color.BLUE, "Los Angeles", true);
        City testCity2 = new City(Color.BLACK, "San Francisco");


        Assertions.assertEquals(testCity2.findConnection(testCity1), desiredValue);
    }

    @Test
    void cityInfection1() {
        ArrayList<Color> desiredValue = new ArrayList<Color>();
        desiredValue.add(Color.BLACK);

        City testCity1 = new City(Color.BLUE, "Los Angeles", true);
        testCity1.addInfectionCube(Color.BLACK);

        Assertions.assertEquals(testCity1.getInfectionCubes(), desiredValue);
    }

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

        Assertions.assertEquals(testCity1.getInfectionCubes(), desiredValue);
    }

    @Test
    void cityRemoveInfection1() {
        ArrayList<Color> desiredValue = new ArrayList<Color>();
        desiredValue.add(Color.BLUE);

        City testCity1 = new City(Color.BLUE, "Los Angeles", true);
        testCity1.addInfectionCube(Color.BLUE);
        testCity1.addInfectionCube(Color.BLUE);
        testCity1.removeInfectionCube(Color.BLUE);

        Assertions.assertEquals(testCity1.getInfectionCubes(), desiredValue);
    }

    @Test
    void cityRemoveInfection2() {
        ArrayList<Color> desiredValue = new ArrayList<Color>();
        desiredValue.add(Color.BLUE);

        City testCity1 = new City(Color.BLUE, "Los Angeles", true);
        testCity1.addInfectionCube(Color.BLUE);
        testCity1.addInfectionCube(Color.BLUE);
        testCity1.removeInfectionCube(Color.BLACK);

        Assertions.assertEquals(testCity1.getInfectionCubes(), desiredValue);
    }

    @Test
    void cityResearchStation1() {
        Boolean desiredValue = false;

        City testCity1 = new City(Color.BLUE, "Los Angeles", true);
        testCity1.removeResearchStation();

        Assertions.assertEquals(testCity1.getResearchStation(), desiredValue);
    }

    @Test
    void cityResearchStation2() {
        Boolean desiredValue = true;

        City testCity1 = new City(Color.BLUE, "Los Angeles", true);
        testCity1.addResearchStation();

        Assertions.assertEquals(testCity1.getResearchStation(), desiredValue);
    }

    @Test
    void cityResearchStation3() {
        Boolean desiredValue = false;

        City testCity1 = new City(Color.BLUE, "Los Angeles");
        testCity1.removeResearchStation();

        Assertions.assertEquals(testCity1.getResearchStation(), desiredValue);
    }

    @Test
    void cityResearchStation4() {
        Boolean desiredValue = true;

        City testCity1 = new City(Color.BLUE, "Los Angeles");
        testCity1.addResearchStation();

        Assertions.assertEquals(testCity1.getResearchStation(), desiredValue);
    }

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

        Assertions.assertEquals(result, desiredValue);
    }

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

        Assertions.assertEquals(testCity1.getInfectionCubes(), desiredValue);
    }

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

        Assertions.assertEquals(result, desiredValue);
    }
}
