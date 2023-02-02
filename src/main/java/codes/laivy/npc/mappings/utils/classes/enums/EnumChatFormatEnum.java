package codes.laivy.npc.mappings.utils.classes.enums;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.utils.classes.java.EnumObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EnumChatFormatEnum extends EnumExecutor {

    public static @NotNull EnumChatFormat BLACK() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumChatFormat");
        return new EnumChatFormat(enumExec.getEnums().get("BLACK").getValue());
    }
    public static @NotNull EnumChatFormat DARK_BLUE() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumChatFormat");
        return new EnumChatFormat(enumExec.getEnums().get("DARK_BLUE").getValue());
    }
    public static @NotNull EnumChatFormat DARK_GREEN() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumChatFormat");
        return new EnumChatFormat(enumExec.getEnums().get("DARK_GREEN").getValue());
    }
    public static @NotNull EnumChatFormat DARK_AQUA() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumChatFormat");
        return new EnumChatFormat(enumExec.getEnums().get("DARK_AQUA").getValue());
    }
    public static @NotNull EnumChatFormat DARK_RED() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumChatFormat");
        return new EnumChatFormat(enumExec.getEnums().get("DARK_RED").getValue());
    }
    public static @NotNull EnumChatFormat DARK_PURPLE() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumChatFormat");
        return new EnumChatFormat(enumExec.getEnums().get("DARK_PURPLE").getValue());
    }
    public static @NotNull EnumChatFormat GOLD() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumChatFormat");
        return new EnumChatFormat(enumExec.getEnums().get("GOLD").getValue());
    }
    public static @NotNull EnumChatFormat GRAY() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumChatFormat");
        return new EnumChatFormat(enumExec.getEnums().get("GRAY").getValue());
    }
    public static @NotNull EnumChatFormat DARK_GRAY() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumChatFormat");
        return new EnumChatFormat(enumExec.getEnums().get("DARK_GRAY").getValue());
    }
    public static @NotNull EnumChatFormat BLUE() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumChatFormat");
        return new EnumChatFormat(enumExec.getEnums().get("BLUE").getValue());
    }
    public static @NotNull EnumChatFormat GREEN() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumChatFormat");
        return new EnumChatFormat(enumExec.getEnums().get("GREEN").getValue());
    }
    public static @NotNull EnumChatFormat AQUA() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumChatFormat");
        return new EnumChatFormat(enumExec.getEnums().get("AQUA").getValue());
    }
    public static @NotNull EnumChatFormat RED() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumChatFormat");
        return new EnumChatFormat(enumExec.getEnums().get("RED").getValue());
    }
    public static @NotNull EnumChatFormat LIGHT_PURPLE() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumChatFormat");
        return new EnumChatFormat(enumExec.getEnums().get("LIGHT_PURPLE").getValue());
    }
    public static @NotNull EnumChatFormat YELLOW() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumChatFormat");
        return new EnumChatFormat(enumExec.getEnums().get("YELLOW").getValue());
    }
    public static @NotNull EnumChatFormat WHITE() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumChatFormat");
        return new EnumChatFormat(enumExec.getEnums().get("WHITE").getValue());
    }

    public EnumChatFormatEnum(@NotNull ClassExecutor classExecutor) {
        super(classExecutor);
    }

    public static class EnumChatFormat extends EnumObjExec {
        public EnumChatFormat(@Nullable Enum<?> value) {
            super(value);
        }
    }

    public static class EnumChatFormatClass extends ClassExecutor {
        public EnumChatFormatClass(@NotNull String className) {
            super(className);
        }
    }
}
