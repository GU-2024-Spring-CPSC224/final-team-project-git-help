package edu.gonzaga;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CureTest {
    @Test
    void cureConstructor1() {
        Color desiredValue = Color.BLUE;
        Cure testCure = new Cure(Color.BLUE);

        Assertions.assertEquals(desiredValue, testCure.getColor());
    }

    @Test
    void cureStatus1() {
        Integer desiredValue = 0;
        Cure testCure = new Cure(Color.BLUE);

        testCure.getStatus();

        Assertions.assertEquals(desiredValue, testCure.getStatus());
    }

    @Test
    void cureStatus2() {
        Integer desiredValue = 1;
        Cure testCure = new Cure(Color.BLUE);

        testCure.cure();

        Assertions.assertEquals(desiredValue, testCure.getStatus());
    }

    @Test
    void cureStatus3() {
        Integer desiredValue = 2;
        Cure testCure = new Cure(Color.BLUE);

        testCure.cure();
        testCure.eradicate();

        Assertions.assertEquals(desiredValue, testCure.getStatus());
    }

    @Test
    void cureStatus4() {
        Integer desiredValue = 0;
        Cure testCure = new Cure(Color.BLACK);

        testCure.eradicate();

        Assertions.assertEquals(desiredValue, testCure.getStatus());
    }

    @Test
    void cureStatus5() {
        Integer desiredValue = 1;
        Cure testCure = new Cure(Color.YELLOW);

        testCure.cure();
        testCure.cure();

        Assertions.assertEquals(desiredValue, testCure.getStatus());
    }

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
