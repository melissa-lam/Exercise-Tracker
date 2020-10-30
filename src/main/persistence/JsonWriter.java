package persistence;

import model.ExerciseList;
import model.Goals;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

// Represents a writer that writes JSON representation of workroom to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of exercise list, goals, and completed goals to file
    public void write(ExerciseList el, Goals g, Goals cg) {
        JSONArray jsonEL = el.toJsonArray();
        JSONArray jsonG = g.toJsonArray();
        JSONArray jsonCG = cg.toJsonArray();
        JSONObject jsonMerge = new JSONObject();
        jsonMerge.put("exercises", jsonEL);
        jsonMerge.put("goals", jsonG);
        jsonMerge.put("completed goals", jsonCG);
        saveToFile(jsonMerge.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
