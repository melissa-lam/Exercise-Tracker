package model;

import exceptions.EmptyException;
import exceptions.NotInListException;
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
    public void testRemoveExerciseEmptyException() {
        try {
            testExerciseList.removeExercise(exercise1);
            fail("The exercise list is empty.");
        } catch (EmptyException e) {
            //expected
        } catch (NotInListException e) {
            fail("EmptyException should have been thrown.");
        }
    }

    @Test
    public void testRemoveExerciseNotInListException() {
        testExerciseList.addExercise(exercise1);
        testExerciseList.addExercise(exercise2);
        try {
            testExerciseList.removeExercise(exercise3);
            fail("This exercise was not in the list.");
        } catch (EmptyException e) {
            fail("NotInListException should have been thrown.");
        } catch (NotInListException e) {
            //expected
        }
    }

    @Test
    public void testRemove() {
        testExerciseList.addExercise(exercise1);
        testExerciseList.addExercise(exercise2);
        testExerciseList.addExercise(exercise3);
        assertEquals(3, testExerciseList.length());
        try {
            testExerciseList.removeExercise(exercise1);
        } catch (EmptyException e) {
            fail("NotInListException should have been thrown.");
        } catch (NotInListException e) {
            fail("EmptyException should have been thrown.");
        }
        assertEquals(2, testExerciseList.length());
        assertTrue(testExerciseList.contains(exercise2));
        assertTrue(testExerciseList.contains(exercise3));
        try {
            testExerciseList.removeExercise(exercise2);
        } catch (EmptyException e) {
            fail("EmptyException should have been thrown.");
        } catch (NotInListException e) {
            fail("NotInListException should have been thrown.");
        }
        assertEquals(1, testExerciseList.length());
        assertTrue(testExerciseList.contains(exercise3));
        try {
            testExerciseList.removeExercise(exercise3);
        } catch (EmptyException e) {
            fail("EmptyException should have been thrown.");
        } catch (NotInListException e) {
            fail("NotInListException should have been thrown.");
        }
        assertEquals(0, testExerciseList.length());
    }

    @Test
    public void testRemoveExerciseByIndexEmptyException() {
        try {
            testExerciseList.removeIndex(1);
            fail("EmptyException should have been thrown.");
        } catch (EmptyException e) {
            //expected
        } catch (NotInListException e) {
            fail("NotInListException should not have been thrown.");
        }
    }

    @Test
    public void testRemoveExerciseByIndexNotInList() {
        testExerciseList.addExercise(exercise1);
        testExerciseList.addExercise(exercise2);
        try {
            testExerciseList.removeIndex(4);
            fail("NotInListException should have been thrown");
        } catch (EmptyException e) {
            fail("EmptyException should have been thrown.");
        } catch (NotInListException e) {
            //expected
        }
    }

    @Test
    public void testRemoveExerciseByIndex() {
        testExerciseList.addExercise(exercise1);
        testExerciseList.addExercise(exercise2);
        testExerciseList.addExercise(exercise3);
        try {
            testExerciseList.removeIndex(1);
        } catch (EmptyException e) {
            fail("EmptyException should not have been thrown.");
        } catch (NotInListException e) {
            fail("NotInListException should not have been thrown.");
        }
        assertTrue(testExerciseList.contains(exercise1));
        assertFalse(testExerciseList.contains(exercise2));
        assertTrue(testExerciseList.contains(exercise3));
    }

    @Test
    public void testExercisesFromDateEmptyException() {
        ExerciseList newList = null;
        try {
            newList = testExerciseList.exercisesFromDate("October 12");
            fail("EmptyException should have been thrown.");
        } catch (EmptyException e) {
            //expected
        }
    }

    @Test
    public void testExercisesFromDate() {
        ExerciseList newList = null;
        testExerciseList.addExercise(exercise1);
        testExerciseList.addExercise(exercise2);
        testExerciseList.addExercise(exercise3);
        testExerciseList.addExercise(exercise4);
        testExerciseList.addExercise(exercise5);
        try {
            newList = testExerciseList.exercisesFromDate("October 12");
        } catch (EmptyException e) {
            fail("EmptyException should not have been thrown.");
        }
        assertEquals(2, newList.length());
        assertTrue(newList.contains(exercise1));
        assertTrue(newList.contains(exercise5));
        ExerciseList noExercisesFromDate = null;
        try {
            noExercisesFromDate = testExerciseList.exercisesFromDate("May 1");
        } catch (EmptyException e) {
            fail("EmptyException should not have been thrown.");
        }
        assertEquals(0, noExercisesFromDate.length());
    }

    @Test
    public void testExercisesFromTypeEmptyException() {
        ExerciseList newList = null;
        try {
            newList = testExerciseList.exercisesFromType("Cardio");
            fail("EmptyException should have been thrown");
        } catch (EmptyException e) {
            //expected
        }
    }

    @Test
    public void testExercisesFromType() {
        ExerciseList newList = null;
        testExerciseList.addExercise(exercise1);
        testExerciseList.addExercise(exercise2);
        testExerciseList.addExercise(exercise3);
        testExerciseList.addExercise(exercise4);
        testExerciseList.addExercise(exercise5);
        try {
            newList = testExerciseList.exercisesFromType("Cardio");
        } catch (EmptyException e) {
            fail("EmptyException should not have been thrown.");
        }
        assertEquals(2, newList.length());
        assertTrue(newList.contains(exercise1));
        assertTrue(newList.contains(exercise3));
        ExerciseList noExercisesFromType = null;
        try {
            noExercisesFromType = testExerciseList.exercisesFromType("Abs");
        } catch (EmptyException e) {
            fail("EmptyException should not have been thrown.");
        }
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
