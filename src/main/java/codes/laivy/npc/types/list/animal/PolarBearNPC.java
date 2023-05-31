package codes.laivy.npc.types.list.animal;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.PolarBear;
import codes.laivy.npc.types.AgeableEntityLivingNPC;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.commands.NPCConfiguration;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static codes.laivy.npc.config.Translate.translate;

public class PolarBearNPC extends AgeableEntityLivingNPC {

    public static @NotNull PolarBearNPC fastInstance(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location, @NotNull Object object) {
        return new PolarBearNPC(id, players, location);
    }

    public static void debug(@NotNull Location location) {
        PolarBearNPC bear = new PolarBearNPC(new ArrayList<>(), location);
        bear.debug();
        bear.destroy();
    }

    protected PolarBearNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType type, @NotNull Location location) {
        super(id, players, type, location);
    }
    public PolarBearNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(NPC.getNextNpcId(), players, location);
    }
    public PolarBearNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(id, players, Entity.EntityType.POLAR_BEAR, location);
    }

    public boolean isStanding() {
        return getEntity().isStanding();
    }
    public void setStanding(boolean flag) {
        getEntity().setStanding(flag);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();

        list.add(new NPCConfiguration("standing", "/laivynpc config standing") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                PolarBearNPC polarBearNPC = (PolarBearNPC) npc;
                polarBearNPC.setStanding(!polarBearNPC.isStanding());
                sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
            }
        });

        return list;
    }

    @Override
    public @NotNull PolarBear getEntity() {
        return (PolarBear) super.getEntity();
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("PolarBearNPC Configuration", new HashMap<String, Object>() {{
            put("Standing", isStanding());
        }});

        return map;
    }

    public static @NotNull PolarBearNPC deserialize(@NotNull ConfigurationSection section) {
        PolarBearNPC npc = (PolarBearNPC) AgeableEntityLivingNPC.deserialize(section);

        section = section.getConfigurationSection("PolarBearNPC Configuration");
        npc.setStanding(section.getBoolean("Standing"));

        return npc;
    }
    //

}
