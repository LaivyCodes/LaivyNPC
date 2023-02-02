package codes.laivy.npc.mappings.utils.classes.entity.monster;

import codes.laivy.npc.mappings.utils.classes.entity.EntityLiving;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Silverfish extends EntityLiving {
    public Silverfish(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull SilverfishClass getClassExecutor() {
        return (SilverfishClass) laivynpc().getVersion().getClassExec("Entity:Silverfish");
    }

    public static class SilverfishClass extends EntityLivingClass {
        public SilverfishClass(@NotNull String className) {
            super(className);
        }
    }
}
