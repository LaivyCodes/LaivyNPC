package codes.laivy.npc.types.list.monster;

import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import codes.laivy.npc.mappings.utils.classes.entity.monster.Zombie;
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

public class ZombieNPC extends EntityLivingNPC {

    public static @NotNull ZombieNPC fastInstance(@NotNull List<OfflinePlayer> player, @NotNull Location location, @Nullable Object object) {
        return new ZombieNPC(player, location);
    }

    public static void debug(@NotNull Location location) {
        ZombieNPC zombieNPC = new ZombieNPC(new ArrayList<>(), location);
        zombieNPC.debug();
        zombieNPC.destroy();
    }

    @Override
    protected void debug() {
        super.debug();
        setVillager(!isVillager());
    }

    public ZombieNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.ZOMBIE, location);
    }

    public boolean isVillager() {
        return getEntity().isVillager();
    }
    public void setVillager(boolean flag) {
        getEntity().setVillager(flag);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public @NotNull Zombie getEntity() {
        return (Zombie) super.getEntity();
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();
        list.add(new NPCConfiguration("villager", "/laivynpc config villager (flag)") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                ZombieNPC zombieNPC = (ZombieNPC) npc;

                if (args.length > 0) {
                    boolean flag;
                    if (args[0].equalsIgnoreCase("false")) {
                        flag = false;
                    } else if (args[0].equalsIgnoreCase("true")) {
                        flag = true;
                    } else {
                        sender.performCommand("laivynpc config " + getName());
                        return;
                    }

                    zombieNPC.setVillager(flag);
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
        map.put("ZombieNPC Configuration", new HashMap<String, Object>() {{
            put("Villager", isVillager());
        }});

        return map;
    }

    public static @NotNull ZombieNPC deserialize(@NotNull ConfigurationSection section) {
        ZombieNPC npc = (ZombieNPC) EntityLivingNPC.deserialize(section);

        section = section.getConfigurationSection("ZombieNPC Configuration");
        npc.setVillager(section.getBoolean("Villager"));

        return npc;
    }
    //
}
