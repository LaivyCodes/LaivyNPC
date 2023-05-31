package codes.laivy.npc.types.list.monster.zombie;

import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.Zombie;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.Zombie.Type;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.ZombieDrowned;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.ZombieHusk;
import codes.laivy.npc.types.NPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ZombieDrownedNPC extends ZombieNPC {

    public static @NotNull ZombieDrownedNPC fastInstance(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new ZombieDrownedNPC(id, players, location);
    }

    public static void debug(@NotNull Location location) {
        ZombieDrownedNPC zombieNPC = new ZombieDrownedNPC(new ArrayList<>(), location);
        zombieNPC.debug();
        zombieNPC.destroy();
    }

    protected ZombieDrownedNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Zombie.Type type, @NotNull Location location) {
        super(id, players, type, location);
    }
    public ZombieDrownedNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(NPC.getNextNpcId(), players, location);
    }
    public ZombieDrownedNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(id, players, Type.DROWNED, location);
    }

    public final @NotNull Zombie.Type getType() {
        return Type.DROWNED;
    }

    @Override
    public @NotNull ZombieDrowned getEntity() {
        return (ZombieDrowned) super.getEntity();
    }
}
