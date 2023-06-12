package codes.laivy.npc.types.list.monster.zombie;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.ZombieGiant;
import codes.laivy.npc.types.EntityLivingNPC;
import codes.laivy.npc.types.NPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ZombieGiantNPC extends EntityLivingNPC {

    public static @NotNull ZombieGiantNPC fastInstance(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new ZombieGiantNPC(id, players, location);
    }

    public static void debug(@NotNull Location location) {
        ZombieGiantNPC zombieNPC = new ZombieGiantNPC(new ArrayList<>(), location);
        zombieNPC.debug();
        zombieNPC.destroy();
    }

    @Override
    protected void debug() {
        super.debug();
    }

    public ZombieGiantNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(NPC.getNextNpcId(), players, location);
    }
    public ZombieGiantNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(id, players, Entity.EntityType.GIANT, location);
    }

    @Override
    public @NotNull ZombieGiant getEntity() {
        return (ZombieGiant) super.getEntity();
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        return super.serialize();
    }

    public static @NotNull ZombieGiantNPC deserialize(@NotNull ConfigurationSection section) {
        return (ZombieGiantNPC) EntityLivingNPC.deserialize(section);
    }
    //
}
