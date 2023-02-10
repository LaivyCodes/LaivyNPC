package codes.laivy.npc.types.list.animal;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.Dolphin;
import codes.laivy.npc.types.EntityLivingNPC;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.commands.NPCConfiguration;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static codes.laivy.npc.config.Translate.translate;

public class DolphinNPC extends EntityLivingNPC {

    public static @NotNull DolphinNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new DolphinNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        DolphinNPC dolphinNPC = new DolphinNPC(new ArrayList<>(), location);
        dolphinNPC.debug();
        dolphinNPC.destroy();
    }

    @Override
    public void debug() {
        super.debug();
        setFish(!hasFish());
    }

    public DolphinNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.DOLPHIN, location);
    }

    public boolean hasFish() {
        return getEntity().hasFish();
    }
    @ApiStatus.Experimental
    public void setFish(boolean fish) {
        getEntity().setFish(fish);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public @NotNull Dolphin getEntity() {
        return (Dolphin) super.getEntity();
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();

        list.add(new NPCConfiguration("fish", "/laivynpc config fish") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                DolphinNPC dolphinNPC = (DolphinNPC) npc;
                dolphinNPC.setFish(!dolphinNPC.hasFish());
                sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
            }
        });

        return list;
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("DolphinNPC Configuration", new HashMap<String, Object>() {{
            put("Fish", hasFish());
        }});

        return map;
    }

    public static @NotNull DolphinNPC deserialize(@NotNull ConfigurationSection section) {
        DolphinNPC npc = (DolphinNPC) EntityLivingNPC.deserialize(section);

        section = section.getConfigurationSection("DolphinNPC Configuration");
        npc.setFish(section.getBoolean("Fish"));

        return npc;
    }
    //

}
