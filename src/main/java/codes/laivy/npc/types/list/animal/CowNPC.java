package codes.laivy.npc.types.list.animal;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.Cow;
import codes.laivy.npc.types.AgeableEntityLivingNPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CowNPC extends AgeableEntityLivingNPC {

    public static @NotNull CowNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new CowNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        CowNPC cow = new CowNPC(new ArrayList<>(), location);
        cow.debug();
        cow.destroy();
    }

    protected CowNPC(@NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType type, @NotNull Location location) {
        super(players, type, location);
    }
    public CowNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.COW, location);
    }

    @Override
    public @NotNull Cow getEntity() {
        return (Cow) super.getEntity();
    }
}
