package codes.laivy.npc.mappings.defaults.classes.java;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ObjectObjExec extends ObjectExecutor {
    public ObjectObjExec(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull ClassExecutor getClassExecutor() {
        return ClassExecutor.OBJECT;
    }
}
