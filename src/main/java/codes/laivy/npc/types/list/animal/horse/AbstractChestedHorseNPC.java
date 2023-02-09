package codes.laivy.npc.types.list.animal.horse;

import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.AbstractChestedHorse;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.AbstractHorse;
import codes.laivy.npc.types.AgeableEntityLivingNPC;
import codes.laivy.npc.types.EntityNPC;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.commands.NPCConfiguration;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static codes.laivy.npc.config.Translate.translate;

public class AbstractChestedHorseNPC extends AbstractHorseNPC {
    @Override
    public void debug() {
        super.debug();
        setChest(!hasChest());
    }

    protected AbstractChestedHorseNPC(@NotNull List<OfflinePlayer> players, @NotNull AbstractHorse.Type type, @NotNull Location location) {
        super(players, type, location);
    }

    public boolean hasChest() {
        return getEntity().hasChest();
    }
    public void setChest(boolean chest) {
        getEntity().setChest(chest);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();

        list.add(new NPCConfiguration("chest", "/laivynpc config chest") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                AbstractChestedHorseNPC horseNPC = (AbstractChestedHorseNPC) npc;
                horseNPC.setChest(!horseNPC.hasChest());
                sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
            }
        });

        return list;
    }

    @Override
    public @NotNull AbstractChestedHorse getEntity() {
        return (AbstractChestedHorse) super.getEntity();
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("AbstractChestedHorseNPC Configuration", new LinkedHashMap<String, Object>() {{
            put("Chest", hasChest());
        }});
        return map;
    }

    public static @NotNull AbstractChestedHorseNPC deserialize(@NotNull ConfigurationSection section) {
        AbstractChestedHorseNPC npc = (AbstractChestedHorseNPC) AgeableEntityLivingNPC.deserialize(section);
        npc.setChest(section.getConfigurationSection("AbstractChestedHorseNPC Configuration").getBoolean("Chest"));
        return npc;
    }
    //

}
