package codes.laivy.npc.types.list.monster.skeleton;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.skeleton.Skeleton;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.skeleton.Skeleton.Type;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.skeleton.SkeletonWither;
import codes.laivy.npc.types.NPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SkeletonWitherNPC extends SkeletonNPC {

    public static @NotNull SkeletonWitherNPC fastInstance(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new SkeletonWitherNPC(id, players, location);
    }

    public static void debug(@NotNull Location location) {
        SkeletonWitherNPC skeletonNPC = new SkeletonWitherNPC(new ArrayList<>(), location);
        skeletonNPC.debug();
        skeletonNPC.destroy();
    }

    protected SkeletonWitherNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Skeleton.Type type, @NotNull Location location) {
        super(id, players, type, location);
    }
    public SkeletonWitherNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(NPC.getNextNpcId(), players, location);
    }
    public SkeletonWitherNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(id, players, Type.WITHER, location);
    }

    public final @NotNull Skeleton.Type getType() {
        return Type.WITHER;
    }

    @Override
    public @NotNull SkeletonWither getEntity() {
        return (SkeletonWither) super.getEntity();
    }

}
