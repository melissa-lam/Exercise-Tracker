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

//    public void setGoal() {
//        this.hours = 0;
//        this.type = "";
//        this.date = "";
//    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public int getHours() {
        return hours;
    }

    public String getName() {
        return (type + ": " + date + " for " + hours + " hours");
    }

}
