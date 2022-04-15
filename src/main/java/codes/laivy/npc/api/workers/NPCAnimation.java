package codes.laivy.npc.api.workers;

import codes.laivy.npc.api.types.INPC;
import codes.laivy.npc.reflection.ReflectionUtils;
import codes.laivy.npc.reflection.packets.EntityLookPacket;
import codes.laivy.npc.reflection.packets.HeadRotationPacket;
import codes.laivy.npc.reflection.packets.LaivyNPCPacket;
import codes.laivy.npc.utils.Validation;
import com.sun.istack.internal.Nullable;
import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static codes.laivy.npc.LaivyNPC.plugin;

public class NPCAnimation {

    private final INPC npc;

    private HeadRotationType headRotationType;
    private BukkitTask headRotationTask;

    public NPCAnimation(INPC npc) {
        Validation.notNull(npc, new NullPointerException("O NPC não pode ser nulo"));
        Validation.isTrue(npc.getAnimation() != null, new IllegalStateException("Esse NPC já possui uma animação"));

        this.npc = npc;
        this.headRotationType = null;
    }

    public INPC getNPC() {
        return npc;
    }

    public HeadRotationType getHeadRotationType() {
        return headRotationType;
    }

    public void lookToEntity(Entity entity) {
        List<LaivyNPCPacket> packets = new ArrayList<>();

        Location faceLocation = faceLocation(npc.getNPCLocation(), entity.getLocation());

        packets.add(new HeadRotationPacket(npc.getNPCEntity(), faceLocation.getYaw()));
        packets.add(new EntityLookPacket(npc.getNPCEntity(), faceLocation.getYaw(), faceLocation.getPitch() + 90));

        ReflectionUtils.sendPacketToPlayer(npc.getPlayer(), packets);
    }

    // Special thanks to NickAc
    private static Location faceLocation(Location entity, Location to) {
        if (entity.getWorld() != to.getWorld()) {
            throw new IllegalStateException("Mundos diferentes");
        }

        double xDiff = to.getX() - entity.getX();
        double yDiff = to.getY() - entity.getY();
        double zDiff = to.getZ() - entity.getZ();

        double distanceXZ = Math.sqrt(xDiff * xDiff + zDiff * zDiff);
        double distanceY = Math.sqrt(distanceXZ * distanceXZ + yDiff * yDiff);

        double yaw = Math.toDegrees(Math.acos(xDiff / distanceXZ));
        double pitch = Math.toDegrees(Math.acos(yDiff / distanceY)) - 90.0D;
        if (zDiff < 0.0D) {
            yaw += Math.abs(180.0D - yaw) * 2.0D;
        }

        entity.setYaw((float) (yaw - 90.0F));
        entity.setPitch((float) (pitch - 90.0F));

        return entity;
    }

    public void setHeadRotationType(@Nullable HeadRotationType headRotationType, int rotationInterval) {
        this.headRotationType = headRotationType;

        if (headRotationTask != null) {
            headRotationTask.cancel();
            headRotationTask = null;
        }

        if (headRotationType != null) {
            headRotationTask = new BukkitRunnable() {
                @Override
                public void run() {
                    int viewDistance = getNPC().getController().getViewDistance();

                    Entity nearEntity = getNearestEntity(npc.getNPCLocation());
                    if (headRotationType == HeadRotationType.FOLLOW_NEAREST_LIVING_ENTITY) {
                        LivingEntity nearLivingEntity = getNearestLivingEntity(npc.getNPCLocation());
                        if (nearLivingEntity != null && (nearLivingEntity.getLocation().distance(npc.getNPCLocation()) <= viewDistance)) {
                            lookToEntity(nearLivingEntity);
                            return;
                        }
                    }
                    if (headRotationType == HeadRotationType.FOLLOW_NEAREST_PLAYER) {
                        LivingEntity nearPlayer = getNearestPlayer(npc.getNPCLocation());
                        if (nearPlayer != null && (nearPlayer.getLocation().distance(npc.getNPCLocation()) <= viewDistance)) {
                            lookToEntity(nearPlayer);
                            return;
                        }
                    }

                    if (nearEntity != null && (nearEntity.getLocation().distance(npc.getNPCLocation()) <= viewDistance)) {
                        lookToEntity(nearEntity);
                    }
                }
            }.runTaskTimer(plugin(), rotationInterval, rotationInterval);
        }
    }

    public enum HeadRotationType {

        FOLLOW_NEAREST_PLAYER,
        FOLLOW_NEAREST_ENTITY,
        FOLLOW_NEAREST_LIVING_ENTITY;

    }

    private static LivingEntity getNearestLivingEntity(Location location) {
        LivingEntity nearest = null;
        for (Entity p : location.getWorld().getEntities()) {
            if (p instanceof LivingEntity) {
                if (nearest == null) nearest = (LivingEntity) p;
                else if (p.getLocation().distance(location) < nearest.getLocation().distance(location)) nearest = (LivingEntity) p;
            }
        }
        return nearest;
    }
    private static Player getNearestPlayer(Location location) {
        Player nearest = null;
        for (Entity p : location.getWorld().getEntities()) {
            if (p instanceof Player) {
                if (nearest == null) nearest = (Player) p;
                else if (p.getLocation().distance(location) < nearest.getLocation().distance(location)) nearest = (Player) p;
            }
        }
        return nearest;
    }
    private static Entity getNearestEntity(Location location) {
        Entity nearest = null;
        for (Entity p : location.getWorld().getEntities()) {
            if (nearest == null) nearest = p;
            else if (p.getLocation().distance(location) < nearest.getLocation().distance(location)) nearest = p;
        }
        return nearest;
    }

}
