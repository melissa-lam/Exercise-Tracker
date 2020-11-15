package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import persistence.WritableArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a list of goals
public class Goals implements WritableArray {
    private List<Goal> goals;

    public Goals() {
        this.goals = new ArrayList<>();
    }

    // EFFECTS: returns the size of the list in integer
    public int length() {
        return goals.size();
    }

   // EFFECTS: returns true if the list contains the given goal, false otherwise
    public boolean contains(Goal g) {
        return goals.contains(g);
    }

    // MODIFIES: this
    // EFFECTS: adds a goal to the current list of goals to do
    public void addGoals(Goal g) {
        goals.add(g);
    }

    // REQUIRES: list not be empty and must have given goal in the list already
    // MODIFIES: this
    // EFFECTS: removes a goal from the current list
    public void removeGoals(Goal g) {
        String name = g.getName();
        goals.removeIf(goal -> name.equals(goal.getName()));
    }

    public List<Goal> getGoals() {
        return Collections.unmodifiableList(goals);
    }

    // EFFECTS: returns a list of goals made for that current date
    public Goals goalsByDate(String date) {
        Goals goalsByDate = new Goals();
        for (Goal g: goals) {
            if (date.equals(g.getDate())) {
                goalsByDate.addGoals(g);
            }
        }
        return goalsByDate;
    }

    // EFFECTS: returns a list of goals made for that current type
    public Goals goalsByType(String type) {
        Goals goalsByType = new Goals();
        for (Goal g: goals) {
            if (type.equals(g.getType())) {
                goalsByType.addGoals(g);
            }
        }
        return goalsByType;
    }

    // EFFECTS: returns the names of all the goals in the list in the form:
    //          Type: date for number of hours
    public List<String> getNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Goal g: goals) {
            names.add(g.getName());
        }
        return names;
    }

    // EFFECTS: returns a json array of json objects of goals
    @Override
    public JSONArray toJsonArray() {
        JSONArray jsonArray = new JSONArray();

        for (Goal g : goals) {
            jsonArray.put(g.toJson());
        }
        return jsonArray;
    }

}
