package codes.laivy.npc.types.list.decoration;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.decoration.LeashKnot;
import codes.laivy.npc.types.EntityNPC;
import codes.laivy.npc.types.NPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LeashKnotNPC extends EntityNPC {

    public static @NotNull LeashKnotNPC fastInstance(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new LeashKnotNPC(id, players, location);
    }

    public static void debug(@NotNull Location location) {
        LeashKnotNPC leashKnotNPC = new LeashKnotNPC(new ArrayList<>(), location);
        leashKnotNPC.debug();
        leashKnotNPC.destroy();
    }

    protected LeashKnotNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType type, @NotNull Location location) {
        super(id, players, type, location);
    }
    public LeashKnotNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(NPC.getNextNpcId(), players, location);
    }
    public LeashKnotNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(id, players, Entity.EntityType.LEASH_KNOT, location);
        getHolograms().setDistanceFromNPC(-1.25D);
    }

    @Override
    public @NotNull LeashKnot getEntity() {
        return (LeashKnot) super.getEntity();
    }

}
