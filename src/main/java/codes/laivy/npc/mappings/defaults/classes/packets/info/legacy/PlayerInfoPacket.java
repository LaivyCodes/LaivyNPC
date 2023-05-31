package codes.laivy.npc.mappings.defaults.classes.packets.info.legacy;

import codes.laivy.npc.LaivyNPC;
import codes.laivy.npc.mappings.defaults.classes.packets.Packet;
import codes.laivy.npc.mappings.defaults.classes.packets.info.PlayerInfoUpdatePacket;
import codes.laivy.npc.mappings.defaults.classes.packets.info.PlayerInfoRemovePacket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerInfoPacket extends Packet implements PlayerInfoUpdatePacket, PlayerInfoRemovePacket {
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
