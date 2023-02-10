package codes.laivy.npc.types.list.monster.zombie;

import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.Zombie;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.Zombie.Type;
import codes.laivy.npc.mappings.versions.V1_11_R1;
import codes.laivy.npc.mappings.versions.V1_9_R1;
import codes.laivy.npc.types.EntityLivingNPC;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.commands.NPCConfiguration;
import codes.laivy.npc.types.list.boss.wither.WitherSkullNPC;
import codes.laivy.npc.utils.ReflectionUtils;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static codes.laivy.npc.LaivyNPC.laivynpc;
import static codes.laivy.npc.config.Translate.translate;

public class ZombieNPC extends EntityLivingNPC {

    public static @NotNull ZombieNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new ZombieNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        ZombieNPC zombieNPC = new ZombieNPC(new ArrayList<>(), location);
        zombieNPC.debug();
        zombieNPC.destroy();
    }

    protected ZombieNPC(@NotNull List<OfflinePlayer> players, @NotNull Type type, @NotNull Location location) {
        super(players, type.getEntityType(), location);
    }
    public ZombieNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(players, Type.NORMAL, location);
    }

    public boolean isVillager() {
        return getEntity().getType() != null && getEntity().getType() == Type.VILLAGER;
    }

    public @Nullable Zombie.Type getType() {
        return getEntity().getType();
    }

    @Override
    public @NotNull Zombie getEntity() {
        return (Zombie) super.getEntity();
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();
        if (!ReflectionUtils.isCompatible(V1_11_R1.class)) {
            if (ReflectionUtils.isCompatible(V1_9_R1.class)) {
                list.add(new NPCConfiguration("type", "/laivynpc config type (type)") {
                    @Override
                    public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                        ZombieNPC zombieNPC = (ZombieNPC) npc;

                        if (args.length > 0) {
                            Type type;
                            try {
                                type = Type.valueOf(args[0].toUpperCase());
                                if (!type.isCompatible()) {
                                    return;
                                }

                                laivynpc().getVersion().setEntityZombieType(getEntity(), type);
                                sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
                                entityType = type.getEntityType();

                                sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                                return;
                            } catch (IllegalArgumentException ignore) {
                            }
                        }

                        StringBuilder builder = new StringBuilder();
                        int row = 0;
                        for (Type type : Type.values()) {
                            if (!type.isCompatible()) continue;

                            if (row != 0) builder.append("§7, ");
                            builder.append("§f").append(type.name());
                            row++;
                        }

                        sender.sendMessage("§cUse " + getSyntax());
                        sender.sendMessage(translate(sender, "npc.commands.general.available_options", builder));
                    }
                });
            } else {
                list.add(new NPCConfiguration("villager", "/laivynpc config villager") {
                    @Override
                    public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                        ZombieNPC zombieNPC = (ZombieNPC) npc;

                        Type type = Type.NORMAL;
                        if (!zombieNPC.isVillager()) {
                            type = Type.VILLAGER;
                        }

                        laivynpc().getVersion().setEntityZombieType(getEntity(), type);
                        entityType = type.getEntityType();
                        entity = getNewEntity();
                        respawn();
                        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);

                        sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                    }
                });
            }
        }
        return list;
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("ZombieNPC Configuration", new HashMap<String, Object>() {{
            if (getType() != null) {
                put("Type", getType().name());
            }
        }});

        return map;
    }

    public static @NotNull ZombieNPC deserialize(@NotNull ConfigurationSection section) {
        ZombieNPC npc = (ZombieNPC) EntityLivingNPC.deserialize(section);

        if (!ReflectionUtils.isCompatible(V1_11_R1.class)) {
            section = section.getConfigurationSection("ZombieNPC Configuration");
            if (section.contains("Type")) {
                Type type = Type.valueOf(section.getString("Type").toUpperCase());
                if (type.isCompatible()) {
                    laivynpc().getVersion().setEntityZombieType(npc.getEntity(), type);
                    npc.sendUpdatePackets(npc.getSpawnedPlayers(), false, false, true, false, false, false);
                    npc.entityType = type.getEntityType();
                }
            }
        }

        return npc;
    }
    //
}
