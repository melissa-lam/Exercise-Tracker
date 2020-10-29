package model;

import org.json.JSONObject;
import persistence.Writable;

public class Exercise implements Writable {
    private int hours;
    private String type;
    private String date;

    public Exercise(String type, String date, int hours) {
        this.type = type;
        this.date = date;
        this.hours = hours;
    }

    // EFFECTS: return the type of an exercise
    public String getType() {
        return type;
    }

    // EFFECTS: return the date of an exercise
    public String getDate() {
        return date;
    }

    // EFFECTS: return the number of hours in integer of an exercise
    public int getHours() {
        return hours;
    }

    // EFFECTS: returns the name of an exercise in the form of:
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
