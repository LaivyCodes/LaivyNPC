package codes.laivy.npc.mappings.utils.classes.datawatcher;

import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.utils.classes.java.ObjectObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class DataWatcherItem extends ObjectExecutor implements VersionedDataWatcherObject {
    public DataWatcherItem(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull DataWatcherItemClass getClassExecutor() {
        return (DataWatcherItemClass) laivynpc().getVersion().getClassExec("DataWatcher:Item");
    }

    @Override
    public @Nullable Object get() {
        return laivynpc().getVersion().getMethodExec("DataWatcher:Item:get").invokeInstance(this);
    }

    @Override
    public void set(@Nullable Object object) {
        laivynpc().getVersion().getMethodExec("DataWatcher:Item:set").invokeInstance(this, new ObjectObjExec(object));
    }

    public static class DataWatcherItemClass extends ClassExecutor {
        public DataWatcherItemClass(@NotNull String className) {
            super(className);
        }
    }
}
