package codes.laivy.npc.types.list.animal;

import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import codes.laivy.npc.mappings.utils.classes.entity.animal.Sheep;
import codes.laivy.npc.mappings.utils.classes.enums.EnumColorEnum;
import codes.laivy.npc.mappings.utils.classes.java.EnumObjExec;
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

import static codes.laivy.npc.LaivyNPC.laivynpc;
import static codes.laivy.npc.config.Translate.translate;

public class SheepNPC extends AgeableEntityLivingNPC {

    public static @NotNull SheepNPC fastInstance(@NotNull List<OfflinePlayer> player, @NotNull Location location, @Nullable Object object) {
        return new SheepNPC(player, location);
    }

    public static void debug(@NotNull Location location) {
        SheepNPC sheep = new SheepNPC(new ArrayList<>(), location);
        sheep.debug();
        sheep.destroy();
    }

    @Override
    public void debug() {
        super.debug();
        for (EnumObjExec color : laivynpc().getVersion().getEnumExec("EnumColor").getEnums().values()) {
            setColor(new EnumColorEnum.EnumColor(color.getValue()));
        }
        setSheared(!isSheared());
    }

    public SheepNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.SHEEP, location);
    }

    public @NotNull EnumColorEnum.EnumColor getColor() {
        return getEntity().getColor();
    }
    public void setColor(@NotNull EnumColorEnum.EnumColor color) {
        getEntity().setColor(color);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    public boolean isSheared() {
        return getEntity().isSheared();
    }
    public void setSheared(boolean flag) {
        getEntity().setSheared(flag);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public @NotNull Sheep getEntity() {
        return (Sheep) super.getEntity();
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();
        list.add(new NPCConfiguration("color", "/laivynpc config color (color)") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                SheepNPC sheepNPC = (SheepNPC) npc;

                if (args.length > 0) {
                    try {
                        EnumColorEnum.EnumColor color = new EnumColorEnum.EnumColor(laivynpc().getVersion().getEnumExec("EnumColor").valueOf(args[0].toUpperCase()).getValue());
                        sheepNPC.setColor(color);
                        sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                    } catch (IllegalArgumentException ignore) {
                        sender.performCommand("laivynpc config color");
                        return;
                    }
                    return;
                }

                StringBuilder builder = new StringBuilder();
                int row = 0;
                for (EnumObjExec color : laivynpc().getVersion().getEnumExec("EnumColor").getEnums().values()) {
                    if (row != 0) builder.append("§7, ");
                    builder.append("§f").append(new EnumColorEnum.EnumColor(color.getValue()).name());
                    row++;
                }

                sender.sendMessage("§cUse /laivynpc config color (color)");
                sender.sendMessage(translate(sender, "npc.commands.general.available_options", builder));
            }
        });
        list.add(new NPCConfiguration("sheared", "/laivynpc config sheared (flag)") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                SheepNPC sheepNPC = (SheepNPC) npc;

                if (args.length > 0) {
                    boolean flag;
                    if (args[0].equalsIgnoreCase("false")) {
                        flag = false;
                    } else if (args[0].equalsIgnoreCase("true")) {
                        flag = true;
                    } else {
                        sender.performCommand("laivynpc config sheared");
                        return;
                    }

                    sheepNPC.setSheared(flag);
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
        map.put("SheepNPC Configuration", new HashMap<String, Object>() {{
            put("Color", getColor().name());
            put("Sheared", isSheared());
        }});

        return map;
    }

    public static @NotNull SheepNPC deserialize(@NotNull ConfigurationSection section) {
        SheepNPC npc = (SheepNPC) AgeableEntityLivingNPC.deserialize(section);

        section = section.getConfigurationSection("SheepNPC Configuration");
        npc.setColor(new EnumColorEnum.EnumColor(laivynpc().getVersion().getEnumExec("EnumColor").valueOf(section.getString("Color")).getValue()));
        npc.setSheared(section.getBoolean("Sheared"));

        return npc;
    }
    //
}
