package codes.laivy.npc.types.list.ambient;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.ambient.Bat;
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

public class BatNPC extends EntityLivingNPC {

    public static @NotNull BatNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new BatNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        BatNPC bat = new BatNPC(new ArrayList<>(), location);
        bat.debug();
        bat.destroy();
    }

    @Override
    protected void debug() {
        super.debug();
        setAsleep(!isAsleep());
    }

    protected BatNPC(@NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType type, @NotNull Location location) {
        super(players, type, location);
    }
    public BatNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.BAT, location.clone().add(0, 1, 0));
        getHolograms().setDistanceFromNPC(-1.25D);
        setAsleep(false);
    }

    public boolean isAsleep() {
        return getEntity().isAsleep();
    }
    public void setAsleep(boolean flag) {
        getEntity().setAsleep(flag);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();
        list.add(SET_ASLEEP);
        return list;
    }

    @Override
    public @NotNull Bat getEntity() {
        return (Bat) super.getEntity();
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("BatNPC Configuration", new HashMap<String, Object>() {{
            put("Asleep", isAsleep());
        }});
        return map;
    }

    public static @NotNull BatNPC deserialize(@NotNull ConfigurationSection section) {
        BatNPC npc = (BatNPC) EntityLivingNPC.deserialize(section);
        npc.setAsleep(section.getConfigurationSection("BatNPC Configuration").getBoolean("Asleep"));
        return npc;
    }
    //

    // Commands
    public static final @NotNull NPCConfiguration SET_ASLEEP = new NPCConfiguration("asleep", "/laivynpc config asleep (asleep)") {
        @Override
        public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
            if (args.length > 0) {
                boolean flag;
                if (args[0].equalsIgnoreCase("false")) {
                    flag = false;
                } else if (args[0].equalsIgnoreCase("true")) {
                    flag = true;
                } else {
                    sender.performCommand("laivynpc config asleep");
                    return;
                }

                ((BatNPC) npc).setAsleep(flag);
                sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
            } else {
                sender.sendMessage("§cUse /laivynpc config asleep (flag)");
            }
        }
    };
    //

}
