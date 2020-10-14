//package model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class LeadershipBoardTest {
//    private Goal goal1;
//    private Goal goal2;
//    private Goals goals1;
//    private Goals goals2;
//    private Person person1;
//    private Person person2;
//
//    @BeforeEach
//    void runBefore() {
//        dailyBest = new DailyBest();
//        goal1 = new Goal("Cardio", "June 1", 1);
//        goal1 = new Goal("Cardio", "June 2", 1);
//        goals1 = new Goals();
//        goals1.addGoals(goal1);
//        goals1.addGoals(goal2);
//        person1 = new Person("Mel", 19, 100, 150);
//        person2 = new Person("Jen", 19, 120, 170);
//    }
//
//    @Test
//    public void testLength() {
//        dailyBest.insertPerson(person1);
//        dailyBest.insertPerson(person2);
//        assertEquals(2, dailyBest.length());
//    }
//
//    @Test
//    public void testContains() {
//        dailyBest.insertPerson(person1);
//        dailyBest.insertPerson(person2);
//        assertTrue(dailyBest.contains(person1));
//        assertTrue(dailyBest.contains(person2));
//    }
//
//    @Test
//    public void testInsertPersonNull() {
//        dailyBest.insertPerson(person1);
//        assertEquals(1, dailyBest.length());
//    }
//
//    @Test
//    public void testInsertPerson() {
//        dailyBest.insertPerson(person1);
//        dailyBest.insertPerson(person2);
//        assertEquals(2, dailyBest.length());
//        assertTrue(dailyBest.contains(person1));
//        assertTrue(dailyBest.contains(person2));
//    }
//
//    @Test
//    public void testRemovePerson() {
//        dailyBest.insertPerson(person1);
//        dailyBest.insertPerson(person2);
//        assertEquals(2, dailyBest.length());
//        dailyBest.removePerson(person1);
//        assertEquals(1, dailyBest.length());
//        assertTrue(dailyBest.contains(person2));
//    }
//}
