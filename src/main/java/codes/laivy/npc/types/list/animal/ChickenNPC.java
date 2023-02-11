package codes.laivy.npc.types.list.animal;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.Chicken;
import codes.laivy.npc.types.AgeableEntityLivingNPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ChickenNPC extends AgeableEntityLivingNPC {

    public static @NotNull ChickenNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new ChickenNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        ChickenNPC chicken = new ChickenNPC(new ArrayList<>(), location);
        chicken.debug();
        chicken.destroy();
    }

    protected ChickenNPC(@NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType type, @NotNull Location location) {
        super(players, type, location);
    }
    public ChickenNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.CHICKEN, location);
        getHolograms().setDistanceFromNPC(-1.25D);
    }

    @Override
    public @NotNull Chicken getEntity() {
        return (Chicken) super.getEntity();
    }
}
