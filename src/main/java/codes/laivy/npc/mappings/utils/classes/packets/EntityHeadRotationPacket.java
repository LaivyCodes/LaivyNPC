package codes.laivy.npc.mappings.utils.classes.packets;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EntityHeadRotationPacket extends Packet {
    public EntityHeadRotationPacket(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull EntityHeadRotationPacketClass getClassExecutor() {
        return (EntityHeadRotationPacketClass) laivynpc().getVersion().getClassExec("PacketPlayOutEntityHeadRotation");
    }

    public static class EntityHeadRotationPacketClass extends PacketClass {
        public EntityHeadRotationPacketClass(@NotNull String className) {
            super(className);
        }
    }
}
