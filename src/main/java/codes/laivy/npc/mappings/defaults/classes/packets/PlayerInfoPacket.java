package codes.laivy.npc.mappings.defaults.classes.packets;

import codes.laivy.npc.LaivyNPC;
import codes.laivy.npc.mappings.versions.V1_19_R2;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerInfoPacket extends Packet {
    public PlayerInfoPacket(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull PlayerInfoPacketClass getClassExecutor() {
        return (PlayerInfoPacketClass) LaivyNPC.laivynpc().getVersion().getClassExec("PacketPlayOutPlayerInfo");
    }

    public static class PlayerInfoPacketClass extends PacketClass {
        public PlayerInfoPacketClass(@NotNull String className) {
            super(className);

            if (ReflectionUtils.isCompatible(V1_19_R2.class)) {
                throw new UnsupportedOperationException("This packet isn't available anymore since 1.19.3+");
            }
        }
    }
}
