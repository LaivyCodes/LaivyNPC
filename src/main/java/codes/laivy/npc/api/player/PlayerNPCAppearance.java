package codes.laivy.npc.api.player;

import codes.laivy.npc.api.GlowingStatus;
import codes.laivy.npc.exceptions.LaivyNPCException;
import codes.laivy.npc.reflection.ReflectionUtils;
import codes.laivy.npc.utils.Validation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static codes.laivy.npc.reflection.ReflectionUtils.getVersion;
import static codes.laivy.npc.reflection.ReflectionUtils.sendPacketToPlayer;

public final class PlayerNPCAppearance {

    private final PlayerNPC playerNPC;

    private PlayerNPCSkin playerNPCSkin;
    private GlowingStatus glowingStatus;
    private boolean onFire = false;

    public PlayerNPCAppearance(PlayerNPC playerNPC) {
        Validation.notNull(playerNPC, new NullPointerException("O NPC não pode ser nulo"));
        Validation.isTrue(playerNPC.getAppearance() != null, new IllegalStateException("Esse NPC já possui uma aparência"));

        this.playerNPC = playerNPC;

        if (getVersion().versionCode >= 9) {
            this.glowingStatus = new GlowingStatus(GlowingStatus.GlowingColor.WHITE, false);
        }

        setSkin(PlayerNPCSkin.DEFAULT_SKIN);
    }

    public PlayerNPC getPlayerNPC() {
        return playerNPC;
    }

    public GlowingStatus getGlowingStatus() {
        return glowingStatus;
    }
    public void setGlowingStatus(GlowingStatus glowingStatus) {
        Validation.notNull(glowingStatus, new NullPointerException("O status de brilho não pode ser nulo"));

        sendPacketToPlayer(playerNPC.getPlayer(), PlayerNPCController.Developers.getUpdateScoreboardPackets(playerNPC.getController()));
        this.glowingStatus = glowingStatus;
    }

    public boolean isOnFire() {
        return onFire;
    }

    public void setOnFire(boolean onFire) {
        this.onFire = onFire;
    }

    public PlayerNPCSkin getSkin() {
        return playerNPCSkin;
    }

    public void setSkin(PlayerNPCSkin playerNPCSkin) {
        Validation.notNull(playerNPCSkin, new NullPointerException("A skin não pode ser nula"));
        this.playerNPCSkin = playerNPCSkin;

        try {
            if (playerNPC.getController().canSpawn()) {
                playerNPC.getController().reCreate();
            }

            Object propertyMap = ReflectionUtils.executeMethod(playerNPC.getProfile(), getVersion().GameProfile_getProperties);
            Object property = ReflectionUtils.construct(Class.forName(getVersion().Property), "textures", playerNPCSkin.getTexture(), playerNPCSkin.getSignature());

            Method putMethod = propertyMap.getClass().getMethod("put", Object.class, Object.class);
            putMethod.invoke(propertyMap, "textures", property);

            if (playerNPC.getController().canSpawn()) {
                playerNPC.getController().spawn();
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new LaivyNPCException(e);
        }
    }

}