package codes.laivy.npc.types.list.boss.dragon;

import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import codes.laivy.npc.mappings.utils.classes.entity.boss.dragon.EnderSignal;
import codes.laivy.npc.types.EntityNPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class EnderSignalNPC extends EntityNPC {

    public static @NotNull EnderSignalNPC fastInstance(@NotNull List<OfflinePlayer> player, @NotNull Location location, @Nullable Object object) {
        return new EnderSignalNPC(player, location);
    }

    public static void debug(@NotNull Location location) {
        EnderSignalNPC signalNPC = new EnderSignalNPC(new ArrayList<>(), location);
        signalNPC.debug();
        signalNPC.destroy();
    }

    public EnderSignalNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.ENDER_SIGNAL, location);
        getHolograms().setDistanceFromNPC(-1.3D);
    }

    @Override
    public @NotNull EnderSignal getEntity() {
        return (EnderSignal) super.getEntity();
    }
}
