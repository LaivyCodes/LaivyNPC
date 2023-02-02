package codes.laivy.npc.mappings.utils.classes.datawatcher;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class WatchableObject extends ObjectExecutor {
    public WatchableObject(@Nullable Object value) {
        super(value);
    }

    public @NotNull Object get() {
        return Objects.requireNonNull(laivynpc().getVersion().getMethodExec("WatchableObject:getValue").invokeInstance(this));
    }

    @Override
    public @NotNull WatchableObjectClass getClassExecutor() {
        return (WatchableObjectClass) laivynpc().getVersion().getClassExec("WatchableObject");
    }

    public static class WatchableObjectClass extends ClassExecutor {
        public WatchableObjectClass(@NotNull String className) {
            super(className);
        }
    }
}
