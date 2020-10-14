package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    }

    @Test
    public void testRemoveExercise() {
        testExerciseList.addExercise(exercise1);
        testExerciseList.addExercise(exercise2);
        assertEquals(2, testExerciseList.length());
        testExerciseList.removeExercise(exercise1);
        assertEquals(1, testExerciseList.length());
        assertTrue(testExerciseList.contains(exercise2));
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
    }

    @Test
    public void testGetTotalHours() {
        testExerciseList.addExercise(exercise1);
        testExerciseList.addExercise(exercise2);
        testExerciseList.addExercise(exercise3);
        assertEquals(4, testExerciseList.getTotalHours());
    }

    @Test
    public void testGetTotalHoursForDate() {
        testExerciseList.addExercise(exercise1);
        testExerciseList.addExercise(exercise2);
        testExerciseList.addExercise(exercise3);
        testExerciseList.addExercise(exercise4);
        assertEquals(3, testExerciseList.getTotalHoursForDate("June 21"));
    }

    @Test
    public void testGetTotalHoursForType() {
        testExerciseList.addExercise(exercise1);
        testExerciseList.addExercise(exercise2);
        testExerciseList.addExercise(exercise3);
        testExerciseList.addExercise(exercise4);
        assertEquals(2, testExerciseList.getTotalHoursForType("Cardio"));
    }

}
