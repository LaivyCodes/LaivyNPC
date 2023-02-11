package codes.laivy.npc.mappings.defaults.classes.java;

import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import org.jetbrains.annotations.NotNull;

public class LongObjExec extends ObjectExecutor {
    public LongObjExec(long value) {
        super(value);
    }

    @Override
    public @NotNull ClassExecutor getClassExecutor() {
        return ClassExecutor.LONG;
    }
}
