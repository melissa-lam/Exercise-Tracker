package model;

import java.util.ArrayList;
import java.util.List;

public class Goals {
    private List<Goal> goals;
    private List<String> names;

    public Goals() {
        this.goals = new ArrayList<>();
        this.names = new ArrayList<>();
    }

    public int length() {
        return goals.size();
    }

    public boolean contains(Goal g) {
        return goals.contains(g);
    }

    // MODIFIES: this
    // EFFECTS: adds a goal to the current list of goals to do
    public void addGoals(Goal g) {
        goals.add(g);
    }

    // REQUIRES: list not be empty
    // MODIFIES: this
    // EFFECTS: removes a goal from the current list
    public void removeGoals(Goal g) {
        String name = g.getName();
        for (Goal goal: goals) {
            if (name.equals(goal.getName())) {
                goals.remove(goal);
            }
        }
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

    // EFFECTS: returns the number of completed goals
    public int numGoals() {
        return goals.size();
    }

    public List<String> getNames() {
        for (Goal g: goals) {
            names.add(g.getName());
        }
        return names;
    }

}
