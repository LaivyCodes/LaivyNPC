package codes.laivy.npc.types.player;

import codes.laivy.npc.exceptions.NPCIllegalSkinException;
import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcher;
import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.Parrot;
import codes.laivy.npc.mappings.defaults.classes.entity.player.EntityPlayer;
import codes.laivy.npc.mappings.defaults.classes.enums.*;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.GameProfile;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.Property;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.PropertyMap;
import codes.laivy.npc.mappings.defaults.classes.others.objects.PlayerConnection;
import codes.laivy.npc.mappings.defaults.classes.packets.IPacket;
import codes.laivy.npc.mappings.defaults.classes.packets.info.action.IPlayerInfoAction;
import codes.laivy.npc.mappings.defaults.classes.scoreboard.Scoreboard;
import codes.laivy.npc.mappings.defaults.classes.scoreboard.ScoreboardTeam;
import codes.laivy.npc.mappings.versions.V1_12_R1;
import codes.laivy.npc.mappings.versions.V1_16_R3;
import codes.laivy.npc.mappings.versions.V1_9_R1;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.commands.NPCConfiguration;
import codes.laivy.npc.types.utils.NPCHeadRotation;
import codes.laivy.npc.utils.ReflectionUtils;
import codes.laivy.npc.utils.SkinUtils;
import codes.laivy.npc.utils.UUIDUtils;
import codes.laivy.npc.utils.Validation;
import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemorySection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static codes.laivy.npc.LaivyNPC.laivynpc;
import static codes.laivy.npc.config.Translate.translate;

public class PlayerNPC extends NPC {

    private @NotNull Skin skin = Skin.DEFAULT_SKIN;
    private final @NotNull UUID uniqueId;

    protected @NotNull EntityPlayer player;

    // Appearance
    private boolean showOnTablist = false;
    //

    public static void debug(@NotNull Location location) {
        if (ReflectionUtils.isCompatible(V1_16_R3.class)) {
            Bukkit.getScheduler().runTask(laivynpc(), () -> {
                PlayerNPC npc = new PlayerNPC(new ArrayList<>(), location);
                npc.debug();
                npc.destroy();
            });
        } else {
            PlayerNPC npc = new PlayerNPC(new ArrayList<>(), location);
            npc.debug();
            npc.destroy();
        }
    }

    @Override
    protected void debug() {
        super.debug();
        setSkin("ItsLaivy");
        setTablistName("ItsLaivy");

        if (ReflectionUtils.isCompatible(V1_12_R1.class)) {
            Parrot parrot1 = (Parrot) laivynpc().getVersion().createEntity(Entity.EntityType.PARROT, getLocation());
            Parrot parrot2 = (Parrot) laivynpc().getVersion().createEntity(Entity.EntityType.PARROT, getLocation());

            parrot1.setVariant(Parrot.Variant.RED);
            parrot2.setVariant(Parrot.Variant.GRAY);

            setEntityShoulder(Shoulder.LEFT, parrot1);
            setEntityShoulder(Shoulder.RIGHT, parrot2);
        }

        setShowOnTablist(true);
    }

    public PlayerNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(NPC.getNextNpcId(), players, UUID.randomUUID(), location);
    }

    public PlayerNPC(@NotNull List<OfflinePlayer> players, @Nullable UUID uniqueId, @NotNull Location npcLocation) {
        this(NPC.getNextNpcId(), players, uniqueId, npcLocation);
    }
    public PlayerNPC(int id, @NotNull List<OfflinePlayer> players, @Nullable UUID uniqueId, @NotNull Location npcLocation) {
        super(id, players, npcLocation);
        if (ReflectionUtils.isCompatible(V1_16_R3.class) && !Bukkit.isPrimaryThread()) {
            throw new UnsupportedOperationException("The player NPC couldn't be created async at 1.16.5+");
        }

        if (uniqueId == null) this.uniqueId = UUIDUtils.getRandomUniqueId();
        else this.uniqueId = uniqueId;

        getHolograms().setDistanceFromNPC(-0.25D);

        player = getNewEntity();
    }

    public @Nullable Parrot.Variant getEntityShoulder(@NotNull Shoulder shoulder) {
        return getEntity().getEntityShoulder(shoulder);
    }
    public void setEntityShoulder(@NotNull Shoulder shoulder, @Nullable Parrot entity) {
        getEntity().setEntityShoulder(shoulder, entity);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    protected @NotNull EntityPlayer getNewEntity() {
        EntityPlayer player = laivynpc().getVersion().createPlayer(laivynpc().getVersion().createGameProfile(getUniqueId(), generateRandomName()), getLocation());
        if (isShowOnTablist()) {
            player.setTablistName(getTablistName());
        }
        return player;
    }
    @ApiStatus.Internal
    @ApiStatus.Experimental
    protected @NotNull String generateRandomName() {
        // TODO: 09/06/2023 Change this
        return String.valueOf(hashCode());
    }

    public @NotNull Skin getSkin() {
        return skin;
    }
    public void setSkin(@NotNull String name) {
        new Thread(() -> {
            try {
                String[] strings = SkinUtils.getSkinFromName(name);
                setSkin(new Skin(strings[0], strings[1], name));
            } catch (NPCIllegalSkinException ignore) {
            }
        }).start();
    }
    public void setSkin(@NotNull Skin skin) {
        this.skin = skin;

        Bukkit.getScheduler().runTask(laivynpc(), () -> {
            try {
                Set<UUID> uuids = getSpawnedPlayers();

                boolean spawned = isSpawnedForSomeone();
                reCreate();

                PropertyMap propertyMap = getProfile().getProperties();
                Property property = Property.createTextureProperty(skin.getTexture(), skin.getSignature());
                propertyMap.put("textures", property);

                visiblePlayers = uuids;

                if (spawned) spawn();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public @NotNull UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    public @NotNull EntityPlayer getEntity() {
        return player;
    }

    public void reCreate() {
        hide();
        player = getNewEntity();
    }

    // TODO: 17/02/2023 Recreate whole spawn system
    @Override
    public void spawn(@NotNull Player player) {
        setCanSpawn(true);

        Bukkit.getScheduler().runTaskLater(laivynpc(), () -> {
            if (canSpawn()) {
                ReflectionUtils.sendPacketToPlayer(getSpawnPackets(player), player);

                if (!getSpawnedPlayers().contains(player.getUniqueId())) {
                    getSpawnedPlayers().add(player.getUniqueId());
                    getHolograms().hideHolograms(Collections.singletonList(player));
                    ReflectionUtils.sendPacketToPlayer(getHologramsUpdatePackets(player), player);
                }

                sendUpdatePackets(player, true, true, false, true, false, true);

                NPCHeadRotation current = getHeadRotation();
                if (current != null) setHeadRotation(new NPCHeadRotation(this, current.getInterval(), current.getEntityType()));
            }

            Bukkit.getScheduler().runTaskLater(laivynpc(), () -> {
                ReflectionUtils.sendPacketToPlayer(removeFromTablist(), player);
            }, 20);
        }, 20);
    }

    public void sendUpdatePackets(@NotNull Player player, boolean scoreboard, boolean equipments, boolean metadata, boolean location, final boolean holograms, boolean movement) {
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

    @Override
    public void hide(@NotNull Player player) {
        Validation.isTrue(isDestroyed(), new IllegalStateException("This NPC is destroyed, you need to recreate it."));

        getSpawnedPlayers().remove(player.getUniqueId());

        ReflectionUtils.sendPacketToPlayer(getDestroyPackets(player), player);
        getHolograms().hideHolograms(Collections.singletonList(player));
    }

    public boolean isShowOnTablist() {
        return showOnTablist;
    }
    public void setShowOnTablist(boolean showOnTablist) {
        if (showOnTablist && getTablistName() == null) {
            throw new NullPointerException("The tablist name cannot be null in that case!");
        }

        this.showOnTablist = showOnTablist;
    }

    @Nullable
    public String getTablistName() {
        return getEntity().getTablistName();
    }
    public void setTablistName(@Nullable String tablistName) {
        getEntity().setTablistName(tablistName);
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();

        list.add(SKIN_CONFIG);
        list.add(TABLIST_CONFIG);

        if (ReflectionUtils.isCompatible(V1_12_R1.class)) {
            list.add(PARROT_CONFIG);
        }

        return list;
    }

    @NotNull
    public GameProfile getProfile() {
        return getEntity().getProfile();
    }

    @NotNull
    private List<IPacket> removeFromTablist() {
        List<IPacket> packets = new LinkedList<>();

        if (!isShowOnTablist()) {
            IPacket packet = laivynpc().getVersion().createPlayerInfoPacket(IPlayerInfoAction.REMOVE_PLAYER(), getEntity());
            packets.add(packet);
        }

        return packets;
    }

    @Override
    public @NotNull List<IPacket> getSpawnPackets(@NotNull Player player) {
        List<@NotNull IPacket> packets = new LinkedList<>();

        if (!getSpawnedPlayers().contains(player.getUniqueId())) {
            // Update add player packet
            IPacket packet = laivynpc().getVersion().createPlayerInfoPacket(IPlayerInfoAction.ADD_PLAYER(), getEntity());
            packets.add(packet);
            // Named spawn
            packets.add(laivynpc().getVersion().createSpawnNamedPacket(getEntity()));
        }

        // Update displayName packet
        IPacket packet = laivynpc().getVersion().createPlayerInfoPacket(IPlayerInfoAction.UPDATE_DISPLAY_NAME(), getEntity());
        packets.add(packet);
        //

        // TODO: 26/12/2022 1.14 poses
//        if (getPose() == EntityPose.CROUCHING) setPose(EntityPose.CROUCHING);
//        else if (getPose() == EntityPose.FALL_FLYING) setPose(EntityPose.FALL_FLYING);
//        else if (getPose() == EntityPose.SWIMMING) setPose(EntityPose.SWIMMING);

        packets.addAll(getMetadataUpdatePackets(player));

        return packets;
    }
    @Override
    public @NotNull List<IPacket> getDestroyPackets(@NotNull Player player) {
        List<@NotNull IPacket> packets = new LinkedList<>();

        IPacket packet = laivynpc().getVersion().createPlayerInfoPacket(IPlayerInfoAction.REMOVE_PLAYER(), getEntity());
        packets.add(packet);

        packets.addAll(laivynpc().getVersion().createDestroyPacket(getEntity()));
        return packets;
    }

    @Override
    public @NotNull List<IPacket> getMetadataUpdatePackets(@NotNull Player player) {
        List<IPacket> packets = new LinkedList<>();

        try {
            DataWatcher watcher = getEntity().getDataWatcher();

            boolean isGlowing = false;
            if (getGlowing() != null) {
                isGlowing = getGlowing().isActive();
            }

            // Fire and Glowing
            //noinspection DataFlowIssue
            byte b = (byte) watcher.get(0);

            if (isGlowing) b = (byte) (b | 0x40);
            else b = (byte) (b & ~(0x40));

            if (isOnFire()) b = (byte) (b | 0x01);
            else b = (byte) (b & ~(0x01));

            watcher.set(0, b);
            //

            // Skin Parts
            b = 0;
            Skin.Parts parts = getSkin().getParts();
            if (parts.hasCape()) b = (byte) (b | 0x1);
            if (parts.hasJacket()) b = (byte) (b | 0x2);
            if (parts.hasLeftSleeve()) b = (byte) (b | 0x4);
            if (parts.hasRightSleeve()) b = (byte) (b | 0x8);
            if (parts.hasLeftPants()) b = (byte) (b | 0x10);
            if (parts.hasRightPants()) b = (byte) (b | 0x20);
            if (parts.hasHat()) b = (byte) (b | 0x40);
            //

            // TODO: 12/06/2023 DataWatcherObject
            watcher.set((int) laivynpc().getVersion().getObject("Metadata:Player:SkinParts"), b);
            packets.add(laivynpc().getVersion().createMetadataPacket(getEntity(), watcher, true));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return packets;
    }
    @Override
    public @NotNull List<IPacket> getScoreboardUpdatePackets(@NotNull Player player) {
        List<IPacket> packets = new LinkedList<>();

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

            ScoreboardTeam team = scoreboard.getTeam(getProfile().getName());
            if (team == null) {
                team = laivynpc().getVersion().createScoreboardTeam(scoreboard, getProfile().getName());
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return packets;
    }
    @Override
    public @NotNull List<@NotNull IPacket> getEquipmentsUpdatePackets(@NotNull Player player) {
        if (!getEquipments().isEmpty()) {
            return new LinkedList<>(laivynpc().getVersion().createEquipmentPacket(getEntity(), getEquipments()));
        }
        return new LinkedList<>(laivynpc().getVersion().createEquipmentPacket(getEntity(), new HashMap<EnumItemSlotEnum.EnumItemSlot, ItemStack>() {{
            put(EnumItemSlotEnum.EnumItemSlot.HEAD, new ItemStack(Material.AIR));
            put(EnumItemSlotEnum.EnumItemSlot.CHEST, new ItemStack(Material.AIR));
            put(EnumItemSlotEnum.EnumItemSlot.LEGS, new ItemStack(Material.AIR));
            put(EnumItemSlotEnum.EnumItemSlot.FEET, new ItemStack(Material.AIR));
            put(EnumItemSlotEnum.EnumItemSlot.MAINHAND, new ItemStack(Material.AIR));
            if (ReflectionUtils.isCompatible(V1_9_R1.class)) {
                put(EnumItemSlotEnum.EnumItemSlot.OFFHAND, new ItemStack(Material.AIR));
            }
        }}));
    }

    //
    // Defaults
    //

    @NotNull
    public static NPCConfiguration SKIN_CONFIG = new NPCConfiguration("skin", "/laivynpc config skin (name)") {
        @Override
        public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
            if (args.length > 0) {
                PlayerNPC playerNPC = (PlayerNPC) npc;
                playerNPC.setSkin(args[0]);
                sender.sendMessage(translate(sender, "npc.player.skin.changed", args[0]));
            } else {
                sender.sendMessage("§cUse /laivynpc config skin (name)");
            }
        }
    };

    @NotNull
    public static NPCConfiguration PARROT_CONFIG = new NPCConfiguration("parrot", "/laivynpc config parrot (right/left) (variant)") {
        @Override
        public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
            PlayerNPC playerNPC = (PlayerNPC) npc;

            if (!ReflectionUtils.isCompatible(V1_12_R1.class)) {
                throw new IllegalStateException("This command is only available since 1.12!");
            }

            if (args.length >= 1) {
                Shoulder position;
                try {
                    position = Shoulder.valueOf(args[0].toUpperCase());
                } catch (IllegalArgumentException ignore) {
                    StringBuilder builder = new StringBuilder();
                    int row = 0;
                    for (Shoulder shoulder : Shoulder.values()) {
                        if (row != 0) builder.append("§7, ");
                        builder.append("§f").append(shoulder.name());
                        row++;
                    }

                    sender.sendMessage("§cUse " + getSyntax());
                    sender.sendMessage(translate(sender, "npc.commands.general.available_options", builder));
                    return;
                }

                if (args.length > 1) {
                    Parrot.Variant variant;
                    try {
                        variant = Parrot.Variant.valueOf(args[1].toUpperCase());
                    } catch (IllegalArgumentException ignore) {
                        StringBuilder builder = new StringBuilder();
                        int row = 0;
                        for (Parrot.Variant variantE : Parrot.Variant.values()) {
                            if (row != 0) builder.append("§7, ");
                            builder.append("§f").append(variantE.name());
                            row++;
                        }

                        sender.sendMessage("§cUse " + getSyntax());
                        sender.sendMessage(translate(sender, "npc.commands.general.available_options", builder));
                        return;
                    }

                    Parrot parrot = (Parrot) laivynpc().getVersion().createEntity(Entity.EntityType.PARROT, playerNPC.getLocation());
                    parrot.setVariant(variant);
                    parrot.setLocation(npc.getLocation());

                    playerNPC.setEntityShoulder(position, parrot);
                } else {
                    playerNPC.setEntityShoulder(position, null);
                }

                sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
            } else {
                sender.sendMessage("§cUse " + getSyntax());
            }
        }
    };

    @NotNull
    public static NPCConfiguration TABLIST_CONFIG = new NPCConfiguration("tablist", "/laivynpc config tablist (name)") {
        @Override
        public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
            PlayerNPC playerNPC = (PlayerNPC) npc;

            if (args.length > 0) {
                StringBuilder name = new StringBuilder();
                for (int row = 0; row < args.length; row++) {
                    name.append(args[row]);
                    if (row + 1 != args.length) name.append(" ");
                }

                String colouredName = ChatColor.translateAlternateColorCodes('&', name.toString());

                playerNPC.setTablistName(colouredName);
                playerNPC.setShowOnTablist(true);
                sender.sendMessage(translate(sender, "npc.player.tablist.changed", colouredName + "§f"));
            } else {
                playerNPC.setShowOnTablist(false);
                sender.sendMessage(translate(sender, "npc.player.tablist.removed"));
            }
            playerNPC.respawn();
        }
    };

    //
    // Defaults
    //

    // Serializators
    @Override
    public void load(@NotNull ConfigurationSection map) {
        super.load(map);

        ConfigurationSection playerNpc;
        ConfigurationSection skin = null;
        ConfigurationSection parts = null;

        try {
            playerNpc = map.getConfigurationSection("PlayerNPC Configuration");
            if (playerNpc.contains("Skin")) {
                skin = playerNpc.getConfigurationSection("Skin");
                parts = skin.getConfigurationSection("Parts");
            }
        } catch (Exception e) {
            e.printStackTrace();
            laivynpc().log("§cCouldn't load the skin of the PlayerNPC '" + getId() + "'!");
            return;
        }

        try {
            boolean shownTablist = playerNpc.getBoolean("Shown on Tablist");
            String tablistName = null;
            if (playerNpc.contains("Tablist name (if shown)")) {
                tablistName = playerNpc.getString("Tablist name (if shown)");
            }

            setTablistName(tablistName);
            setShowOnTablist(tablistName != null && shownTablist);
        } catch (Exception e) {
            e.printStackTrace();
            laivynpc().log("§cCouldn't save the tablist configurations of the PlayerNPC '" + getId() + "'!");
        }

        try {
            if (skin != null) {
                if (skin.contains("Nickname")) {
                    String nickname = skin.getString("Nickname");
                    setSkin(nickname);
                } else {
                    String texture = skin.getString("Texture");
                    String signature = skin.getString("Signature");
                    setSkin(new Skin(texture, signature, null));
                }

                Skin skinInstance = getSkin();
                skinInstance.getParts().setCape(parts.getBoolean("hasCape"));
                skinInstance.getParts().setHat(parts.getBoolean("hasHat"));
                skinInstance.getParts().setJacket(parts.getBoolean("hasJacket"));
                skinInstance.getParts().setLeftPants(parts.getBoolean("hasLeftPants"));
                skinInstance.getParts().setRightPants(parts.getBoolean("hasRightPants"));
                skinInstance.getParts().setLeftSleeve(parts.getBoolean("hasLeftSleeve"));
                skinInstance.getParts().setRightSleeve(parts.getBoolean("hasRightSleeve"));

                setSkin(skinInstance);
            }
        } catch (Exception e) {
            e.printStackTrace();
            laivynpc().log("§cCouldn't load the skin of the PlayerNPC '" + getId() + "'!");
        }

        try {
            if (ReflectionUtils.isCompatible(V1_12_R1.class)) {
                V1_12_R1 version = (V1_12_R1) laivynpc().getVersion();

                if (playerNpc.contains("Entity Shoulder")) {
                    ConfigurationSection parrotsSec = playerNpc.getConfigurationSection("Entity Shoulder");

                    for (Map.Entry<String, Object> parrots : parrotsSec.getValues(false).entrySet()) {
                        String variantStr = (String) parrots.getValue();
                        Parrot.Variant variant = Parrot.Variant.valueOf(variantStr);

                        // Create parrot
                        @NotNull Parrot parrot = (Parrot) version.createEntity(Entity.EntityType.PARROT, getLocation());
                        parrot.setVariant(variant);
                        parrot.setLocation(getLocation());
                        // Define parrot
                        System.out.println("Sent: '" + parrots.getKey() + "', '" + parrot.getVariant() + "'");

                        Bukkit.getScheduler().runTaskLater(laivynpc(), () -> {
                            setEntityShoulder(Shoulder.valueOf(parrots.getKey()), parrot);
                        }, 50);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            laivynpc().log("§cCouldn't load shoulders entities from PlayerNPC '" + getId() + "'!");
        }

        try {
            respawn();
            sendUpdatePackets(getVisiblePlayers(), true, true, true, true, true, true);
        } catch (Exception e) {
            e.printStackTrace();
            laivynpc().log("§cCouldn't respawn of the PlayerNPC '" + getId() + "' when tried to load!");
        }
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> map = super.serialize();
        Map<String, Object> playerNPC = new TreeMap<>();
        Map<String, Object> skin = new TreeMap<>();
        Map<String, Object> partsMap = new TreeMap<>();
        Map<String, Object> parrotMap = new TreeMap<>();

        map.put("PlayerNPC Configuration", playerNPC);
        playerNPC.put("UUID", getUniqueId().toString());

        try {
            playerNPC.put("Shown on Tablist", isShowOnTablist());
            if (getTablistName() != null) {
                playerNPC.put("Tablist name (if shown)", getTablistName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            laivynpc().log("§cCouldn't serialize the pose/tablist of the PlayerNPC '" + getId() + "'!");
        }

        try {
            Skin.Parts parts = getSkin().getParts();
            if (getSkin() != Skin.DEFAULT_SKIN) {
                playerNPC.put("Skin", skin);

                Skin cSkin = getSkin();
                if (cSkin.getNickname() != null) {
                    skin.put("Nickname", cSkin.getNickname());
                } else {
                    skin.put("Texture", cSkin.getTexture());
                    skin.put("Signature", cSkin.getSignature());
                }

                skin.put("Parts", partsMap);
                partsMap.put("hasCape", parts.hasCape());
                partsMap.put("hasHat", parts.hasHat());
                partsMap.put("hasJacket", parts.hasJacket());
                partsMap.put("hasLeftPants", parts.hasLeftPants());
                partsMap.put("hasRightPants", parts.hasRightPants());
                partsMap.put("hasLeftSleeve", parts.hasLeftSleeve());
                partsMap.put("hasRightSleeve", parts.hasRightSleeve());
            }
        } catch (Exception e) {
            e.printStackTrace();
            laivynpc().log("§cCouldn't save the skin of the PlayerNPC '" + getId() + "'!");
        }

        try {
            if (ReflectionUtils.isCompatible(V1_12_R1.class)) {
                Parrot.@Nullable Variant left = getEntityShoulder(Shoulder.LEFT);
                Parrot.@Nullable Variant right = getEntityShoulder(Shoulder.RIGHT);

                if (left != null || right != null) {
                    playerNPC.put("Entity Shoulder", parrotMap);
                    if (left != null) {
                        parrotMap.put(Shoulder.LEFT.name(), left.name());
                    }
                    if (right != null) {
                        parrotMap.put(Shoulder.RIGHT.name(), right.name());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            laivynpc().log("§cCouldn't save the shoulders entities from the PlayerNPC '" + getId() + "'!");
        }

        return map;
    }

    @NotNull
    public static PlayerNPC deserialize(@NotNull ConfigurationSection map) {
        try {
            ConfigurationSection playerNpc = map.getConfigurationSection("PlayerNPC Configuration");
            MemorySection locationMap = (MemorySection) map.get("Location");

            int id = NPC.getNextNpcId();
            if (map.contains("Id")) {
                id = map.getInt("Id");
            }

            World world = Bukkit.getWorld((String) locationMap.get("world"));
            double x = (double) locationMap.get("x");
            double y = (double) locationMap.get("y");
            double z = (double) locationMap.get("z");

            if (world != null) {
                Location location = new Location(world, x, y, z);

                UUID uuid = UUIDUtils.getRandomUniqueId();
                if (!playerNpc.getString("UUID").equals("")) {
                    uuid = UUID.fromString(playerNpc.getString("UUID"));
                }

                PlayerNPC npc = new PlayerNPC(id, new ArrayList<>(), uuid, location);
                npc.setCanSpawn(true);
                npc.load(map);
                return npc;
            } else {
                throw new NullPointerException("Couldn't get the world of this PlayerNPC");
            }
        } catch (Exception e) {
            throw new RuntimeException("PlayerNPC's deserialization", e);
        }
    }
    //
}
