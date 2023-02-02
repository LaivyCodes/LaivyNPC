package codes.laivy.npc.types.list.monster;

import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import codes.laivy.npc.mappings.utils.classes.entity.monster.Witch;
import codes.laivy.npc.types.EntityLivingNPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WitchNPC extends EntityLivingNPC {

    public static @NotNull WitchNPC fastInstance(@NotNull List<OfflinePlayer> player, @NotNull Location location, @Nullable Object object) {
        return new WitchNPC(player, location);
    }

    public static void debug(@NotNull Location location) {
        WitchNPC witchNPC = new WitchNPC(new ArrayList<>(), location);
        witchNPC.debug();
        witchNPC.destroy();
    }

    public WitchNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.WITCH, location);
    }

    @Override
    public @NotNull Witch getEntity() {
        return (Witch) super.getEntity();
    }
}
