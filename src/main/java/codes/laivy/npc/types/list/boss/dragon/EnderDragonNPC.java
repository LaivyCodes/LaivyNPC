package codes.laivy.npc.types.list.boss.dragon;

import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import codes.laivy.npc.mappings.utils.classes.entity.boss.dragon.EnderDragon;
import codes.laivy.npc.types.EntityLivingNPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class EnderDragonNPC extends EntityLivingNPC {

    public static @NotNull EnderDragonNPC fastInstance(@NotNull List<OfflinePlayer> player, @NotNull Location location, @Nullable Object object) {
        return new EnderDragonNPC(player, location);
    }

    public static void debug(@NotNull Location location) {
        EnderDragonNPC enderDragonNPC = new EnderDragonNPC(new ArrayList<>(), location);
        enderDragonNPC.debug();
        enderDragonNPC.destroy();
    }

    public EnderDragonNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.ENDER_DRAGON, location);
        getHolograms().setDistanceFromNPC(2.5D);
    }

    @Override
    public @NotNull EnderDragon getEntity() {
        return (EnderDragon) super.getEntity();
    }
}
