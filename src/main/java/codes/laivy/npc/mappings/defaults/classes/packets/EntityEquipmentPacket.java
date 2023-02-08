package codes.laivy.npc.mappings.defaults.classes.packets;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EntityEquipmentPacket extends Packet {
    public EntityEquipmentPacket(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull EntityEquipmentPacketClass getClassExecutor() {
        return (EntityEquipmentPacketClass) laivynpc().getVersion().getClassExec("PacketPlayOutEntityEquipment");
    }

    public static class EntityEquipmentPacketClass extends PacketClass {
        public EntityEquipmentPacketClass(@NotNull String className) {
            super(className);
        }
    }
}
