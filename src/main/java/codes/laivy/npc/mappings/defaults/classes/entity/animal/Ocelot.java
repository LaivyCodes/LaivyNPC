package codes.laivy.npc.mappings.defaults.classes.entity.animal;

import codes.laivy.npc.mappings.defaults.classes.entity.TameableEntityLiving;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Ocelot extends TameableEntityLiving {

    public Ocelot(@Nullable Object value) {
        super(value);
    }

    @Override
    public void setTamed(boolean flag) {
    }
    @Override
    public boolean isTamed() {
        return false;
    }

    @Override
    public void setSitting(boolean flag) {
    }
    @Override
    public boolean isSitting() {
        return false;
    }

    @Override
    public @NotNull OcelotClass getClassExecutor() {
        return (OcelotClass) laivynpc().getVersion().getClassExec("Entity:Ocelot");
    }

    public static class OcelotClass extends TameableEntityLivingClass {
        public OcelotClass(@NotNull String className) {
            super(className);
        }
    }
}
