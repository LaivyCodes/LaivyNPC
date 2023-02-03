package codes.laivy.npc.mappings.utils.classes.datawatcher;

import codes.laivy.npc.LaivyNPC;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.versions.V1_9_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class DataWatcher extends ObjectExecutor {
    public DataWatcher(@Nullable Object value) {
        super(value);
    }

    public void set(int index, @NotNull Object object) {
        for (Map.Entry<@NotNull Integer, @NotNull VersionedDataWatcherObject> map : values().entrySet()) {
            if (map.getKey() == index) {
                map.getValue().set(object);
                return;
            }
        }
        throw new IllegalStateException("Couldn't find this index '" + index + "'");
    }
    public @Nullable Object get(int index) {
        return values().get(index).get();
    }
    public @Nullable Object get(@NotNull DataWatcherObject object) {
        if (!ReflectionUtils.isCompatible(V1_9_R1.class)) {
            throw new IllegalStateException("This method is compatible only with 1.9+");
        }

        return laivynpc().getVersion().getMethodExec("DataWatcher:get:DataWatcherObject").invokeInstance(this, object);
    }
    public void set(@NotNull DataWatcherObject object, @NotNull ObjectExecutor value) {
        if (!ReflectionUtils.isCompatible(V1_9_R1.class)) {
            throw new IllegalStateException("This method is compatible only with 1.9+");
        }

        laivynpc().getVersion().getMethodExec("DataWatcher:set:DataWatcherObject").invokeInstance(this, object, value);
    }

    public @NotNull Map<@NotNull Integer, @NotNull VersionedDataWatcherObject> values() {
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
