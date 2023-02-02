package codes.laivy.npc.mappings.utils.classes.datawatcher;

import codes.laivy.npc.LaivyNPC;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class DataWatcher extends ObjectExecutor {
    public DataWatcher(@Nullable Object value) {
        super(value);
    }

    public void set(int index, @NotNull Object object) {
        laivynpc().getVersion().dataWatcherSet(this, index, object);
    }
    public @NotNull Object get(int index) {
        return values().get(index).get();
    }

    public @NotNull Map<@NotNull Integer, @NotNull WatchableObject> values() {
        return laivynpc().getVersion().dataWatcherGetValues(this);
    }

    @Override
    public @NotNull DataWatcherClass getClassExecutor() {
        return (DataWatcherClass) LaivyNPC.laivynpc().getVersion().getClassExec("DataWatcher");
    }

    public static class DataWatcherClass extends ClassExecutor {
        public DataWatcherClass(@NotNull String className) {
            super(className);
        }
    }
}
