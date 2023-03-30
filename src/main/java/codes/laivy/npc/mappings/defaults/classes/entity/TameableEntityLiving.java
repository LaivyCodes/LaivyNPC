package codes.laivy.npc.mappings.defaults.classes.entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class TameableEntityLiving extends AgeableEntityLiving {
    public TameableEntityLiving(@Nullable Object value) {
        super(value);
    }

    public boolean isTamed() {
        return laivynpc().getVersion().isEntityTamed(this);
    }
    public void setTamed(boolean flag) {
        laivynpc().getVersion().setEntityTamed(this, flag);
    }

    public boolean isSitting() {
        return laivynpc().getVersion().isEntitySitting(this);
    }
    public void setSitting(boolean flag) {
        laivynpc().getVersion().setEntitySitting(this, flag);
    }

    @Override
    public @NotNull TameableEntityLiving.TameableEntityLivingClass getClassExecutor() {
        return (TameableEntityLivingClass) laivynpc().getVersion().getClassExec("Entity:Tameable");
    }

    public static class TameableEntityLivingClass extends AgeableEntityLivingClass {
        public TameableEntityLivingClass(@NotNull String className) {
            super(className);
        }
    }
}
