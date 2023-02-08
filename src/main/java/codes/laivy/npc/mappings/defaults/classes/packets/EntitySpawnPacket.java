package codes.laivy.npc.mappings.defaults.classes.packets;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EntitySpawnPacket extends Packet {
    public EntitySpawnPacket(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull EntitySpawnPacketClass getClassExecutor() {
        return (EntitySpawnPacketClass) laivynpc().getVersion().getClassExec("PacketPlayOutSpawnEntity");
    }

    public static class EntitySpawnPacketClass extends PacketClass {
        public EntitySpawnPacketClass(@NotNull String className) {
            super(className);
        }
    }
}
