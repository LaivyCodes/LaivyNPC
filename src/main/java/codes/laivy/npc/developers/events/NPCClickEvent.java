package codes.laivy.npc.developers.events;

import codes.laivy.npc.types.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;

public class NPCClickEvent extends NPCEvent implements Cancellable {

    private final Player player;
    private final NPC.ClickType clickType;

    private boolean cancelled = false;

    public NPCClickEvent(@NotNull NPC npc, @NotNull Player player, @NotNull NPC.ClickType clickType) {
        super(npc);
        this.player = player;
        this.clickType = clickType;
    }

    @NotNull
    public Player getPlayer() {
        return player;
    }

    @NotNull
    public NPC.ClickType getClickType() {
        return clickType;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

}
