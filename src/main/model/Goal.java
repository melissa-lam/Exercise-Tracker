package model;

public class Goal {
    private int hours;
    private String type;
    private String date;

    public Goal(String type, String date, int hours) {
        this.hours = hours;
        this.type = type;
        this.date = date;
    }

    // EFFECTS: returns the exercise type for a goal
    public String getType() {
        return type;
    }

    // EFFECTS: returns the date for this goal to be completed
    public String getDate() {
        return date;
    }

    // EFFECTS: returns the number of hours this goal will take in integer
    public int getHours() {
        return hours;
    }

    // EFFECTS: returns the name of this goal
    public String getName() {
        return (type + ": " + date + " for " + hours + " hours");
    }

}
