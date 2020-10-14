package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoalTest {
    private Goal goal;
    private Goal goal1;

    @BeforeEach
    void runBefore() {
        goal = new Goal("Running", "June 1", 1);
        goal1 = new Goal("Swimming", "June 5", 2);
    }

    @Test
    public void testGetType() {
        assertEquals("Running", goal.getType());
        assertEquals("Swimming", goal1.getType());
    }

    @Test
    public void testGetDate() {
        assertEquals("June 1", goal.getDate());
        assertEquals("June 5", goal1.getDate());
    }

    @Test
    public void testGetHours() {
        assertEquals(1, goal.getHours());
        assertEquals(2, goal1.getHours());
    }

    @Test
    public void testGetName() {
        assertEquals("Running: June 1 for 1 hours", goal.getName());
        assertEquals("Swimming: June 5 for 2 hours", goal1.getName());
    }
}
