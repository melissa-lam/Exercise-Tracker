package ui;

import model.*;

import java.util.Scanner;

public class ExerciseTracker {
    private Exercise exercise;
    private ExerciseList exercises;
    private Goal goal;
    private Goals goals;
    private Goals completedGoals;
    private Scanner input;

    public ExerciseTracker() {
        runExerciseTracker();
    }

    // MODIFIES: this
    // EFFECTS: displays the display menu and prompts the command to choose between viewing exercises or goal
    //          information or to quit
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

    // EFFECTS: displays options for users to choose from (one of: view exercise info, view goals info,
    //          or quit)
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\te -> View Exercise Information");
        System.out.println("\tg -> View Goals Information");
        System.out.println("\tq -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: initializes all the fields (exercise, exercises, goal, goals, completed goals), and
    //          the scanner
    private void init() {
        exercise = new Exercise("","",0);
        exercises = new ExerciseList();
        goal = new Goal("", "", 0);
        goals = new Goals();
        completedGoals = new Goals();
        input = new Scanner(System.in);
    }

    // MODIFIES: this
    // EFFECTS: prompts command to execute when user chooses an option (one of e or g)
    private void processCommand(String command) {
        if (command.equals("e")) {
            exerciseInfo();
        } else if (command.equals("g")) {
            goalInfo();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: displays the exercise display menu and prompts the command to choose between exercise options
    //          or to go back
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

    // EFFECTS: displays options for users to choose from (one of: record exercise, remove exercise, or view past
    //          exercise information, or go back)
    private void displayExerciseInfo() {
        System.out.println("\nSelect from:");
        System.out.println("\te -> Record an exercise");
        System.out.println("\tre -> Remove an exercise");
        System.out.println("\tpe -> View past exercise information");
        System.out.println("\tb -> Back");
    }

    // MODIFIES: this
    // EFFECTS: prompts command to execute when user chooses an option (one of e, re, or pe)
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

    // MODIFIES: this
    // EFFECTS: displays the goal display menu and prompts the command to choose between goal options
    //          or to go back
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

    // EFFECTS: displays options for users to choose from (one of: record goal, remove goal, record completed goal,
    //          view completed goals, view goals to do, or to go back)
    private void displayGoalInfo() {
        System.out.println("\nSelect from:");
        System.out.println("\tg -> Record a goal you want to accomplish");
        System.out.println("\trg -> Remove a goal");
        System.out.println("\tcg -> Record a completed goal");
        System.out.println("\tvcg -> View completed goals");
        System.out.println("\tvtg -> View goals todo");
        System.out.println("\tb -> Back");
    }

    // MODIFIES: this
    // EFFECTS: prompts command to execute when user chooses an option (one of g, rg, cg, vcg, or vtg)
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
