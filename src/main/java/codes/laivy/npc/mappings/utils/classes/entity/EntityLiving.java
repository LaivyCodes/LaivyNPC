package codes.laivy.npc.mappings.utils.classes.entity;

import codes.laivy.npc.LaivyNPC;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EntityLiving extends Entity {
    public EntityLiving(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull EntityLivingClass getClassExecutor() {
        return (EntityLivingClass) LaivyNPC.laivynpc().getVersion().getClassExec("EntityLiving");
    }

    public static class EntityLivingClass extends EntityClass {
        public EntityLivingClass(@NotNull String className) {
            super(className);
        }
    }
}
