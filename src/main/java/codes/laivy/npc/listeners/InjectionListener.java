package codes.laivy.npc.listeners;

import codes.laivy.npc.mappings.defaults.classes.packets.listeners.InjectionUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@SuppressWarnings("unused")
public class InjectionListener implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        try {
            InjectionUtils.injectPlayer(e.getPlayer());
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        try {
            InjectionUtils.removePlayer(e.getPlayer());
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

}
