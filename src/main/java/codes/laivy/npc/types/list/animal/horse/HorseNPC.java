package codes.laivy.npc.types.list.animal.horse;

import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.AbstractHorse;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.Horse;
import codes.laivy.npc.mappings.defaults.classes.enums.HorseArmor;
import codes.laivy.npc.types.AgeableEntityLivingNPC;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.commands.NPCConfiguration;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static codes.laivy.npc.config.Translate.translate;

public class HorseNPC extends AbstractHorseNPC {

    public static @NotNull HorseNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @NotNull Object object) {
        return new HorseNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        HorseNPC horse = new HorseNPC(new ArrayList<>(), location);
        horse.debug();
        horse.destroy();
    }

    @Override
    public void debug() {
        super.debug();
        setArmor(getArmor());
    }

    public HorseNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, AbstractHorse.Type.HORSE, location);
    }

    public @NotNull HorseArmor getArmor() {
        return getEntity().getArmor();
    }
    public void setArmor(@NotNull HorseArmor armor) {
        getEntity().setArmor(armor);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public @NotNull Horse.Type getType() {
        return AbstractHorse.Type.HORSE;
    }

    @Override
    public @NotNull Horse getEntity() {
        return (Horse) super.getEntity();
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();

        list.add(new NPCConfiguration("armor", "/laivynpc config armor (armor)") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                HorseNPC horseNPC = (HorseNPC) npc;

                if (args.length > 0) {
                    try {
                        horseNPC.setArmor(HorseArmor.valueOf(args[0].toUpperCase()));
                        sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                    } catch (IllegalArgumentException ignore) {
                        sender.performCommand("laivynpc config " + getName());
                        return;
                    }
                    return;
                }

                StringBuilder builder = new StringBuilder();
                int row = 0;
                for (HorseArmor variant : HorseArmor.values()) {
                    if (row != 0) builder.append("§7, ");
                    builder.append("§f").append(variant.name());
                    row++;
                }

                sender.sendMessage("§cUse " + getSyntax());
                sender.sendMessage(translate(sender, "npc.commands.general.available_options", builder));
            }
        });

        return list;
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("HorseNPC Configuration", new LinkedHashMap<String, Object>() {{
            put("Armor", getArmor().name());
        }});
        return map;
    }

    public static @NotNull HorseNPC deserialize(@NotNull ConfigurationSection section) {
        HorseNPC npc = (HorseNPC) AgeableEntityLivingNPC.deserialize(section);

        ConfigurationSection config = section.getConfigurationSection("HorseNPC Configuration");
        npc.setArmor(HorseArmor.valueOf(config.getString("Armor").toUpperCase()));

        return npc;
    }
    //

}
