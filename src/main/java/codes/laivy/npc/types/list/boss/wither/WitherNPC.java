package codes.laivy.npc.types.list.boss.wither;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.boss.wither.Wither;
import codes.laivy.npc.types.EntityLivingNPC;
import codes.laivy.npc.types.NPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WitherNPC extends EntityLivingNPC {

    public static @NotNull WitherNPC fastInstance(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new WitherNPC(id, players, location);
    }

    public static void debug(@NotNull Location location) {
        WitherNPC witherNPC = new WitherNPC(new ArrayList<>(), location);
        witherNPC.debug();
        witherNPC.destroy();
    }

    protected WitherNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType type, @NotNull Location location) {
        super(id, players, type, location);
    }
    public WitherNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(NPC.getNextNpcId(), players, location);
    }
    public WitherNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(id, players, Entity.EntityType.WITHER, location);
        getHolograms().setDistanceFromNPC(2D);
    }

    @Override
    public @NotNull Wither getEntity() {
        return (Wither) super.getEntity();
    }
}
