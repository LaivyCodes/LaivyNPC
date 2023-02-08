package codes.laivy.npc.types.list.animal.horse;

import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.AbstractHorse;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.Horse;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.HorseMule;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.HorseSkeleton;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HorseSkeletonNPC extends HorseNPC {

    public static @NotNull HorseSkeletonNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @NotNull Object object) {
        return new HorseSkeletonNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        HorseSkeletonNPC horse = new HorseSkeletonNPC(new ArrayList<>(), location);
        horse.debug();
        horse.destroy();
    }

    public HorseSkeletonNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, AbstractHorse.Type.SKELETON, location);
    }

    public final @NotNull Horse.Type getType() {
        return AbstractHorse.Type.SKELETON;
    }

    @Override
    public @NotNull HorseSkeleton getEntity() {
        return (HorseSkeleton) super.getEntity();
    }
}
