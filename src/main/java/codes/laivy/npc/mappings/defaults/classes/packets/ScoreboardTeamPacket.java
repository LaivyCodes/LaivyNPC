package codes.laivy.npc.mappings.defaults.classes.packets;

import codes.laivy.npc.LaivyNPC;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ScoreboardTeamPacket extends Packet {
    public ScoreboardTeamPacket(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull ScoreboardTeamPacketClass getClassExecutor() {
        return (ScoreboardTeamPacketClass) LaivyNPC.laivynpc().getVersion().getClassExec("PacketPlayOutScoreboardTeam");
    }

    public static class ScoreboardTeamPacketClass extends PacketClass {
        public ScoreboardTeamPacketClass(@NotNull String className) {
            super(className);
        }
    }
}
