package codes.laivy.npc.mappings.defaults.classes.packets.info.action;

import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.versions.V1_19_R2;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public interface IPlayerInfoAction {

    static @NotNull IPlayerInfoAction ADD_PLAYER() {
        EnumExecutor enumExec;

        if (ReflectionUtils.isCompatible(V1_19_R2.class)) {
            enumExec = laivynpc().getVersion().getEnumExec("PlayerInfo:Action");
        } else {
            enumExec = laivynpc().getVersion().getEnumExec("PacketPlayOutPlayerInfo:EnumPlayerInfoAction");
        }

        String name = laivynpc().getVersion().getText("PacketPlayOutPlayerInfo:EnumPlayerInfoAction:ADD_PLAYER");
        return laivynpc().getVersion().getPlayerInfoAction(enumExec.valueOf(name).getValue());
    }
    static @NotNull IPlayerInfoAction REMOVE_PLAYER() {
        if (ReflectionUtils.isCompatible(V1_19_R2.class)) {
            return () -> {
                throw new UnsupportedOperationException("The REMOVE_PLAYER info action doesn't holds a value since 1.19.3+");
            };
        }

        String name = laivynpc().getVersion().getText("PacketPlayOutPlayerInfo:EnumPlayerInfoAction:REMOVE_PLAYER");
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("PacketPlayOutPlayerInfo:EnumPlayerInfoAction");
        return laivynpc().getVersion().getPlayerInfoAction(enumExec.valueOf(name).getValue());
    }
    static @NotNull IPlayerInfoAction UPDATE_DISPLAY_NAME() {
        EnumExecutor enumExec;

        if (ReflectionUtils.isCompatible(V1_19_R2.class)) {
            enumExec = laivynpc().getVersion().getEnumExec("PlayerInfo:Action");
        } else {
            enumExec = laivynpc().getVersion().getEnumExec("PacketPlayOutPlayerInfo:EnumPlayerInfoAction");
        }

        String name = laivynpc().getVersion().getText("PacketPlayOutPlayerInfo:EnumPlayerInfoAction:UPDATE_DISPLAY_NAME");
        return laivynpc().getVersion().getPlayerInfoAction(enumExec.valueOf(name).getValue());
    }

    @Nullable Object getValue();
}
