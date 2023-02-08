package codes.laivy.npc.types.list.monster.skeleton;

import codes.laivy.npc.mappings.defaults.classes.entity.monster.skeleton.Skeleton;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.skeleton.Skeleton.Type;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.skeleton.SkeletonWither;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SkeletonWitherNPC extends SkeletonNPC {

    public static @NotNull SkeletonWitherNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new SkeletonWitherNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        SkeletonWitherNPC skeletonNPC = new SkeletonWitherNPC(new ArrayList<>(), location);
        skeletonNPC.debug();
        skeletonNPC.destroy();
    }

    public SkeletonWitherNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Type.WITHER, location);
    }

    public final @NotNull Skeleton.Type getType() {
        return Type.WITHER;
    }

    @Override
    public @NotNull SkeletonWither getEntity() {
        return (SkeletonWither) super.getEntity();
    }

}
