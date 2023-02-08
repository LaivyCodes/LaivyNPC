package codes.laivy.npc.mappings.defaults.classes.java;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import org.jetbrains.annotations.NotNull;

public class IntegerObjExec extends ObjectExecutor {
    public IntegerObjExec(int value) {
        super(value);
    }

    @Override
    public @NotNull ClassExecutor getClassExecutor() {
        return ClassExecutor.INT;
    }
}
