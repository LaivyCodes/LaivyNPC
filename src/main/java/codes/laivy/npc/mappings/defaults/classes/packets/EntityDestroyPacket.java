package codes.laivy.npc.mappings.defaults.classes.packets;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EntityDestroyPacket extends Packet {
    public EntityDestroyPacket(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull EntityDestroyPacketClass getClassExecutor() {
        return (EntityDestroyPacketClass) laivynpc().getVersion().getClassExec("PacketPlayOutEntityDestroy");
    }

    public static class EntityDestroyPacketClass extends PacketClass {
        public EntityDestroyPacketClass(@NotNull String className) {
            super(className);
        }
    }
}
