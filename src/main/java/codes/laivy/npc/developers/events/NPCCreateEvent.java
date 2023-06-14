package codes.laivy.npc.developers.events;

import codes.laivy.npc.types.NPC;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;

public class NPCCreateEvent extends NPCEvent implements Cancellable {

    private boolean cancel = false;

    public NPCCreateEvent(boolean async, @NotNull NPC npc) {
        super(async, npc);
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
