package codes.laivy.npc.types.list.animal;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.Ocelot;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.TameableLivingEntityNPC;
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

public class OcelotNPC extends TameableLivingEntityNPC {

    public static @NotNull OcelotNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new OcelotNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        OcelotNPC ocelot = new OcelotNPC(new ArrayList<>(), location);
        ocelot.debug();
        ocelot.destroy();
    }

    @Override
    public void debug() {
        super.debug();
        for (Ocelot.CatVariant variant : Ocelot.CatVariant.values()) {
            setVariant(variant);
        } setVariant(getVariant());
    }

    public OcelotNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.OCELOT, location);
        getHolograms().setDistanceFromNPC(-1.25D);
    }

    public @NotNull Ocelot.CatVariant getVariant() {
        return getEntity().getVariant();
    }
    public void setVariant(@NotNull Ocelot.CatVariant variant) {
        getEntity().setVariant(variant);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public @NotNull Ocelot getEntity() {
        return (Ocelot) super.getEntity();
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();

        list.add(new NPCConfiguration("variant", "/laivynpc config variant") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                OcelotNPC ocelotNPC = (OcelotNPC) npc;

                if (args.length > 0) {
                    try {
                        Ocelot.CatVariant variant = Ocelot.CatVariant.valueOf(args[0].toUpperCase());
                        ocelotNPC.setVariant(variant);
                        sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                    } catch (IllegalArgumentException ignore) {
                        sender.performCommand("laivynpc config variant");
                        return;
                    }
                    return;
                }

                StringBuilder builder = new StringBuilder();
                int row = 0;
                for (Ocelot.CatVariant variant : Ocelot.CatVariant.values()) {
                    if (row != 0) builder.append("§7, ");
                    builder.append("§f").append(variant.name());
                    row++;
                }

                sender.sendMessage("§cUse /laivynpc config variant (variant)");
                sender.sendMessage(translate(sender, "npc.commands.general.available_options", builder));
            }
        });

        return list;
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("OcelotNPC Configuration", new HashMap<String, Object>() {{
            put("Variant", getVariant().getId());
        }});

        return map;
    }

    public static @NotNull OcelotNPC deserialize(@NotNull ConfigurationSection section) {
        OcelotNPC npc = (OcelotNPC) TameableLivingEntityNPC.deserialize(section);

        section = section.getConfigurationSection("OcelotNPC Configuration");
        npc.setVariant(Ocelot.CatVariant.getById(section.getInt("Variant")));

        return npc;
    }
    //

}
