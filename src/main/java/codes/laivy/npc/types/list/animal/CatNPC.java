package codes.laivy.npc.types.list.animal;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.Cat;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.TameableEntityLivingNPC;
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

public class CatNPC extends TameableEntityLivingNPC {

    public static @NotNull CatNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new CatNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        CatNPC ocelotNPC = new CatNPC(new ArrayList<>(), location);
        ocelotNPC.debug();
        ocelotNPC.destroy();
    }

    @Override
    public void debug() {
        super.debug();
        setVariant(getVariant());
    }

    protected CatNPC(@NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType type, @NotNull Location location) {
        super(players, type, location);
    }
    public CatNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.CAT, location);
        getHolograms().setDistanceFromNPC(-1.25D);
    }

    public @NotNull Cat.Variant getVariant() {
        return getEntity().getVariant();
    }
    public void setVariant(@NotNull Cat.Variant variant) {
        getEntity().setVariant(variant);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public @NotNull Cat getEntity() {
        return (Cat) super.getEntity();
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();

        list.add(new NPCConfiguration("variant", "/laivynpc config variant") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                CatNPC ocelotNPC = (CatNPC) npc;

                if (args.length > 0) {
                    try {
                        Cat.Variant variant = Cat.Variant.valueOf(args[0].toUpperCase());
                        ocelotNPC.setVariant(variant);
                        sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                    } catch (IllegalArgumentException ignore) {
                        sender.performCommand("laivynpc config " + getName());
                        return;
                    }
                    return;
                }

                StringBuilder builder = new StringBuilder();
                int row = 0;
                for (Cat.Variant variant : Cat.Variant.values()) {
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
        map.put("OcelotNPC Configuration", new HashMap<String, Object>() {{
            put("Variant", getVariant().name());
        }});

        return map;
    }

    public static @NotNull CatNPC deserialize(@NotNull ConfigurationSection section) {
        CatNPC npc = (CatNPC) TameableEntityLivingNPC.deserialize(section);

        section = section.getConfigurationSection("OcelotNPC Configuration");
        npc.setVariant(Cat.Variant.valueOf(section.getString("Variant").toUpperCase()));

        return npc;
    }
    //

}
