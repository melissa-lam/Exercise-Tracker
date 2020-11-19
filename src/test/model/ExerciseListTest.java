package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Tests for exercise list class
public class ExerciseListTest {
    private ExerciseList testExerciseList;
    private Exercise exercise1;
    private Exercise exercise2;
    private Exercise exercise3;
    private Exercise exercise4;
    private Exercise exercise5;

    @BeforeEach
    void runBefore() {
        testExerciseList = new ExerciseList();
        exercise1 = new Exercise("Cardio", "October 12", 1);
        exercise2 = new Exercise("Flexibility", "June 21", 2);
        exercise3 = new Exercise("Cardio", "May 4", 1);
        exercise4 = new Exercise("Endurance", "June 21", 1);
        exercise5 = new Exercise("Flexibility", "October 12", 2);
    }

    @Test
    public void testAddExercise() {
        testExerciseList.addExercise(exercise1);
        assertTrue(testExerciseList.contains(exercise1));
        assertEquals(1, testExerciseList.length());
        testExerciseList.addExercise(exercise2);
        assertTrue(testExerciseList.contains(exercise2));
        assertEquals(2, testExerciseList.length());
        testExerciseList.addExercise(exercise3);
        assertTrue(testExerciseList.contains(exercise3));
        assertEquals(3,testExerciseList.length());
    }

    @Test
    public void testRemoveExercise() {
        testExerciseList.addExercise(exercise1);
        testExerciseList.addExercise(exercise2);
        testExerciseList.addExercise(exercise3);
        assertEquals(3, testExerciseList.length());
        testExerciseList.removeExercise(exercise1);
        assertEquals(2, testExerciseList.length());
        assertTrue(testExerciseList.contains(exercise2));
        assertTrue(testExerciseList.contains(exercise3));
        testExerciseList.removeExercise(exercise2);
        assertEquals(1, testExerciseList.length());
        assertTrue(testExerciseList.contains(exercise3));
        testExerciseList.removeExercise(exercise3);
        assertEquals(0, testExerciseList.length());
    }

    @Test
    public void testRemoveExerciseByIndex() {
        testExerciseList.addExercise(exercise1);
        testExerciseList.addExercise(exercise2);
        testExerciseList.addExercise(exercise3);
        testExerciseList.removeIndex(1);
        assertFalse(testExerciseList.contains(exercise1));
        assertTrue(testExerciseList.contains(exercise2));
        assertTrue(testExerciseList.contains(exercise3));
    }

    @Test
    public void testExercisesFromDate() {
        testExerciseList.addExercise(exercise1);
        testExerciseList.addExercise(exercise2);
        testExerciseList.addExercise(exercise3);
        testExerciseList.addExercise(exercise4);
        testExerciseList.addExercise(exercise5);
        ExerciseList newList = testExerciseList.exercisesFromDate("October 12");
        assertEquals(2, newList.length());
        assertTrue(newList.contains(exercise1));
        assertTrue(newList.contains(exercise5));
        ExerciseList noExercisesFromDate = testExerciseList.exercisesFromDate("May 1");
        assertEquals(0, noExercisesFromDate.length());
    }

    @Test
    public void testExercisesFromType() {
        testExerciseList.addExercise(exercise1);
        testExerciseList.addExercise(exercise2);
        testExerciseList.addExercise(exercise3);
        testExerciseList.addExercise(exercise4);
        testExerciseList.addExercise(exercise5);
        ExerciseList newList = testExerciseList.exercisesFromType("Cardio");
        assertEquals(2, newList.length());
        assertTrue(newList.contains(exercise1));
        assertTrue(newList.contains(exercise3));
        ExerciseList noExercisesFromType = testExerciseList.exercisesFromType("Abs");
        assertEquals(0, noExercisesFromType.length());
    }

    @Test
    public void testGetTotalHours() {
        assertEquals(0, testExerciseList.getTotalHours());
        testExerciseList.addExercise(exercise1);
        testExerciseList.addExercise(exercise2);
        testExerciseList.addExercise(exercise3);
        assertEquals(4, testExerciseList.getTotalHours());
    }

    @Test
    public void testGetTotalHoursForDate() {
        assertEquals(0,testExerciseList.getTotalHoursForDate("June 21"));
        testExerciseList.addExercise(exercise1);
        testExerciseList.addExercise(exercise2);
        testExerciseList.addExercise(exercise3);
        testExerciseList.addExercise(exercise4);
        assertEquals(3, testExerciseList.getTotalHoursForDate("June 21"));
    }

    @Test
    public void testGetTotalHoursForType() {
        assertEquals(0,testExerciseList.getTotalHoursForType("Cardio"));
        testExerciseList.addExercise(exercise1);
        testExerciseList.addExercise(exercise2);
        testExerciseList.addExercise(exercise3);
        testExerciseList.addExercise(exercise4);
        assertEquals(2, testExerciseList.getTotalHoursForType("Cardio"));
    }

    @Test
    public void testGetNames() {
        testExerciseList.addExercise(exercise1);
        testExerciseList.addExercise(exercise2);
        List<String> names = testExerciseList.getNames();
        assertTrue(names.contains("Cardio: October 12 for 1 hours"));
        assertTrue(names.contains("Flexibility: June 21 for 2 hours"));
    }

}
