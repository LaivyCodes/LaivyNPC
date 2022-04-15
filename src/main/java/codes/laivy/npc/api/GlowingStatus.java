package codes.laivy.npc.api;

import codes.laivy.npc.reflection.ReflectionUtils;
import codes.laivy.npc.utils.Validation;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.bukkit.ChatColor;

import static codes.laivy.npc.reflection.ReflectionUtils.getEnum;
import static codes.laivy.npc.reflection.ReflectionUtils.getVersion;

public class GlowingStatus {

    private GlowingColor color;
    private boolean active;

    public GlowingStatus(@NotNull GlowingColor color, @Nullable boolean active) {
        if (getVersion().versionCode < 9) {
            throw new IllegalStateException("O efeito luminoso só está disponível na versão 1.9 ou superior");
        }

        Validation.notNull(color, new NullPointerException("O NPC não pode ser nulo"));

        this.color = color;
        this.active = active;
    }

    public GlowingColor getColor() {
        return color;
    }

    public void setColor(GlowingColor color) {
        if (getVersion().versionCode < 9) {
            throw new IllegalStateException("O efeito luminoso só está disponível na versão 1.9 ou superior");
        }

        this.color = color;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        if (getVersion().versionCode < 9) {
            throw new IllegalStateException("O efeito luminoso só está disponível na versão 1.9 ou superior");
        }

        this.active = active;
    }

    public enum GlowingColor {

        BLACK(getEnum(ReflectionUtils.getClass(getVersion().EnumChatFormat), getVersion().EnumChatFormat_BLACK)),
        DARK_BLUE(getEnum(ReflectionUtils.getClass(getVersion().EnumChatFormat), getVersion().EnumChatFormat_DARK_BLUE)),
        DARK_GREEN(getEnum(ReflectionUtils.getClass(getVersion().EnumChatFormat), getVersion().EnumChatFormat_DARK_GREEN)),
        DARK_AQUA(getEnum(ReflectionUtils.getClass(getVersion().EnumChatFormat), getVersion().EnumChatFormat_DARK_AQUA)),
        DARK_RED(getEnum(ReflectionUtils.getClass(getVersion().EnumChatFormat), getVersion().EnumChatFormat_DARK_RED)),
        DARK_PURPLE(getEnum(ReflectionUtils.getClass(getVersion().EnumChatFormat), getVersion().EnumChatFormat_DARK_PURPLE)),
        GOLD(getEnum(ReflectionUtils.getClass(getVersion().EnumChatFormat), getVersion().EnumChatFormat_GOLD)),
        GRAY(getEnum(ReflectionUtils.getClass(getVersion().EnumChatFormat), getVersion().EnumChatFormat_GRAY)),
        DARK_GRAY(getEnum(ReflectionUtils.getClass(getVersion().EnumChatFormat), getVersion().EnumChatFormat_DARK_GRAY)),
        BLUE(getEnum(ReflectionUtils.getClass(getVersion().EnumChatFormat), getVersion().EnumChatFormat_BLUE)),
        GREEN(getEnum(ReflectionUtils.getClass(getVersion().EnumChatFormat), getVersion().EnumChatFormat_GREEN)),
        AQUA(getEnum(ReflectionUtils.getClass(getVersion().EnumChatFormat), getVersion().EnumChatFormat_AQUA)),
        RED(getEnum(ReflectionUtils.getClass(getVersion().EnumChatFormat), getVersion().EnumChatFormat_RED)),
        LIGHT_PURPLE(getEnum(ReflectionUtils.getClass(getVersion().EnumChatFormat), getVersion().EnumChatFormat_LIGHT_PURPLE)),
        YELLOW(getEnum(ReflectionUtils.getClass(getVersion().EnumChatFormat), getVersion().EnumChatFormat_YELLOW)),
        WHITE(getEnum(ReflectionUtils.getClass(getVersion().EnumChatFormat), getVersion().EnumChatFormat_WHITE));

        private final Object enumChatFormat;

        GlowingColor(Object enumChatFormat) {
            this.enumChatFormat = enumChatFormat;
        }

        public ChatColor getChatColor() {
            return ChatColor.valueOf(name());
        }

        public Object getEnumChatFormat() {
            return this.enumChatFormat;
        }

        public static GlowingColor getColor(ChatColor chatColor) {
            try {
                return valueOf(chatColor.name());
            } catch (IllegalArgumentException e) {
                return WHITE;
            }
        }
    }

}