package codes.laivy.npc.mappings.defaults.classes.packets;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.defaults.classes.java.EnumObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EntityUseInPacket extends Packet {
    public EntityUseInPacket(@Nullable Object value) {
        super(value);
    }

    public int getEntityId() {
        //noinspection ConstantConditions
        return (int) laivynpc().getVersion().getFieldExec("PacketPlayInUseEntity:entityId").invokeInstance(this);
    }

    public @NotNull ActionEnum.Action getClickAction() {
        return laivynpc().getVersion().getEntityUseInPacketAction(this);
    }

    public static class EntityUseInPacketClass extends ClassExecutor {
        public EntityUseInPacketClass(@NotNull String className) {
            super(className);
        }
    }

    public static class ActionEnum extends EnumExecutor {

        public static @NotNull Action INTERACT() {
            EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("PacketPlayInUseEntity:EnumEntityUseAction");
            String name = laivynpc().getVersion().getText("PacketPlayInUseEntity:EnumEntityUseAction:INTERACT");

            return new Action(enumExec.getEnums().get(name).getValue());
        }
        public static @NotNull Action ATTACK() {
            EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("PacketPlayInUseEntity:EnumEntityUseAction");
            String name = laivynpc().getVersion().getText("PacketPlayInUseEntity:EnumEntityUseAction:ATTACK");

            return new Action(enumExec.getEnums().get(name).getValue());
        }
        public static @NotNull Action INTERACT_AT() {
            EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("PacketPlayInUseEntity:EnumEntityUseAction");
            String name = laivynpc().getVersion().getText("PacketPlayInUseEntity:EnumEntityUseAction:INTERACT_AT");

            return new Action(enumExec.getEnums().get(name).getValue());
        }

        public ActionEnum(@NotNull ClassExecutor classExecutor) {
            super(classExecutor);
        }

        public static class Action extends EnumObjExec {
            public Action(@Nullable Enum<?> value) {
                super(value);
            }
        }
        public static class ActionClass extends ClassExecutor {
            public ActionClass(@NotNull String className) {
                super(className);
            }
        }
    }
}
