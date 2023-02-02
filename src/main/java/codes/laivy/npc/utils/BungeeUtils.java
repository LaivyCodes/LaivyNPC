package codes.laivy.npc.utils;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.ChannelNotRegisteredException;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class BungeeUtils {

    public static void redirectToServer(@NotNull Player player, @NotNull String serverName) {
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            out.writeUTF("Connect");
            out.writeUTF(serverName);
            player.sendPluginMessage(laivynpc(), "BungeeCord", b.toByteArray());
            b.close();
            out.close();
        } catch (ChannelNotRegisteredException e) {
            throw new RuntimeException("Your server is linked to BungeeCord?", e);
        } catch (Exception e) {
            throw new RuntimeException("Error trying to redirect player to another bungee server", e);
        }
    }

}
