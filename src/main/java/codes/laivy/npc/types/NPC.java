package codes.laivy.npc.types;

import codes.laivy.npc.developers.events.NPCDestroyEvent;
import codes.laivy.npc.mappings.defaults.classes.others.objects.PlayerConnection;
import codes.laivy.npc.mappings.defaults.classes.packets.IPacket;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.MethodExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.player.EntityPlayer;
import codes.laivy.npc.mappings.defaults.classes.entity.decoration.ArmorStand;
import codes.laivy.npc.mappings.defaults.classes.enums.EntityPose;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumChatFormatEnum;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumChatFormatEnum.EnumChatFormat;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumItemSlotEnum;
import codes.laivy.npc.mappings.versions.V1_9_R1;
import codes.laivy.npc.types.clicks.CommandClickAction;
import codes.laivy.npc.types.clicks.ServerRedirectClickAction;
import codes.laivy.npc.types.commands.NPCConfiguration;
import codes.laivy.npc.types.commands.NPCItemsEditorConfiguration;
import codes.laivy.npc.types.list.ambient.BatNPC;
import codes.laivy.npc.types.list.ambient.EggNPC;
import codes.laivy.npc.types.list.animal.*;
import codes.laivy.npc.types.list.animal.fish.CodNPC;
import codes.laivy.npc.types.list.animal.fish.PufferfishNPC;
import codes.laivy.npc.types.list.animal.fish.SalmonNPC;
import codes.laivy.npc.types.list.animal.fish.TropicalfishNPC;
import codes.laivy.npc.types.list.animal.horse.*;
import codes.laivy.npc.types.list.boss.dragon.EnderDragonNPC;
import codes.laivy.npc.types.list.boss.dragon.EnderSignalNPC;
import codes.laivy.npc.types.list.boss.wither.WitherNPC;
import codes.laivy.npc.types.list.boss.wither.WitherSkullNPC;
import codes.laivy.npc.types.list.decoration.ArmorStandNPC;
import codes.laivy.npc.types.list.decoration.ItemFrameNPC;
import codes.laivy.npc.types.list.decoration.LeashKnotNPC;
import codes.laivy.npc.types.list.item.FallingBlockNPC;
import codes.laivy.npc.types.list.item.ItemNPC;
import codes.laivy.npc.types.list.monster.*;
import codes.laivy.npc.types.list.monster.illagers.EvokerNPC;
import codes.laivy.npc.types.list.monster.illagers.IllusionerNPC;
import codes.laivy.npc.types.list.monster.illagers.VindicatorNPC;
import codes.laivy.npc.types.list.monster.skeleton.SkeletonNPC;
import codes.laivy.npc.types.list.monster.skeleton.SkeletonStrayNPC;
import codes.laivy.npc.types.list.monster.skeleton.SkeletonWitherNPC;
import codes.laivy.npc.types.list.monster.zombie.ZombieDrownedNPC;
import codes.laivy.npc.types.list.monster.zombie.ZombieHuskNPC;
import codes.laivy.npc.types.list.monster.zombie.ZombieNPC;
import codes.laivy.npc.types.list.monster.zombie.ZombieVillagerNPC;
import codes.laivy.npc.types.list.npc.VillagerNPC;
import codes.laivy.npc.types.list.vehicle.BoatNPC;
import codes.laivy.npc.types.player.PlayerNPC;
import codes.laivy.npc.types.utils.GlowingStatus;
import codes.laivy.npc.types.utils.NPCHeadRotation;
import codes.laivy.npc.types.utils.NPCHologramText;
import codes.laivy.npc.types.workers.NPCHolograms;
import codes.laivy.npc.utils.ReflectionUtils;
import codes.laivy.npc.utils.Validation;
import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemorySection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.lang.reflect.Method;
import java.util.*;

import static codes.laivy.npc.LaivyNPC.laivynpc;
import static codes.laivy.npc.config.Translate.translate;

public abstract class NPC {

    public static @Nullable NPC getByEntityId(int entityId) {
        for (NPC npc : NPCS_ID.values()) {
            if (npc.getAllIDs().contains(entityId)) {
                return npc;
            }
        }
        return null;
    }

    public enum ClickType {
        RIGHT_CLICK(true, false),
        LEFT_CLICK(false, false),

        RIGHT_SHIFT_CLICK(true, true),
        LEFT_SHIFT_CLICK(false, true),
        ;

        private final boolean right;
        private final boolean shift;

        ClickType(boolean right, boolean shift) {
            this.right = right;
            this.shift = shift;
        }

        public boolean isRightClick() {
            return right;
        }
        public boolean isShiftClick() {
            return shift;
        }
    }

    public interface ClickAction {
        void run(@NotNull Player clickedPlayer, ClickType type);

        default @NotNull Map<@NotNull String, @Nullable Object> serialize() {
            return new HashMap<>();
        }
    }
    public static final @NotNull ClickAction BLANK_ACTION = (clickedPlayer, type) -> {
        // Blank Action
    };

    // ---/-/--- //

    public static final @NotNull Set<@NotNull NPC> PUBLIC_NPCS = new HashSet<>();
    public static final @NotNull Map<@NotNull UUID, @NotNull Set<NPC>> ALL_NPCS = new HashMap<>();
    public static final @NotNull TreeMap<@NotNull Integer, @NotNull NPC> NPCS_ID = new TreeMap<>(Comparator.naturalOrder());

    public static int getNextNpcId() {
        if (!NPCS_ID.isEmpty()) {
            return NPCS_ID.lastKey() + 1;
        }
        return 0;
    }

    // ---/-/--- //

    private final @NotNull Set<@Nullable UUID> players;
    private boolean publicView;

    private int id;

    protected @NotNull Set<@NotNull UUID> visiblePlayers = new HashSet<>();

    public abstract @NotNull Entity getEntity();

    // Controller
    private @Range(from = 1, to = 64) int viewDistance = 64;
    private boolean canSpawn = false;
    protected @Nullable BukkitTask controllerTask;
    //

    // Interaction
    private @NotNull ClickAction clickAction = BLANK_ACTION;
    private long interactIntervalMs = 250;
    //

    // Location
    private @NotNull Location location;
    //

    private boolean hidden = false;
    private boolean saveable = true;

    // Packets
    public abstract @NotNull List<IPacket> getSpawnPackets(@NotNull Player player);
    public abstract @NotNull List<IPacket> getDestroyPackets(@NotNull Player player);

    public abstract @NotNull List<IPacket> getMetadataUpdatePackets(@NotNull Player player);
    public abstract @NotNull List<@NotNull IPacket> getScoreboardUpdatePackets(@NotNull Player player);
    public abstract @NotNull List<@NotNull IPacket> getEquipmentsUpdatePackets(@NotNull Player player);

    public @NotNull List<IPacket> getLocationUpdatePackets() {
        return Collections.singletonList(laivynpc().getVersion().createTeleportPacket(getEntity()));
    }
    public @NotNull List<IPacket> getHologramsUpdatePackets(Player player) {
        return new LinkedList<>(getHolograms().getHologramSpawnPackets(player));
    }
    public @NotNull List<IPacket> getMovementUpdatePackets() {
        return new LinkedList<IPacket>() {{
            add(laivynpc().getVersion().createHeadRotationPacket(getEntity(), (int) getLocation().getYaw()));
            add(laivynpc().getVersion().createLookPacket(getEntity(), getLocation().getYaw(), getLocation().getPitch()));
        }};
    }
    //

    // Attributes
    private final Map<EnumItemSlotEnum.@NotNull EnumItemSlot, @NotNull ItemStack> equipments = new HashMap<>();
    private boolean onFire = false;
    //

    // Glowing status
    protected @Nullable GlowingStatus glowing;
    //

    // Holograms
    protected @NotNull NPCHolograms holograms = new NPCHolograms(this);
    //

    // Collidable
    protected boolean collidable = false;
    //

    // Head Rotation Type
    private @Nullable NPCHeadRotation headRotation;
    //

    public NPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location) {
        Set<UUID> uuids = new HashSet<>();

        if (players.size() > 0) {
            this.publicView = false;

            for (OfflinePlayer player : players) {
                uuids.add(player.getUniqueId());
            }
        } else {
            this.publicView = true;
            PUBLIC_NPCS.add(this);

            for (Player player : Bukkit.getOnlinePlayers()) {
                uuids.add(player.getUniqueId());
            }
        }

        this.players = uuids;
        this.location = location.clone();

        this.id = id;
        NPCS_ID.put(this.id, this);

        for (OfflinePlayer player : getPlayersInstances()) {
            ALL_NPCS.putIfAbsent(player.getUniqueId(), new HashSet<>());
            ALL_NPCS.get(player.getUniqueId()).add(this);
        }

        // Task
        controllerTask = new BukkitRunnable() {
            @Override
            public void run() {
                if (!hasSomePlayerOnline()) {
                    return;
                } if (isDestroyed()) {
                    this.cancel();
                    return;
                }

                if (canSpawn()) {
                    for (UUID uuid : getVisiblePlayers()) {
                        Player player = Bukkit.getPlayer(uuid);

                        if (!isPlayerAtRange(player)) {
                            hide(player);
                        } else if (!isSpawned(player)) {
                            spawn(player);
                        }
                    }
                } else if (isSpawnedForSomeone()) {
                    hide();
                }
            }
        }.runTaskTimer(laivynpc(), 20, 20);
        //
    }

    public @Nullable GlowingStatus getGlowing() {
        return glowing;
    }

    public void setGlowing(@Nullable GlowingStatus glowing) {
        if (glowing != null && glowing.getNPC() != this) {
            throw new IllegalArgumentException("This GlowingStatus's NPC isn't this NPC.");
        }

        this.glowing = glowing;
        sendUpdatePackets(getSpawnedPlayers(), true, false, true, false, false, false);
    }

    public boolean isCollidable() {
        return collidable;
    }

    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }

    /**
     * @return true if the NPC could be selected and is visible in '/npc list' command.
     */
    public boolean isHidden() {
        return hidden;
    }
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    /**
     * @return true if the NPC is saved on the config on the plugin disable
     */
    public boolean isSaveable() {
        return saveable;
    }
    public void setSaveable(boolean saveable) {
        this.saveable = saveable;
    }

    protected void debug() {
        setSaveable(false);

        setHeadRotation(new NPCHeadRotation(this, 5, Player.class));
        setOnFire(true);
        setLocation(getLocation().add(0, 1, 0));
        setLocation(getLocation().add(0, -1, 0));
        setItem(EnumItemSlotEnum.EnumItemSlot.HEAD, new ItemStack(Material.DIAMOND_HELMET));
        setItem(EnumItemSlotEnum.EnumItemSlot.CHEST, new ItemStack(Material.DIAMOND_CHESTPLATE));
        setItem(EnumItemSlotEnum.EnumItemSlot.LEGS, new ItemStack(Material.DIAMOND_LEGGINGS));
        setItem(EnumItemSlotEnum.EnumItemSlot.FEET, new ItemStack(Material.DIAMOND_BOOTS));
        setItem(EnumItemSlotEnum.EnumItemSlot.MAINHAND, new ItemStack(Material.DIAMOND_SWORD));
        if (ReflectionUtils.isCompatible(V1_9_R1.class)) {
            setItem(EnumItemSlotEnum.EnumItemSlot.OFFHAND, new ItemStack(Material.DIAMOND_PICKAXE));
            setGlowing(new GlowingStatus(this, EnumChatFormatEnum.WHITE(), true) {{
               setRainbow(new Rainbow(1));
            }});
        }
        getHolograms().setLine(3, new NPCHologramText("§7LaivyNPC", this));
        getHolograms().setLine(2, new NPCHologramText("§eThe best NPCs plugin :)", this));
        getHolograms().setLine(0, new NPCHologramText("§0Lets goo", this));
        spawn();
    }

    public @NotNull ClickAction getClickAction() {
        return clickAction;
    }
    public void setClickAction(@Nullable ClickAction clickAction) {
        if (clickAction == null) this.clickAction = BLANK_ACTION;
        else this.clickAction = clickAction;
    }

    public long getInteractIntervalMs() {
        return interactIntervalMs;
    }
    public void setInteractIntervalMs(long interactIntervalMs) {
        this.interactIntervalMs = interactIntervalMs;
    }

    public @Nullable NPCHeadRotation getHeadRotation() {
        return headRotation;
    }

    public @NotNull NPCHolograms getHolograms() {
        return holograms;
    }

    public @NotNull Set<UUID> getPlayers() {
        return new HashSet<>(players);
    }

    public final void removePlayer(@NotNull Player... players) {
        for (Player player : players) {
            this.players.remove(player.getUniqueId());

            if (canSpawn()) {
                if (isSpawned(player)) {
                    hide(player);
                }
            }

            ALL_NPCS.putIfAbsent(player.getUniqueId(), new HashSet<>());
            ALL_NPCS.get(player.getUniqueId()).remove(this);
        }
    }
    public final void addPlayer(@NotNull Player... players) {
        for (Player player : players) if (!this.players.contains(player.getUniqueId())) {
            this.players.add(player.getUniqueId());

            if (canSpawn()) {
                if (!isSpawned(player)) {
                    spawn(player);
                }
            }

            ALL_NPCS.putIfAbsent(player.getUniqueId(), new HashSet<>());
            ALL_NPCS.get(player.getUniqueId()).add(this);
        }
    }

    @ApiStatus.Internal
    public @NotNull Set<OfflinePlayer> getPlayersInstances() {
        Set<OfflinePlayer> players = new HashSet<>();
        for (UUID uuid : this.players) {
            players.add(Bukkit.getOfflinePlayer(uuid));
        }
        return players;
    }

    public boolean isPublicView() {
        return publicView;
    }

    /**
     * @deprecated Some NPCs like GuardianNPCs doesn't support this.
     */
    @ApiStatus.Internal
    @Deprecated
    public void setPublicView(boolean publicView) {
        if (publicView) PUBLIC_NPCS.add(this);
        else PUBLIC_NPCS.remove(this);

        this.publicView = publicView;
    }

    public @NotNull Location getLocation() {
        return location;
    }

    public void setLocation(@NotNull Location location) {
        this.setLocation(location, true);
    }
    public void setLocation(@NotNull Location location, boolean update) {
        this.location = location;
        getEntity().setLocation(location);
        sendUpdatePackets(getVisiblePlayers(), false, false, false, update, update, update);
    }

    public int getId() {
        return id;
    }

    public @NotNull Set<Integer> getAllIDs() {
        return new HashSet<Integer>() {{
            for (Integer line : getHolograms().getLines()) {
                NPCHologramText holo = getHolograms().getLine(line);

                if (holo != null) {
                    for (ArmorStand stand : holo.getArmorStands()) {
                        add(stand.getId());
                    }
                }
            }
            add(getEntity().getId());
        }};
    }

    public Set<Player> getOnlinePlayers() {
        Set<Player> players = new HashSet<>();

        if (isPublicView()) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                players.add(player.getPlayer());
            }
        } else {
            for (UUID uuid : getPlayers()) {
                OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
                if (player.isOnline()) players.add(player.getPlayer());
            }
        }

        return players;
    }
    public Set<UUID> getVisiblePlayers() {
        Set<UUID> players = new HashSet<>();
        for (Player player : getOnlinePlayers()) {
            if (isPlayerAtRange(player.getPlayer())) players.add(player.getUniqueId());
        }
        players.addAll(getSpawnedPlayers());
        return players;
    }
    public @NotNull Set<@NotNull UUID> getSpawnedPlayers() {
        for (UUID uuid : new ArrayList<>(visiblePlayers)) {
            Player player = Bukkit.getPlayer(uuid);
            if (player == null) visiblePlayers.remove(uuid);
        }
        return visiblePlayers;
    }

    // Controller
    @Range(from = 1, to = 64)
    public int getViewDistance() {
        return viewDistance;
    }

    public void setViewDistance(@Range(from = 1, to = 64) int viewDistance) {
        this.viewDistance = viewDistance;
    }

    public boolean canSpawn() {
        return canSpawn;
    }

    public void setCanSpawn(boolean canSpawn) {
        this.canSpawn = canSpawn;
    }

    public @Nullable BukkitTask getControllerTask() {
        return controllerTask;
    }

    public void setControllerTask(@Nullable BukkitTask controllerTask) {
        this.controllerTask = controllerTask;
    }

    public boolean isDestroyed() {
        return getControllerTask() == null && NPCS_ID.containsKey(getId()) && NPCS_ID.get(getId()) == this;
    }

    public void reCreate() {
        hide();
    }
    public void spawn(@NotNull Player player) {
        Validation.isTrue(isDestroyed(), new IllegalStateException("This NPC is destroyed, you need to recreate it."));

        setCanSpawn(true);

        ReflectionUtils.sendPacketToPlayer(getSpawnPackets(player), player);

        getSpawnedPlayers().add(player.getUniqueId());

        sendUpdatePackets(player, true, true, true, true, true, true);
    }

    public void hide(@NotNull Player player) {
        Validation.isTrue(isDestroyed(), new IllegalStateException("This NPC is destroyed, you need to recreate it."));

        ReflectionUtils.sendPacketToPlayer(getDestroyPackets(player), player);
        getHolograms().hideHolograms(Collections.singletonList(player));

        getSpawnedPlayers().remove(player.getUniqueId());
    }

    public void spawn() {
        for (@NotNull UUID player : getVisiblePlayers()) {
            spawn(Bukkit.getPlayer(player));
        }
    }
    public void despawn() {
        hide();
        setCanSpawn(false);
    }

    public void destroy() {
        // Event
        NPCDestroyEvent event = new NPCDestroyEvent(!Bukkit.isPrimaryThread(), this);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        //

        if (controllerTask != null) {
            controllerTask.cancel();
        }
        setHeadRotation(null);

        despawn();

        NPCS_ID.remove(getId());

        for (UUID uuid : getPlayers()) {
            if (NPC.ALL_NPCS.containsKey(uuid)) {
                NPC.ALL_NPCS.get(uuid).remove(this);
            }
        }

        NPC.PUBLIC_NPCS.remove(this);
    }
    public void respawn() {
        if (canSpawn()) {
            hide();
            spawn();
        }
    }
    public void hide() {
        for (UUID uuid : new ArrayList<>(getSpawnedPlayers())) {
            hide(Bukkit.getPlayer(uuid));
        }
    }

    public boolean isPlayerAtRange(Player player) {
        Location playerLocation = player.getLocation();
        if (!(playerLocation.getWorld().equals(getLocation().getWorld()))) {
            return false;
        }

        return player.getLocation().distance(getLocation()) <= getViewDistance();
    }
    public boolean isParsing() {
        if (!NPCS_ID.containsKey(this.id)) return false;

        for (UUID uuid : getPlayers()) {
            if (ALL_NPCS.get(uuid).contains(this)) {
                return true;
            }
        }

        return false;
    }
    public boolean isSpawnedForSomeone() {
        return getSpawnedPlayers().size() != 0;
    }
    public boolean isSpawned(@NotNull OfflinePlayer player) {
        return getSpawnedPlayers().contains(player.getUniqueId());
    }
    public boolean hasSomePlayerOnline() {
        for (UUID uuid : getPlayers()) {
            if (Bukkit.getPlayer(uuid) != null) return true;
        }
        return false;
    }
    //

    // Attributes
    @ApiStatus.Internal
    protected @NotNull Map<EnumItemSlotEnum.@NotNull EnumItemSlot, @NotNull ItemStack> getEquipments() {
        return equipments;
    }
    public void setItem(EnumItemSlotEnum.@NotNull EnumItemSlot slot, @Nullable ItemStack item) {
        if (item == null) {
            getEquipments().remove(slot);
            respawn();
            return;
        } else {
            getEquipments().put(slot, item);
        }

        sendUpdatePackets(getSpawnedPlayers(), false, true, false, false, false, false);
    }
    public @Nullable ItemStack getItem(EnumItemSlotEnum.@NotNull EnumItemSlot slot) {
        if (getEquipments().containsKey(slot)) {
            return getEquipments().get(slot);
        } return null;
    }

    public boolean isOnFire() {
        return onFire;
    }
    public void setOnFire(boolean onFire) {
        this.onFire = onFire;
    }
    //

    // Commands
    public List<NPCConfiguration> getByCommandConfigurations() {
        return new LinkedList<NPCConfiguration>() {{
            add(SPAWN_CONFIG);
            add(RESPAWN_CONFIG);
            add(DESTROY_CONFIG);
            add(FIRE_TOGGLE_CONFIG);
            add(VIEW_DISTANCE_CONFIG);
            add(ITEMS_EDITOR_CONFIG);
            add(LOCATION_CHANGE_CONFIG);
            add(CLICK_COMMAND_CONFIG);
            add(CLICK_SERVER_REDIRECT_CONFIG);
            add(HEAD_MOVEMENT_CONFIG);
            add(HOLOGRAMS_CONFIG);
            add(INVISIBLE_CONFIG);
            add(HOLO_HEIGHT_CONFIG);
            add(DISPLAY_NAME_CONFIG);

            // TODO: 24/12/2022 1.14 poses
//            if (ReflectionUtils.isCompatible(V1_14_R1.class)) {
//                add(NPC_POSE_CONFIG);
//            }
        }};
    }
    //

    // Packets
    public final void sendUpdatePackets(@NotNull Set<@NotNull UUID> players, boolean scoreboard, boolean equipments, boolean metadata, boolean location, boolean holograms, boolean movement) {
        for (UUID uuid : players) {
            this.sendUpdatePackets(Bukkit.getPlayer(uuid), scoreboard, equipments, metadata, location, holograms, movement);
        }
    }
    public void sendUpdatePackets(@NotNull Player player, boolean scoreboard, boolean equipments, boolean metadata, boolean location, boolean holograms, boolean movement) {
        if (!player.isOnline()) {
            return;
        } if (isDestroyed()) {
            return;
        }

        Set<IPacket> packetList = new LinkedHashSet<>();

        if (scoreboard) packetList.addAll(getScoreboardUpdatePackets(player));
        if (equipments) packetList.addAll(getEquipmentsUpdatePackets(player));
        if (metadata) packetList.addAll(getMetadataUpdatePackets(player));
        if (location) packetList.addAll(getLocationUpdatePackets());
        if (holograms) packetList.addAll(getHologramsUpdatePackets(player));
        if (movement) packetList.addAll(getMovementUpdatePackets());

        PlayerConnection conn = EntityPlayer.getEntityPlayer(player).getPlayerConnection();
        for (IPacket packet : packetList) {
            conn.sendPacket(packet);
        }
    }
    //

    // Poses
    public void setPose(@NotNull EntityPose pose) {
        getEntity().setPose(pose);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }
    public @NotNull EntityPose getPose() {
        return getEntity().getPose();
    }
    //

    public boolean isInvisible() {
        return getEntity().isInvisible();
    }
    public void setInvisible(boolean flag) {
        getEntity().setInvisible(flag);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    public @Nullable String getCustomName() {
        return getEntity().getCustomName();
    }
    public void setCustomName(@Nullable String name) {
        getEntity().setCustomName(name);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    public boolean isCustomNameVisible() {
        return getEntity().isCustomNameVisible();
    }
    public void setCustomNameVisible(boolean visible) {
        getEntity().setCustomNameVisible(visible);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    // Head
    public void setHeadRotation(float yaw, float pitch) {
        Location location = getLocation();
        location.setPitch(pitch);
        location.setYaw(yaw);
        setLocation(location, false);
        sendUpdatePackets(getVisiblePlayers(), false, false, false, false, false, true);
    }
    public void setHeadRotation(@Nullable NPCHeadRotation headRotation) {
        this.headRotation = headRotation;
    }

    public void lookTo(@NotNull Location to) {
        Location npcLoc = getLocation();

        if (to.getWorld() != npcLoc.getWorld()) {
            throw new IllegalStateException("Different worlds");
        }

        double xDiff = to.getX() - npcLoc.getX();
        double yDiff = to.getY() - npcLoc.getY();
        double zDiff = to.getZ() - npcLoc.getZ();

        double distanceXZ = Math.sqrt(xDiff * xDiff + zDiff * zDiff);
        double distanceY = Math.sqrt(distanceXZ * distanceXZ + yDiff * yDiff);

        double yaw = Math.toDegrees(Math.acos(xDiff / distanceXZ));
        double pitch = Math.toDegrees(Math.acos(yDiff / distanceY)) - 90.0D;
        if (zDiff < 0.0D) {
            yaw += Math.abs(180.0D - yaw) * 2.0D;
        }

        npcLoc.setYaw((float) (yaw - 90.0F));
        npcLoc.setPitch((float) (pitch - 90.0F));

        setHeadRotation(npcLoc.getYaw(), npcLoc.getPitch() + 90);
    }
    //

    //
    // Defaults
    //

    public static @NotNull NPCConfiguration SPAWN_CONFIG = new NPCConfiguration("spawn", "/laivynpc config spawn") {
        @Override
        public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
            npc.setCanSpawn(!npc.canSpawn());
            if (npc.canSpawn()) {
                sender.sendMessage(translate(sender, "npc.commands.spawn.allowed"));
            } else {
                sender.sendMessage(translate(sender, "npc.commands.spawn.disallowed"));
            }
        }
    };

    public static @NotNull NPCConfiguration RESPAWN_CONFIG = new NPCConfiguration("respawn", "/laivynpc config respawn") {
        @Override
        public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
            npc.respawn();
            sender.sendMessage(translate(sender, "npc.commands.respawn"));
        }
    };

    public static @NotNull NPCConfiguration DESTROY_CONFIG = new NPCConfiguration("destroy", "/laivynpc config destroy") {
        @Override
        public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
            npc.destroy();
            sender.sendMessage(translate(sender, "npc.commands.destroy"));
        }
    };

    public static NPCConfiguration FIRE_TOGGLE_CONFIG = new NPCConfiguration("fire", "/laivynpc config fire") {
        @Override
        public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
            if (npc.isOnFire()) sender.sendMessage(translate(sender, "npc.commands.fire.disallowed"));
            else sender.sendMessage(translate(sender, "npc.commands.fire.allowed"));

            npc.setOnFire(!npc.isOnFire());
            npc.sendUpdatePackets(npc.getSpawnedPlayers(), false, false, true, false, false, false);
        }
    };

    public static NPCConfiguration VIEW_DISTANCE_CONFIG = new NPCConfiguration("view-distance", "/laivynpc config view-distance (distance)") {
        @Override
        public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
            if (args.length > 0) {
                try {
                    int distance = Integer.parseInt(args[0]);
                    if (distance < 2 || distance > 64) {
                        sender.sendMessage(translate(sender, "npc.commands.distance.range"));
                    } else {
                        npc.setViewDistance(distance);
                        sender.sendMessage(translate(sender, "npc.commands.distance", distance));
                    }
                    return;
                } catch (NumberFormatException ignore) {
                }
            }
            sender.sendMessage("§cUse /laivynpc config view-distance (distância em blocos)");
        }
    };

    public static @NotNull NPCConfiguration ITEMS_EDITOR_CONFIG = new NPCItemsEditorConfiguration();

    public static @NotNull NPCConfiguration LOCATION_CHANGE_CONFIG = new NPCConfiguration("location", "/laivynpc config location") {
        @Override
        public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
            npc.setLocation(sender.getLocation(), true);
            sender.sendMessage(translate(sender, "npc.commands.location"));
        }
    };

    public static @NotNull NPCConfiguration CLICK_COMMAND_CONFIG = new NPCConfiguration("click-command", "/laivynpc config click-command") {
        @Override
        public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
            NPC.ClickAction old = npc.getClickAction();
            if (args.length >= 1) {
                StringBuilder fullCommand = null;
                if (args.length >= 2) {
                    fullCommand = new StringBuilder(args[1]);
                    for (int row = 2; row < args.length; row++) {
                        fullCommand.append(" ").append(args[row]);
                    }
                }

                ClickType[] types;
                try {
                    types = new ClickType[] {
                            ClickType.valueOf(args[0].toUpperCase())
                    };
                } catch (IllegalArgumentException ignore) {
                    if (args[0].equalsIgnoreCase("all")) {
                        types = ClickType.values();
                    } else {
                        sender.performCommand("laivynpc config click-command");
                        return;
                    }
                }

                if (fullCommand == null) {
                    npc.setClickAction(NPC.BLANK_ACTION);
                    sender.sendMessage(translate(sender, "npc.commands.click.removed"));
                } else {
                    CommandClickAction action = new CommandClickAction(new HashMap<>());
                    if (old instanceof CommandClickAction) action = (CommandClickAction) old;

                    for (ClickType type : types) {
                        action.getCommands().put(type, fullCommand.toString());
                    }

                    npc.setClickAction(action);
                    sender.sendMessage(translate(sender, "npc.commands.click.changed"));
                }

                return;
            }

            StringBuilder types = new StringBuilder("§6ALL");
            for (ClickType type : ClickType.values()) {
                types.append("§c, §6").append(type);
            }

            sender.sendMessage("§cUse /laivynpc config click-command (type) (command)");
            sender.sendMessage(translate(sender, "npc.commands.click.command.types") + ": " + types);
            sender.sendMessage(translate(sender, "npc.commands.click.command.warning"));
        }
    };
    public static @NotNull NPCConfiguration CLICK_SERVER_REDIRECT_CONFIG = new NPCConfiguration("click-server-redirect", "/laivynpc config click-server-redirect") {
        @Override
        public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
            NPC.ClickAction old = npc.getClickAction();
            if (args.length >= 1) {
                StringBuilder fullCommand = null;
                if (args.length >= 2) {
                    fullCommand = new StringBuilder(args[1]);
                    for (int row = 2; row < args.length; row++) {
                        fullCommand.append(" ").append(args[row]);
                    }
                }

                ClickType[] types;
                try {
                    types = new ClickType[] {
                            ClickType.valueOf(args[0].toUpperCase())
                    };
                } catch (IllegalArgumentException ignore) {
                    if (args[0].equalsIgnoreCase("all")) {
                        types = ClickType.values();
                    } else {
                        sender.performCommand("laivynpc config click-server-redirect");
                        return;
                    }
                }

                if (fullCommand == null) {
                    npc.setClickAction(NPC.BLANK_ACTION);
                    sender.sendMessage(translate(sender, "npc.commands.click.removed"));
                } else {
                    ServerRedirectClickAction action = new ServerRedirectClickAction(new HashMap<>());
                    if (old instanceof ServerRedirectClickAction) action = (ServerRedirectClickAction) old;

                    for (ClickType type : types) {
                        action.getServers().put(type, fullCommand.toString());
                    }

                    npc.setClickAction(action);
                    sender.sendMessage(translate(sender, "npc.commands.click.changed"));
                }

                return;
            }

            StringBuilder types = new StringBuilder("§6ALL");
            for (ClickType type : ClickType.values()) {
                types.append("§c, §6").append(type);
            }

            sender.sendMessage("§cUse /laivynpc config click-server-redirect (type) (server name)");
            sender.sendMessage(translate(sender, "npc.commands.click.command.types") + ": " + types);
        }
    };

    public static @NotNull NPCConfiguration HEAD_MOVEMENT_CONFIG = new NPCConfiguration("head", "/laivynpc config head (type)") {
        @Override
        public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
            if (args.length > 0) {
                NPCHeadRotation.Type type;
                if (args[0].equalsIgnoreCase(translate(sender, "npc.commands.head_rotation.names.none"))) {
                    type = NPCHeadRotation.Type.NONE;
                } else if (args[0].equalsIgnoreCase(translate(sender, "npc.commands.head_rotation.names.nearest_player"))) {
                    type = NPCHeadRotation.Type.FOLLOW_NEAREST_PLAYER;
                } else if (args[0].equalsIgnoreCase(translate(sender, "npc.commands.head_rotation.names.nearest_monster"))) {
                    type = NPCHeadRotation.Type.FOLLOW_NEAREST_MONSTER;
                } else if (args[0].equalsIgnoreCase(translate(sender, "npc.commands.head_rotation.names.nearest_animal"))) {
                    type = NPCHeadRotation.Type.FOLLOW_NEAREST_ANIMAL;
                } else if (args[0].equalsIgnoreCase(translate(sender, "npc.commands.head_rotation.names.nearest_entity"))) {
                    type = NPCHeadRotation.Type.FOLLOW_NEAREST_ENTITY;
                } else if (args[0].equalsIgnoreCase(translate(sender, "npc.commands.head_rotation.names.nearest_living_entity"))) {
                    type = NPCHeadRotation.Type.FOLLOW_NEAREST_LIVING_ENTITY;
                } else {
                    sender.performCommand("laivynpc config head");
                    return;
                }

                if (type == NPCHeadRotation.Type.NONE) {
                    npc.setHeadRotation(null);
                } else {
                    npc.setHeadRotation(new NPCHeadRotation(npc, 5, type.getEntityClass()));
                }

                sender.sendMessage(translate(sender, "npc.commands.head_rotation.changed"));
            } else {
                sender.sendMessage("§cUse /laivynpc config head (type)");
                sender.sendMessage(translate(sender, "npc.commands.head_rotation.available"));
                sender.sendMessage("");
                sender.sendMessage("§8 - §6" + translate(sender, "npc.commands.head_rotation.names.none") + " §c- " + translate(sender, "npc.commands.head_rotation.names.none.description"));
                sender.sendMessage("§8 - §6" + translate(sender, "npc.commands.head_rotation.names.nearest_player") + " §c- " + translate(sender, "npc.commands.head_rotation.names.nearest_player.description"));
                sender.sendMessage("§8 - §6" + translate(sender, "npc.commands.head_rotation.names.nearest_monster") + " §c- " + translate(sender, "npc.commands.head_rotation.names.nearest_monster.description"));
                sender.sendMessage("§8 - §6" + translate(sender, "npc.commands.head_rotation.names.nearest_animal") + " §c- " + translate(sender, "npc.commands.head_rotation.names.nearest_animal.description"));
                sender.sendMessage("§8 - §6" + translate(sender, "npc.commands.head_rotation.names.nearest_entity") + " §c- " + translate(sender, "npc.commands.head_rotation.names.nearest_entity.description"));
                sender.sendMessage("§8 - §6" + translate(sender, "npc.commands.head_rotation.names.nearest_living_entity") + " §c- " + translate(sender, "npc.commands.head_rotation.names.nearest_living_entity.description"));
            }
        }
    };

    public static @NotNull NPCConfiguration NPC_POSE_CONFIG = new NPCConfiguration("pose", "/laivynpc config pose") {
        @Override
        public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
            if (args.length > 0) {
                EntityPose pose;
                try {
                    pose = EntityPose.valueOf(args[0].toUpperCase());
                    if (pose == EntityPose.SLEEPING && npc.getViewDistance() > 16) {
                        sender.sendMessage(translate(sender, "npc.commands.pose.sleeping.error.view_distance"));
                        return;
                    }

                    npc.setPose(pose);
                    sender.sendMessage(translate(sender, "npc.commands.pose.changed", pose.name().toLowerCase()));
                    return;
                } catch (IllegalArgumentException ignore) {
                }
            }

            StringBuilder types = new StringBuilder();
            int row = 0;
            for (EntityPose pose : EntityPose.values()) {
                if (pose.isPlayerPose() && !(npc.getEntity() instanceof EntityPlayer)) continue;
                if (row > 0) types.append("§c, ");
                types.append("§6").append(pose.name().toLowerCase());
                row++;
            }
            sender.sendMessage(translate(sender, "npc.commands.pose.available", types));

            if (npc.getPose() == EntityPose.SLEEPING) {
                sender.sendMessage(translate(sender, "npc.commands.sleep.deactivated"));
                npc.setPose(EntityPose.STANDING);
            } else {
                if (npc.getViewDistance() > 16) {
                    sender.sendMessage(translate(sender, "npc.commands.sleep.error.view_distance"));
                    return;
                }

                sender.sendMessage(translate(sender, "npc.commands.sleep.activated"));
                npc.setPose(EntityPose.SLEEPING);
            }
        }
    };

    public static @NotNull NPCConfiguration HOLOGRAMS_CONFIG = new NPCConfiguration("holo", "/laivynpc config holo (line) (opacity) (text)") {
        @Override
        public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
            if (args.length > 0) {
                try {
                    int line = Integer.parseInt(args[0]);

                    if (args.length > 2) {
                        StringBuilder textBuilder = new StringBuilder(args[2]);
                        for (int row = 3; row < args.length; row++) {
                            textBuilder.append(" ").append(args[row]);
                        }

                        String text = ChatColor.translateAlternateColorCodes('&', textBuilder.toString());
                        NPCHologramText.TextOpacity opacity;

                        try {
                            opacity = NPCHologramText.TextOpacity.valueOf(args[1].toUpperCase());
                        } catch (IllegalArgumentException ignore) {
                            sender.performCommand("laivynpc config holo");
                            return;
                        }

                        if (line < 0 || line > 999) {
                            sender.sendMessage(translate(sender, "npc.commands.hologram.line_range"));
                            return;
                        }

                        npc.getHolograms().setLine(line, new NPCHologramText(text, opacity, npc));
                        sender.sendMessage(translate(sender, "npc.commands.hologram.line_changed", line));
                    } else {
                        npc.getHolograms().setLine(line, null);
                        sender.sendMessage(translate(sender, "npc.commands.hologram.line_removed"));
                    }
                    return;
                } catch (NumberFormatException ignore) {
                }
            }

            StringBuilder opacity = new StringBuilder();
            int row = 0;
            for (NPCHologramText.TextOpacity opacityEnum : NPCHologramText.TextOpacity.values()) {
                opacity.append("§6").append(opacityEnum.name());
                row++;
                if (row != NPCHologramText.TextOpacity.values().length) {
                    opacity.append("§c, ");
                }
            }

            sender.sendMessage("§cUse /laivynpc config holo (line) (opacity) (text)");
            sender.sendMessage("");
            sender.sendMessage("§8 - §c" + translate(sender, "npc.commands.hologram.leave_blank_to_remove"));
            sender.sendMessage("§8 - §c" + translate(sender, "npc.commands.hologram.available_opacities") + ": " + opacity);
        }
    };

    public static @NotNull NPCConfiguration INVISIBLE_CONFIG = new NPCConfiguration("invisible", "/laivynpc config invisible") {
        @Override
        public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
            npc.setInvisible(!npc.isInvisible());
            sender.sendMessage(translate(sender, "npc.commands.invisible.changed"));
        }
    };

    public static @NotNull NPCConfiguration HOLO_HEIGHT_CONFIG = new NPCConfiguration("holo-height", "/laivynpc config holo-height (height)") {
        @Override
        public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
            if (args.length > 0) {
                try {
                    double value = Double.parseDouble(args[0].replace(",", "."));
                    npc.getHolograms().setDistanceFromNPC(value);
                    npc.sendUpdatePackets(npc.getSpawnedPlayers(), false, false, false, false, true, false);
                    sender.sendMessage(translate(sender, "npc.commands.holo_height.changed", value));
                    return;
                } catch (NumberFormatException ignore) {
                }
            }

            sender.sendMessage("§cUse /laivynpc config holo-height (height)");
        }
    };

    public static @NotNull NPCConfiguration DISPLAY_NAME_CONFIG = new NPCConfiguration("display-name", "/laivynpc config display-name (name)") {
        @Override
        public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
            if (args.length > 0) {
                StringBuilder textBuilder = new StringBuilder(args[0]);
                for (int row = 1; row < args.length; row++) {
                    textBuilder.append(" ").append(args[row]);
                }
                String name = ChatColor.translateAlternateColorCodes('&', textBuilder.toString());

                npc.setCustomName(name);
                sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                return;
            }

            sender.sendMessage("§cUse " + getSyntax());
        }
    };

    //
    // Defaults
    //

    // Serializators
    /**
     * Ao serializar um NPC (envia-lo à config.yml), para evitar com que o valor "Type" fique com o nome do pacote inteiro da classe do seu NPC, basta registrar a sua classe aqui com: Value = Tipo
     * Exemplo ¹: Key: PlayerNPC.class, Value: Player
     * Exemplo ²: Key: AxolotlNPC.class, Value: Axolotl
     */
    public static final Map<@NotNull String, @NotNull Class<? extends NPC>> REGISTERED_NPCS_CLASSES = new LinkedHashMap<@NotNull String, @NotNull Class<? extends NPC>>() {{
        put("Player", PlayerNPC.class);
        put("Bat", BatNPC.class);
        put("Egg", EggNPC.class);
        put("Chicken", ChickenNPC.class);
        put("Pig", PigNPC.class);
        put("ArmorStand", ArmorStandNPC.class);
        put("Horse", HorseNPC.class);
        put("Cow", CowNPC.class);
        put("IronGolem", IronGolemNPC.class);
        put("Rabbit", RabbitNPC.class);
        put("Sheep", SheepNPC.class);
        put("Snowman", SnowmanNPC.class);
        put("Squid", SquidNPC.class);
        put("Wolf", WolfNPC.class);
        put("ItemFrame", ItemFrameNPC.class);
        put("LeashKnot", LeashKnotNPC.class);
        put("FallingBlock", FallingBlockNPC.class);
        put("Item", ItemNPC.class);
        put("EnderDragon", EnderDragonNPC.class);
        put("EnderSignal", EnderSignalNPC.class);
        put("Wither", WitherNPC.class);
        put("WitherSkull", WitherSkullNPC.class);
        put("Blaze", BlazeNPC.class);
        put("Creeper", CreeperNPC.class);
        put("Enderman", EndermanNPC.class);
        put("Ghast", GhastNPC.class);
        put("Zombie", ZombieNPC.class);
        put("Guardian", GuardianNPC.class);
        put("Silverfish", SilverfishNPC.class);
        put("Skeleton", SkeletonNPC.class);
        put("Slime", SlimeNPC.class);
        put("Spider", SpiderNPC.class);
        put("Witch", WitchNPC.class);
        put("Villager", VillagerNPC.class);
        put("Shulker", ShulkerNPC.class);
        put("PolarBear", PolarBearNPC.class);
        put("Vindicator", VindicatorNPC.class);
        put("Evoker", EvokerNPC.class);
        put("Vex", VexNPC.class);
        put("Llama", LlamaNPC.class);
        put("Illusioner", IllusionerNPC.class);
        put("Parrot", ParrotNPC.class);
        put("Dolphin", DolphinNPC.class);
        put("Drowned", ZombieDrownedNPC.class);
        put("Husk", ZombieHuskNPC.class);
        put("Zombie Villager", ZombieVillagerNPC.class);
        put("Skeleton Wither", SkeletonWitherNPC.class);
        put("Stray", SkeletonStrayNPC.class);
        put("Donkey", HorseDonkeyNPC.class);
        put("Mule", HorseMuleNPC.class);
        put("Horse Skeleton", HorseSkeletonNPC.class);
        put("Horse Zombie", HorseZombieNPC.class);
        put("Cod", CodNPC.class);
        put("Salmon", SalmonNPC.class);
        put("PufferFish", PufferfishNPC.class);
        put("TropicalFish", TropicalfishNPC.class);
        put("Phantom", PhantomNPC.class);
        put("Turtle", TurtleNPC.class);
        put("Cat", CatNPC.class);
        put("Boat", BoatNPC.class);
    }};

    @SuppressWarnings("unchecked")
    @ApiStatus.Internal
    public static @NotNull NPC loadFromConfig(@NotNull ConfigurationSection section) {
        try {
            String typeClass = (String) section.get("Type");
            Class<? extends NPC> npcClass;

            if (REGISTERED_NPCS_CLASSES.containsKey(typeClass)) {
                npcClass = REGISTERED_NPCS_CLASSES.get(typeClass);
            } else if (ReflectionUtils.getNullableClass(typeClass) != null) {
                npcClass = (Class<? extends NPC>) ReflectionUtils.getClass(typeClass);
            } else {
                throw new IllegalFormatFlagsException("Couldn't find this type of NPC \"" + typeClass + "\"");
            }

            // Verify if the method exists on the class
            if (MethodExecutor.hasMethodWithReturn(npcClass, "deserialize", npcClass, ConfigurationSection.class)) {
                MethodExecutor method = new MethodExecutor(new ClassExecutor(npcClass), new ClassExecutor(NPC.class), "deserialize", "Deserializes a NPC from the configuration ¹", new ClassExecutor(ConfigurationSection.class));
                method.load();

                return (NPC) Objects.requireNonNull(method.invokeStatic(new ObjectExecutor(section) {
                    @Override
                    public @NotNull ClassExecutor getClassExecutor() {
                        return new ClassExecutor(ConfigurationSection.class);
                    }
                }));
            } else {
                Class<?> clazz = npcClass;
                while (clazz.getSuperclass() != null) {
                    clazz = clazz.getSuperclass();

                    // Verify if the method exists on the super class otherwise
                    if (MethodExecutor.hasMethodWithReturn(clazz, "deserialize", clazz, ConfigurationSection.class)) {
                        MethodExecutor method = new MethodExecutor(new ClassExecutor(clazz), new ClassExecutor(NPC.class), "deserialize", "Deserializes a NPC from the configuration ²", new ClassExecutor(ConfigurationSection.class));
                        method.load();

                        return (NPC) Objects.requireNonNull(method.invokeStatic(new ObjectExecutor(section) {
                            @Override
                            public @NotNull ClassExecutor getClassExecutor() {
                                return new ClassExecutor(ConfigurationSection.class);
                            }
                        }));
                    }
                    //
                }

                throw new NoSuchMethodException("Couldn't find a deserialization method.");
            }
            //
        } catch (Exception e) {
            throw new RuntimeException("Problems during the configuration NPC's creation...", e);
        }
    }

    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = new LinkedHashMap<>();

        Map<String, Object> location = new LinkedHashMap<>();
        Map<String, Object> head = new LinkedHashMap<>();
        Map<String, Object> appearence = new LinkedHashMap<>();
        Map<String, Object> headRotation = new LinkedHashMap<>();
        Map<String, Object> equipments = new LinkedHashMap<>();
        Map<String, Object> interact = new LinkedHashMap<>();

        map.put("Id", getId());

        try {
            location.put("world", getLocation().getWorld().getName());
            location.put("x", getLocation().getX());
            location.put("y", getLocation().getY());
            location.put("z", getLocation().getZ());

            map.put("Location", location);

            if (getCustomName() != null) {
                map.put("Display name", getCustomName());
            }
            map.put("Display name visible", isCustomNameVisible());
        } catch (Exception e) {
            e.printStackTrace();
            laivynpc().log("§cCouldn't save the NPC's location and display name '" + getId() + "'!");
        }

        map.put("Hidden", isHidden());

        //
        // Head Rotation
        try {
            if (getHeadRotation() != null) {
                head.put("Interval", getHeadRotation().getInterval());
                head.put("Type", getHeadRotation().getEntityType().getName());
                map.put("Head", head);
            }
        } catch (Exception e) {
            e.printStackTrace();
            laivynpc().log("§cCouldn't save the head rotation pattern '" + getId() + "'!");
        }
        // Head Rotation
        //

        //
        // Hologram
        try {
            if (getHolograms().getLines().size() != 0) {
                map.put("Hologram", getHolograms().serialize());
            }
        } catch (Exception e) {
            e.printStackTrace();
            laivynpc().log("§cCouldn't save the NPC's holograms '" + getId() + "'!");
        }
        // Hologram
        //

        //
        // Serialize Interaction
        NPC.ClickAction click = getClickAction();
        try {
            if (click != NPC.BLANK_ACTION) {
                Method serialize = click.getClass().getMethod("serialize");
                Method deserialize = click.getClass().getMethod("deserialize", Map.class);

                // Verify if the methods are correct
                if (serialize.getReturnType() == Map.class && deserialize.getParameterTypes().length == 1 && deserialize.getParameterTypes()[0] == Map.class && deserialize.getReturnType() == click.getClass()) {
                    //noinspection unchecked
                    Map<String, Object> sMap = (Map<String, Object>) serialize.invoke(click);

                    interact.put("Class", click.getClass().getName());
                    interact.put("Current (do not touch)", sMap);
                    map.put("Interaction", interact);
                } else {
                    throw new NoSuchMethodException("The class needs to contains a 'serialize' method that returns Map<String, Object> and needs to contains a static method named 'deserialize' that returns the ClickAction and receives the param Map<String, Object>!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            laivynpc().log("Couldn't serialize this click type '" + click.getClass().getSimpleName() + "' of the NPC '" + getId() + "'!");
        }
        // Serialize Interaction
        //

        //
        // Appearance
        try {
            map.put("Appearance", appearence);
            appearence.put("Fire", isOnFire());

            Map<String, Object> glow = new LinkedHashMap<>();
            if (getGlowing() != null) {
                glow.put("Enabled", getGlowing().isActive());
                if (getGlowing().getRainbow() != null) {
                    glow.put("Rainbow", new LinkedHashMap<String, Object>() {{
                        put("Interval (ticks)", getGlowing().getRainbow().getUpdateTime());
                        put("Rainbow Colors", new LinkedList<String>() {{
                            for (EnumChatFormat obj : getGlowing().getRainbow().getColors()) {
                                add(obj.name());
                            }
                        }});
                    }});
                } else {
                    glow.put("Color", getGlowing().getColor().name());
                }
                appearence.put("Glow", glow);
            }
        } catch (Exception e) {
            e.printStackTrace();
            laivynpc().log("§cCouldn't save the appearance (fire/glowing) of the NPC '" + getId() + "'!");
        }
        // Appearance
        //

        try {
            map.put("View Distance", getViewDistance());
            map.put("Invisible", isInvisible());
        } catch (Exception e) {
            e.printStackTrace();
            laivynpc().log("§cCouldn't save the view-distance/invisibility of the NPC '" + getId() + "'!");
        }

        //
        // Animation
        try {
            // Head Rotation
            map.put("Head Rotation", headRotation);
            headRotation.put("Pitch", getLocation().getPitch());
            headRotation.put("Yaw", getLocation().getYaw());
            //

            ItemStack item;
            if ((item = getItem(EnumItemSlotEnum.EnumItemSlot.HEAD)) != null) equipments.put("Head", item.serialize());
            if ((item = getItem(EnumItemSlotEnum.EnumItemSlot.CHEST)) != null) equipments.put("Chest", item.serialize());
            if ((item = getItem(EnumItemSlotEnum.EnumItemSlot.LEGS)) != null) equipments.put("Legs", item.serialize());
            if ((item = getItem(EnumItemSlotEnum.EnumItemSlot.FEET)) != null) equipments.put("Feet", item.serialize());
            if ((item = getItem(EnumItemSlotEnum.EnumItemSlot.MAINHAND)) != null) equipments.put("Main Hand", item.serialize());
            if ((item = getItem(EnumItemSlotEnum.EnumItemSlot.OFFHAND)) != null) equipments.put("Off Hand", item.serialize());

            map.put("Equipments", equipments);

//            if (ReflectionUtils.isCompatible(V1_14_R1.class)) {
//                map.put("Pose", getPose().name());
//            }
        } catch (Exception e) {
            e.printStackTrace();
            laivynpc().log("§cCouldn't save the animations (items, poses, rotations, collisions) of the NPC '" + getId() + "'!");
        }
        // Animation
        //

        //
        // Type
        try {
            // TODO: 16/08/2022 Um sistema melhor para coletar o nome da classe de um NPC
            String type = getClass().getName();
            if (REGISTERED_NPCS_CLASSES.containsValue(getClass())) {
                for (Map.Entry<String, Class<? extends NPC>> forMap : REGISTERED_NPCS_CLASSES.entrySet()) {
                    if (forMap.getValue().equals(getClass())) {
                        type = forMap.getKey();
                        break;
                    }
                }
            }
            map.put("Type", type);
        } catch (Exception e) {
            e.printStackTrace();
            laivynpc().log("§cCouldn't save NPC type '" + getId() + "'!");
        }
        // Type
        //

        map.put("Collidable", isCollidable());

        return map;
    }

    public void load(@NotNull ConfigurationSection map) {
        int id = NPC.getNextNpcId();
        if (map.contains("Id")) {
            id = map.getInt("Id");
        }

        this.id = id;

        try {
            MemorySection locationMap = (MemorySection) map.get("Location");

            World world = Bukkit.getWorld((String) locationMap.get("world"));
            double x = (double) locationMap.get("x");
            double y = (double) locationMap.get("y");
            double z = (double) locationMap.get("z");

            if (world == null) {
                throw new NullPointerException("Couldn't load the world '" + locationMap.get("world") + "'");
            }

            Location location = new Location(world, x, y, z);
            setLocation(location, true);

            if (map.contains("Display name")) {
                setCustomName(map.getString("Display name"));
            }
            setCustomNameVisible(map.getBoolean("Display name visible"));
        } catch (Exception e) {
            e.printStackTrace();
            laivynpc().log("§cCouldn't load the location of the NPC '" + getId() + "'!");
        }

        setHidden(map.getBoolean("Hidden"));

        if (map.contains("Head")) {
            try {
                ConfigurationSection head = map.getConfigurationSection("Head");
                int interval = head.getInt("Interval");
                String type = head.getString("Type");
                Class<? extends org.bukkit.entity.Entity> entityClass;

                try {
                    //noinspection unchecked
                    entityClass = (Class<? extends org.bukkit.entity.Entity>) ReflectionUtils.getNullableClass(type);
                    if (entityClass == null) {
                        throw new NullPointerException("Couldn't load this entity's class of the head rotation pattern of the NPC '" + getId() + "'!");
                    }
                } catch (ClassCastException ignore2) {
                    throw new ClassCastException("This class isn't a entity class of head rotation pattern of the NPC '" + getId() + "'!");
                }

                setHeadRotation(new NPCHeadRotation(this, interval, entityClass));
            } catch (Exception e) {
                e.printStackTrace();
                laivynpc().log("§cCouldn't load the head rotation pattern of the NPC '" + getId() + "'!");
            }
        }

        //
        // Appearance
        try {
            ConfigurationSection appearence = map.getConfigurationSection("Appearance");

            if (appearence.contains("Glow")) {
                try {
                    ConfigurationSection glow = appearence.getConfigurationSection("Glow");

                    boolean enabled = glow.getBoolean("Enabled");

                    EnumChatFormatEnum enumClass = EnumChatFormatEnum.getInstance();
                    if (glow.contains("Rainbow")) {
                        int interval = glow.getInt("Interval (ticks)");
                        EnumChatFormat[] colors = new EnumChatFormat[glow.getList("Rainbow Colors").size()];

                        int row = 0;
                        for (Object color : glow.getList("Rainbow Colors")) {
                            colors[row] = new EnumChatFormat(EnumChatFormatEnum.getInstance().valueOf(color.toString()).getValue());
                            row++;
                        }

                        setGlowing(new GlowingStatus(this, EnumChatFormatEnum.WHITE(), enabled) {{
                            setRainbow(new Rainbow(interval, colors));
                        }});
                    } else {
                        EnumChatFormat color = new EnumChatFormat(enumClass.valueOf(glow.getString("Color").toUpperCase()).getValue());
                        setGlowing(new GlowingStatus(this, color, enabled));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    laivynpc().log("§cCouldn't load the glowing of the NPC '" + getId() + "'!");
                }
            }

            setOnFire(appearence.getBoolean("Fire"));
        } catch (Exception e) {
            e.printStackTrace();
            laivynpc().log("§cCouldn't load the NPC appearance of the NPC '" + getId() + "'!");
        }
        // Appearence
        //

        try {
            setViewDistance(map.getInt("View Distance"));
            setInvisible(map.getBoolean("Invisible"));
        } catch (Exception e) {
            e.printStackTrace();
            laivynpc().log("§cCouldn't load the secondary options (view-distance/invisibility) of the NPC '" + getId() + "'!");
        }

        //
        // Deserialize Interaction
        try {
            if (map.contains("Interaction")) {
                ConfigurationSection secInteraction = map.getConfigurationSection("Interaction");
                ConfigurationSection secCurrent = secInteraction.getConfigurationSection("Current (do not touch)");

                //noinspection unchecked
                Class<? extends ClickType> c = (Class<? extends ClickType>) ReflectionUtils.getNullableClass(secInteraction.getString("Class"));

                if (c != null) {
                    try {
                        //noinspection JavaReflectionMemberAccess
                        Method method = c.getDeclaredMethod("deserialize", Map.class);
                        NPC.ClickAction click = (NPC.ClickAction) method.invoke(null, secCurrent.getValues(true));

                        setClickAction(click);
                    } catch (NoSuchMethodException ignore) {
                        throw new NullPointerException("The class '" + c.getName() + "' needs to have a 'deserialize' method with the param 'Map<String, Object>' to deserialization!");
                    }
                } else {
                    throw new NullPointerException("Couldn't find the class '" + (secCurrent.getString("Class")) + "' when tried to deserialize the ClickAction!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            laivynpc().log("§cCouldn't load the NPC's click type of the NPC '" + getId() + "'!");
        }
        // Deserialize Interaction
        //

        //
        // Hologram
        try {
            ConfigurationSection hologram = map.getConfigurationSection("Hologram");
            holograms = NPCHolograms.deserialize(this, hologram.getValues(true));
        } catch (Exception e) {
            e.printStackTrace();
            laivynpc().log("§cCouldn't load the holograms of the NPC '" + getId() + "'!");
        }
        // Hologram
        //

        // Poses
        // TODO: 24/12/2022 1.14 poses
//        if (ReflectionUtils.isCompatible(V1_14_R1.class)) {
//            try {
//                EntityPose pose = EntityPose.valueOf(map.getString("Pose"));
//                setPose(pose);
//            } catch (Exception e) {
//                e.printStackTrace();
//                laivynpc().log("§cCouldn't load the NPC poses of the NPC '" + getId() + "'!");
//            }
//        }
        //

        //
        // Head Rotation
        try {
            ConfigurationSection headRotation = map.getConfigurationSection("Head Rotation");
            setHeadRotation((float) headRotation.getDouble("Yaw"), (float) headRotation.getDouble("Pitch"));
        } catch (Exception e) {
            e.printStackTrace();
            laivynpc().log("§cCouldn't load head rotation of npc '" + getId() + "'!");
        }
        // Head Rotation
        //

        //
        // Equipments
        try {
            ConfigurationSection equipments = map.getConfigurationSection("Equipments");

            if (equipments.contains("Head")) setItem(EnumItemSlotEnum.EnumItemSlot.HEAD, ItemStack.deserialize(equipments.getConfigurationSection("Head").getValues(true)));
            if (equipments.contains("Chest")) setItem(EnumItemSlotEnum.EnumItemSlot.CHEST, ItemStack.deserialize(equipments.getConfigurationSection("Chest").getValues(true)));
            if (equipments.contains("Legs")) setItem(EnumItemSlotEnum.EnumItemSlot.LEGS, ItemStack.deserialize(equipments.getConfigurationSection("Legs").getValues(true)));
            if (equipments.contains("Feet")) setItem(EnumItemSlotEnum.EnumItemSlot.FEET, ItemStack.deserialize(equipments.getConfigurationSection("Feet").getValues(true)));
            if (equipments.contains("Main Hand")) setItem(EnumItemSlotEnum.EnumItemSlot.MAINHAND, ItemStack.deserialize(equipments.getConfigurationSection("Main Hand").getValues(true)));
            if (equipments.contains("Off Hand")) setItem(EnumItemSlotEnum.EnumItemSlot.OFFHAND, ItemStack.deserialize(equipments.getConfigurationSection("Off Hand").getValues(true)));
        } catch (Exception e) {
            e.printStackTrace();
            laivynpc().log("§cCouldn't load the items of the NPC '" + getId() + "'!");
        }

        if (ReflectionUtils.isCompatible(V1_9_R1.class)) {
            if (map.contains("Collidable")) {
                setCollidable(map.getBoolean("Collidable"));
            }
        }
    }
    //

}
