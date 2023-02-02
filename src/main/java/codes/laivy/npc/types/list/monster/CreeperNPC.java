package codes.laivy.npc.types.list.monster;

import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import codes.laivy.npc.mappings.utils.classes.entity.monster.Creeper;
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

public class CreeperNPC extends EntityLivingNPC {

    public static @NotNull CreeperNPC fastInstance(@NotNull List<OfflinePlayer> player, @NotNull Location location, @Nullable Object object) {
        return new CreeperNPC(player, location);
    }

    public static void debug(@NotNull Location location) {
        CreeperNPC creeperNPC = new CreeperNPC(new ArrayList<>(), location);
        creeperNPC.debug();
        creeperNPC.destroy();
    }

    @Override
    protected void debug() {
        super.debug();
        setPowered(!isPowered());
        setIgnited(!isIgnited());
    }

    public CreeperNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.CREEPER, location);
    }

    public boolean isPowered() {
        return getEntity().isPowered();
    }
    public void setPowered(boolean flag) {
        getEntity().setPowered(flag);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    public boolean isIgnited() {
        return getEntity().isIgnited();
    }
    public void setIgnited(boolean flag) {
        getEntity().setIgnited(flag);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();
        list.add(new NPCConfiguration("powered", "/laivynpc config powered (flag)") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                CreeperNPC creeperNPC = (CreeperNPC) npc;

                if (args.length > 0) {
                    boolean flag;
                    if (args[0].equalsIgnoreCase("false")) {
                        flag = false;
                    } else if (args[0].equalsIgnoreCase("true")) {
                        flag = true;
                    } else {
                        sender.performCommand("laivynpc config powered");
                        return;
                    }

                    creeperNPC.setPowered(flag);
                    sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                    return;
                }

                sender.sendMessage("§cUse " + getSyntax());
            }
        });
        list.add(new NPCConfiguration("ignited", "/laivynpc config ignited (flag)") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                CreeperNPC creeperNPC = (CreeperNPC) npc;

                if (args.length > 0) {
                    boolean flag;
                    if (args[0].equalsIgnoreCase("false")) {
                        flag = false;
                    } else if (args[0].equalsIgnoreCase("true")) {
                        flag = true;
                    } else {
                        sender.performCommand("laivynpc config ignited");
                        return;
                    }

                    creeperNPC.setIgnited(flag);
                    sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                    return;
                }

                sender.sendMessage("§cUse " + getSyntax());
            }
        });
        return list;
    }

    @Override
    public @NotNull Creeper getEntity() {
        return (Creeper) super.getEntity();
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("CreeperNPC Configuration", new HashMap<String, Object>() {{
            put("Powered", isPowered());
            put("Ignited", isIgnited());
        }});

        return map;
    }

    public static @NotNull CreeperNPC deserialize(@NotNull ConfigurationSection section) {
        CreeperNPC npc = (CreeperNPC) EntityLivingNPC.deserialize(section);

        section = section.getConfigurationSection("CreeperNPC Configuration");
        npc.setPowered(section.getBoolean("Powered"));
        npc.setIgnited(section.getBoolean("Ignited"));

        return npc;
    }
    //
}
