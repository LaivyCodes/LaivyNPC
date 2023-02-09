package codes.laivy.npc.mappings.defaults.classes.java;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class EnumObjExec extends ObjectExecutor {
    public EnumObjExec(@NotNull Enum<?> value) {
        super(value);
    }

    public @NotNull String name() {
        return getValue().name();
    }

    @Override
    public @NotNull Enum<?> getValue() {
        return (Enum<?>) Objects.requireNonNull(super.getValue());
    }
    @Override
    public void setValue(@Nullable Object value) {
        if (value == null) {
            throw new NullPointerException("Enum object executors couldn't have their values as null");
        }
        super.setValue(value);
    }

    @Override
    public @NotNull ClassExecutor getClassExecutor() {
        return ClassExecutor.ENUM;
    }
}
