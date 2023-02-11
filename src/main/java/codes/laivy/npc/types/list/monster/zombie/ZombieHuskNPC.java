package codes.laivy.npc.types.list.monster.zombie;

import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.Zombie;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.Zombie.Type;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.ZombieHusk;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ZombieHuskNPC extends ZombieNPC {

    public static @NotNull ZombieHuskNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new ZombieHuskNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        ZombieHuskNPC zombieNPC = new ZombieHuskNPC(new ArrayList<>(), location);
        zombieNPC.debug();
        zombieNPC.destroy();
    }

    protected ZombieHuskNPC(@NotNull List<OfflinePlayer> players, @NotNull Zombie.Type type, @NotNull Location location) {
        super(players, type, location);
    }
    public ZombieHuskNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Type.HUSK, location);
    }

    public final @NotNull Zombie.Type getType() {
        return Type.HUSK;
    }

    @Override
    public @NotNull ZombieHusk getEntity() {
        return (ZombieHusk) super.getEntity();
    }
}
