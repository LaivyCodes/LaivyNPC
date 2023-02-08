package codes.laivy.npc.types.list.monster.zombie;

import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.Zombie;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.Zombie.Type;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.ZombieVillager;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ZombieVillagerNPC extends ZombieNPC {

    public static @NotNull ZombieVillagerNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new ZombieVillagerNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        ZombieVillagerNPC zombieNPC = new ZombieVillagerNPC(new ArrayList<>(), location);
        zombieNPC.debug();
        zombieNPC.destroy();
    }

    public ZombieVillagerNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Type.VILLAGER, location);
    }

    public final @NotNull Zombie.Type getType() {
        return Type.VILLAGER;
    }

    @Override
    public @NotNull ZombieVillager getEntity() {
        return (ZombieVillager) super.getEntity();
    }
}
