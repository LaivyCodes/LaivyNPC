package codes.laivy.npc.developers.events;

import codes.laivy.npc.types.NPC;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class NPCEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final @NotNull NPC npc;

    public NPCEvent(@NotNull NPC npc) {
        this.npc = npc;
    }

    @NotNull
    public NPC getNPC() {
        return npc;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    public HandlerList getHandlers() {
        return handlers;
    }
}
