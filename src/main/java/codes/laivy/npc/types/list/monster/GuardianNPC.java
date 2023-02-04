package codes.laivy.npc.types.list.monster;

import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import codes.laivy.npc.mappings.utils.classes.entity.monster.Guardian;
import codes.laivy.npc.types.EntityLivingNPC;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.commands.NPCConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static codes.laivy.npc.config.Translate.translate;

public class GuardianNPC extends EntityLivingNPC {

    public static @NotNull GuardianNPC fastInstance(@NotNull List<OfflinePlayer> player, @NotNull Location location, @Nullable Object object) {
        return new GuardianNPC(player, location);
    }

    public static void debug(@NotNull Location location) {
        GuardianNPC guardianNPC = new GuardianNPC(new ArrayList<>(), location);
        guardianNPC.debug();
        guardianNPC.destroy();
    }

    @Override
    protected void debug() {
        super.debug();
        setTarget(getTarget());
    }

    public GuardianNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.GUARDIAN, location);
    }

    public @Nullable Location getTarget() {
        if (getEntity().getTarget() != null) {
            int id = getEntity().getTarget().getId();

            NPC npc = NPC.getByEntityId(id);
            if (npc != null) {
                return npc.getLocation();
            }
        }
        return null;
    }
    public void setTarget(@Nullable Location location) {
        if (getEntity().getTarget() != null) {
            NPC npc = NPC.getByEntityId(getEntity().getTarget().getId());
            if (npc != null) npc.destroy();
        }

        if (location != null) {
            GuardianNPC npc;
            if (isPublicView()) {
                npc = new GuardianNPC(new ArrayList<>(), location);
            } else {
                List<OfflinePlayer> players = new ArrayList<>();
                for (UUID uuid : getPlayers()) {
                    players.add(Bukkit.getOfflinePlayer(uuid));
                }

                npc = new GuardianNPC(players, location);
            }

            npc.setSaveable(false);
            npc.setHidden(true);
            npc.setInvisible(true);
            npc.spawn();

            getEntity().setTarget(npc.getEntity());
        } else {
            getEntity().setTarget(null);
        }
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();
        list.add(new NPCConfiguration("laser", "/laivynpc config laser") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                GuardianNPC guardianNPC = (GuardianNPC) npc;

                if (guardianNPC.getHeadRotation() != null) {
                    sender.sendMessage(translate(sender, "npc.commands.guardian.laser.head_rotation"));
                    return;
                }

                if (guardianNPC.getTarget() != null) {
                    guardianNPC.setTarget(null);
                    sender.sendMessage(translate(sender, "npc.commands.guardian.laser.removed"));
                } else {
                    guardianNPC.setTarget(sender.getLocation());
                    sender.sendMessage(translate(sender, "npc.commands.guardian.laser.set"));
                }
            }
        });
        return list;
    }

    @Override
    public @NotNull Guardian getEntity() {
        return (Guardian) super.getEntity();
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();

        map.put("GuardianNPC Configuration", new HashMap<String, Object>() {{
            if (getTarget() != null) {
                put("Laser", getTarget().serialize());
            }
        }});

        return map;
    }

    public static @NotNull GuardianNPC deserialize(@NotNull ConfigurationSection section) {
        GuardianNPC npc = (GuardianNPC) EntityLivingNPC.deserialize(section);

        final ConfigurationSection guardianSection = section.getConfigurationSection("GuardianNPC Configuration");
        if (guardianSection.contains("Laser")) {
            npc.setTarget(Location.deserialize(guardianSection.getConfigurationSection("Laser").getValues(true)));
        }

        return npc;
    }
    //
}