package codes.laivy.npc.types.list.animal.horse;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.AbstractHorse;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.Horse;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.Llama;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumColorEnum;
import codes.laivy.npc.mappings.defaults.classes.java.EnumObjExec;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.commands.NPCConfiguration;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static codes.laivy.npc.config.Translate.translate;

public class LlamaNPC extends AbstractChestedHorseNPC {

    public static @NotNull LlamaNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @NotNull Object object) {
        return new LlamaNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        LlamaNPC horse = new LlamaNPC(new ArrayList<>(), location);
        horse.debug();
        horse.destroy();
    }

    @Override
    public void debug() {
        super.debug();
        setVariant(getVariant());
        setCarpetColor(getCarpetColor());
    }

    protected LlamaNPC(@NotNull List<OfflinePlayer> players, @NotNull AbstractHorse.Type type, @NotNull Location location) {
        super(players, type, location);
    }
    public LlamaNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, AbstractHorse.Type.LLAMA, location);
    }

    public @NotNull Llama.Variant getVariant() {
        return getEntity().getVariant();
    }
    public void setVariant(@NotNull Llama.Variant variant) {
        getEntity().setVariant(variant);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    public @Nullable EnumColorEnum.EnumColor getCarpetColor() {
        return getEntity().getCarpetColor();
    }
    public void setCarpetColor(@Nullable EnumColorEnum.EnumColor color) {
        getEntity().setCarpetColor(color);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    public @NotNull Horse.Type getType() {
        return AbstractHorse.Type.LLAMA;
    }

    @Override
    public @NotNull Llama getEntity() {
        return (Llama) super.getEntity();
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();

        list.add(new NPCConfiguration("variant", "/laivynpc config variant (variant)") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                LlamaNPC llamaNPC = (LlamaNPC) npc;

                if (args.length > 0) {
                    try {
                        Llama.Variant variant = Llama.Variant.valueOf(args[0].toUpperCase());
                        if (!variant.isCompatible()) {
                            sender.performCommand("laivynpc config " + getName());
                            return;
                        }

                        llamaNPC.setVariant(variant);
                        sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                    } catch (IllegalArgumentException ignore) {
                        sender.performCommand("laivynpc config " + getName());
                        return;
                    }
                    return;
                }

                StringBuilder builder = new StringBuilder();
                int row = 0;
                for (Llama.Variant variant : Llama.Variant.values()) {
                    if (!variant.isCompatible()) {
                        return;
                    }

                    if (row != 0) builder.append("§7, ");
                    builder.append("§f").append(variant.name());
                    row++;
                }

                sender.sendMessage("§cUse " + getSyntax());
                sender.sendMessage(translate(sender, "npc.commands.general.available_options", builder));
            }
        });
        list.add(new NPCConfiguration("carpet-color", "/laivynpc config carpet-color (color)") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                LlamaNPC llamaNPC = (LlamaNPC) npc;

                if (args.length > 0) {
                    try {
                        @Nullable EnumColorEnum.EnumColor color = null;
                        if (!args[0].equalsIgnoreCase("none")) {
                            color = new EnumColorEnum.EnumColor(EnumColorEnum.getInstance().valueOf(args[0].toUpperCase()).getValue());
                        }
                        llamaNPC.setCarpetColor(color);
                        sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                    } catch (IllegalArgumentException ignore) {
                        sender.performCommand("laivynpc config " + getName());
                        return;
                    }
                    return;
                }

                StringBuilder builder = new StringBuilder("§fNONE§7, ");
                int row = 0;
                for (@NotNull EnumObjExec color : EnumColorEnum.getInstance().values()) {
                    if (row != 0) builder.append("§7, ");
                    builder.append("§f").append(color.name());
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
        map.put("LlamaNPC Configuration", new LinkedHashMap<String, Object>() {{
            put("Variant", getVariant().name());
            if (getCarpetColor() != null) {
                put("Color", getCarpetColor().name());
            }
        }});
        return map;
    }

    public static @NotNull LlamaNPC deserialize(@NotNull ConfigurationSection section) {
        LlamaNPC npc = (LlamaNPC) AbstractChestedHorseNPC.deserialize(section);

        ConfigurationSection configs = section.getConfigurationSection("LlamaNPC Configuration");

        npc.setVariant(Llama.Variant.valueOf(configs.getString("Variant").toUpperCase()));
        if (configs.contains("Color")) {
            npc.setCarpetColor(new EnumColorEnum.EnumColor(EnumColorEnum.getInstance().valueOf(configs.getString("Color").toUpperCase()).getValue()));
        }

        return npc;
    }
    //

}
