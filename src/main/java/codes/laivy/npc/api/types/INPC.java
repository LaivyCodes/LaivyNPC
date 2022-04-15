package codes.laivy.npc.api.types;

import codes.laivy.npc.api.workers.NPCAnimation;
import codes.laivy.npc.api.workers.NPCController;
import com.avaje.ebean.validation.NotNull;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface INPC {

    @NotNull
    Player getPlayer();

    @NotNull
    String getNPCName();

    @NotNull
    Location getNPCLocation();

    @NotNull
    Object getNPCEntity();

    @NotNull
    NPCAnimation getAnimation();
    @NotNull
    NPCController getController();

}
