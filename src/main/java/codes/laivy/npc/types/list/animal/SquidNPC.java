package codes.laivy.npc.types.list.animal;

import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import codes.laivy.npc.mappings.utils.classes.entity.animal.Squid;
import codes.laivy.npc.types.EntityLivingNPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SquidNPC extends EntityLivingNPC {

    public static @NotNull SquidNPC fastInstance(@NotNull List<OfflinePlayer> player, @NotNull Location location, @Nullable Object object) {
        return new SquidNPC(player, location);
    }

    public static void debug(@NotNull Location location) {
        SquidNPC squidNPC = new SquidNPC(new ArrayList<>(), location);
        squidNPC.debug();
        squidNPC.destroy();
    }

    public SquidNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.SQUID, location);
        getHolograms().setDistanceFromNPC(-1.05D);
    }

    @Override
    public @NotNull Squid getEntity() {
        return (Squid) super.getEntity();
    }
}
