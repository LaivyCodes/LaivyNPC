package codes.laivy.npc.types.list.monster.illagers;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.skeleton.Skeleton;
import codes.laivy.npc.types.EntityLivingNPC;
import codes.laivy.npc.types.NPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class VindicatorNPC extends EntityLivingNPC {

    public static @NotNull VindicatorNPC fastInstance(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new VindicatorNPC(id, players, location);
    }

    public static void debug(@NotNull Location location) {
        VindicatorNPC vindicatorNPC = new VindicatorNPC(new ArrayList<>(), location);
        vindicatorNPC.debug();
        vindicatorNPC.destroy();
    }

    protected VindicatorNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType type, @NotNull Location location) {
        super(id, players, type, location);
    }
    public VindicatorNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(NPC.getNextNpcId(), players, location);
    }
    public VindicatorNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(id, players, Entity.EntityType.VINDICATOR, location);
    }
}
