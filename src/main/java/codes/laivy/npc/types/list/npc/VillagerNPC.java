package codes.laivy.npc.types.list.npc;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.npc.Villager;
import codes.laivy.npc.mappings.defaults.classes.entity.npc.VillagerProfession;
import codes.laivy.npc.mappings.versions.V1_14_R1;
import codes.laivy.npc.types.EntityLivingNPC;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.commands.NPCConfiguration;
import codes.laivy.npc.types.list.animal.fish.TropicalfishNPC;
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

public class VillagerNPC extends EntityLivingNPC {

    public static @NotNull VillagerNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new VillagerNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        VillagerNPC villagerNPC = new VillagerNPC(new ArrayList<>(), location);
        villagerNPC.debug();
        villagerNPC.destroy();
    }

    @Override
    protected void debug() {
        super.debug();
        setProfession(getProfession());
        if (ReflectionUtils.isCompatible(V1_14_R1.class)) {
            setType(getType());
        }
    }

    public VillagerNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.VILLAGER, location);
    }

    public @NotNull VillagerProfession getProfession() {
        return getEntity().getProfession();
    }
    public void setProfession(@NotNull VillagerProfession profession) {
        getEntity().setProfession(profession);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    // 1.14+ ONLY!!
    public @NotNull Villager.Type getType() {
        return getEntity().getType();
    }
    // 1.14+ ONLY!!
    public void setType(@NotNull Villager.Type type) {
        getEntity().setType(type);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();

        list.add(new NPCConfiguration("profession", "/laivynpc config profession (profession)") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                VillagerNPC villagerNPC = (VillagerNPC) npc;

                if (args.length > 0) {
                    try {
                        VillagerProfession.Type type = VillagerProfession.Type.valueOf(args[0].toUpperCase());
                        villagerNPC.setProfession(new VillagerProfession(type, 1));
                        sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                    } catch (IllegalArgumentException ignore) {
                        sender.performCommand("laivynpc config " + getName());
                        return;
                    }
                    return;
                }

                StringBuilder builder = new StringBuilder();
                int row = 0;
                for (VillagerProfession.Type type : VillagerProfession.Type.values()) {
                    if (row != 0) builder.append("§7, ");
                    builder.append("§f").append(type.name());
                    row++;
                }

                sender.sendMessage("§cUse " + getSyntax());
                sender.sendMessage(translate(sender, "npc.commands.general.available_options", builder));
            }
        });
        if (ReflectionUtils.isCompatible(V1_14_R1.class)) {
            list.add(new NPCConfiguration("type", "/laivynpc config type (type)") {
                @Override
                public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                    VillagerNPC villagerNPC = (VillagerNPC) npc;

                    if (args.length > 0) {
                        try {
                            Villager.Type type = Villager.Type.valueOf(args[0].toUpperCase());
                            if (!type.isCompatible()) return;

                            villagerNPC.setType(type);
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
            list.add(new NPCConfiguration("level", "/laivynpc config level (level)") {
                @Override
                public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                    VillagerNPC villagerNPC = (VillagerNPC) npc;

                    if (args.length > 0) {
                        int level;
                        try {
                            level = Integer.parseInt(args[0].replace(",", "."));
                        } catch (NumberFormatException ignore) {
                            sender.performCommand("laivynpc config " + getName());
                            return;
                        }

                        VillagerProfession profession = new VillagerProfession(villagerNPC.getProfession().getType(), level);
                        villagerNPC.setProfession(profession);

                        sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                        return;
                    }

                    sender.sendMessage("§cUse " + getSyntax());
                }
            });
        }

        return list;
    }

    @Override
    public @NotNull Villager getEntity() {
        return (Villager) super.getEntity();
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("VillagerNPC Configuration", new HashMap<String, Object>() {{
            put("Profession", getProfession().serialize());
            if (ReflectionUtils.isCompatible(V1_14_R1.class)) {
                put("Type", getType().name());
            }
        }});

        return map;
    }

    public static @NotNull VillagerNPC deserialize(@NotNull ConfigurationSection section) {
        VillagerNPC npc = (VillagerNPC) EntityLivingNPC.deserialize(section);

        section = section.getConfigurationSection("VillagerNPC Configuration");
        npc.setProfession(VillagerProfession.deserialize(section.getConfigurationSection("Profession").getValues(true)));
        if (ReflectionUtils.isCompatible(V1_14_R1.class)) {
            if (section.contains("Type")) {
                npc.setType(Villager.Type.valueOf(section.getString("Type").toUpperCase()));
            }
        }

        return npc;
    }
    //
}
