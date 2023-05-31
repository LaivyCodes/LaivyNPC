package codes.laivy.npc.mappings.defaults.classes.packets.info.refactored;

import codes.laivy.npc.mappings.defaults.classes.packets.Packet;
import codes.laivy.npc.mappings.defaults.classes.packets.info.PlayerInfoRemovePacket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerInfoRemovePacketProvider extends Packet implements PlayerInfoRemovePacket {
    public PlayerInfoRemovePacketProvider(@Nullable Object value) {
        super(value);
    }

    public static final class PlayerInfoRemovePacketProviderClass extends PacketClass {
        public PlayerInfoRemovePacketProviderClass(@NotNull String className) {
            super(className);
        }
    }
}
