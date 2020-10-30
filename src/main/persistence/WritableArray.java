package persistence;

import org.json.JSONArray;

// Represents an interface that helps return an list as a JSON array
public interface WritableArray {
    // EFFECTS: returns this as JSON array
    public JSONArray toJsonArray();
}
