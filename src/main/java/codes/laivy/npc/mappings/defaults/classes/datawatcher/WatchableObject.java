package codes.laivy.npc.mappings.defaults.classes.datawatcher;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.defaults.classes.java.ObjectObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class WatchableObject extends ObjectExecutor implements VersionedDataWatcherObject {
    public WatchableObject(@Nullable Object value) {
        super(value);
    }

    public @NotNull Object get() {
        return Objects.requireNonNull(laivynpc().getVersion().getMethodExec("WatchableObject:getValue").invokeInstance(this));
    }

    @Override
    public void set(@Nullable Object object) {
        laivynpc().getVersion().getMethodExec("WatchableObject:setValue").invokeInstance(this, new ObjectObjExec(object));
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
