package codes.laivy.npc.types.list.animal.horse;

import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.AbstractHorse;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.Horse;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.HorseDonkey;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.HorseMule;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HorseMuleNPC extends HorseNPC {

    public static @NotNull HorseMuleNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @NotNull Object object) {
        return new HorseMuleNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        HorseMuleNPC horse = new HorseMuleNPC(new ArrayList<>(), location);
        horse.debug();
        horse.destroy();
    }

    public HorseMuleNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, AbstractHorse.Type.MULE, location);
    }

    public final @NotNull Horse.Type getType() {
        return AbstractHorse.Type.MULE;
    }

    @Override
    public @NotNull HorseMule getEntity() {
        return (HorseMule) super.getEntity();
    }
}
