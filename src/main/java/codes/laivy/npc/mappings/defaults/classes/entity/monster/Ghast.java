package codes.laivy.npc.mappings.defaults.classes.entity.monster;

import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.versions.V1_9_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Ghast extends EntityLiving {

    public static @NotNull DataWatcherObject ATTACKING_METADATA() {
        if (ReflectionUtils.isCompatible(V1_9_R1.class)) {
            return new DataWatcherObject(laivynpc().getVersion().getFieldExec("Metadata:Ghast:DataWatcher:Attacking").invokeStatic());
        } else {
            throw new IllegalStateException("Metadata objects is compatible only at 1.9+");
        }
    }

    public Ghast(@Nullable Object value) {
        super(value);
    }

    public boolean isAttacking() {
        return laivynpc().getVersion().isEntityGhastAttacking(this);
    }
    public void setAttacking(boolean flag) {
        laivynpc().getVersion().setEntityGhastAttacking(this, flag);
    }

    @Override
    public @NotNull GhastClass getClassExecutor() {
        return (GhastClass) laivynpc().getVersion().getClassExec("Entity:Ghast");
    }

    public static class GhastClass extends EntityLivingClass {
        public GhastClass(@NotNull String className) {
            super(className);
        }
    }
}