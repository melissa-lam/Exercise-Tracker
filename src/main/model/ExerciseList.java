package model;

import java.util.ArrayList;
import java.util.List;

public class ExerciseList {
    private List<Exercise> exercises;
    private List<String> names;

    public ExerciseList() {
        this.exercises = new ArrayList<>();
        this.names = new ArrayList<>();
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

    // REQUIRES: list not be empty and must have given exercise in that list
    // MODIFIES: this
    // EFFECTS: removes an Exercise from the list
    public void removeExercise(Exercise e) {
        String name = e.getName();
        for (Exercise exercise: exercises) {
            if (name.equals(exercise.getName())) {
                exercises.remove(exercise);
            }
        }
    }

    // REQUIRES: must have exercises on that date
    // EFFECTS: returns an exercise list from that particular date
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
    // EFFECTS: returns an exercise list from that particular type
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

    public List<String> getNames() {
        for (Exercise g: exercises) {
            names.add(g.getName());
        }
        return names;
    }

}
