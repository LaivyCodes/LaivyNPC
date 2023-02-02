package codes.laivy.npc.mappings.utils.classes.enums;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.utils.classes.java.EnumObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EnumPlayerInfoActionEnum extends EnumExecutor {

    public static @NotNull EnumPlayerInfoAction ADD_PLAYER() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("PacketPlayOutPlayerInfo:EnumPlayerInfoAction");
        String name = laivynpc().getVersion().getText("PacketPlayOutPlayerInfo:EnumPlayerInfoAction:ADD_PLAYER");

        return new EnumPlayerInfoAction(enumExec.valueOf(name).getValue());
    }
    public static @NotNull EnumPlayerInfoAction REMOVE_PLAYER() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("PacketPlayOutPlayerInfo:EnumPlayerInfoAction");
        String name = laivynpc().getVersion().getText("PacketPlayOutPlayerInfo:EnumPlayerInfoAction:REMOVE_PLAYER");

        return new EnumPlayerInfoAction(enumExec.valueOf(name).getValue());
    }

    public EnumPlayerInfoActionEnum(@NotNull ClassExecutor classExecutor) {
        super(classExecutor);
    }

    public static class EnumPlayerInfoAction extends EnumObjExec {
        public EnumPlayerInfoAction(@Nullable Enum<?> value) {
            super(value);
        }
    }

    public static class EnumPlayerInfoActionClass extends ClassExecutor {
        public EnumPlayerInfoActionClass(@NotNull String className) {
            super(className);
        }
    }
}
