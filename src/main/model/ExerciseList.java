package model;

import java.util.ArrayList;
import java.util.List;
import model.Exercise;

public class ExerciseList {
    private List<Exercise> exercises;
    private Exercise exercise;

    public ExerciseList() {
        this.exercises = new ArrayList<Exercise>();
    }

//    public List<Exercise> getExerciseList() {
//        return exercises;
//    }

    public int length() {
        return exercises.size();
    }

    public boolean contains(Exercise e) {
        return exercises.contains(e);
    }

    // MODIFIES: this
    // EFFECTS: adds an Exercise to the list
    public void addExercise(Exercise e) {
        exercises.add(e);
    }

    // REQUIRES: list not be empty and must have given exercise in that list
    // MODIFIES: this
    // EFFECTS: removes an Exercise from the list
    public void removeExercise(Exercise e) {
        exercises.remove(e);
    }

    // REQUIRES: must have exercises on that date
    // EFFECTS: returns an exercise list from that particular date
    public ExerciseList exercisesFromDate(String d) {
        ExerciseList exercisesFromDate = new ExerciseList();
        for (Exercise e: exercises) {
            if (e.getDate() == d) {
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
            if (e.getType() == type) {
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
            if (e.getDate() == date) {
                totalHours += e.getHours();
            }
        }
        return totalHours;
    }

    // EFFECTS: returns total hours exercised for that type
    public int getTotalHoursForType(String type) {
        int totalHours = 0;
        for (Exercise e: exercises) {
            if (e.getType() == type) {
                totalHours += e.getHours();
            }
        }
        return totalHours;
    }

}
