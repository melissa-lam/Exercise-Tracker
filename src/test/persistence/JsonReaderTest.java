package persistence;

import model.Exercise;
import model.ExerciseList;
import model.Goal;
import model.Goals;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Test for JsonReader class
// Code modified from JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReaderTest extends JsonTest{
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ExerciseList el = reader.readExercises();
            Goals g = reader.readGoals();
            Goals cg = reader.readCompletedGoals();
            fail("IO exception expected");
        } catch (IOException e) {
            //expected
        }
    }


    @Test
    void testReaderEmptyExerciseTracker() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyExerciseTracker.json");
        try {
            ExerciseList el = reader.readExercises();
            Goals g = reader.readGoals();
            Goals cg = reader.readCompletedGoals();
            assertEquals(0, el.length());
            assertEquals(0, g.length());
            assertEquals(0, cg.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralExerciseTracker() {
        try {
            JsonReader reader = new JsonReader("./data/testReaderGeneralExerciseTracker.json");
            ExerciseList el = reader.readExercises();
            Goals g = reader.readGoals();
            Goals cg = reader.readCompletedGoals();
            List<Exercise> exercises = el.getExercises();
            List<Goal> goals = g.getGoals();
            List<Goal> completedGoals = cg.getGoals();
            assertEquals(2, el.length());
            assertEquals(2, g.length());
            checkExercise("Cardio", "Oct 13", 1, exercises.get(0));
            checkExercise("Swimming", "Oct 15", 2, exercises.get(1));
            checkGoal("Arms", "Oct 16",1, goals.get(0));
            checkGoal("Jogging", "Nov 1", 1, goals.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }

}
