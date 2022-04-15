package codes.laivy.npc.api.player;

import codes.laivy.npc.api.GlowingStatus;
import codes.laivy.npc.api.workers.NPCController;
import codes.laivy.npc.exceptions.LaivyNPCException;
import codes.laivy.npc.reflection.ReflectionUtils;
import codes.laivy.npc.reflection.packets.*;
import codes.laivy.npc.utils.Validation;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.reflect.FieldUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.NPC;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static codes.laivy.npc.LaivyNPC.plugin;
import static codes.laivy.npc.reflection.ReflectionUtils.getEnum;
import static codes.laivy.npc.reflection.ReflectionUtils.getVersion;

public final class PlayerNPCController extends NPCController {

    private final PlayerNPC playerNPC;
    private final BukkitTask task;

    public PlayerNPCController(PlayerNPC playerNPC) {
        super(playerNPC);
        Validation.isTrue(playerNPC.getController() != null, new IllegalStateException("Esse NPC já possui um controlador"));

        this.playerNPC = playerNPC;

        task = new BukkitRunnable() {
            @Override
            public void run() {
                if (canSpawn()) {
                    if (!isSpawned) {
                        if (playerNPC.getPlayer().getWorld().equals(playerNPC.getNPCLocation().getWorld()) && playerNPC.getPlayer().getLocation().distance(playerNPC.getNPCLocation()) <= getViewDistance()) {
                            spawn();
                            isSpawned = true;
                        }
                    } else {
                        if (playerNPC.getPlayer().getWorld().equals(playerNPC.getNPCLocation().getWorld()) && playerNPC.getPlayer().getLocation().distance(playerNPC.getNPCLocation()) > getViewDistance()) {
                            isSpawned = false;
                            hide();
                        }
                    }
                }
            }
        }.runTaskTimer(plugin(), 20, 20);
    }

    public void reCreate() {
        playerNPC.getController().hide();
        playerNPC.profile = ReflectionUtils.createGameProfile(playerNPC.getNPCUniqueId(), playerNPC.getNPCName());
        playerNPC.npcEntity = ReflectionUtils.createEntityPlayer(Bukkit.getServer(), playerNPC.getProfile(), playerNPC.getNPCLocation());

        ReflectionUtils.sendPacketToPlayer(playerNPC.getPlayer(), PlayerNPCController.Developers.getUpdateScoreboardPackets(playerNPC.getController()));
    }

    public void spawn() {
        ReflectionUtils.sendPacketToPlayer(playerNPC.getPlayer(), Developers.spawnPlayerNPCPackets(this));
        setCanSpawn(true);
    }
    public void despawn() {
        hide();
        setCanSpawn(false);
    }

    public void respawn() {
        if (canSpawn()) {
            playerNPC.getController().hide();
            playerNPC.getController().spawn();
        }
    }
    public void hide() {
        ReflectionUtils.sendPacketToPlayer(playerNPC.getPlayer(), Developers.destroyPlayerNPCPackets(this));
    }

    public BukkitTask getNPCRefreshTask() {
        return task;
    }
    public PlayerNPC getPlayerNPC() {
        return playerNPC;
    }

    public static final class Developers {

        public static List<LaivyNPCPacket> spawnPlayerNPCPackets(PlayerNPCController playerNPCController) {
            Validation.notNull(playerNPCController, new NullPointerException("O controlador não pode ser nulo"));

            List<LaivyNPCPacket> packets = new ArrayList<>();

            packets.add(new PlayerInfoPacket(PlayerInfoPacket.Action.ADD_PLAYER, playerNPCController.playerNPC.npcEntity));
            packets.add(new EntitySpawnPacket(playerNPCController.playerNPC.npcEntity));

            packets.addAll(metadataUpdatePlayerNPCPackets(playerNPCController));

            // packets.add(new PlayerInfoPacket(PlayerInfoPacket.Action.REMOVE_PLAYER, controller.playerNPC.entityPlayer));

            return packets;
        }

        public static List<LaivyNPCPacket> destroyPlayerNPCPackets(PlayerNPCController playerNPCController) {
            Validation.notNull(playerNPCController, new NullPointerException("O controlador não pode ser nulo"));

            List<LaivyNPCPacket> packets = new ArrayList<>();

            packets.add(new EntityDestroyPacket(playerNPCController.playerNPC.npcEntity));
            packets.add(new PlayerInfoPacket(PlayerInfoPacket.Action.REMOVE_PLAYER, playerNPCController.playerNPC.npcEntity));

            return packets;
        }

        public static List<LaivyNPCPacket> getUpdateScoreboardPackets(PlayerNPCController playerNPCController) {
            Validation.notNull(playerNPCController, new NullPointerException("O controlador não pode ser nulo"));
            List<LaivyNPCPacket> packets = new ArrayList<>();

            try {
                Object gameProfile = playerNPCController.playerNPC.profile;
                Object scoreboard;

                Object glowColor = null;
                if (getVersion().versionCode >= 9) {
                    glowColor = playerNPCController.playerNPC.getAppearance().getGlowingStatus().getColor().getEnumChatFormat();
                }
                if (glowColor == null) glowColor = GlowingStatus.GlowingColor.WHITE.getEnumChatFormat();

                try {
                    Object craftPlayer = Class.forName(getVersion().CraftPlayer).cast(playerNPCController.getPlayerNPC().getPlayer());
                    Object craftScoreboard = craftPlayer.getClass().getMethod("getScoreboard").invoke(craftPlayer);
                    scoreboard = craftScoreboard.getClass().getMethod("getHandle").invoke(craftScoreboard);
                } catch (Exception e) {
                    throw new LaivyNPCException(e);
                }

                Validate.notNull(scoreboard, "Error at NMS Scoreboard");

                String profileName = (String) ReflectionUtils.executeMethod(gameProfile, "getName");

                Object team = scoreboard.getClass().getMethod(getVersion().Scoreboard_getTeam, String.class).invoke(scoreboard, profileName);
                Object newScoreboardTeam = Class.forName(getVersion().ScoreboardTeam).getConstructor(Class.forName(getVersion().Scoreboard), String.class).newInstance(scoreboard, profileName);

                Object scoreboardTeam = ((team == null) ? newScoreboardTeam : team);

                Enum<?> NEVER = getEnum(Class.forName(getVersion().ScoreboardTeamBase_EnumNameTagVisibility), getVersion().ScoreboardTeamBase_EnumNameTagVisibility_NEVER);
                ReflectionUtils.executeMethod(scoreboardTeam, getVersion().ScoreboardTeam_setNameTagVisibility, NEVER);
                ReflectionUtils.executeMethod(scoreboardTeam, getVersion().ScoreboardTeam_setColor, glowColor);

                Object g = scoreboardTeam.getClass().getMethod(getVersion().ScoreboardTeam_getPlayers).invoke(scoreboardTeam);
                g.getClass().getMethod("add", Object.class).invoke(g, profileName);

                if (getVersion().versionCode >= 13) {
                    ReflectionUtils.executeMethod(scoreboard, getVersion().Scoreboard_addPlayerToTeam, profileName, scoreboardTeam);
                } else {
                    ReflectionUtils.executeMethod(scoreboard, getVersion().Scoreboard_addPlayerToTeam, profileName, profileName);
                }

                packets.add(new ScoreboardTeamPacket(scoreboardTeam, true));
                packets.add(new ScoreboardTeamPacket(scoreboardTeam, false));
            } catch (InvocationTargetException | NoSuchMethodException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                throw new LaivyNPCException(e);
            }

            return packets;
        }

        // Special thanks to SergiFerry (PlayerNPC plugin)
        @SuppressWarnings("unchecked")
        public static List<LaivyNPCPacket> metadataUpdatePlayerNPCPackets(PlayerNPCController playerNPCController) {
            Validation.notNull(playerNPCController, new NullPointerException("O controlador não pode ser nulo"));
            List<LaivyNPCPacket> packets = new ArrayList<>();

            try {
                DataWatcherController dataWatcherController = new DataWatcherController(playerNPCController.playerNPC.npcEntity);

                // GLOWING AND FIRE EFFECT
                boolean isGlowing = false;
                if (playerNPCController.playerNPC.getAppearance().getGlowingStatus() != null) {
                    isGlowing = playerNPCController.playerNPC.getAppearance().getGlowingStatus().isActive();
                }

                boolean isOnFire = playerNPCController.playerNPC.getAppearance().isOnFire();

                if (getVersion().versionCode >= 9) {
                    playerNPCController.playerNPC.npcEntity.getClass().getMethod(getVersion().Entity_setGlowingTag, boolean.class).invoke(playerNPCController.playerNPC.npcEntity, isGlowing);
                }

                Map<Integer, ?> map;
                try {
                    if (getVersion().versionCode >= 9) {
                        map = (Map<Integer, ?>) FieldUtils.readDeclaredField(dataWatcherController.getDataWatcher(), getVersion().DataWatcher_itemsById, true);
                    } else {
                        map = (Map<Integer, ?>) FieldUtils.readDeclaredField(dataWatcherController.getDataWatcher(), getVersion().DataWatcher_itemsById, true);
                    }
                } catch (IllegalArgumentException ignore) {
                    map = (Map<Integer, ?>) FieldUtils.readDeclaredField(dataWatcherController.getDataWatcher(), getVersion().DataWatcher_itemsById_alternative, true);
                }

                Object item = map.get(0);
                byte b = (Byte) ReflectionUtils.executeMethod(item, "b");

                if (isGlowing) b = (byte) (b | 64);
                else b = (byte) (b & (~(1)));

                if (isOnFire) b = (byte) (b | 1);
                else b = (byte) (b & (~(1)));

                dataWatcherController.setValue(getVersion().Metadata_ENTITY_FLAGS, b);
                // GLOWING AND FIRE EFFECT

                b = 0;
                PlayerNPCSkin.Parts parts = playerNPCController.playerNPC.getAppearance().getSkin().getParts();
                if (parts.isCape()) b = (byte) (b | 0x1);
                if (parts.isJacket()) b = (byte) (b | 0x2);
                if (parts.isLeftSleeve()) b = (byte) (b | 0x4);
                if (parts.isRightSleeve()) b = (byte) (b | 0x8);
                if (parts.isLeftPants()) b = (byte) (b | 0x10);
                if (parts.isRightPants()) b = (byte) (b | 0x20);
                if (parts.isHat()) b = (byte) (b | 0x40);

                dataWatcherController.setValue(getVersion().Metadata_PLAYER_SKIN_PARTS_FLAGS, b);
                packets.add(new EntityMetadataPacket(playerNPCController.playerNPC.npcEntity, dataWatcherController.getDataWatcher(), true));
            } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                throw new LaivyNPCException(e);
            }

            return packets;
        }

    }

}