package codes.laivy.npc.types.list.animal.horse;

import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.AbstractHorse;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.Horse;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.HorseDonkey;
import codes.laivy.npc.types.NPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class HorseDonkeyNPC extends AbstractChestedHorseNPC {

    public static @NotNull HorseDonkeyNPC fastInstance(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location, @NotNull Object object) {
        return new HorseDonkeyNPC(id, players, location);
    }

    public static void debug(@NotNull Location location) {
        HorseDonkeyNPC horse = new HorseDonkeyNPC(new ArrayList<>(), location);
        horse.debug();
        horse.destroy();
    }

    protected HorseDonkeyNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull AbstractHorse.Type type, @NotNull Location location) {
        super(id, players, type, location);
    }
    public HorseDonkeyNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(NPC.getNextNpcId(), players, location);
    }
    public HorseDonkeyNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(id, players, AbstractHorse.Type.DONKEY, location);
    }

    public @NotNull Horse.Type getType() {
        return AbstractHorse.Type.DONKEY;
    }

    @Override
    public @NotNull HorseDonkey getEntity() {
        return (HorseDonkey) super.getEntity();
    }
}
