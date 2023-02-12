package codes.laivy.npc.types.player;

import codes.laivy.npc.exceptions.NPCIllegalSkinException;
import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcher;
import codes.laivy.npc.mappings.defaults.classes.entity.EntityPlayer;
import codes.laivy.npc.mappings.defaults.classes.enums.*;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.GameProfile;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.Property;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.PropertyMap;
import codes.laivy.npc.mappings.defaults.classes.packets.Packet;
import codes.laivy.npc.mappings.defaults.classes.scoreboard.Scoreboard;
import codes.laivy.npc.mappings.defaults.classes.scoreboard.ScoreboardTeam;
import codes.laivy.npc.mappings.versions.V1_9_R1;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.commands.NPCConfiguration;
import codes.laivy.npc.types.utils.NPCHeadRotation;
import codes.laivy.npc.utils.ReflectionUtils;
import codes.laivy.npc.utils.SkinUtils;
import codes.laivy.npc.utils.UUIDUtils;
import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemorySection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static codes.laivy.npc.LaivyNPC.laivynpc;
import static codes.laivy.npc.config.Translate.translate;

public class PlayerNPC extends NPC {

    private @NotNull PlayerNPCSkin skin = PlayerNPCSkin.DEFAULT_SKIN;
    private final @NotNull UUID uniqueId;

    protected @NotNull EntityPlayer player;

    // Appearance
    private boolean showOnTablist = false;
    private String tablistName = null;
    //

    public static void debug(@NotNull Location location) {
        PlayerNPC npc = new PlayerNPC(new ArrayList<>(), location);
        npc.debug();
        npc.destroy();
    }

    @Override
    protected void debug() {
        super.debug();
        setSkin("ItsLaivy");
        setTablistName("ItsLaivy");
        setShowOnTablist(true);
    }

    public PlayerNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(players, UUID.randomUUID(), location);
    }
    public PlayerNPC(@NotNull List<OfflinePlayer> players, @Nullable UUID uniqueId, @NotNull Location npcLocation) {
        super(players, npcLocation);

        if (uniqueId == null) this.uniqueId = UUIDUtils.genRandomUUID();
        else this.uniqueId = uniqueId;

        getHolograms().setDistanceFromNPC(-0.25D);

        player = getNewEntity();
    }

    private @NotNull EntityPlayer getNewEntity() {
        return laivynpc().getVersion().createPlayer(laivynpc().getVersion().createGameProfile(getUniqueId(), ""), getLocation());
    }

    public @NotNull PlayerNPCSkin getSkin() {
        return skin;
    }
    public void setSkin(@NotNull String name) {
        new Thread(() -> {
            try {
                String[] strings = SkinUtils.getSkinFromName(name);
                setSkin(new PlayerNPCSkin(strings[0], strings[1], name));
            } catch (NPCIllegalSkinException ignore) {
            }
        }).start();
    }
    public void setSkin(@NotNull PlayerNPCSkin skin) {
        this.skin = skin;

        Bukkit.getScheduler().runTask(laivynpc(), () -> {
            try {
                Set<UUID> uuids = getSpawnedPlayers();

                reCreate();

                PropertyMap propertyMap = getProfile().getProperties();
                Property property = Property.createTextureProperty(skin.getTexture(), skin.getSignature());
                propertyMap.put("textures", property);

                visiblePlayers = uuids;
                spawn();
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
    @Override
    public void spawn(@NotNull Player player) {
        setCanSpawn(true);

        ReflectionUtils.sendPacketToPlayer(getSpawnPackets(player), player);
        getSpawnedPlayers().add(player.getUniqueId());

        NPCHeadRotation current = getHeadRotation();
        if (current != null) setHeadRotation(new NPCHeadRotation(this, current.getInterval(), current.getEntityType()));

        Bukkit.getScheduler().runTaskLater(laivynpc(), () -> ReflectionUtils.sendPacketToPlayer(removeFromTablist(), player), 40);

        sendUpdatePackets(player, true, true, true, true, true, true);
    }

    public boolean isShowOnTablist() {
        return showOnTablist;
    }
    public void setShowOnTablist(boolean showOnTablist) {
        if (showOnTablist && tablistName == null) {
            throw new NullPointerException("The tablist name cannot be null in that case!");
        }

        this.showOnTablist = showOnTablist;
        sendUpdatePackets(getSpawnedPlayers(), true, false, false, false, false, false);
    }

    @Nullable
    public String getTablistName() {
        return tablistName;
    }
    public void setTablistName(@Nullable String tablistName) {
        this.tablistName = tablistName;
        sendUpdatePackets(getSpawnedPlayers(), true, false, false, false, false, false);
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();

        list.add(SKIN_CONFIG);
        list.add(TABLIST_CONFIG);

        return list;
    }

    @NotNull
    public GameProfile getProfile() {
        return getEntity().getProfile();
    }

    @NotNull
    private List<Packet> removeFromTablist() {
        List<Packet> packets = new LinkedList<>();

        if (!isShowOnTablist()) {
            packets.add(laivynpc().getVersion().createPlayerInfoPacket(EnumPlayerInfoActionEnum.REMOVE_PLAYER(), getEntity()));
        }

        return packets;
    }

    @Override
    public @NotNull List<Packet> getSpawnPackets(@NotNull Player player) {
        List<@NotNull Packet> packets = new LinkedList<>();

        packets.add(laivynpc().getVersion().createPlayerInfoPacket(EnumPlayerInfoActionEnum.ADD_PLAYER(), getEntity()));
        packets.add(laivynpc().getVersion().createSpawnNamedPacket(getEntity()));

        // TODO: 26/12/2022 1.14 poses
//        if (getPose() == EntityPose.CROUCHING) setPose(EntityPose.CROUCHING);
//        else if (getPose() == EntityPose.FALL_FLYING) setPose(EntityPose.FALL_FLYING);
//        else if (getPose() == EntityPose.SWIMMING) setPose(EntityPose.SWIMMING);

        packets.addAll(getMetadataUpdatePackets(player));

        return packets;
    }
    @Override
    public @NotNull List<Packet> getDestroyPackets(@NotNull Player player) {
        List<@NotNull Packet> packets = new LinkedList<>();

        packets.add(laivynpc().getVersion().createDestroyPacket(getEntity()));
        packets.add(laivynpc().getVersion().createPlayerInfoPacket(EnumPlayerInfoActionEnum.REMOVE_PLAYER(), getEntity()));

        return packets;
    }
    @Override
    public @NotNull List<Packet> getMetadataUpdatePackets(@NotNull Player player) {
        List<Packet> packets = new LinkedList<>();

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

        try {
            DataWatcher watcher = getEntity().getDataWatcher();

            byte b = 0;
            PlayerNPCSkin.Parts parts = getSkin().getParts();
            if (parts.hasCape()) b = (byte) (b | 0x1);
            if (parts.hasJacket()) b = (byte) (b | 0x2);
            if (parts.hasLeftSleeve()) b = (byte) (b | 0x4);
            if (parts.hasRightSleeve()) b = (byte) (b | 0x8);
            if (parts.hasLeftPants()) b = (byte) (b | 0x10);
            if (parts.hasRightPants()) b = (byte) (b | 0x20);
            if (parts.hasHat()) b = (byte) (b | 0x40);

            // TODO: 26/12/2022 1.14 Parrots
//            // PARROTS
//            for (ParrotShoulder.ShoulderPosition position : ParrotShoulder.ShoulderPosition.values()) {
//                @NotNull Map<ParrotShoulder.ShoulderPosition, ParrotShoulder> map = controller.getNPC().getParrotShoulderMap();
//
//                if (map.containsKey(position)) {
//                    dataWatcherController.setByIndex(position.getShoulderPosition(), map.get(position).getParrotData());
//                } else {
//                    dataWatcherController.setByIndex(position.getShoulderPosition(), ReflectionUtils.construct(ReflectionUtils.getVersion().NBTTagCompound));
//                }
//            }
//            // PARROTS

            watcher.set((int) laivynpc().getVersion().getObject("Metadata:Player:SkinParts"), b);
            packets.add(laivynpc().getVersion().createMetadataPacket(getEntity(), watcher, true));
        } catch (Exception e) {
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

            // TabList name
            if (isShowOnTablist()) {
                if (getTablistName() != null) {
                    team.setPrefix(getTablistName());
                }
            }
            // TabList name

            scoreboard.addToTeam(team, getEntity());

            packets.add(laivynpc().getVersion().createScoreboardTeamPacket(team, true));
            packets.add(laivynpc().getVersion().createScoreboardTeamPacket(team, false));
            packets.addAll(getEquipmentsUpdatePackets(player));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return packets;
    }
    @Override
    public @NotNull List<@NotNull Packet> getEquipmentsUpdatePackets(@NotNull Player player) {
        return new LinkedList<>(laivynpc().getVersion().createEquipmentPacket(getEntity(), getEquipments()));
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

                playerNPC.setTablistName(ChatColor.translateAlternateColorCodes('&', name.toString()));
                playerNPC.setShowOnTablist(true);
                sender.sendMessage(translate(sender, "npc.player.tablist.changed", name));
            } else {
                playerNPC.setShowOnTablist(false);
                sender.sendMessage(translate(sender, "npc.player.tablist.removed"));
            }

            playerNPC.respawn();
        }
    };

    // TODO: 26/12/2022 1.14 Parrots
//    @NotNull
//    public static NPCConfiguration PARROT_CONFIG = new NPCConfiguration("parrot", "/laivynpc config parrot (right/left) (variant)") {
//        @Override
//        public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
//            PlayerNPC playerNPC = (PlayerNPC) npc;
//
//            if (args.length > 1) {
//                ParrotShoulder.ShoulderPosition position = null;
//                if (args[0].equalsIgnoreCase("right")) {
//                    position = ParrotShoulder.ShoulderPosition.RIGHT_SHOULDER;
//                } else if (args[0].equalsIgnoreCase("left")) {
//                    position = ParrotShoulder.ShoulderPosition.LEFT_SHOULDER;
//                }
//
//                if (position != null) {
//                    if (args[1].equalsIgnoreCase(translate("REMOVE"))) {
//                        playerNPC.setParrotShoulder(position, null);
//                        sender.sendMessage(translate("NPC_PLAYER:PARROT_REMOVED"));
//                        return;
//                    } else {
//                        try {
//                            ParrotShoulder.ParrotShoulderVariant variant = ParrotShoulder.ParrotShoulderVariant.valueOf(args[1].toUpperCase());
//                            playerNPC.setParrotShoulder(position, new ParrotShoulder(variant, playerNPC));
//                            sender.sendMessage(translate("NPC_PLAYER:PARROT_ADDED"));
//                            return;
//                        } catch (IllegalArgumentException ignore) {}
//                    }
//                }
//            }
//
//            // Erro de sintaxe
//            StringBuilder parrots = new StringBuilder("§6" + translate("REMOVE").toUpperCase() + "§c, ");
//            for (ParrotShoulder.ParrotShoulderVariant variant : ParrotShoulder.ParrotShoulderVariant.values()) {
//                parrots.append("§c, §6").append(variant.name());
//            }
//
//            sender.sendMessage("§cUse /laivynpc config parrot (right/left) (variant)");
//            sender.sendMessage("§c" + translate("NPC_VARIANT:AVAILABLE_VARIANTS") + ": " + parrots);
//        }
//    };
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
            String tablistName = playerNpc.getString("Tablist name (if shown)");
            setTablistName(tablistName);
            setShowOnTablist(shownTablist);
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
                    setSkin(new PlayerNPCSkin(texture, signature, null));
                }

                PlayerNPCSkin skinInstance = getSkin();
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

        // TODO: 26/12/2022 1.14 Poses
//        try {
//            if (map.contains("Parrot Shoulder")) {
//                ConfigurationSection parrotsSec = map.getConfigurationSection("Parrot Shoulder");
//
//                for (Map.Entry<String, Object> parrots : parrotsSec.getValues(false).entrySet()) {
//                    //noinspection unchecked
//                    ParrotShoulder parrot = ParrotShoulder.deserialize((Map<String, Object>) parrots.getValue(), this);
//                    getParrotShoulderMap().put(ParrotShoulder.ShoulderPosition.valueOf(parrots.getKey()), parrot);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            laivynpc().log("§cNão foi possível carregar os papagaios do PlayerNPC '" + getId() + "'!");
//        }

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
            playerNPC.put("Tablist name (if shown)", (getTablistName() == null ? "" : getTablistName()));
        } catch (Exception e) {
            e.printStackTrace();
            laivynpc().log("§cCouldn't serialize the pose/tablist of the PlayerNPC '" + getId() + "'!");
        }

        try {
            PlayerNPCSkin.Parts parts = getSkin().getParts();
            if (getSkin() != PlayerNPCSkin.DEFAULT_SKIN) {
                playerNPC.put("Skin", skin);

                PlayerNPCSkin cSkin = getSkin();
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

        // TODO: 26/12/2022 1.14 Parrots
//        try {
//            if (ReflectionUtils.getVersion().versionCode >= 12) {
//                playerNPC.put("Parrot Shoulder", parrotMap);
//                for (ParrotShoulder.ShoulderPosition position : ParrotShoulder.ShoulderPosition.values()) {
//                    if (getParrotShoulderMap().containsKey(position)) {
//                        parrotMap.put(position.name(), getParrotShoulderMap().get(position).serialize());
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            laivynpc().log("§cCouldn't save the parrots of the PlayerNPC '" + getId() + "'!");
//        }

        return map;
    }

    @NotNull
    public static PlayerNPC deserialize(@NotNull ConfigurationSection map) {
        try {
            ConfigurationSection playerNpc = map.getConfigurationSection("PlayerNPC Configuration");
            MemorySection locationMap = (MemorySection) map.get("Location");

            World world = Bukkit.getWorld((String) locationMap.get("world"));
            double x = (double) locationMap.get("x");
            double y = (double) locationMap.get("y");
            double z = (double) locationMap.get("z");

            if (world != null) {
                Location location = new Location(world, x, y, z);

                UUID uuid = UUIDUtils.genRandomUUID();
                if (!playerNpc.getString("UUID").equals("")) {
                    uuid = UUID.fromString(playerNpc.getString("UUID"));
                }

                PlayerNPC npc = new PlayerNPC(new ArrayList<>(), uuid, location);
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
