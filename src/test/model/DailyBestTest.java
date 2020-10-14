//package model;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class DailyBestTest {
//    private DailyBest dailyBest;
//    private Goals completedGoals;
//    private Goal goal;
//    private Goal goal2;
//    private Goal goal3;
//
//    void runBefore() {
//        dailyBest = new DailyBest();
//        completedGoals = new Goals();
//        goal = new Goal("Cardio", "June 1", 1);
//        goal2 = new Goal("Cardio", "June 1", 2);
//        goal3 = new Goal("Cardio", "June 1", 3);
//    }
//
//    @Test
//    public void testInsertDailyBestGoals() {
//        dailyBest.addGoals(goal);
//        dailyBest.addGoals(goal3);
//        assertEquals(2, dailyBest.length());
//        dailyBest.insertDailyBestGoals(goal2);
//        assertEquals(goal2, dailyBest.getPosition(1));
//    }
//}
