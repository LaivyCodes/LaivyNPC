package codes.laivy.npc.types.list.monster;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.Silverfish;
import codes.laivy.npc.types.EntityLivingNPC;
import codes.laivy.npc.types.NPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SilverfishNPC extends EntityLivingNPC {

    public static @NotNull SilverfishNPC fastInstance(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new SilverfishNPC(id, players, location);
    }

    public static void debug(@NotNull Location location) {
        SilverfishNPC silverfishNPC = new SilverfishNPC(new ArrayList<>(), location);
        silverfishNPC.debug();
        silverfishNPC.destroy();
    }

    protected SilverfishNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType type, @NotNull Location location) {
        super(id, players, type, location);
    }
    public SilverfishNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(NPC.getNextNpcId(), players, location);
    }
    public SilverfishNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(id, players, Entity.EntityType.SILVERFISH, location);
    }

    @Override
    public @NotNull Silverfish getEntity() {
        return (Silverfish) super.getEntity();
    }
}
