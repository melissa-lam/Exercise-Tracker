package model;

import org.json.JSONObject;
import persistence.Writable;

public class Goal implements Writable {
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

    // EFFECTS: returns the name of this goal in the form:
    //          Type: date for number of hours
    public String getName() {
        return (type + ": " + date + " for " + hours + " hours");
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("type", type);
        json.put("date", date);
        json.put("hours", hours);
        return json;
    }

}
