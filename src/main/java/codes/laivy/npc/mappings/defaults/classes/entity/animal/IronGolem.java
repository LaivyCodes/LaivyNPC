package codes.laivy.npc.mappings.defaults.classes.entity.animal;

import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class IronGolem extends EntityLiving {
    public IronGolem(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull IronGolemClass getClassExecutor() {
        return (IronGolemClass) laivynpc().getVersion().getClassExec("Entity:IronGolem");
    }

    public static class IronGolemClass extends EntityLivingClass {
        public IronGolemClass(@NotNull String className) {
            super(className);
        }
    }
}
