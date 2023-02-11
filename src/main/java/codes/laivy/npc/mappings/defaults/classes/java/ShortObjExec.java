package codes.laivy.npc.mappings.defaults.classes.java;

import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import org.jetbrains.annotations.NotNull;

public class ShortObjExec extends ObjectExecutor {
    public ShortObjExec(short value) {
        super(value);
    }

    @Override
    public @NotNull ClassExecutor getClassExecutor() {
        return ClassExecutor.SHORT;
    }
}
