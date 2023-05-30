package codes.laivy.npc.listeners;

import codes.laivy.npc.developers.events.NPCSelectEvent;
import codes.laivy.npc.LaivyNPC;
import codes.laivy.npc.types.NPC;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.*;

// TODO: 30/05/2023 Enhance
public class NPCListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    private void select(NPCSelectEvent event) {
        if (event.getNPC().isHidden()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void playerJoin(PlayerJoinEvent event) {
        try {
            if (NPC.ALL_NPCS.containsKey(event.getPlayer().getUniqueId())) {
                List<NPC> list = new ArrayList<>(NPC.ALL_NPCS.get(event.getPlayer().getUniqueId()));

                Bukkit.getScheduler().runTaskLater(LaivyNPC.laivynpc(), () -> {
                    for (NPC npc : list) {
                        if (npc.canSpawn() && npc.isPlayerAtRange(event.getPlayer())) {
                            npc.spawn(event.getPlayer());
                        }
                    }
                }, 20);
            }

            for (NPC npc : NPC.PUBLIC_NPCS) {
                npc.addPlayer(event.getPlayer());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @EventHandler
    private void playerQuit(PlayerQuitEvent event) {
        for (NPC npc : NPC.PUBLIC_NPCS) {
            npc.removePlayer(event.getPlayer());
        }
    }

}
