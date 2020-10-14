package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseTest {
    private Exercise testExercise;

    @BeforeEach
    void runBefore() {
        testExercise = new Exercise("Cardio", "October 12", 1);
    }

    @Test
    public void testGetType() {
        assertEquals("Cardio", testExercise.getType());
    }

    @Test
    public void testGetDate() {
        assertEquals("October 12", testExercise.getDate());
    }

    @Test
    public void testGetHours() {
        assertEquals(1, testExercise.getHours());
    }

}