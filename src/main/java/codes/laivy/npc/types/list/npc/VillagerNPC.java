package codes.laivy.npc.types.list.npc;

import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import codes.laivy.npc.mappings.utils.classes.entity.npc.Villager;
import codes.laivy.npc.mappings.utils.classes.entity.npc.VillagerProfession;
import codes.laivy.npc.types.EntityLivingNPC;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.commands.NPCConfiguration;
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

    public static @NotNull VillagerNPC fastInstance(@NotNull List<OfflinePlayer> player, @NotNull Location location, @Nullable Object object) {
        return new VillagerNPC(player, location);
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
        }});

        return map;
    }

    public static @NotNull VillagerNPC deserialize(@NotNull ConfigurationSection section) {
        VillagerNPC npc = (VillagerNPC) EntityLivingNPC.deserialize(section);

        section = section.getConfigurationSection("VillagerNPC Configuration");
        npc.setProfession(VillagerProfession.deserialize(section.getConfigurationSection("Profession").getValues(true)));

        return npc;
    }
    //
}
