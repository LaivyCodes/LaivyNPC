package codes.laivy.npc.debug;

import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import codes.laivy.npc.utils.JsonParser;

public class DebugLog {

    private final String name;
    private String message;

    public DebugLog(@NotNull String name) {
        this(name, null);
    }
    public DebugLog(@NotNull String name, @Nullable String message) {
        this.name = name;
        this.message = message;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @Nullable
    public String getMessage() {
        return message;
    }

    public void setMessage(@Nullable String message) {
        this.message = message;
    }

    public JsonObject serialize() {
        JsonObject object = new JsonObject();

        String message = "Undefined";
        if (getMessage() != null) {
            message = getMessage();
        }

        object.add("Name", JsonParser.parse("\"" + getName() + "\""));
        object.add("Message", JsonParser.parse("\"" + message + "\""));

        return object;
    }

}
