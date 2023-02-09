package codes.laivy.npc.types.list.monster.illagers;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.types.EntityLivingNPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class VindicatorNPC extends EntityLivingNPC {

    public static @NotNull VindicatorNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new VindicatorNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        VindicatorNPC vindicatorNPC = new VindicatorNPC(new ArrayList<>(), location);
        vindicatorNPC.debug();
        vindicatorNPC.destroy();
    }

    public VindicatorNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.VINDICATOR, location);
    }
}
