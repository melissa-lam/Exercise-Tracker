package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import model.Exercise;
import model.Goal;

// Test for Json objects
// Code modified from JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonTest {
    protected void checkExercise(String type, String date, int hours, Exercise exercise) {
        assertEquals(type, exercise.getType());
        assertEquals(date, exercise.getDate());
        assertEquals(hours, exercise.getHours());
    }

    protected void checkGoal(String type, String date, int hours, Goal goal) {
        assertEquals(type, goal.getType());
        assertEquals(date, goal.getDate());
        assertEquals(hours, goal.getHours());
    }
}
