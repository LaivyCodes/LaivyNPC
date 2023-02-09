package codes.laivy.npc.types.list.animal.horse;

import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.AbstractHorse;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.Horse;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.HorseZombie;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HorseZombieNPC extends AbstractHorseNPC {

    public static @NotNull HorseZombieNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @NotNull Object object) {
        return new HorseZombieNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        HorseZombieNPC horse = new HorseZombieNPC(new ArrayList<>(), location);
        horse.debug();
        horse.destroy();
    }

    public HorseZombieNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, AbstractHorse.Type.ZOMBIE, location);
    }

    public @NotNull Horse.Type getType() {
        return AbstractHorse.Type.ZOMBIE;
    }

    @Override
    public @NotNull HorseZombie getEntity() {
        return (HorseZombie) super.getEntity();
    }
}