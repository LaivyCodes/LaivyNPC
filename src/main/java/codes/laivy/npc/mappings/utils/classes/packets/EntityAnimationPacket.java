package codes.laivy.npc.mappings.utils.classes.packets;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EntityAnimationPacket extends Packet {
    public EntityAnimationPacket(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull EntityAnimationPacketClass getClassExecutor() {
        return (EntityAnimationPacketClass) laivynpc().getVersion().getClassExec("PacketPlayOutAnimation");
    }

    public static class EntityAnimationPacketClass extends PacketClass {
        public EntityAnimationPacketClass(@NotNull String className) {
            super(className);
        }
    }
}
