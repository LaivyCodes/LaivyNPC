package codes.laivy.npc.mappings.defaults.classes.entity;

import codes.laivy.npc.mappings.defaults.classes.java.BooleanObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class TameableEntityLiving extends AgeableEntityLiving {
    public TameableEntityLiving(@Nullable Object value) {
        super(value);
    }

    public boolean isTamed() {
        return (boolean) Objects.requireNonNull(laivynpc().getVersion().getMethodExec("Entity:Tameable:isTamed").invokeInstance(this));
    }
    public void setTamed(boolean flag) {
        laivynpc().getVersion().getMethodExec("Entity:Tameable:setTamed").invokeInstance(this, new BooleanObjExec(flag));
    }

    public boolean isSitting() {
        return (boolean) Objects.requireNonNull(laivynpc().getVersion().getMethodExec("Entity:Tameable:isSitting").invokeInstance(this));
    }
    public void setSitting(boolean flag) {
        laivynpc().getVersion().getMethodExec("Entity:Tameable:setSitting").invokeInstance(this, new BooleanObjExec(flag));
    }

    @Override
    public @NotNull TameableLivingEntityClass getClassExecutor() {
        return (TameableLivingEntityClass) laivynpc().getVersion().getClassExec("Entity:Tameable");
    }

    public static class TameableLivingEntityClass extends AgeableLivingEntityClass {
        public TameableLivingEntityClass(@NotNull String className) {
            super(className);
        }
    }
}
