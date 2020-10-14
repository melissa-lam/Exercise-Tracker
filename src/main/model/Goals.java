package model;

import java.util.ArrayList;
import java.util.List;

public class Goals {
    private List<Goal> goals;
    private List<Goal> completedGoals;
    private List<String> names;

    public Goals() {
        this.goals = new ArrayList<Goal>();
        this.completedGoals = new ArrayList<Goal>();
        this.names = new ArrayList<String>();
    }

    public int length() {
        return goals.size();
    }

    public boolean contains(Goal g) {
        return goals.contains(g);
    }

//    public Goal getPosition(int pos) {
//        return goals.get(pos);
//    }

    // MODIFIES: this
    // EFFECTS: adds a goal to the current list of goals to do
    public void addGoals(Goal g) {
        goals.add(g);
    }

//    // MODIFIES: this
//    // EFFECTS: adds a goal to the current list of completed goals
//    public void addCompletedGoals(Goal g) {
//        completedGoals.add(g);
//    }

    // REQUIRES: list not be empty
    // MODIFIES: this
    // EFFECTS: removes a goal from the current list
    public void removeGoals(Goal g) {
        goals.remove(g);
    }

    // EFFECTS: returns a list of goals made for that current date
    public Goals goalsByDate(String date) {
        Goals goalsByDate = new Goals();
        for (Goal g: goals) {
            if (g.getDate() == date) {
                goalsByDate.addGoals(g);
            }
        }
        return goalsByDate;
    }

    // EFFECTS: returns a list of goals made for that current type
    public Goals goalsByType(String type) {
        Goals goalsByType = new Goals();
        for (Goal g: goals) {
            if (g.getType() == type) {
                goalsByType.addGoals(g);
            }
        }
        return goalsByType;
    }

    // EFFECTS: returns the number of completed goals
    public int numGoals() {
        return goals.size();
    }

//    // EFFECTS: returns the number of completed goals
//    public int numCompletedGoals() {
//        return completedGoals.size();
//    }

//    // EFFECTS: returns the list of completed goals
//    public List<Goal> completedGoals() {
//        return completedGoals;
//    }

    // EFFECTS: returns the number of remaining goals left to do
    public int numRemainingGoals() {
        return goals.size();
    }

//    // EFFECTS: returns the list of remaining goals
//    public List<Goal> remainingGoals() {
//        return goals;
//    }

    public List<String> getNames() {
        for (Goal g: goals) {
            names.add(g.getName());
        }
        return names;
    }

//    public boolean containsName(String s) {
//        return names.contains(s);
//    }
}
