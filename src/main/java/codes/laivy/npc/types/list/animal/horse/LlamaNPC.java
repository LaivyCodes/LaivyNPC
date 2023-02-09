package codes.laivy.npc.types.list.animal.horse;

import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.AbstractHorse;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.Horse;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.HorseMule;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.Llama;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class LlamaNPC extends AbstractChestedHorseNPC {

    public static @NotNull LlamaNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @NotNull Object object) {
        return new LlamaNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        LlamaNPC horse = new LlamaNPC(new ArrayList<>(), location);
        horse.debug();
        horse.destroy();
    }

    public LlamaNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, AbstractHorse.Type.LLAMA, location);
    }

    public @NotNull Horse.Type getType() {
        return AbstractHorse.Type.LLAMA;
    }

    @Override
    public @NotNull Llama getEntity() {
        return (Llama) super.getEntity();
    }
}
