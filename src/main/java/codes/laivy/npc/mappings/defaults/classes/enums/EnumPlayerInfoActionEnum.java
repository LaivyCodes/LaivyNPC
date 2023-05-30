package codes.laivy.npc.mappings.defaults.classes.enums;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.defaults.classes.java.EnumObjExec;
import codes.laivy.npc.mappings.versions.V1_19_R2;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EnumPlayerInfoActionEnum extends EnumExecutor {

    public static @Nullable EnumPlayerInfoAction ADD_PLAYER() {
        if (ReflectionUtils.isCompatible(V1_19_R2.class)) {
            return null;
        }

        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("PacketPlayOutPlayerInfo:EnumPlayerInfoAction");
        String name = laivynpc().getVersion().getText("PacketPlayOutPlayerInfo:EnumPlayerInfoAction:ADD_PLAYER");

        return new EnumPlayerInfoAction(enumExec.valueOf(name).getValue());
    }
    public static @Nullable EnumPlayerInfoAction REMOVE_PLAYER() {
        if (ReflectionUtils.isCompatible(V1_19_R2.class)) {
            return null;
        }

        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("PacketPlayOutPlayerInfo:EnumPlayerInfoAction");
        String name = laivynpc().getVersion().getText("PacketPlayOutPlayerInfo:EnumPlayerInfoAction:REMOVE_PLAYER");

        return new EnumPlayerInfoAction(enumExec.valueOf(name).getValue());
    }
    public static @Nullable EnumPlayerInfoAction UPDATE_DISPLAY_NAME() {
        if (ReflectionUtils.isCompatible(V1_19_R2.class)) {
            return null;
        }

        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("PacketPlayOutPlayerInfo:EnumPlayerInfoAction");
        String name = laivynpc().getVersion().getText("PacketPlayOutPlayerInfo:EnumPlayerInfoAction:UPDATE_DISPLAY_NAME");

        return new EnumPlayerInfoAction(enumExec.valueOf(name).getValue());
    }

    public EnumPlayerInfoActionEnum(@NotNull ClassExecutor classExecutor) {
        super(classExecutor);
    }

    public static class EnumPlayerInfoAction extends EnumObjExec {
        public EnumPlayerInfoAction(@NotNull Enum<?> value) {
            super(value);
        }
    }

    public static class EnumPlayerInfoActionClass extends ClassExecutor {
        public EnumPlayerInfoActionClass(@NotNull String className) {
            super(className);
        }
    }
}
