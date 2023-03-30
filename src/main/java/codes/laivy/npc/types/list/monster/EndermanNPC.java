package codes.laivy.npc.types.list.monster;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.Enderman;
import codes.laivy.npc.types.EntityLivingNPC;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.commands.NPCConfiguration;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static codes.laivy.npc.config.Translate.translate;

public class EndermanNPC extends EntityLivingNPC {

    public static @NotNull EndermanNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new EndermanNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        EndermanNPC endermanNPC = new EndermanNPC(new ArrayList<>(), location);
        endermanNPC.debug();
        endermanNPC.destroy();
    }

    @Override
    protected void debug() {
        super.debug();
        setCarried(Material.AIR);
        getCarried();
        setScreaming(!isScreaming());
    }

    protected EndermanNPC(@NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType type, @NotNull Location location) {
        super(players, type, location);
    }
    public EndermanNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.ENDERMAN, location);
        getHolograms().setDistanceFromNPC(1D);
    }

    public @Nullable Material getCarried() {
        return getEntity().getCarrying();
    }
    public void setCarried(@Nullable Material material) {
        getEntity().setCarrying(material);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    public boolean isScreaming() {
        return getEntity().isScreaming();
    }
    public void setScreaming(boolean flag) {
        getEntity().setScreaming(flag);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();
        list.add(new NPCConfiguration("carried", "/laivynpc config carried") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                EndermanNPC endermanNPC = (EndermanNPC) npc;

                ItemStack item = sender.getInventory().getItemInHand();
                if (item == null) item = new ItemStack(Material.AIR);
                if (!item.getType().isBlock()) {
                    sender.sendMessage(translate(sender, "npc.commands.enderman.carried.block"));
                    return;
                }

                endermanNPC.setCarried(item.getType());
                sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
            }
        });
        list.add(new NPCConfiguration("screaming", "/laivynpc config screaming") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                EndermanNPC endermanNPC = (EndermanNPC) npc;
                endermanNPC.setScreaming(!endermanNPC.isScreaming());
                sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
            }
        });

        return list;
    }

    @Override
    public @NotNull Enderman getEntity() {
        return (Enderman) super.getEntity();
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("EndermanNPC Configuration", new HashMap<String, Object>() {{
            if (getCarried() != null) {
                put("Carried", getCarried().name());
            }
            put("Screaming", isScreaming());
        }});

        return map;
    }

    public static @NotNull EndermanNPC deserialize(@NotNull ConfigurationSection section) {
        EndermanNPC npc = (EndermanNPC) EntityLivingNPC.deserialize(section);

        section = section.getConfigurationSection("EndermanNPC Configuration");
        if (section.contains("Carried")) {
            npc.setCarried(Material.valueOf(section.getString("Carried")));
        }
        npc.setScreaming(section.getBoolean("Screaming"));

        return npc;
    }
    //
}
