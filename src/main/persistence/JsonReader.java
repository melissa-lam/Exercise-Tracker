package persistence;

import model.Exercise;
import model.ExerciseList;
import model.Goal;
import model.Goals;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    public ExerciseList readExercises() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseExerciseList((JSONArray) jsonObject.get("exercises"));
    }

    public Goals readGoals() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGoals((JSONArray) jsonObject.get("goals"));
    }

    public Goals readCompletedGoals() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGoals((JSONArray) jsonObject.get("completed goals"));
    }

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    private ExerciseList parseExerciseList(JSONArray jsonArray) {
        ExerciseList el = new ExerciseList();
        addExercises(el, jsonArray);
        return el;
    }

    private void addExercises(ExerciseList el, JSONArray jsonArray) {
        for (Object json : jsonArray) {
            JSONObject nextExercise = (JSONObject) json;
            addExercise(el, nextExercise);
        }
    }

    // MODIFIES: el
    // EFFECTS: parses exercise from JSON object and adds it to exercise list
    private void addExercise(ExerciseList el, JSONObject jsonObject) {
        String type = jsonObject.getString("type");
        String date = jsonObject.getString("date");
        int hours = jsonObject.getInt("hours");
        Exercise exercise = new Exercise(type, date, hours);
        el.addExercise(exercise);
    }

    private Goals parseGoals(JSONArray jsonArray) {
        Goals g = new Goals();
        addGoals(g, jsonArray);
        return g;
    }

    private void addGoals(Goals g, JSONArray jsonArray) {
        for (Object json : jsonArray) {
            JSONObject nextGoal = (JSONObject) json;
            addGoal(g, nextGoal);
        }
    }

    // MODIFIES: g
    // EFFECTS: parses exercise from JSON object and adds it to exercise list
    private void addGoal(Goals g, JSONObject jsonObject) {
        String type = jsonObject.getString("type");
        String date = jsonObject.getString("date");
        int hours = jsonObject.getInt("hours");
        Goal goal = new Goal(type, date, hours);
        g.addGoals(goal);
    }

}
