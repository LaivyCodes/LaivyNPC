package codes.laivy.npc.debug;

import codes.laivy.npc.LaivyNPC;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

import codes.laivy.npc.utils.JsonParser;

import java.util.ArrayList;
import java.util.List;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class DebugResult {

    private final String mappingVersion;
    private final long startedTime;
    private long finishedTime = 0;
    private final List<DebugLog> log = new ArrayList<>();

    public DebugResult() {
        this.startedTime = System.currentTimeMillis();
        this.mappingVersion = laivynpc().getVersion().getName();
    }

    /**
     * Gets the log information for this debug result
     * @return the logs
     */
    @NotNull
    public List<DebugLog> getLogs() {
        return log;
    }

    /**
     * @return The mapping version name
     */
    @NotNull
    public String getMappingVersion() {
        return mappingVersion;
    }

    /**
     * The time that the debug starts the process
     * @return the time that the debug started
     */
    public long getStartedTime() {
        return startedTime;
    }

    /**
     * @return get the time that the debug tooks to run
     */
    public long getTime() {
        return getStartedTime() - getFinishedTime();
    }

    /**
     * The time that the debug finished the process
     * @return the time that the debug finished
     */
    public long getFinishedTime() {
        return finishedTime;
    }

    public boolean isFinished()  {
        return this.finishedTime != 0;
    }

    public void finish() {
        this.finishedTime = System.currentTimeMillis() - getStartedTime();
    }

    public JsonObject serialize() {
        if (!isFinished()) {
            throw new IllegalStateException("This DebugResult isn't finished yet!");
        }

        JsonObject object = new JsonObject();

        JsonArray logs = new JsonArray();
        for (DebugLog log : getLogs()) {
            logs.add(log.serialize());
        }

        object.add("Started Time", JsonParser.parse("\"" + getStartedTime() + "\""));
        object.add("Finished Time", JsonParser.parse("\"" + getFinishedTime() + "\""));
        object.add("Logs", logs);

        return object;
    }

}
