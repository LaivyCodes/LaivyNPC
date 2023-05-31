package codes.laivy.npc.types.list.animal.horse;

import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.AbstractHorse;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.Horse;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.HorseMule;
import codes.laivy.npc.types.NPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HorseMuleNPC extends AbstractChestedHorseNPC {

    public static @NotNull HorseMuleNPC fastInstance(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location, @NotNull Object object) {
        return new HorseMuleNPC(id, players, location);
    }

    public static void debug(@NotNull Location location) {
        HorseMuleNPC horse = new HorseMuleNPC(new ArrayList<>(), location);
        horse.debug();
        horse.destroy();
    }

    protected HorseMuleNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull AbstractHorse.Type type, @NotNull Location location) {
        super(id, players, type, location);
    }
    public HorseMuleNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(NPC.getNextNpcId(), players, location);
    }
    public HorseMuleNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(id, players, AbstractHorse.Type.MULE, location);
    }

    public @NotNull Horse.Type getType() {
        return AbstractHorse.Type.MULE;
    }

    @Override
    public @NotNull HorseMule getEntity() {
        return (HorseMule) super.getEntity();
    }
}
