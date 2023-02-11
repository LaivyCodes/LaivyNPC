package codes.laivy.npc.types.list.vehicle;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.types.EntityNPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BoatNPC extends EntityNPC {

    public static @NotNull BoatNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new BoatNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        BoatNPC boatNPC = new BoatNPC(new ArrayList<>(), location);
        boatNPC.debug();
        boatNPC.destroy();
    }

    protected BoatNPC(@NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType type, @NotNull Location location) {
        super(players, type, location);
    }
    public BoatNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.BOAT, location);
    }
}
