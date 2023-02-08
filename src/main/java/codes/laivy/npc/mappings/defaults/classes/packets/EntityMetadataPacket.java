package codes.laivy.npc.mappings.defaults.classes.packets;

import codes.laivy.npc.LaivyNPC;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EntityMetadataPacket extends Packet {
    public EntityMetadataPacket(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull EntityMetadataPacketClass getClassExecutor() {
        return (EntityMetadataPacketClass) LaivyNPC.laivynpc().getVersion().getClassExec("PacketPlayOutEntityMetadata");
    }

    public static class EntityMetadataPacketClass extends PacketClass {
        public EntityMetadataPacketClass(@NotNull String className) {
            super(className);
        }
    }
}
