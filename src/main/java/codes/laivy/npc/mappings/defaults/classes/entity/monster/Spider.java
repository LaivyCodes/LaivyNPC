package codes.laivy.npc.mappings.defaults.classes.entity.monster;

import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.defaults.classes.java.BooleanObjExec;
import codes.laivy.npc.mappings.versions.V1_9_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Spider extends EntityLiving {

    public static @NotNull DataWatcherObject CLIMBING_METADATA() {
        if (ReflectionUtils.isCompatible(V1_9_R1.class)) {
            return new DataWatcherObject(laivynpc().getVersion().getFieldExec("Metadata:Spider:Climbing").invokeStatic());
        } else {
            throw new IllegalStateException("Metadata objects is compatible only at 1.9+");
        }
    }

    public Spider(@Nullable Object value) {
        super(value);
    }

    public boolean isClimbing() {
        return laivynpc().getVersion().isEntitySpiderClimbing(this);
    }
    public void setClimbing(boolean flag) {
        laivynpc().getVersion().setEntitySpiderClimbing(this, flag);
    }

    @Override
    public @NotNull SpiderClass getClassExecutor() {
        return (SpiderClass) laivynpc().getVersion().getClassExec("Entity:Spider");
    }

    public static class SpiderClass extends EntityLiving.EntityLivingClass {
        public SpiderClass(@NotNull String className) {
            super(className);
        }
    }
}
