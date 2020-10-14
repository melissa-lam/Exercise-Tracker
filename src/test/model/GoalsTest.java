package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoalsTest {
    private Goals testGoals;
    private Goals testCompleted;
    private Goal goals1;
    private Goal goals2;
    private Goal goals3;
    private List<String> names;

    @BeforeEach
    void runBefore() {
        testGoals = new Goals();
        testCompleted = new Goals();
        goals1 = new Goal("Running", "June 1", 3);
        goals2 = new Goal("Cardio", "June 1", 2);
        goals3 = new Goal("Cardio", "July 22", 1);
    }

    @Test
    public void testAddGoals() {
        testGoals.addGoals(goals1);
        assertEquals(1, testGoals.length());
        assertTrue(testGoals.contains(goals1));
        testGoals.addGoals(goals2);
        testGoals.addGoals(goals3);
        assertEquals(3, testGoals.length());
        assertTrue(testGoals.contains(goals2));
        assertTrue(testGoals.contains(goals3));
    }

    @Test
    public void testRemoveGoals() {
        testGoals.addGoals(goals1);
        testGoals.addGoals(goals2);
        testGoals.addGoals(goals3);
        testGoals.removeGoals(goals1);
        assertEquals(2,testGoals.length());
        assertTrue(testGoals.contains(goals2));
        assertTrue(testGoals.contains(goals3));
    }

    @Test
    public void testGoalsByDate() {
        testGoals.addGoals(goals1);
        testGoals.addGoals(goals2);
        testGoals.addGoals(goals3);
        Goals goalsByDate = testGoals.goalsByDate("June 1");
        assertEquals(2, goalsByDate.length());
        assertTrue(testGoals.contains(goals1));
        assertTrue(testGoals.contains(goals2));
    }

    @Test
    public void testGoalsByType() {
        testGoals.addGoals(goals1);
        testGoals.addGoals(goals2);
        testGoals.addGoals(goals3);
        Goals goalsByType = testGoals.goalsByType("Cardio");
        assertEquals(2, goalsByType.length());
        assertTrue(testGoals.contains(goals2));
        assertTrue(testGoals.contains(goals3));
    }

    @Test
    public void testNumGoals() {
        testCompleted.addGoals(goals1);
        testCompleted.addGoals(goals2);
        testCompleted.addGoals(goals3);
        assertEquals(3, testCompleted.numGoals());
    }

//    @Test
//    public void testNumCompletedGoals() {
//        testCompleted.addCompletedGoals(goals1);
//        testCompleted.addCompletedGoals(goals2);
//        testCompleted.addCompletedGoals(goals3);
//        assertEquals(3, testCompleted.numCompletedGoals());
//    }

    @Test
    public void testNumRemainingGoals() {
        testGoals.addGoals(goals1);
        testGoals.addGoals(goals2);
        testGoals.addGoals(goals3);
        assertEquals(3, testGoals.numRemainingGoals());
    }

    @Test
    public void testRemainingGoals() {
        testGoals.addGoals(goals1);
        testGoals.addGoals(goals2);
        testGoals.addGoals(goals3);
        assertEquals(3, testGoals.length());
    }

    @Test
    public void testGetNames() {
        testGoals.addGoals(goals1);
        testGoals.addGoals(goals2);
        List<String> names = testGoals.getNames();
        assertTrue(names.contains("Running: June 1 for 3 hours"));
        assertTrue(names.contains("Cardio: June 1 for 2 hours"));
    }

}
