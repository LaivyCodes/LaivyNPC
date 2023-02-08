package codes.laivy.npc.mappings.defaults.classes.entity;

import codes.laivy.npc.mappings.defaults.classes.java.IntegerObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static codes.laivy.npc.LaivyNPC.*;

public class AgeableLivingEntity extends EntityLiving {
    public AgeableLivingEntity(@Nullable Object value) {
        super(value);
    }

    public boolean isBaby() {
        return (boolean) Objects.requireNonNull(laivynpc().getVersion().getMethodExec("Entity:Ageable:isBaby").invokeInstance(this));
    }
    public void setBaby(boolean flag) {
        laivynpc().getVersion().getMethodExec("Entity:Ageable:setAge").invokeInstance(this, new IntegerObjExec(flag ? -1 : 10000));
    }

    @Override
    public @NotNull AgeableLivingEntityClass getClassExecutor() {
        return (AgeableLivingEntityClass) laivynpc().getVersion().getClassExec("Entity:Ageable");
    }

    public static class AgeableLivingEntityClass extends EntityLivingClass {
        public AgeableLivingEntityClass(@NotNull String className) {
            super(className);
        }
    }
}
