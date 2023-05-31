package codes.laivy.npc.types.list.boss.dragon;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.boss.dragon.EnderSignal;
import codes.laivy.npc.types.EntityNPC;
import codes.laivy.npc.types.NPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class EnderSignalNPC extends EntityNPC {

    public static @NotNull EnderSignalNPC fastInstance(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new EnderSignalNPC(id, players, location);
    }

    public static void debug(@NotNull Location location) {
        EnderSignalNPC signalNPC = new EnderSignalNPC(new ArrayList<>(), location);
        signalNPC.debug();
        signalNPC.destroy();
    }

    protected EnderSignalNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType type, @NotNull Location location) {
        super(id, players, type, location);
    }
    public EnderSignalNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(NPC.getNextNpcId(), players, location);
    }
    public EnderSignalNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(id, players, Entity.EntityType.ENDER_SIGNAL, location);
        getHolograms().setDistanceFromNPC(-1.3D);
    }

    @Override
    public @NotNull EnderSignal getEntity() {
        return (EnderSignal) super.getEntity();
    }
}
