package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import persistence.WritableArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExerciseList implements WritableArray {
    private List<Exercise> exercises;

    public ExerciseList() {
        this.exercises = new ArrayList<>();
    }

    // EFFECTS: return the number of Exercises in this list of exercises
    public int length() {
        return exercises.size();
    }

    // EFFECTS: return true if the exercise is contained in the list of exercises, false otherwise
    public boolean contains(Exercise e) {
        return exercises.contains(e);
    }

    // MODIFIES: this
    // EFFECTS: adds an Exercise to the list of exercises
    public void addExercise(Exercise e) {
        exercises.add(e);
    }

    public List<Exercise> getExercises() {
        return Collections.unmodifiableList(exercises);
    }

    // REQUIRES: list cannot be empty and must have given exercise in that list
    // MODIFIES: this
    // EFFECTS: removes an Exercise from the list
    public void removeExercise(Exercise e) {
        String name = e.getName();
        exercises.removeIf(exercise -> name.equals(exercise.getName()));
    }

    // REQUIRES: must have exercises on that date
    // EFFECTS: returns a new exercise list from that particular date
    public ExerciseList exercisesFromDate(String d) {
        ExerciseList exercisesFromDate = new ExerciseList();
        for (Exercise e: exercises) {
            if (d.equals(e.getDate())) {
                exercisesFromDate.addExercise(e);
            }
        }
        return exercisesFromDate;
    }

    // REQUIRES: must have exercises from that type
    // EFFECTS: returns a new exercise list from that particular type
    public ExerciseList exercisesFromType(String type) {
        ExerciseList exercisesFromType = new ExerciseList();
        for (Exercise e: exercises) {
            if (type.equals(e.getType())) {
                exercisesFromType.addExercise(e);
            }
        }
        return exercisesFromType;
    }

    // EFFECTS: returns total hours exercised
    public int getTotalHours() {
        int totalHours = 0;
        for (Exercise e: exercises) {
            totalHours += e.getHours();
        }
        return totalHours;
    }

    // EFFECTS: returns total hours exercised on that date
    public int getTotalHoursForDate(String date) {
        int totalHours = 0;
        for (Exercise e: exercises) {
            if (date.equals(e.getDate())) {
                totalHours += e.getHours();
            }
        }
        return totalHours;
    }

    // EFFECTS: returns total hours exercised for that type
    public int getTotalHoursForType(String type) {
        int totalHours = 0;
        for (Exercise e: exercises) {
            if (type.equals(e.getType())) {
                totalHours += e.getHours();
            }
        }
        return totalHours;
    }

    // EFFECTS: gets the names of each exercise in the list in the form of:
    //          Type: date for number of hours
    public List<String> getNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Exercise g: exercises) {
            names.add(g.getName());
        }
        return names;
    }

    @Override
    public JSONArray toJsonArray() {
        JSONArray jsonArray = new JSONArray();

        for (Exercise e : exercises) {
            jsonArray.put(e.toJson());
        }
        return jsonArray;
    }

}
