package codes.laivy.npc.mappings.utils.classes.java;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EnumObjExec extends ObjectExecutor {
    public EnumObjExec(@Nullable Enum<?> value) {
        super(value);
    }

    public @NotNull String name() {
        if (getValue() != null) return getValue().name();
        else throw new NullPointerException("You cannot get the name() of a null enum instance!");
    }

    @Override
    public @Nullable Enum<?> getValue() {
        return (Enum<?>) super.getValue();
    }

    @Override
    public @NotNull ClassExecutor getClassExecutor() {
        return ClassExecutor.ENUM;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EnumObjExec) {
            EnumObjExec enumObjExec = (EnumObjExec) obj;
            if (enumObjExec.getValue() != null && getValue() != null) {
                return getValue().equals(enumObjExec.getValue());
            }
        }
        return super.equals(obj);
    }
}
