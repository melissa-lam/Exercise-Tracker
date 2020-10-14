package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoalTest {
    private Goal goal;

    @BeforeEach
    void runBefore() {
        goal = new Goal("Running", "June 1", 1);
    }

    @Test
    public void testGetType() {
        assertEquals("Running", goal.getType());
    }

    @Test
    public void testGetDate() {
        assertEquals("June 1", goal.getDate());
    }

    @Test
    public void testGetHours() {
        assertEquals(1, goal.getHours());
    }

    @Test
    public void testGetName() {
        assertEquals("Running: June 1 for 1 hours", goal.getName());
    }
}
