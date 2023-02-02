package codes.laivy.npc.mappings.utils.classes.packets;

import codes.laivy.npc.LaivyNPC;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.utils.classes.entity.EntityPlayer;
import codes.laivy.npc.mappings.utils.classes.others.objects.PlayerConnection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public abstract class Packet extends ObjectExecutor {
    public Packet(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull PacketClass getClassExecutor() {
        return (PacketClass) laivynpc().getVersion().getClassExec("Packet");
    }

    /**
     * Sends this packet for a list of players
     * @param players the players that will receive this packet
     */
    public void send(@NotNull Player... players) {
        PlayerConnection[] connections = new PlayerConnection[players.length];
        int row = 0;
        for (Player player : players) {
            connections[row] = EntityPlayer.getEntityPlayer(player).getPlayerConnection();
            row++;
        }

        LaivyNPC.laivynpc().getVersion().sendPacket(this, connections);
    }

    public static class PacketClass extends ClassExecutor {
        public PacketClass(@NotNull String className) {
            super(className);
        }
    }
}
