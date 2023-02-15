package codes.laivy.npc.types.list.boss.dragon;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.boss.dragon.EnderDragon;
import codes.laivy.npc.types.EntityLivingNPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EnderDragonNPC extends EntityLivingNPC {

    public static @NotNull EnderDragonNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new EnderDragonNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        Bukkit.getScheduler().runTask(laivynpc(), () -> {
            EnderDragonNPC enderDragonNPC = new EnderDragonNPC(new ArrayList<>(), location);
            enderDragonNPC.debug();
            enderDragonNPC.destroy();
        });
    }

    protected EnderDragonNPC(@NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType type, @NotNull Location location) {
        super(players, type, location);
        if (!Bukkit.isPrimaryThread()) {
            throw new UnsupportedOperationException("The ender dragon NPC needs to be created synchronously");
        }
    }
    public EnderDragonNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(players, Entity.EntityType.ENDER_DRAGON, location);
        getHolograms().setDistanceFromNPC(2.5D);
    }

    @Override
    public @NotNull EnderDragon getEntity() {
        return (EnderDragon) super.getEntity();
    }
}
