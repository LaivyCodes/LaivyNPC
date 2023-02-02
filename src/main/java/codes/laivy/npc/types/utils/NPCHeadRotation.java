package codes.laivy.npc.types.utils;

import codes.laivy.npc.LaivyNPC;
import codes.laivy.npc.types.NPC;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NPCHeadRotation {

    private final @NotNull NPC npc;

    private final @NotNull BukkitTask task;
    private final int interval;

    private final @NotNull Class<? extends Entity> entityType;

    public NPCHeadRotation(@NotNull NPC npc, int interval, @NotNull Class<? extends Entity> entityType) {
        this.npc = npc;
        this.interval = interval;
        this.entityType = entityType;

        this.task = new BukkitRunnable() {
            @Override
            public void run() {
                if (getNPC().getHeadRotation() != NPCHeadRotation.this) {
                    cancel();
                    return;
                }

                Entity entity = getNearest(getNPC().getLocation(), getNPC().getViewDistance(), getEntityType());
                if (entity != null) getNPC().lookTo(entity.getLocation());
            }
        }.runTaskTimer(LaivyNPC.laivynpc(), interval, interval);
    }

    public NPC getNPC() {
        return npc;
    }

    public @NotNull BukkitTask getTask() {
        return task;
    }

    public int getInterval() {
        return interval;
    }

    public @NotNull Class<? extends Entity> getEntityType() {
        return entityType;
    }

    public enum Type {

        NONE(null),
        FOLLOW_NEAREST_PLAYER(Player.class),
        FOLLOW_NEAREST_MONSTER(Monster.class),
        FOLLOW_NEAREST_ANIMAL(Animals.class),

        FOLLOW_NEAREST_ENTITY(Entity.class),
        FOLLOW_NEAREST_LIVING_ENTITY(LivingEntity.class);

        private final Class<? extends Entity> entityClass;

        Type(Class<? extends Entity> entityClass) {
            this.entityClass = entityClass;
        }

        public Class<? extends Entity> getEntityClass() {
            return entityClass;
        }
    }

    @Nullable
    private static Entity getNearest(@NotNull Location location, int radius, @NotNull Class<? extends Entity> entityClass) {
        Entity nearest = null;

        for (Entity e : location.getWorld().getEntities()) {
            if (e.getLocation().distance(location) < radius && entityClass.isInstance(e)) {
                if (nearest == null) nearest = e;
                else if (e.getLocation().distance(location) < nearest.getLocation().distance(location)) nearest = e;
            }
        }

        return nearest;
    }
}
