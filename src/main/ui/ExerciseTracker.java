package ui;

import model.*;

import java.util.Scanner;

public class ExerciseTracker {
    private Exercise exercise;
    private ExerciseList exercises;
    private Goal goal;
    private Goals goals;
    private Goals completedGoals;
//    private Person person;
//    private People people;
    private Scanner input;

    public ExerciseTracker() {
        runExerciseTracker();
    }

    public void runExerciseTracker() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nThank you!");
    }

//    private void displayMenu() {
//        System.out.println("\nSelect from:");
//        System.out.println("\tp -> Choose profile");
//        System.out.println("\tn -> Make a new profile");
//        System.out.println("\tq -> Quit");
//    }
//
//    private void processCommand(String command) {
//        if (command.equals("p")) {
//            choosePerson();
//        } else if (command.equals("n")) {
//            makeNewPerson();
//        } else {
//            System.out.println("Selection not valid...");
//        }
//    }
//
//    private void choosePerson() {
//        System.out.println("Input profile name: ");
//        input.nextLine();
//        String name = input.nextLine();
//        people.getPerson(name);
//        runProfile();
//    }
//
//    private void makeNewPerson() {
//        System.out.println("Enter your profile details here:");
//        System.out.println("Enter your name:");
//        input.nextLine();
//        String name = input.nextLine();
//        System.out.println("Enter your age:");
//        Integer age = Integer.valueOf(input.nextLine());
//        System.out.println("Enter your height:");
//        Integer height = Integer.valueOf(input.nextLine());
//        System.out.println("Enter your weight:");
//        Integer weight = Integer.valueOf(input.nextLine());
//        Person person = new Person(name, age, height, weight);
//        people.addPerson(person);
//        System.out.println("You have made a new profile!");
//    }

//    private void runProfile() {
//        boolean keepGoing = true;
//        String command = null;
//
//        while (keepGoing) {
//            displayMenuProfile();
//            command = input.next();
//            command = command.toLowerCase();
//
//            if (command.equals("q")) {
//                keepGoing = false;
//            } else {
//                processCommandProfile(command);
//            }
//        }
//        System.out.println("\nThank you!");
//    }

    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\te -> View Exercise Information");
        System.out.println("\tg -> View Goals Information");
        System.out.println("\tq -> Quit");
    }

    private void init() {
        exercise = new Exercise("","",0);
        exercises = new ExerciseList();
        goal = new Goal("", "", 0);
        goals = new Goals();
        completedGoals = new Goals();
//        person = new Person("",0,0,0);
//        people = new People();
        input = new Scanner(System.in);
    }

    private void processCommand(String command) {
        if (command.equals("e")) {
            exerciseInfo();
        } else if (command.equals("g")) {
            goalInfo();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void exerciseInfo() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayExerciseInfo();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("b")) {
                keepGoing = false;
            } else {
                processCommandExerciseInfo(command);
            }
        }
        System.out.println("\nThank you!");
    }

    private void displayExerciseInfo() {
        System.out.println("\nSelect from:");
        System.out.println("\te -> Record an exercise");
        System.out.println("\tre -> Remove an exercise");
        System.out.println("\tpe -> View past exercise information");
        System.out.println("\tb -> Back");
    }

    private void processCommandExerciseInfo(String command) {
        if (command.equals("e")) {
            recordExercise();
        } else if (command.equals("re")) {
            removeExercise();
        } else if (command.equals("pe")) {
            viewPastExercises();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void goalInfo() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayGoalInfo();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("b")) {
                keepGoing = false;
            } else {
                processCommandGoalInfo(command);
            }
        }
        System.out.println("\nThank you!");
    }

    private void displayGoalInfo() {
        System.out.println("\nSelect from:");
        System.out.println("\tg -> Record a goal");
        System.out.println("\trg -> Remove a goal");
        System.out.println("\tcg -> Record a completed goal");
        System.out.println("\tvcg -> View completed goals");
        System.out.println("\tvtg -> View goals todo");
        System.out.println("\tb -> Back");
    }

    private void processCommandGoalInfo(String command) {
        if (command.equals("g")) {
            recordGoal();
        } else if (command.equals("rg")) {
            removeGoal();
        } else if (command.equals("cg")) {
            recordCompletedGoal();
        } else if (command.equals("vcg")) {
            viewCompletedGoals();
        } else if (command.equals("vtg")) {
            viewGoalsToDo();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void recordExercise() {
        System.out.println("Record your exercise with the type, date, and hours!");
        System.out.println("Input your exercise type here:");
        input.nextLine();
        String type = input.nextLine();
        System.out.println("Type: " + type);
        System.out.println("Input the date here:");
        String date = input.nextLine();
        System.out.println("Type: " + type);
        System.out.println("Date: " + date);
        System.out.println("Input the hours you've exercised here: ");
        int hours = Integer.valueOf(input.next());
        System.out.println("Type: " + type);
        System.out.println("Date: " + date);
        System.out.println("Hours: " + hours);
        Exercise exercise = new Exercise(type, date, hours);
        exercises.addExercise(exercise);
    }

    private void removeExercise() {
        System.out.println("Please input the exercise type, date, and hours you want to remove: ");
        System.out.println("Input exercise type here: ");
        input.nextLine();
        String type = input.nextLine();
        System.out.println("Input exercise date here: ");
        String date = input.nextLine();
        System.out.println("Input exercise hours here:");
        Integer hours = Integer.valueOf(input.nextLine());
        Exercise e = new Exercise(type, date, hours);
        exercises.removeExercise(e);
        System.out.println("You have removed this exercise.");
    }

    private void recordGoal() {
        System.out.println("Record your goal with the type, date, and hours!");
        System.out.println("Input your goal type here:");
        input.nextLine();
        String type = input.nextLine();
        System.out.println("Type: " + type);
        System.out.println("Input the date here:");
        String date = input.nextLine();
        System.out.println("Type: " + type);
        System.out.println("Date: " + date);
        System.out.println("Input the hours you're planning to exercise here: ");
        int hours = Integer.valueOf(input.nextLine());
        System.out.println("Type: " + type);
        System.out.println("Date: " + date);
        System.out.println("Hours: " + hours);
        Goal goal = new Goal(type, date, hours);
        goals.addGoals(goal);
        System.out.println("You have recorded this goal.");
    }

    private void removeGoal() {
        System.out.println("Please input the goal type, date, and hours you want to remove: ");
        System.out.println("Input goal type here: ");
        input.nextLine();
        String type = input.nextLine();
        System.out.println("Type: " + type);
        System.out.println("Input goal date here: ");
        String date = input.nextLine();
        System.out.println("Type: " + type);
        System.out.println("Date: " + date);
        System.out.println("Input goal hours here:");
        Integer hours = Integer.valueOf(input.nextLine());
        System.out.println("Type: " + type);
        System.out.println("Date: " + date);
        System.out.println("Hours: " + hours);
        Goal g = new Goal(type, date, hours);
        goals.removeGoals(g);
        System.out.println("You have removed this goal.");
    }

    private void recordCompletedGoal() {
        System.out.println("Record your completed goal with the type, date, and hours!");
        System.out.println("Input your goal type here:");
        input.nextLine();
        String type = input.nextLine();
        System.out.println("Type: " + type);
        System.out.println("Input the date here:");
        String date = input.nextLine();
        System.out.println("Type: " + type);
        System.out.println("Date: " + date);
        System.out.println("Input the hours you're planning to exercise here: ");
        int hours = Integer.valueOf(input.nextLine());
        System.out.println("Type: " + type);
        System.out.println("Date: " + date);
        System.out.println("Hours: " + hours);
        Goal goal = new Goal(type, date, hours);
        completedGoals.addGoals(goal);
        goals.removeGoals(goal);
    }

    private void viewCompletedGoals() {
        System.out.println("Here is a list of your completed goals: " + completedGoals.getNames());
    }

    private void viewGoalsToDo() {
        System.out.println("Here is a list of your goals: " + goals.getNames());
    }

//    private void viewOtherInfo() {
//        boolean keepGoing = true;
//        String command = null;
//
//        while (keepGoing) {
//            viewOtherInfoDisplay();
//            command = input.next();
//            command = command.toLowerCase();
//
//            if (command.equals("q")) {
//                keepGoing = false;
//            } else {
//                processCommandOtherInfo(command);
//            }
//        }
//        System.out.println("\nThank you!");
//    }
//
//    private void viewOtherInfoDisplay() {
//        System.out.println("\nSelect from:");
//        System.out.println("\te -> View past exercises");
//        System.out.println("\tg -> View goals");
//        System.out.println("\tq -> Quit");
//    }
//
//    private void processCommandOtherInfo(String command) {
//        if (command.equals("e")) {
//            viewPastExercises();
//        } else if (command.equals("g")) {
//            viewGoals();
//        } else {
//            System.out.println("Selection not valid...");
//        }
//    }

    private void viewPastExercises() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            viewPastExercisesDisplay();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("b")) {
                keepGoing = false;
            } else {
                processCommandPastExercises(command);
            }
        }
        System.out.println("\nThank you!");
    }

    private void viewPastExercisesDisplay() {
        System.out.println("\nSelect from:");
        System.out.println("\td -> View by Date");
        System.out.println("\tt -> View by Exercise Type");
        System.out.println("\ta -> View All Past Exercises");
        System.out.println("\tb -> Back");
    }

    private void processCommandPastExercises(String command) {
        if (command.equals("d")) {
            viewExercisesByDate();
        } else if (command.equals("t")) {
            viewExercisesByType();
        } else if (command.equals("a")) {
            viewAllExercises();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void viewGoals() {
        System.out.println("Here is a list of your goals: " + goals.getNames());
    }

    private void viewExercisesByDate() {
        System.out.println("Input your date to filter here:");
        input.nextLine();
        String date = input.nextLine();
        ExerciseList byDate = exercises.exercisesFromDate(date);
        System.out.println("Here is the list of exercises by " + date + ":" + byDate.getNames());
    }

    private void viewExercisesByType() {
        System.out.println("Input your type to filter here:");
        input.nextLine();
        String type = input.nextLine();
        ExerciseList byType = exercises.exercisesFromType(type);
        System.out.println("Here is the list of exercises by " + type + ":" + byType.getNames());
    }

    private void viewAllExercises() {
        System.out.println("Here is a list of all your exercises: " + exercises.getNames());
    }

}