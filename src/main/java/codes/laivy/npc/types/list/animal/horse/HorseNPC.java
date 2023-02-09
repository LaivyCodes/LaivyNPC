package codes.laivy.npc.types.list.animal.horse;

import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.AbstractHorse;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.Horse;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class HorseNPC extends AbstractHorseNPC {

    public static @NotNull HorseNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @NotNull Object object) {
        return new HorseNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        HorseNPC horse = new HorseNPC(new ArrayList<>(), location);
        horse.debug();
        horse.destroy();
    }

    public HorseNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, AbstractHorse.Type.HORSE, location);
    }

    @Override
    public @NotNull Horse.Type getType() {
        return AbstractHorse.Type.HORSE;
    }

    @Override
    public @NotNull Horse getEntity() {
        return (Horse) super.getEntity();
    }

}
