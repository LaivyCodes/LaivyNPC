package codes.laivy.npc.mappings.defaults.classes.enums;

import codes.laivy.npc.mappings.defaults.classes.java.IntegerObjExec;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.defaults.classes.java.EnumObjExec;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EnumColorEnum extends EnumExecutor {

    public static @NotNull EnumColorEnum getInstance() {
        return (EnumColorEnum) laivynpc().getVersion().getEnumExec("EnumColor");
    }

    public static @NotNull EnumColor WHITE() {
        return new EnumColor(getInstance().getEnums().get("WHITE").getValue());
    }
    public static @NotNull EnumColor ORANGE() {
        return new EnumColor(getInstance().getEnums().get("ORANGE").getValue());
    }
    public static @NotNull EnumColor MAGENTA() {
        return new EnumColor(getInstance().getEnums().get("MAGENTA").getValue());
    }
    public static @NotNull EnumColor LIGHT_BLUE() {
        return new EnumColor(getInstance().getEnums().get("LIGHT_BLUE").getValue());
    }
    public static @NotNull EnumColor YELLOW() {
        return new EnumColor(getInstance().getEnums().get("YELLOW").getValue());
    }
    public static @NotNull EnumColor LIME() {
        return new EnumColor(getInstance().getEnums().get("LIME").getValue());
    }
    public static @NotNull EnumColor PINK() {
        return new EnumColor(getInstance().getEnums().get("PINK").getValue());
    }
    public static @NotNull EnumColor GRAY() {
        return new EnumColor(getInstance().getEnums().get("GRAY").getValue());
    }
    public static @NotNull EnumColor SILVER() {
        return new EnumColor(getInstance().getEnums().get("SILVER").getValue());
    }
    public static @NotNull EnumColor CYAN() {
        return new EnumColor(getInstance().getEnums().get("CYAN").getValue());
    }
    public static @NotNull EnumColor PURPLE() {
        return new EnumColor(getInstance().getEnums().get("PURPLE").getValue());
    }
    public static @NotNull EnumColor BLUE() {
        return new EnumColor(getInstance().getEnums().get("BLUE").getValue());
    }
    public static @NotNull EnumColor BROWN() {
        return new EnumColor(getInstance().getEnums().get("BROWN").getValue());
    }
    public static @NotNull EnumColor GREEN() {
        return new EnumColor(getInstance().getEnums().get("GREEN").getValue());
    }
    public static @NotNull EnumColor RED() {
        return new EnumColor(getInstance().getEnums().get("RED").getValue());
    }
    public static @NotNull EnumColor BLACK() {
        return new EnumColor(getInstance().getEnums().get("BLACK").getValue());
    }

    public EnumColorEnum(@NotNull ClassExecutor classExecutor) {
        super(classExecutor);
    }

    public static class EnumColor extends EnumObjExec {
        public EnumColor(@NotNull Enum<?> value) {
            super(value);
        }

        public int getColorIndex() {
            //noinspection DataFlowIssue
            return (int) laivynpc().getVersion().getMethodExec("EnumColor:getColorIndex").invokeInstance(this);
        }
    }

    public static @NotNull EnumColor fromColorIndex(int index) {
        return new EnumColor((Enum<?>) Objects.requireNonNull(laivynpc().getVersion().getMethodExec("EnumColor:fromColorIndex").invokeStatic(new IntegerObjExec(index))));
    }

    public static class EnumColorClass extends ClassExecutor {
        public EnumColorClass(@NotNull String className) {
            super(className);
        }
    }
}
