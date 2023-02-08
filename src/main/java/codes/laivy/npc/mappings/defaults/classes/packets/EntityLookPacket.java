package codes.laivy.npc.mappings.defaults.classes.packets;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EntityLookPacket extends Packet {
    public EntityLookPacket(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull EntityLookPacketClass getClassExecutor() {
        return (EntityLookPacketClass) laivynpc().getVersion().getClassExec("PacketPlayOutEntityLook");
    }

    public static class EntityLookPacketClass extends PacketClass {
        public EntityLookPacketClass(@NotNull String className) {
            super(className);
        }
    }
}
