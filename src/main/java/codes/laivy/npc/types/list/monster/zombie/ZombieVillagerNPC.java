package codes.laivy.npc.types.list.monster.zombie;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.Zombie;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.Zombie.Type;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.ZombieVillager;
import codes.laivy.npc.mappings.defaults.classes.entity.npc.Villager;
import codes.laivy.npc.mappings.versions.V1_14_R1;
import codes.laivy.npc.types.EntityLivingNPC;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.commands.NPCConfiguration;
import codes.laivy.npc.utils.ReflectionUtils;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static codes.laivy.npc.config.Translate.translate;

public class ZombieVillagerNPC extends ZombieNPC {

    public static @NotNull ZombieVillagerNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new ZombieVillagerNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        ZombieVillagerNPC zombieNPC = new ZombieVillagerNPC(new ArrayList<>(), location);
        zombieNPC.debug();
        zombieNPC.destroy();
    }

    @Override
    protected void debug() {
        super.debug();
        if (ReflectionUtils.isCompatible(V1_14_R1.class)) {
            setVillagerType(getVillagerType());
        }
    }

    protected ZombieVillagerNPC(@NotNull List<OfflinePlayer> players, @NotNull Zombie.Type type, @NotNull Location location) {
        super(players, type, location);
    }
    public ZombieVillagerNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Type.VILLAGER, location);
    }

    // 1.14+ ONLY!!
    public @NotNull Villager.Type getVillagerType() {
        return getEntity().getVillagerType();
    }
    // 1.14+ ONLY!!
    public void setVillagerType(@NotNull Villager.Type type) {
        getEntity().setVillagerType(type);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    public final @NotNull Zombie.Type getType() {
        return Type.VILLAGER;
    }

    @Override
    public @NotNull ZombieVillager getEntity() {
        return (ZombieVillager) super.getEntity();
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();

        if (ReflectionUtils.isCompatible(V1_14_R1.class)) {
            list.add(new NPCConfiguration("type", "/laivynpc config type (type)") {
                @Override
                public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                    ZombieVillagerNPC zombieVillagerNPC = (ZombieVillagerNPC) npc;

                    if (args.length > 0) {
                        try {
                            Villager.Type type = Villager.Type.valueOf(args[0].toUpperCase());
                            if (!type.isCompatible()) return;

                            zombieVillagerNPC.setVillagerType(type);
                            sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                        } catch (IllegalArgumentException ignore) {
                            sender.performCommand("laivynpc config " + getName());
                            return;
                        }
                        return;
                    }

                    StringBuilder builder = new StringBuilder();
                    int row = 0;
                    for (Villager.Type type : Villager.Type.values()) {
                        if (!type.isCompatible()) continue;

                        if (row != 0) builder.append("§7, ");
                        builder.append("§f").append(type.name());
                        row++;
                    }

                    sender.sendMessage("§cUse " + getSyntax());
                    sender.sendMessage(translate(sender, "npc.commands.general.available_options", builder));
                }
            });
        }

        return list;
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("ZombieVillagerNPC Configuration", new HashMap<String, Object>() {{
            if (ReflectionUtils.isCompatible(V1_14_R1.class)) {
                put("Type", getType().name());
            }
        }});

        return map;
    }

    public static @NotNull ZombieVillagerNPC deserialize(@NotNull ConfigurationSection section) {
        ZombieVillagerNPC npc = (ZombieVillagerNPC) ZombieNPC.deserialize(section);

        section = section.getConfigurationSection("ZombieVillagerNPC Configuration");
        if (ReflectionUtils.isCompatible(V1_14_R1.class)) {
            if (section.contains("Type")) {
                npc.setVillagerType(Villager.Type.valueOf(section.getString("Type").toUpperCase()));
            }
        }

        return npc;
    }
    //
}
