package codes.laivy.npc.mappings.utils.classes.enums;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.utils.classes.java.EnumObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EnumColorEnum extends EnumExecutor {

    public static @NotNull EnumColor WHITE() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumColor");
        return new EnumColor(enumExec.getEnums().get("WHITE").getValue());
    }
    public static @NotNull EnumColor ORANGE() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumColor");
        return new EnumColor(enumExec.getEnums().get("ORANGE").getValue());
    }
    public static @NotNull EnumColor MAGENTA() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumColor");
        return new EnumColor(enumExec.getEnums().get("MAGENTA").getValue());
    }
    public static @NotNull EnumColor LIGHT_BLUE() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumColor");
        return new EnumColor(enumExec.getEnums().get("LIGHT_BLUE").getValue());
    }
    public static @NotNull EnumColor YELLOW() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumColor");
        return new EnumColor(enumExec.getEnums().get("YELLOW").getValue());
    }
    public static @NotNull EnumColor LIME() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumColor");
        return new EnumColor(enumExec.getEnums().get("LIME").getValue());
    }
    public static @NotNull EnumColor PINK() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumColor");
        return new EnumColor(enumExec.getEnums().get("PINK").getValue());
    }
    public static @NotNull EnumColor GRAY() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumColor");
        return new EnumColor(enumExec.getEnums().get("GRAY").getValue());
    }
    public static @NotNull EnumColor SILVER() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumColor");
        return new EnumColor(enumExec.getEnums().get("SILVER").getValue());
    }
    public static @NotNull EnumColor CYAN() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumColor");
        return new EnumColor(enumExec.getEnums().get("CYAN").getValue());
    }
    public static @NotNull EnumColor PURPLE() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumColor");
        return new EnumColor(enumExec.getEnums().get("PURPLE").getValue());
    }
    public static @NotNull EnumColor BLUE() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumColor");
        return new EnumColor(enumExec.getEnums().get("BLUE").getValue());
    }
    public static @NotNull EnumColor BROWN() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumColor");
        return new EnumColor(enumExec.getEnums().get("BROWN").getValue());
    }
    public static @NotNull EnumColor GREEN() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumColor");
        return new EnumColor(enumExec.getEnums().get("GREEN").getValue());
    }
    public static @NotNull EnumColor RED() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumColor");
        return new EnumColor(enumExec.getEnums().get("RED").getValue());
    }
    public static @NotNull EnumColor BLACK() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumColor");
        return new EnumColor(enumExec.getEnums().get("BLACK").getValue());
    }

    public EnumColorEnum(@NotNull ClassExecutor classExecutor) {
        super(classExecutor);
    }

    public static class EnumColor extends EnumObjExec {
        public EnumColor(@Nullable Enum<?> value) {
            super(value);
        }
    }

    public static class EnumColorClass extends ClassExecutor {
        public EnumColorClass(@NotNull String className) {
            super(className);
        }
    }
}
