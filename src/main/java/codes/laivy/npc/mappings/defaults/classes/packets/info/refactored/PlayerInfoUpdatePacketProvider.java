package codes.laivy.npc.mappings.defaults.classes.packets.info.refactored;

import codes.laivy.npc.mappings.defaults.classes.packets.Packet;
import codes.laivy.npc.mappings.defaults.classes.packets.info.PlayerInfoUpdatePacket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class PlayerInfoUpdatePacketProvider extends Packet implements PlayerInfoUpdatePacket {
    public PlayerInfoUpdatePacketProvider(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull PlayerInfoUpdatePacketProviderClass getClassExecutor() {
        return (PlayerInfoUpdatePacketProviderClass) laivynpc().getVersion().getClassExec("ClientboundPlayerInfoUpdatePacket");
    }

    public static final class PlayerInfoUpdatePacketProviderClass extends PacketClass {
        public PlayerInfoUpdatePacketProviderClass(@NotNull String className) {
            super(className);
        }
    }
}
