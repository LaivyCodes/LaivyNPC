package codes.laivy.npc.types.list.animal.fish;

import codes.laivy.npc.mappings.defaults.classes.entity.animal.fish.Fish;
import codes.laivy.npc.types.EntityLivingNPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class FishNPC extends EntityLivingNPC {
    protected FishNPC(@NotNull List<OfflinePlayer> players, @NotNull Fish.Type type, @NotNull Location location) {
        super(players, type.getEntityType(), location);
    }

    public @NotNull Fish.Type getType() {
        return getEntity().getType();
    }

    @Override
    public @NotNull Fish getEntity() {
        return (Fish) super.getEntity();
    }
}
