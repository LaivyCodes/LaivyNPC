package codes.laivy.npc.developers.events;

import codes.laivy.npc.types.NPC;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;

public class NPCSelectEvent extends NPCEvent implements Cancellable {

    private boolean cancelled = false;

    public NPCSelectEvent(boolean async, @NotNull NPC npc) {
        super(async, npc);
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        cancelled = b;
    }
}
