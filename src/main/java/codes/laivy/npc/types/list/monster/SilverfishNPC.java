package codes.laivy.npc.types.list.monster;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.Silverfish;
import codes.laivy.npc.types.EntityLivingNPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SilverfishNPC extends EntityLivingNPC {

    public static @NotNull SilverfishNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new SilverfishNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        SilverfishNPC silverfishNPC = new SilverfishNPC(new ArrayList<>(), location);
        silverfishNPC.debug();
        silverfishNPC.destroy();
    }

    public SilverfishNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.SILVERFISH, location);
    }

    @Override
    public @NotNull Silverfish getEntity() {
        return (Silverfish) super.getEntity();
    }
}
