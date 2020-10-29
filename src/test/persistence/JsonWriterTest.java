package persistence;

import model.Exercise;
import model.ExerciseList;
import model.Goal;
import model.Goals;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{
    @Test
    void testWriterInvalidFile() {
        try {
            ExerciseList el = new ExerciseList();
            Goals g = new Goals();
            Goals cg = new Goals();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
        } catch (FileNotFoundException e) {
            //expected
        }
    }

    @Test
    void testWriterEmptyExerciseTracker() {
        try {
            ExerciseList el = new ExerciseList();
            Goals g = new Goals();
            Goals cg = new Goals();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyExerciseTracker.json");
            writer.open();
            writer.write(el, g, cg);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyExerciseTracker.json");
            el = reader.readExercises();
            g = reader.readGoals();
            cg = reader.readGoals();
            assertEquals(0, el.length());
            assertEquals(0, g.length());
            assertEquals(0, cg.length());
        } catch (FileNotFoundException e) {
            fail("Exception should not have been thrown");
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralExerciseTracker() {
        try {
            ExerciseList el = new ExerciseList();
            Goals g = new Goals();
            Goals cg = new Goals();
            el.addExercise(new Exercise("Cardio", "Oct 13", 1));
            el.addExercise(new Exercise("Swimming", "Oct 15", 2));
            g.addGoals(new Goal("Arms", "Oct 16",1));
            g.addGoals(new Goal("Jogging", "Nov 1", 1));
            cg.addGoals(new Goal("Basketball", "Oct 28", 4));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralExerciseTracker.json");
            writer.open();
            writer.write(el, g, cg);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralExerciseTracker.json");
            el = reader.readExercises();
            g = reader.readGoals();
            cg = reader.readCompletedGoals();
            List<Exercise> exercises = el.getExercises();
            List<Goal> goals = g.getGoals();
            List<Goal> completedGoals = cg.getGoals();
            assertEquals(2, exercises.size());
            assertEquals(2, goals.size());
            assertEquals(1, completedGoals.size());
            checkExercise("Cardio", "Oct 13", 1, exercises.get(0));
            checkExercise("Swimming", "Oct 15", 2, exercises.get(1));
            checkGoal("Arms", "Oct 16",1, goals.get(0));
            checkGoal("Jogging", "Nov 1", 1, goals.get(1));
            checkGoal("Basketball", "Oct 28", 4, completedGoals.get(0));
        } catch (FileNotFoundException e) {
            fail("Exception should not have been thrown");
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }



}
