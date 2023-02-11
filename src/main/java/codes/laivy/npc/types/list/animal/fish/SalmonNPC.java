package codes.laivy.npc.types.list.animal.fish;

import codes.laivy.npc.mappings.defaults.classes.entity.animal.fish.Fish;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.fish.Salmon;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SalmonNPC extends FishNPC {

    public static @NotNull SalmonNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @NotNull Object object) {
        return new SalmonNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        SalmonNPC salmonNPC = new SalmonNPC(new ArrayList<>(), location);
        salmonNPC.debug();
        salmonNPC.destroy();
    }

    protected SalmonNPC(@NotNull List<OfflinePlayer> players, @NotNull Fish.Type type, @NotNull Location location) {
        super(players, type, location);
    }
    public SalmonNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Fish.Type.SALMON, location);
    }

    @Override
    public @NotNull Salmon getEntity() {
        return (Salmon) super.getEntity();
    }
}
