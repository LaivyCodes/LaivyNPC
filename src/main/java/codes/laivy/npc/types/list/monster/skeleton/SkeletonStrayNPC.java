package codes.laivy.npc.types.list.monster.skeleton;

import codes.laivy.npc.mappings.defaults.classes.entity.monster.skeleton.Skeleton;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.skeleton.Skeleton.Type;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.skeleton.SkeletonStray;
import codes.laivy.npc.types.NPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SkeletonStrayNPC extends SkeletonNPC {

    public static @NotNull SkeletonStrayNPC fastInstance(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new SkeletonStrayNPC(id, players, location);
    }

    public static void debug(@NotNull Location location) {
        SkeletonStrayNPC skeletonNPC = new SkeletonStrayNPC(new ArrayList<>(), location);
        skeletonNPC.debug();
        skeletonNPC.destroy();
    }

    protected SkeletonStrayNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Skeleton.Type type, @NotNull Location location) {
        super(id, players, type, location);
    }
    public SkeletonStrayNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(NPC.getNextNpcId(), players, location);
    }
    public SkeletonStrayNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(id, players, Type.STRAY, location);
    }

    public final @NotNull Skeleton.Type getType() {
        return Type.STRAY;
    }

    @Override
    public @NotNull SkeletonStray getEntity() {
        return (SkeletonStray) super.getEntity();
    }

}
