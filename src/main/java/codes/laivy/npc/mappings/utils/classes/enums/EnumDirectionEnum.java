package codes.laivy.npc.mappings.utils.classes.enums;

import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.utils.classes.java.EnumObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EnumDirectionEnum extends EnumExecutor {

    public static @NotNull EnumDirectionEnum getInstance() {
        return (EnumDirectionEnum) laivynpc().getVersion().getEnumExec("EnumDirection");
    }

    public static @NotNull EnumDirection DOWN() {
        return new EnumDirection(getInstance().getEnums().get("DOWN").getValue());
    }
    public static @NotNull EnumDirection UP() {
        return new EnumDirection(getInstance().getEnums().get("UP").getValue());
    }
    public static @NotNull EnumDirection NORTH() {
        return new EnumDirection(getInstance().getEnums().get("NORTH").getValue());
    }
    public static @NotNull EnumDirection SOUTH() {
        return new EnumDirection(getInstance().getEnums().get("SOUTH").getValue());
    }
    public static @NotNull EnumDirection WEST() {
        return new EnumDirection(getInstance().getEnums().get("WEST").getValue());
    }
    public static @NotNull EnumDirection EAST() {
        return new EnumDirection(getInstance().getEnums().get("EAST").getValue());
    }

    public EnumDirectionEnum(@NotNull ClassExecutor classExecutor) {
        super(classExecutor);
    }

    public static final class EnumDirection extends EnumObjExec {
        public EnumDirection(@Nullable Enum<?> value) {
            super(value);
        }
    }

    public static final class EnumDirectionClass extends ClassExecutor {
        public EnumDirectionClass(@NotNull String className) {
            super(className);
        }
    }
}
