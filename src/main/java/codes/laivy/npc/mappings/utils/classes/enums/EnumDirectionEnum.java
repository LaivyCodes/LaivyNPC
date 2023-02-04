package codes.laivy.npc.mappings.utils.classes.enums;

import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.utils.classes.java.EnumObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EnumDirectionEnum extends EnumExecutor {

    public static @NotNull EnumDirection DOWN() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumDirection");
        return new EnumDirection(enumExec.getEnums().get("DOWN").getValue());
    }
    public static @NotNull EnumDirection UP() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumDirection");
        return new EnumDirection(enumExec.getEnums().get("UP").getValue());
    }
    public static @NotNull EnumDirection NORTH() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumDirection");
        return new EnumDirection(enumExec.getEnums().get("NORTH").getValue());
    }
    public static @NotNull EnumDirection SOUTH() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumDirection");
        return new EnumDirection(enumExec.getEnums().get("SOUTH").getValue());
    }
    public static @NotNull EnumDirection WEST() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumDirection");
        return new EnumDirection(enumExec.getEnums().get("WEST").getValue());
    }
    public static @NotNull EnumDirection EAST() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumDirection");
        return new EnumDirection(enumExec.getEnums().get("EAST").getValue());
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
