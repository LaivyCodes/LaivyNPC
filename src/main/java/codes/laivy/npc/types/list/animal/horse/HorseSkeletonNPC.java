package codes.laivy.npc.types.list.animal.horse;

import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.AbstractHorse;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.Horse;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.HorseSkeleton;
import codes.laivy.npc.types.NPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HorseSkeletonNPC extends AbstractHorseNPC {

    public static @NotNull HorseSkeletonNPC fastInstance(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location, @NotNull Object object) {
        return new HorseSkeletonNPC(id, players, location);
    }

    public static void debug(@NotNull Location location) {
        HorseSkeletonNPC horse = new HorseSkeletonNPC(new ArrayList<>(), location);
        horse.debug();
        horse.destroy();
    }

    protected HorseSkeletonNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull AbstractHorse.Type type, @NotNull Location location) {
        super(id, players, type, location);
    }
    public HorseSkeletonNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(NPC.getNextNpcId(), players, location);
    }
    public HorseSkeletonNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(id, players, AbstractHorse.Type.SKELETON, location);
    }

    public @NotNull Horse.Type getType() {
        return AbstractHorse.Type.SKELETON;
    }

    @Override
    public @NotNull HorseSkeleton getEntity() {
        return (HorseSkeleton) super.getEntity();
    }
}
