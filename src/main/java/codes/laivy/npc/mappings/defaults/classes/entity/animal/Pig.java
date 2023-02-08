package codes.laivy.npc.mappings.defaults.classes.entity.animal;

import codes.laivy.npc.mappings.defaults.classes.entity.AgeableLivingEntity;
import codes.laivy.npc.mappings.defaults.classes.java.BooleanObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.*;

public class Pig extends AgeableLivingEntity {
    public Pig(@Nullable Object value) {
        super(value);
    }

    public boolean hasSaddle() {
        //noinspection DataFlowIssue
        return (boolean) laivynpc().getVersion().getMethodExec("Entity:Pig:hasSaddle").invokeInstance(this);
    }
    public void setSaddle(boolean flag) {
        laivynpc().getVersion().getMethodExec("Entity:Pig:setSaddle").invokeInstance(this, new BooleanObjExec(flag));
    }

    @Override
    public @NotNull PigClass getClassExecutor() {
        return (PigClass) laivynpc().getVersion().getClassExec("Entity:Pig");
    }

    public static class PigClass extends AgeableLivingEntityClass {
        public PigClass(@NotNull String className) {
            super(className);
        }
    }
}
