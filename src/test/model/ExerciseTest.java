package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests for exercise class
class ExerciseTest {
    private Exercise testExercise;
    private Exercise testExercise1;
    private Exercise testExercise2;

    @BeforeEach
    void runBefore() {
        testExercise = new Exercise("Cardio", "October 12", 1);
        testExercise1 = new Exercise("Swimming", "October 13", 2);
        testExercise2 = new Exercise("Basketball", "March 1", 3);
    }

    @Test
    public void testGetType() {
        assertEquals("Cardio", testExercise.getType());
        assertEquals("Swimming", testExercise1.getType());
        assertEquals("Basketball", testExercise2.getType());
    }

    @Test
    public void testGetDate() {
        assertEquals("October 12", testExercise.getDate());
        assertEquals("October 13", testExercise1.getDate());
        assertEquals("March 1", testExercise2.getDate());
    }

    @Test
    public void testGetHours() {
        assertEquals(1, testExercise.getHours());
        assertEquals(2, testExercise1.getHours());
        assertEquals(3, testExercise2.getHours());
    }

    @Test
    public void testGetName() {
        assertEquals("Cardio: October 12 for 1 hours", testExercise.getName());
        assertEquals("Swimming: October 13 for 2 hours", testExercise1.getName());
        assertEquals("Basketball: March 1 for 3 hours", testExercise2.getName());
    }
}