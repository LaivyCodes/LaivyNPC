package codes.laivy.npc.types.list.animal;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.Wolf;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumColorEnum;
import codes.laivy.npc.mappings.defaults.classes.java.EnumObjExec;
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

public class WolfNPC extends TameableLivingEntityNPC {

    public static @NotNull WolfNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new WolfNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        WolfNPC wolfNPC = new WolfNPC(new ArrayList<>(), location);
        wolfNPC.debug();
        wolfNPC.destroy();
    }

    @Override
    public void debug() {
        super.debug();
        for (EnumObjExec color : EnumColorEnum.getInstance().getEnums().values()) {
            setCollarColor(new EnumColorEnum.EnumColor(color.getValue()));
        }
        setAngry(!isAngry());
    }

    public WolfNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.WOLF, location);
        getHolograms().setDistanceFromNPC(-1.25D);
    }

    public @NotNull EnumColorEnum.EnumColor getCollarColor() {
        return getEntity().getCollarColor();
    }
    public void setCollarColor(@NotNull EnumColorEnum.EnumColor color) {
        getEntity().setCollarColor(color);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    public boolean isAngry() {
        return getEntity().isAngry();
    }
    public void setAngry(boolean flag) {
        getEntity().setAngry(flag);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();
        list.add(new NPCConfiguration("collar-color", "/laivynpc config collar-color (color)") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                WolfNPC wolfNPC = (WolfNPC) npc;

                if (args.length > 0) {
                    try {
                        EnumColorEnum.EnumColor color = new EnumColorEnum.EnumColor(EnumColorEnum.getInstance().valueOf(args[0].toUpperCase()).getValue());
                        wolfNPC.setCollarColor(color);
                        sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                    } catch (IllegalArgumentException ignore) {
                        sender.performCommand("laivynpc config collar-color");
                        return;
                    }
                    return;
                }

                StringBuilder builder = new StringBuilder();
                int row = 0;
                for (EnumObjExec color : EnumColorEnum.getInstance().getEnums().values()) {
                    if (row != 0) builder.append("§7, ");
                    builder.append("§f").append(new EnumColorEnum.EnumColor(color.getValue()).name());
                    row++;
                }

                sender.sendMessage("§cUse " + getSyntax());
                sender.sendMessage(translate(sender, "npc.commands.general.available_options", builder));
            }
        });
        list.add(new NPCConfiguration("angry", "/laivynpc config angry (flag)") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                WolfNPC wolfNPC = (WolfNPC) npc;

                if (args.length > 0) {
                    boolean flag;
                    if (args[0].equalsIgnoreCase("false")) {
                        flag = false;
                    } else if (args[0].equalsIgnoreCase("true")) {
                        flag = true;
                    } else {
                        sender.performCommand("laivynpc config angry");
                        return;
                    }

                    wolfNPC.setAngry(flag);
                    sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                    return;
                }

                sender.sendMessage("§cUse " + getSyntax());
            }
        });
        return list;
    }

    @Override
    public @NotNull Wolf getEntity() {
        return (Wolf) super.getEntity();
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("WolfNPC Configuration", new HashMap<String, Object>() {{
            put("Collar color", getCollarColor().name());
            put("Angry", isAngry());
        }});

        return map;
    }

    public static @NotNull WolfNPC deserialize(@NotNull ConfigurationSection section) {
        WolfNPC npc = (WolfNPC) TameableLivingEntityNPC.deserialize(section);

        section = section.getConfigurationSection("WolfNPC Configuration");
        npc.setCollarColor(new EnumColorEnum.EnumColor(EnumColorEnum.getInstance().valueOf(section.getString("Collar color")).getValue()));
        npc.setAngry(section.getBoolean("Angry"));

        return npc;
    }
    //
}
