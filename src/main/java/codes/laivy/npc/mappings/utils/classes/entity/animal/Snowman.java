package codes.laivy.npc.mappings.utils.classes.entity.animal;

import codes.laivy.npc.mappings.utils.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.utils.classes.java.BooleanObjExec;
import codes.laivy.npc.mappings.versions.V1_9_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Snowman extends EntityLiving {
    public Snowman(@Nullable Object value) {
        super(value);
    }

    public boolean hasPumpkinHat() {
        if (!ReflectionUtils.isCompatible(V1_9_R1.class)) {
            throw new IllegalStateException("The pumpkin hat of a snowman is only available at 1.9+");
        }

        //noinspection DataFlowIssue
        return (boolean) laivynpc().getVersion().getMethodExec("Entity:Snowman:hasPumpkinHat").invokeInstance(this);
    }
    public void setPumpkinHat(boolean flag) {
        if (!ReflectionUtils.isCompatible(V1_9_R1.class)) {
            throw new IllegalStateException("The pumpkin hat of a snowman is only available at 1.9+");
        }

        laivynpc().getVersion().getMethodExec("Entity:Snowman:setPumpkinHat").invokeInstance(this, new BooleanObjExec(flag));
    }

    @Override
    public @NotNull SnowmanClass getClassExecutor() {
        return (SnowmanClass) laivynpc().getVersion().getClassExec("Entity:Snowman");
    }

    public static class SnowmanClass extends EntityLivingClass {
        public SnowmanClass(@NotNull String className) {
            super(className);
        }
    }
}
