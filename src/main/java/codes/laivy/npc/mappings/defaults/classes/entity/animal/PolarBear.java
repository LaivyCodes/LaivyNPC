package codes.laivy.npc.mappings.defaults.classes.entity.animal;

import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.defaults.classes.entity.AgeableEntityLiving;
import codes.laivy.npc.mappings.defaults.classes.java.BooleanObjExec;
import codes.laivy.npc.mappings.versions.V1_10_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class PolarBear extends AgeableEntityLiving {

    public static @NotNull DataWatcherObject STANDING_METADATA() {
        if (ReflectionUtils.isCompatible(V1_10_R1.class)) {
            return new DataWatcherObject(laivynpc().getVersion().getFieldExec("Metadata:PolarBear:Standing").invokeStatic());
        } else {
            throw new IllegalStateException("This metadata object is compatible only at 1.10+");
        }
    }

    public PolarBear(@Nullable Object value) {
        super(value);
    }

    public boolean isStanding() {
        return laivynpc().getVersion().isEntityPolarBearStanding(this);
    }
    public void setStanding(boolean flag) {
        laivynpc().getVersion().setEntityPolarBearStanding(this, flag);
    }

    @Override
    public @NotNull PolarBearClass getClassExecutor() {
        return (PolarBearClass) laivynpc().getVersion().getClassExec("Entity:PolarBear");
    }

    public static class PolarBearClass extends AgeableEntityLivingClass {
        public PolarBearClass(@NotNull String className) {
            super(className);
        }
    }
}
