package model;

import exceptions.EmptyException;
import exceptions.NotInListException;
import org.json.JSONArray;
import persistence.WritableArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a list of exercises
public class ExerciseList implements WritableArray {
    private List<Exercise> exercises;

    // EFFECTS: constructs an array list of exercise
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

    // EFFECTS: returns the list of exercises
    public List<Exercise> getExercises() {
        return Collections.unmodifiableList(exercises);
    }

    // MODIFIES: this
    // EFFECTS: if exercise list is empty, throw new EmptyException,
    //          if exercise is not in the list, throw new NotInListException,
    //          otherwise removes an Exercise from the list
    public void removeExercise(Exercise e) throws EmptyException, NotInListException {
        if (exercises.isEmpty()) {
            throw new EmptyException("There are no exercises in your list.");
        } else if (!exercises.contains(e)) {
            throw new NotInListException("This exercise is not in your list.");
        } else {
            String name = e.getName();
            exercises.removeIf(exercise -> name.equals(exercise.getName()));
        }
    }

    // MODIFIES: this
    // EFFECTS: if exercise list is empty, throw new EmptyException,
    //          if exercise is not in the list, throw new NotInListException,
    //          otherwise removes an Exercise from the list based on given index
    public void removeIndex(int index) throws EmptyException, NotInListException {
        if (exercises.isEmpty()) {
            throw new EmptyException("There are no exercises in your list.");
        } else if (exercises.size() < index) {
            throw new NotInListException("This exercise is not in your list.");
        } else {
            exercises.remove(index);
        }
    }


    // EFFECTS: if exercise list is empty, throw new EmptyException,
    //          otherwise returns a new exercise list from that particular date
    public ExerciseList exercisesFromDate(String d) throws EmptyException {
        if (exercises.isEmpty()) {
            throw new EmptyException("There are no exercises in your list.");
        } else {
            ExerciseList exercisesFromDate = new ExerciseList();
            for (Exercise e: exercises) {
                if (d.equals(e.getDate())) {
                    exercisesFromDate.addExercise(e);
                }
            }
            return exercisesFromDate;
        }
    }

    // EFFECTS: if exercise list is empty, throw new EmptyException,
    //          otherwise returns a new exercise list from that particular type
    public ExerciseList exercisesFromType(String type) throws EmptyException {
        if (exercises.isEmpty()) {
            throw new EmptyException("There are no exercises in your list.");
        } else {
            ExerciseList exercisesFromType = new ExerciseList();
            for (Exercise e: exercises) {
                if (type.equals(e.getType())) {
                    exercisesFromType.addExercise(e);
                }
            }
            return exercisesFromType;
        }
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

    // EFFECTS: returns a json array of json objects of exercises
    @Override
    public JSONArray toJsonArray() {
        JSONArray jsonArray = new JSONArray();

        for (Exercise e : exercises) {
            jsonArray.put(e.toJson());
        }
        return jsonArray;
    }

}
