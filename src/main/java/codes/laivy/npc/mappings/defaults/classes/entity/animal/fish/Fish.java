package codes.laivy.npc.mappings.defaults.classes.entity.animal.fish;

import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public abstract class Fish extends EntityLiving {
    public Fish(@Nullable Object value) {
        super(value);
    }

    public @NotNull Type getType() {
        return laivynpc().getVersion().getEntityFishType(this);
    }

    @Override
    public @NotNull FishClass getClassExecutor() {
        return (FishClass) laivynpc().getVersion().getClassExec("Entity:Fish");
    }

    public static class FishClass extends EntityLivingClass {
        public FishClass(@NotNull String className) {
            super(className);
        }
    }

    public enum Type {
        COD(EntityType.COD),
        SALMON(EntityType.SALMON),
        PUFFERFISH(EntityType.PUFFERFISH),
        TROPICALFISH(EntityType.TROPICALFISH),
        ;

        private final @NotNull EntityType entityType;

        Type(@NotNull EntityType entityType) {
            this.entityType = entityType;
        }

        public @NotNull EntityType getEntityType() {
            return entityType;
        }
    }
}
