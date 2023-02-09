package codes.laivy.npc.mappings.defaults.classes.entity.monster;

import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.versions.V1_9_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Guardian extends EntityLiving {

    public static @NotNull DataWatcherObject TARGET_METADATA() {
        if (ReflectionUtils.isCompatible(V1_9_R1.class)) {
            return new DataWatcherObject(laivynpc().getVersion().getFieldExec("Metadata:Guardian:Target").invokeStatic());
        } else {
            throw new IllegalStateException("Metadata objects is compatible only at 1.9+");
        }
    }

    private @Nullable EntityLiving target;

    public Guardian(@Nullable Object value) {
        super(value);
    }

    public @Nullable EntityLiving getTarget() {
        return target;
    }
    public void setTarget(@Nullable EntityLiving living) {
        laivynpc().getVersion().setEntityGuardianTarget(this, living);
        target = living;
    }

    @Override
    public @NotNull GuardianClass getClassExecutor() {
        return (GuardianClass) laivynpc().getVersion().getClassExec("Entity:Guardian");
    }

    public static class GuardianClass extends EntityLivingClass {
        public GuardianClass(@NotNull String className) {
            super(className);
        }
    }
}
