package codes.laivy.npc.types;

import codes.laivy.npc.mappings.defaults.classes.entity.AgeableEntityLiving;
import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.types.commands.NPCConfiguration;
import codes.laivy.npc.utils.Validation;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static codes.laivy.npc.LaivyNPC.laivynpc;
import static codes.laivy.npc.config.Translate.translate;

public abstract class AgeableEntityLivingNPC extends EntityLivingNPC {

    @Override
    public void debug() {
        super.debug();
        setBaby(!isBaby());
    }

    public AgeableEntityLivingNPC(@NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType entityType, @NotNull Location location) {
        this(NPC.getNextNpcId(), players, entityType, location);
    }
    public AgeableEntityLivingNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType entityType, @NotNull Location location) {
        super(id, players, entityType, location);
        Validation.isTrue(!entityType.isAgeableEntityLiving(), new IllegalArgumentException("This EntityType isn't a AgeableLivingEntity."));
    }

    protected @NotNull AgeableEntityLiving getNewEntity() {
        return (AgeableEntityLiving) laivynpc().getVersion().createEntity(getEntityType(), getLocation());
    }

    public @NotNull AgeableEntityLiving getEntity() {
        return (AgeableEntityLiving) super.getEntity();
    }

    public boolean isBaby() {
        return getEntity().isBaby();
    }
    public void setBaby(boolean flag) {
        getEntity().setBaby(flag);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();
        list.add(new NPCConfiguration("baby", "/laivynpc config baby") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                if (npc.getEntity() instanceof AgeableEntityLiving) {
                    AgeableEntityLivingNPC ageable = (AgeableEntityLivingNPC) npc;
                    ageable.setBaby(!ageable.isBaby());
                    sender.sendMessage(translate(sender, "npc.commands.baby.changed"));
                }
            }
        });
        return list;
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("AgeableNPC Configuration", new LinkedHashMap<String, Object>() {{
            put("Baby", isBaby());
        }});
        return map;
    }

    public static @NotNull AgeableEntityLivingNPC deserialize(@NotNull ConfigurationSection section) {
        AgeableEntityLivingNPC npc = (AgeableEntityLivingNPC) EntityNPC.deserialize(section);
        npc.setBaby(section.getConfigurationSection("AgeableNPC Configuration").getBoolean("Baby"));
        return npc;
    }
    //

}
