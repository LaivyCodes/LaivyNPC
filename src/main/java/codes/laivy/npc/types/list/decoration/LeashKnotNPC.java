package codes.laivy.npc.types.list.decoration;

import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import codes.laivy.npc.mappings.utils.classes.entity.decoration.LeashKnot;
import codes.laivy.npc.types.EntityNPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LeashKnotNPC extends EntityNPC {

    public static @NotNull LeashKnotNPC fastInstance(@NotNull List<OfflinePlayer> player, @NotNull Location location, @Nullable Object object) {
        return new LeashKnotNPC(player, location);
    }

    public static void debug(@NotNull Location location) {
        LeashKnotNPC leashKnotNPC = new LeashKnotNPC(new ArrayList<>(), location);
        leashKnotNPC.debug();
        leashKnotNPC.destroy();
    }

    public LeashKnotNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.LEASH_KNOT, location);
        getHolograms().setDistanceFromNPC(-1.25D);
    }

    @Override
    public @NotNull LeashKnot getEntity() {
        return (LeashKnot) super.getEntity();
    }

}
