package codes.laivy.npc.types.list.animal.fish;

import codes.laivy.npc.mappings.defaults.classes.entity.animal.fish.Cod;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.fish.Fish;
import codes.laivy.npc.types.NPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CodNPC extends FishNPC {

    public static @NotNull CodNPC fastInstance(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location, @NotNull Object object) {
        return new CodNPC(id, players, location);
    }

    public static void debug(@NotNull Location location) {
        CodNPC codNPC = new CodNPC(new ArrayList<>(), location);
        codNPC.debug();
        codNPC.destroy();
    }

    protected CodNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Fish.Type type, @NotNull Location location) {
        super(id, players, type, location);
    }
    public CodNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(NPC.getNextNpcId(), players, location);
    }
    public CodNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(id, players, Fish.Type.COD, location);
    }

    @Override
    public @NotNull Cod getEntity() {
        return (Cod) super.getEntity();
    }
}
