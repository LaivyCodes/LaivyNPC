package codes.laivy.npc.types.list.animal.horse;

import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.AbstractHorse;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.Horse;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.HorseDonkey;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class HorseDonkeyNPC extends HorseNPC {

    public static @NotNull HorseDonkeyNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @NotNull Object object) {
        return new HorseDonkeyNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        HorseDonkeyNPC horse = new HorseDonkeyNPC(new ArrayList<>(), location);
        horse.debug();
        horse.destroy();
    }

    public HorseDonkeyNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, AbstractHorse.Type.DONKEY, location);
    }

    public final @NotNull Horse.Type getType() {
        return AbstractHorse.Type.DONKEY;
    }

    @Override
    public @NotNull HorseDonkey getEntity() {
        return (HorseDonkey) super.getEntity();
    }
}
