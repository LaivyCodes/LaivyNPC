package codes.laivy.npc.types.list.monster;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.types.EntityLivingNPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class VexNPC extends EntityLivingNPC {

    public static @NotNull VexNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new VexNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        VexNPC vexNPC = new VexNPC(new ArrayList<>(), location);
        vexNPC.debug();
        vexNPC.destroy();
    }

    public VexNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.VEX, location);
    }
}
