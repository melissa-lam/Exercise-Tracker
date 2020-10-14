package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseTest {
    private Exercise testExercise;
    private Exercise testExercise1;

    @BeforeEach
    void runBefore() {
        testExercise = new Exercise("Cardio", "October 12", 1);
        testExercise1 = new Exercise("Swimming", "October 13", 2);
    }

    @Test
    public void testGetType() {
        assertEquals("Cardio", testExercise.getType());
        assertEquals("Swimming", testExercise1.getType());
    }

    @Test
    public void testGetDate() {
        assertEquals("October 12", testExercise.getDate());
        assertEquals("October 13", testExercise1.getDate());
    }

    @Test
    public void testGetHours() {
        assertEquals(1, testExercise.getHours());
        assertEquals(2, testExercise1.getHours());
    }

    @Test
    public void testGetName() {
        assertEquals("Cardio: October 12 for 1 hours", testExercise.getName());
        assertEquals("Swimming: October 13 for 2 hours", testExercise1.getName());
    }
}