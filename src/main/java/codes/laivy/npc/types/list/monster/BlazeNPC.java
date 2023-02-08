package codes.laivy.npc.types.list.monster;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.Blaze;
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

public class BlazeNPC extends EntityLivingNPC {

    public static @NotNull BlazeNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new BlazeNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        BlazeNPC blazeNPC = new BlazeNPC(new ArrayList<>(), location);
        blazeNPC.debug();
        blazeNPC.destroy();
    }

    @Override
    protected void debug() {
        super.debug();
        setCharging(!isCharging());
    }

    public BlazeNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.BLAZE, location);
    }

    public boolean isCharging() {
        return getEntity().isCharging();
    }
    public void setCharging(boolean flag) {
        getEntity().setCharging(flag);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();
        list.add(new NPCConfiguration("charged", "/laivynpc config charged (flag)") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                BlazeNPC blazeNPC = (BlazeNPC) npc;

                if (args.length > 0) {
                    boolean flag;
                    if (args[0].equalsIgnoreCase("false")) {
                        flag = false;
                    } else if (args[0].equalsIgnoreCase("true")) {
                        flag = true;
                    } else {
                        sender.performCommand("laivynpc config charged");
                        return;
                    }

                    blazeNPC.setCharging(flag);
                    sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                    return;
                }

                sender.sendMessage("§cUse " + getSyntax());
            }
        });
        return list;
    }

    @Override
    public @NotNull Blaze getEntity() {
        return (Blaze) super.getEntity();
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("BlazeNPC Configuration", new HashMap<String, Object>() {{
            put("Charging", isCharging());
        }});

        return map;
    }

    public static @NotNull BlazeNPC deserialize(@NotNull ConfigurationSection section) {
        BlazeNPC npc = (BlazeNPC) EntityLivingNPC.deserialize(section);

        section = section.getConfigurationSection("BlazeNPC Configuration");
        npc.setCharging(section.getBoolean("Charging"));

        return npc;
    }
    //
}
