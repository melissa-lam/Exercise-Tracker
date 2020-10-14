package model;

public class Exercise {
    private int hours;
    private String type;
    private String date;
    public Exercise exercise;

    public Exercise(String type, String date, int hours) {
        this.type = type;
        this.date = date;
        this.hours = hours;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public int getHours() {
        return hours;
    }

}
