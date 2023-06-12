package codes.laivy.npc.mappings.defaults.classes.enums;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.defaults.classes.java.EnumObjExec;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EnumChatFormatEnum extends EnumExecutor {

    public static @NotNull EnumChatFormatEnum getInstance() {
        return (EnumChatFormatEnum) laivynpc().getVersion().getEnumExec("EnumChatFormat");
    }
    
    public static @NotNull EnumChatFormat BLACK() {
        return new EnumChatFormat(getInstance().getEnums().get("BLACK").getValue());
    }
    public static @NotNull EnumChatFormat DARK_BLUE() {
        return new EnumChatFormat(getInstance().getEnums().get("DARK_BLUE").getValue());
    }
    public static @NotNull EnumChatFormat DARK_GREEN() {
        return new EnumChatFormat(getInstance().getEnums().get("DARK_GREEN").getValue());
    }
    public static @NotNull EnumChatFormat DARK_AQUA() {
        return new EnumChatFormat(getInstance().getEnums().get("DARK_AQUA").getValue());
    }
    public static @NotNull EnumChatFormat DARK_RED() {
        return new EnumChatFormat(getInstance().getEnums().get("DARK_RED").getValue());
    }
    public static @NotNull EnumChatFormat DARK_PURPLE() {
        return new EnumChatFormat(getInstance().getEnums().get("DARK_PURPLE").getValue());
    }
    public static @NotNull EnumChatFormat GOLD() {
        return new EnumChatFormat(getInstance().getEnums().get("GOLD").getValue());
    }
    public static @NotNull EnumChatFormat GRAY() {
        return new EnumChatFormat(getInstance().getEnums().get("GRAY").getValue());
    }
    public static @NotNull EnumChatFormat DARK_GRAY() {
        return new EnumChatFormat(getInstance().getEnums().get("DARK_GRAY").getValue());
    }
    public static @NotNull EnumChatFormat BLUE() {
        return new EnumChatFormat(getInstance().getEnums().get("BLUE").getValue());
    }
    public static @NotNull EnumChatFormat GREEN() {
        return new EnumChatFormat(getInstance().getEnums().get("GREEN").getValue());
    }
    public static @NotNull EnumChatFormat AQUA() {
        return new EnumChatFormat(getInstance().getEnums().get("AQUA").getValue());
    }
    public static @NotNull EnumChatFormat RED() {
        return new EnumChatFormat(getInstance().getEnums().get("RED").getValue());
    }
    public static @NotNull EnumChatFormat LIGHT_PURPLE() {
        return new EnumChatFormat(getInstance().getEnums().get("LIGHT_PURPLE").getValue());
    }
    public static @NotNull EnumChatFormat YELLOW() {
        return new EnumChatFormat(getInstance().getEnums().get("YELLOW").getValue());
    }
    public static @NotNull EnumChatFormat WHITE() {
        return new EnumChatFormat(getInstance().getEnums().get("WHITE").getValue());
    }

    public static @NotNull EnumChatFormat from(@NotNull ChatColor color) {
        switch (color) {
            case BLACK:
                return BLACK();
            case DARK_BLUE:
                return DARK_BLUE();
            case DARK_GREEN:
                return DARK_GREEN();
            case DARK_AQUA:
                return DARK_AQUA();
            case DARK_RED:
                return DARK_RED();
            case DARK_PURPLE:
                return DARK_PURPLE();
            case GOLD:
                return GOLD();
            case GRAY:
                return GRAY();
            case DARK_GRAY:
                return DARK_GRAY();
            case BLUE:
                return BLUE();
            case GREEN:
                return GREEN();
            case AQUA:
                return AQUA();
            case RED:
                return RED();
            case LIGHT_PURPLE:
                return LIGHT_PURPLE();
            case YELLOW:
                return YELLOW();
            default:
                return WHITE();
        }
    }

    public EnumChatFormatEnum(@NotNull ClassExecutor classExecutor) {
        super(classExecutor);
    }

    public static class EnumChatFormat extends EnumObjExec {
        public EnumChatFormat(@NotNull Enum<?> value) {
            super(value);
        }
    }

    public static class EnumChatFormatClass extends ClassExecutor {
        public EnumChatFormatClass(@NotNull String className) {
            super(className);
        }
    }
}
