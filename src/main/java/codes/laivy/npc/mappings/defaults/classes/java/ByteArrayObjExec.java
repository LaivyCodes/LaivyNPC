package codes.laivy.npc.mappings.defaults.classes.java;

import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import org.jetbrains.annotations.NotNull;

public class ByteArrayObjExec extends ObjectExecutor {
    public ByteArrayObjExec(byte[] value) {
        super(value);
    }

    @Override
    public @NotNull ClassExecutor getClassExecutor() {
        return ClassExecutor.BYTE_ARRAY;
    }
}
