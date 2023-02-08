package codes.laivy.npc.mappings.defaults.classes.packets;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EntityTeleportPacket extends Packet {
    public EntityTeleportPacket(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull EntityTeleportPacketClass getClassExecutor() {
        return (EntityTeleportPacketClass) laivynpc().getVersion().getClassExec("PacketPlayOutEntityTeleport");
    }

    public static class EntityTeleportPacketClass extends PacketClass {
        public EntityTeleportPacketClass(@NotNull String className) {
            super(className);
        }
    }
}
