package persistence;

import org.json.JSONObject;

// Represents an interface that helps return an object as a JSON object
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();

}
