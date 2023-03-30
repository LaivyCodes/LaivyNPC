package codes.laivy.npc.mappings.defaults.classes.packets;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EntitySpawnPacket extends EntityLivingSpawnPacket {
    public EntitySpawnPacket(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull EntitySpawnPacketClass getClassExecutor() {
        return (EntitySpawnPacketClass) laivynpc().getVersion().getClassExec("PacketPlayOutSpawnEntity");
    }

    public static class EntitySpawnPacketClass extends EntityLivingSpawnPacketClass {
        public EntitySpawnPacketClass(@NotNull String className) {
            super(className);
        }
    }
}
