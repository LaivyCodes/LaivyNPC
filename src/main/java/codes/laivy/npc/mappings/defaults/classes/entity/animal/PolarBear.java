package codes.laivy.npc.mappings.defaults.classes.entity.animal;

import codes.laivy.npc.mappings.defaults.classes.entity.AgeableLivingEntity;
import codes.laivy.npc.mappings.defaults.classes.java.BooleanObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class PolarBear extends AgeableLivingEntity {
    public PolarBear(@Nullable Object value) {
        super(value);
    }

    public boolean isStanding() {
        //noinspection DataFlowIssue
        return (boolean) laivynpc().getVersion().getMethodExec("Entity:PolarBear:isStanding").invokeInstance(this);
    }
    public void setStanding(boolean flag) {
        laivynpc().getVersion().getMethodExec("Entity:PolarBear:setStanding").invokeInstance(this, new BooleanObjExec(flag));
    }

    @Override
    public @NotNull PolarBearClass getClassExecutor() {
        return (PolarBearClass) laivynpc().getVersion().getClassExec("Entity:PolarBear");
    }

    public static class PolarBearClass extends AgeableLivingEntityClass {
        public PolarBearClass(@NotNull String className) {
            super(className);
        }
    }
}
