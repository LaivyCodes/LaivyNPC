package codes.laivy.npc.mappings.defaults.classes.entity.monster;

import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.defaults.classes.java.BooleanObjExec;
import codes.laivy.npc.mappings.versions.V1_9_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Blaze extends EntityLiving {

    public static @NotNull DataWatcherObject CHARGING_METADATA() {
        if (ReflectionUtils.isCompatible(V1_9_R1.class)) {
            return new DataWatcherObject(laivynpc().getVersion().getFieldExec("Metadata:Blaze:Charging").invokeStatic());
        } else {
            throw new IllegalStateException("Metadata objects is compatible only at 1.9+");
        }
    }

    public Blaze(@Nullable Object value) {
        super(value);
    }

    public boolean isCharging() {
        return laivynpc().getVersion().isEntityBlazeCharging(this);
    }
    public void setCharging(boolean flag) {
        laivynpc().getVersion().setEntityBlazeCharging(this, flag);
    }

    @Override
    public @NotNull BlazeClass getClassExecutor() {
        return (BlazeClass) laivynpc().getVersion().getClassExec("Entity:Blaze");
    }

    public static class BlazeClass extends EntityLivingClass {
        public BlazeClass(@NotNull String className) {
            super(className);
        }
    }
}
