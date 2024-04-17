package edu.gonzaga;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CureTest {
    /**
     * Tests that a cure can be assigned a color
     * 
     * @author Aiden T
     */
    @Test
    void cureConstructor1() {
        Color desiredValue = Color.BLUE;
        Cure testCure = new Cure(Color.BLUE);

        Assertions.assertEquals(desiredValue, testCure.getColor());
    }

    /**
     * Tests that a cure displays the proper status by default
     * 
     * @author Aiden T
     */
    @Test
    void cureStatus1() {
        Integer desiredValue = 0;
        Cure testCure = new Cure(Color.BLUE);

        testCure.getStatus();

        Assertions.assertEquals(desiredValue, testCure.getStatus());
    }

    /**
     * Tests that a cure displays the proper status after being cured
     * 
     * @author Aiden T
     */
    @Test
    void cureStatus2() {
        Integer desiredValue = 1;
        Cure testCure = new Cure(Color.BLUE);

        testCure.cure();

        Assertions.assertEquals(desiredValue, testCure.getStatus());
    }

    /**
     * Tests that a cure displays the proper status after being eradicated
     * 
     * @author Aiden T
     */
    @Test
    void cureStatus3() {
        Integer desiredValue = 2;
        Cure testCure = new Cure(Color.BLUE);

        testCure.cure();
        testCure.eradicate();

        Assertions.assertEquals(desiredValue, testCure.getStatus());
    }

    /**
     * Tests that a cure can't be eradicated before it is cured
     * 
     * @author Aiden T
     */
    @Test
    void cureStatus4() {
        Integer desiredValue = 0;
        Cure testCure = new Cure(Color.BLACK);

        testCure.eradicate();

        Assertions.assertEquals(desiredValue, testCure.getStatus());
    }

    /**
     * Tests that a cure can't be double cured
     * 
     * @author Aiden T
     */
    @Test
    void cureStatus5() {
        Integer desiredValue = 1;
        Cure testCure = new Cure(Color.YELLOW);

        testCure.cure();
        testCure.cure();

        Assertions.assertEquals(desiredValue, testCure.getStatus());
    }

    /**
     * Tests that a cure can't be double eradicated
     * 
     * @author Aiden T
     */
    @Test
    void cureStatus6() {
        Integer desiredValue = 2;
        Cure testCure = new Cure(Color.RED);

        testCure.cure();
        testCure.eradicate();
        testCure.eradicate();

        Assertions.assertEquals(desiredValue, testCure.getStatus());
    }
}
