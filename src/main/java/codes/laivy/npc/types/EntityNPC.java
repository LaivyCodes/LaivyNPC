package codes.laivy.npc.types;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.MethodExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcher;
import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumChatFormatEnum;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumItemSlotEnum;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumNameTagVisibilityEnum;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumTeamPushEnum;
import codes.laivy.npc.mappings.defaults.classes.java.ObjectObjExec;
import codes.laivy.npc.mappings.defaults.classes.packets.Packet;
import codes.laivy.npc.mappings.defaults.classes.scoreboard.Scoreboard;
import codes.laivy.npc.mappings.defaults.classes.scoreboard.ScoreboardTeam;
import codes.laivy.npc.mappings.versions.V1_9_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import codes.laivy.npc.utils.Validation;
import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemorySection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public abstract class EntityNPC extends NPC {

    protected @NotNull Entity.EntityType entityType;
    protected @NotNull Entity entity;

    public EntityNPC(@NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType entityType, @NotNull Location location) {
        super(players, location);

        Validation.isTrue(entityType.isEntityLiving() && !(this instanceof EntityLivingNPC), new IllegalArgumentException("This EntityType is a LivingEntity, only Entities can be passed here."));

        this.entityType = entityType;
        this.entity = getNewEntity();
    }

    public @NotNull Entity.EntityType getEntityType() {
        return entityType;
    }

    protected @NotNull Entity getNewEntity() {
        return laivynpc().getVersion().createEntity(entityType, getLocation());
    }

    public @NotNull Entity getEntity() {
        return entity;
    }

    @Override
    public @NotNull List<@NotNull Packet> getSpawnPackets(@NotNull Player player) {
        List<@NotNull Packet> packets = new LinkedList<>();

        packets.add(laivynpc().getVersion().createSpawnPacket(getEntity()));
        packets.addAll(getMetadataUpdatePackets(player));

        return packets;
    }

    @Override
    public @NotNull List<@NotNull Packet> getDestroyPackets(@NotNull Player player) {
        List<@NotNull Packet> packets = new LinkedList<>();
        packets.add(laivynpc().getVersion().createDestroyPacket(getEntity()));
        return packets;
    }

    @Override
    public @NotNull List<@NotNull Packet> getMetadataUpdatePackets(@NotNull Player player) {
        List<@NotNull Packet> packets = new LinkedList<>();

        try {
            DataWatcher data = getEntity().getDataWatcher();

            //noinspection DataFlowIssue
            byte b = (byte) data.get(0);

            if (isOnFire()) b = (byte) (b | 1);
            else b = (byte) (b & (~(1)));

            data.set(0, b);

            packets.add(laivynpc().getVersion().createMetadataPacket(getEntity(), data, true));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return packets;
    }

    @Override
    public @NotNull List<Packet> getScoreboardUpdatePackets(@NotNull Player player) {
        List<Packet> packets = new LinkedList<>();

        try {
            Scoreboard scoreboard = Scoreboard.getFrom(player);

            // Glowing
            EnumChatFormatEnum.EnumChatFormat color = null;
            if (ReflectionUtils.isCompatible(V1_9_R1.class)) {
                if (getGlowing() != null) {
                    color = getGlowing().getColor();
                }
            }
            if (color == null) color = EnumChatFormatEnum.WHITE();
            //

            String teamName = getEntity().getId() + "";
            ScoreboardTeam team = scoreboard.getTeam(teamName);
            if (team == null) {
                team = laivynpc().getVersion().createScoreboardTeam(scoreboard, teamName);
            }

            // Disable nametag and set team color
            team.setNameTagVisibility(EnumNameTagVisibilityEnum.NEVER());
            team.setColor(color);
            // Disable nametag and set team color

            // Collisions
            if (ReflectionUtils.isCompatible(V1_9_R1.class)) {
                team.setCollision(isCollidable() ? EnumTeamPushEnum.ALWAYS() : EnumTeamPushEnum.NEVER());
            }
            //

            scoreboard.addToTeam(team, getEntity());

            packets.add(laivynpc().getVersion().createScoreboardTeamPacket(team, true));
            packets.add(laivynpc().getVersion().createScoreboardTeamPacket(team, false));
            packets.addAll(getEquipmentsUpdatePackets(player));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return packets;
    }

    @Override
    public @NotNull List<@NotNull Packet> getEquipmentsUpdatePackets(@NotNull Player player) {
        return new LinkedList<>(laivynpc().getVersion().createEquipmentPacket(getEntity(), getEquipments()));
    }

    // Serializators

    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        Map<String, Object> entityNpc = new LinkedHashMap<>();

        try {
            entityNpc.put("Data", "");
            map.put("EntityNPC Configuration", entityNpc);
        } catch (Exception e) {
            e.printStackTrace();
            laivynpc().log("§cCouldn't save the EntityNPC's configurations '" + getId() + "'");
        }

        return map;
    }

    public static @NotNull EntityNPC deserialize(@NotNull ConfigurationSection map) {
        try {
            ConfigurationSection entityNpc = map.getConfigurationSection("EntityNPC Configuration");

            String type = map.getString("Type");
            MemorySection locationMap = (MemorySection) map.get("Location");

            World world = Bukkit.getWorld((String) locationMap.get("world"));
            double x = (double) locationMap.get("x");
            double y = (double) locationMap.get("y");
            double z = (double) locationMap.get("z");

            if (world != null) {
                Location location = new Location(world, x, y, z);
                String data = entityNpc.getString("Data");

                Class<? extends EntityNPC> aClass;
                if (NPC.REGISTERED_NPCS_CLASSES.containsKey(type)) {
                    //noinspection unchecked
                    aClass = (Class<? extends EntityNPC>) NPC.REGISTERED_NPCS_CLASSES.get(type);
                } else {
                    //noinspection unchecked
                    aClass = (Class<? extends EntityNPC>) ReflectionUtils.getNullableClass(type);

                    if (aClass == null) {
                        throw new NullPointerException("Couldn't find this entity's class '" + type + "'");
                    }
                }

                if (MethodExecutor.hasMethodWithReturn(aClass, "fastInstance", aClass, List.class, Location.class, Object.class)) {
                    MethodExecutor method = new MethodExecutor(new ClassExecutor(aClass), new ClassExecutor(NPC.class), "fastInstance", "Gets the fastInstance of a NPC to load it from the configuration", new ClassExecutor(List.class), new ClassExecutor(Location.class), ClassExecutor.OBJECT);
                    method.load();

                    EntityNPC npc = Objects.requireNonNull((EntityNPC) method
                            .invokeStatic(
                                    new ObjectExecutor(new ArrayList<>()) {
                                        @Override
                                        public @NotNull ClassExecutor getClassExecutor() {
                                            return new ClassExecutor(List.class);
                                        }
                                    }, new ObjectExecutor(location) {
                                        @Override
                                        public @NotNull ClassExecutor getClassExecutor() {
                                            return new ClassExecutor(Location.class);
                                        }
                                    }, new ObjectObjExec(data)));
                    npc.setCanSpawn(true);
                    npc.load(map);
                    return npc;
                } else {
                    throw new NullPointerException("Couldn't deserialize this entity type '" + type + "' because the class doesn't contains a 'fastInstance'");
                }
            } else {
                throw new NullPointerException("Couldn't get the world of the NPC");
            }
        } catch (Throwable e) {
            throw new RuntimeException("Deserializations of the NPC 'EntityNPC'", e);
        }
    }
    //

}
