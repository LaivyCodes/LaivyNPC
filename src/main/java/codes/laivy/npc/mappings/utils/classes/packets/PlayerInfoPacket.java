package codes.laivy.npc.mappings.utils.classes.packets;

import codes.laivy.npc.LaivyNPC;
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
        }
    }
}
