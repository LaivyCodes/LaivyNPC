package codes.laivy.npc.types.list.animal.fish;

import codes.laivy.npc.mappings.defaults.classes.entity.animal.fish.Cod;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.fish.Fish;
import codes.laivy.npc.types.list.animal.horse.HorseNPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CodNPC extends FishNPC {

    public static @NotNull CodNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @NotNull Object object) {
        return new CodNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        CodNPC codNPC = new CodNPC(new ArrayList<>(), location);
        codNPC.debug();
        codNPC.destroy();
    }

    public CodNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Fish.Type.COD, location);
    }

    @Override
    public @NotNull Cod getEntity() {
        return (Cod) super.getEntity();
    }
}
