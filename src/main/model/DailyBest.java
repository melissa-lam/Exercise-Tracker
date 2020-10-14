//package model;
//
//import java.util.LinkedList;
//import java.util.List;
//
//public class DailyBest extends Goals {
//    private Goals dailyBest;
//    private Goals completedGoals;
//
//    public DailyBest() {
//        this.dailyBest = new Goals();
//        this.completedGoals = new Goals();
//    }
//
////    public int length() {
////        return topTen.size();
////    }
//
////    public boolean contains(Person p) {
////        return topTen.contains(p);
////    }
//
//    public void insertDailyBestGoals(Goal g) {
//        int index = 0;
//        for (Goal goal : dailyBest) {
//            index++;
//            if (goal.getHours() <= g.getHours()) {
//                dailyBest.add(index, g);
//            }
//
//        }
//    }
//
//    public Goal getPosition(int pos) {
//        return dailyBest.get(pos);
//    }
//}
//
////    public void insertPerson(Person p) {
////        int index = 0;
////        if (topTen == null) {
////            topTen.add(p);
////        } else {
////            for (Person person: topTen) {
////                index++;
////                if (person.getNumOfGoalsDone() <= p.getNumOfGoalsDone()) {
////                    topTen.add(index, p);
////                }
////            }
////        }
////    }
//
////    public void removePerson(Person p) {
////        topTen.remove(p);
////    }
////
////    public List<Person> getLeadershipBoard() {
////        return topTen;
////    }
//
//
