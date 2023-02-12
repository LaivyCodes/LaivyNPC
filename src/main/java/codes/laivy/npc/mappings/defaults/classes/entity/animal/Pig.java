package codes.laivy.npc.mappings.defaults.classes.entity.animal;

import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.defaults.classes.entity.AgeableEntityLiving;
import codes.laivy.npc.mappings.defaults.classes.java.BooleanObjExec;
import codes.laivy.npc.mappings.versions.V1_9_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.*;

public class Pig extends AgeableEntityLiving {

    public static @NotNull DataWatcherObject SADDLE_METADATA() {
        if (ReflectionUtils.isCompatible(V1_9_R1.class)) {
            return new DataWatcherObject(laivynpc().getVersion().getFieldExec("Metadata:Pig:Saddle").invokeStatic());
        } else {
            throw new IllegalStateException("Metadata objects is compatible only at 1.9+");
        }
    }

    public Pig(@Nullable Object value) {
        super(value);
    }

    public boolean hasSaddle() {
        return laivynpc().getVersion().hasEntityPigSaddle(this);
    }
    public void setSaddle(boolean flag) {
        laivynpc().getVersion().setEntityPigSaddle(this, flag);
    }

    @Override
    public @NotNull PigClass getClassExecutor() {
        return (PigClass) laivynpc().getVersion().getClassExec("Entity:Pig");
    }

    public static class PigClass extends AgeableEntityLivingClass {
        public PigClass(@NotNull String className) {
            super(className);
        }
    }
}
