package codes.laivy.npc.types.list.animal.fish;

import codes.laivy.npc.mappings.defaults.classes.entity.animal.fish.Fish;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.fish.TropicalFish;
import codes.laivy.npc.types.EntityLivingNPC;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.commands.NPCConfiguration;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static codes.laivy.npc.config.Translate.translate;

public class TropicalFishNPC extends FishNPC {

    public static @NotNull TropicalFishNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @NotNull Object object) {
        return new TropicalFishNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        TropicalFishNPC tropicalFishNPC = new TropicalFishNPC(new ArrayList<>(), location);
        tropicalFishNPC.debug();
        tropicalFishNPC.destroy();
    }

    @Override
    protected void debug() {
        super.debug();
        setVariant(getVariant());
    }

    public TropicalFishNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Fish.Type.TROPICALFISH, location);
    }

    public int getVariant() {
        return getEntity().getVariant();
    }
    public void setVariant(int variant) {
        getEntity().setVariant(variant);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public @NotNull TropicalFish getEntity() {
        return (TropicalFish) super.getEntity();
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();

        list.add(new NPCConfiguration("variant", "/laivynpc config variant (variant)") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                TropicalFishNPC tropicalFishNPC = (TropicalFishNPC) npc;

                if (args.length > 0) {
                    int variant;
                    try {
                        variant = Integer.parseInt(args[0].replace(",", "."));
                    } catch (NumberFormatException ignore) {
                        sender.performCommand("laivynpc config " + getName());
                        return;
                    }

                    tropicalFishNPC.setVariant(variant);
                    sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                    return;
                }

                sender.sendMessage("§cUse " + getSyntax());
            }
        });

        return list;
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("TropicalFishNPC Configuration", new LinkedHashMap<String, Object>() {{
            put("Variant", getVariant());
        }});
        return map;
    }

    public static @NotNull TropicalFishNPC deserialize(@NotNull ConfigurationSection section) {
        TropicalFishNPC npc = (TropicalFishNPC) EntityLivingNPC.deserialize(section);
        npc.setVariant(section.getConfigurationSection("TropicalFishNPC Configuration").getInt("Variant"));
        return npc;
    }
    //
}
