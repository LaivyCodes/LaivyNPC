package codes.laivy.npc.mappings.defaults.classes.packets;

import codes.laivy.npc.LaivyNPC;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EntityLivingSpawnPacket extends Packet {
    public EntityLivingSpawnPacket(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull EntityLivingSpawnPacketClass getClassExecutor() {
        return (EntityLivingSpawnPacketClass) LaivyNPC.laivynpc().getVersion().getClassExec("PacketPlayOutSpawnEntityLiving");
    }

    public static class EntityLivingSpawnPacketClass extends PacketClass {
        public EntityLivingSpawnPacketClass(@NotNull String className) {
            super(className);
        }
    }
}
