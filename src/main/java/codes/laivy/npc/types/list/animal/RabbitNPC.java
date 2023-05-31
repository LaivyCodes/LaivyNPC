package codes.laivy.npc.types.list.animal;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.Rabbit;
import codes.laivy.npc.types.AgeableEntityLivingNPC;
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

public class RabbitNPC extends AgeableEntityLivingNPC {

    public static @NotNull RabbitNPC fastInstance(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new RabbitNPC(id, players, location);
    }

    public static void debug(@NotNull Location location) {
        RabbitNPC rabbit = new RabbitNPC(new ArrayList<>(), location);
        rabbit.debug();
        rabbit.destroy();
    }

    @Override
    public void debug() {
        super.debug();
        for (Rabbit.Variant variant : Rabbit.Variant.values()) {
            setVariant(variant);
        } setVariant(getVariant());
    }

    protected RabbitNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType type, @NotNull Location location) {
        super(id, players, type, location);
    }
    public RabbitNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(NPC.getNextNpcId(), players, location);
    }
    public RabbitNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(id, players, Entity.EntityType.RABBIT, location);
    }

    public @NotNull Rabbit.Variant getVariant() {
        return getEntity().getVariant();
    }
    public void setVariant(@NotNull Rabbit.Variant variant) {
        getEntity().setVariant(variant);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public @NotNull Rabbit getEntity() {
        return (Rabbit) super.getEntity();
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();
        list.add(new NPCConfiguration("variant", "/laivynpc config variant (variant)") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                RabbitNPC rabbitNPC = (RabbitNPC) npc;

                if (args.length > 0) {
                    try {
                        Rabbit.Variant variant = Rabbit.Variant.valueOf(args[0].toUpperCase());
                        rabbitNPC.setVariant(variant);
                        sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                    } catch (IllegalArgumentException ignore) {
                        sender.performCommand("laivynpc config variant");
                        return;
                    }
                    return;
                }

                StringBuilder builder = new StringBuilder();
                int row = 0;
                for (Rabbit.Variant variant : Rabbit.Variant.values()) {
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
        map.put("RabbitNPC Configuration", new HashMap<String, Object>() {{
            put("Variant", getVariant().getId());
        }});

        return map;
    }

    public static @NotNull RabbitNPC deserialize(@NotNull ConfigurationSection section) {
        RabbitNPC npc = (RabbitNPC) AgeableEntityLivingNPC.deserialize(section);

        section = section.getConfigurationSection("RabbitNPC Configuration");
        npc.setVariant(Rabbit.Variant.getById(section.getInt("Variant")));

        return npc;
    }
    //

}
