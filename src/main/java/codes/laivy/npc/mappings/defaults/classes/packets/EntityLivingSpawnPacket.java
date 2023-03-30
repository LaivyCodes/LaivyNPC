package codes.laivy.npc.mappings.defaults.classes.packets;

import codes.laivy.npc.LaivyNPC;
import codes.laivy.npc.mappings.versions.V1_19_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EntityLivingSpawnPacket extends Packet {
    public EntityLivingSpawnPacket(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull EntityLivingSpawnPacketClass getClassExecutor() {
        if (ReflectionUtils.isCompatible(V1_19_R1.class)) {
            throw new UnsupportedOperationException("This packet is only allowed before 1.19");
        }

        return (EntityLivingSpawnPacketClass) LaivyNPC.laivynpc().getVersion().getClassExec("PacketPlayOutSpawnEntityLiving");
    }

    public static class EntityLivingSpawnPacketClass extends PacketClass {
        public EntityLivingSpawnPacketClass(@NotNull String className) {
            super(className);
        }
    }
}
