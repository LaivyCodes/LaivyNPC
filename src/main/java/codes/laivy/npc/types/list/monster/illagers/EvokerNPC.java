package codes.laivy.npc.types.list.monster.illagers;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.types.EntityLivingNPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class EvokerNPC extends IllagerWizardNPC {

    public static @NotNull EvokerNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new EvokerNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        EvokerNPC evokerNPC = new EvokerNPC(new ArrayList<>(), location);
        evokerNPC.debug();
        evokerNPC.destroy();
    }

    public EvokerNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.EVOKER, location);
    }
}
