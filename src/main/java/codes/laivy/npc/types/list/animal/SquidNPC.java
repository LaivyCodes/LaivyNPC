package codes.laivy.npc.types.list.animal;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.Squid;
import codes.laivy.npc.types.EntityLivingNPC;
import codes.laivy.npc.types.NPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SquidNPC extends EntityLivingNPC {

    public static @NotNull SquidNPC fastInstance(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new SquidNPC(id, players, location);
    }

    public static void debug(@NotNull Location location) {
        SquidNPC squidNPC = new SquidNPC(new ArrayList<>(), location);
        squidNPC.debug();
        squidNPC.destroy();
    }

    protected SquidNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType type, @NotNull Location location) {
        super(id, players, type, location);
    }
    public SquidNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(NPC.getNextNpcId(), players, location);
    }
    public SquidNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(id, players, Entity.EntityType.SQUID, location);
        getHolograms().setDistanceFromNPC(-1.05D);
    }

    @Override
    public @NotNull Squid getEntity() {
        return (Squid) super.getEntity();
    }
}
