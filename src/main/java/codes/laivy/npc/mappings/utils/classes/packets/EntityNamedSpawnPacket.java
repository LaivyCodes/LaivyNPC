package codes.laivy.npc.mappings.utils.classes.packets;

import codes.laivy.npc.LaivyNPC;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EntityNamedSpawnPacket extends Packet {
    public EntityNamedSpawnPacket(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull EntityNamedSpawnPacket.EntityNamedSpawnPacketClass getClassExecutor() {
        return (EntityNamedSpawnPacketClass) LaivyNPC.laivynpc().getVersion().getClassExec("PacketPlayOutNamedEntitySpawn");
    }

    public static class EntityNamedSpawnPacketClass extends PacketClass {
        public EntityNamedSpawnPacketClass(@NotNull String className) {
            super(className);
        }
    }
}
