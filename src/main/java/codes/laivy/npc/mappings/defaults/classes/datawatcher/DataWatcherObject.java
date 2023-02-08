package codes.laivy.npc.mappings.defaults.classes.datawatcher;

import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.versions.V1_9_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

/**
 * Available only on 1.9+
 */
public class DataWatcherObject extends ObjectExecutor {

    {
        if (!ReflectionUtils.isCompatible(V1_9_R1.class)) {
            throw new IllegalStateException("DataWatcherObject compatible only with 1.9+");
        }
    }

    public DataWatcherObject(@Nullable Object value) {
        super(value);
    }

    public int getId() {
        //noinspection DataFlowIssue
        return (int) laivynpc().getVersion().getMethodExec("DataWatcherObject:getId").invokeInstance(this);
    }

    @Override
    public @NotNull DataWatcherObjectClass getClassExecutor() {
        return (DataWatcherObjectClass) laivynpc().getVersion().getClassExec("DataWatcherObject");
    }

    public static class DataWatcherObjectClass extends ClassExecutor {
        public DataWatcherObjectClass(@NotNull String className) {
            super(className);
        }
    }
}
