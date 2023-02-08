package codes.laivy.npc.mappings.defaults.classes.packets;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EntityPaintingSpawnPacket extends Packet {
    public EntityPaintingSpawnPacket(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull EntityPaintingSpawnPacket.EntityPaintingSpawnPacketClass getClassExecutor() {
        return (EntityPaintingSpawnPacketClass) laivynpc().getVersion().getClassExec("PacketPlayOutSpawnEntityPainting");
    }

    public static class EntityPaintingSpawnPacketClass extends PacketClass {
        public EntityPaintingSpawnPacketClass(@NotNull String className) {
            super(className);
        }
    }
}
