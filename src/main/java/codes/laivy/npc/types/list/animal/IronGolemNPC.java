package codes.laivy.npc.types.list.animal;

import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import codes.laivy.npc.mappings.utils.classes.entity.animal.IronGolem;
import codes.laivy.npc.types.EntityLivingNPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class IronGolemNPC extends EntityLivingNPC {

    public static @NotNull IronGolemNPC fastInstance(@NotNull List<OfflinePlayer> player, @NotNull Location location, @Nullable Object object) {
        return new IronGolemNPC(player, location);
    }

    public static void debug(@NotNull Location location) {
        IronGolemNPC golem = new IronGolemNPC(new ArrayList<>(), location);
        golem.debug();
        golem.destroy();
    }

    public IronGolemNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.IRON_GOLEM, location);
    }

    @Override
    public @NotNull IronGolem getEntity() {
        return (IronGolem) super.getEntity();
    }
}
