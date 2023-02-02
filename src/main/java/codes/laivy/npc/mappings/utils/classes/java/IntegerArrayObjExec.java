package codes.laivy.npc.mappings.utils.classes.java;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import org.jetbrains.annotations.NotNull;

public class IntegerArrayObjExec extends ObjectExecutor {
    public IntegerArrayObjExec(int[] value) {
        super(value);
    }

    @Override
    public @NotNull ClassExecutor getClassExecutor() {
        return ClassExecutor.INT_ARRAY;
    }
}
