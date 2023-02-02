package codes.laivy.npc.mappings.utils.classes.entity;

import codes.laivy.npc.LaivyNPC;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EntityHuman extends EntityLiving {
    public EntityHuman(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull EntityHumanClass getClassExecutor() {
        return (EntityHumanClass) LaivyNPC.laivynpc().getVersion().getClassExec("EntityHuman");
    }

    public static class EntityHumanClass extends EntityLivingClass {
        public EntityHumanClass(@NotNull String className) {
            super(className);
        }
    }
}
