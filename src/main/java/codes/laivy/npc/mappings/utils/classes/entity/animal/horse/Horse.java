package codes.laivy.npc.mappings.utils.classes.entity.animal.horse;

import codes.laivy.npc.mappings.utils.classes.entity.AgeableLivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Horse extends AgeableLivingEntity {
    public Horse(@Nullable Object value) {
        super(value);
    }

    public @NotNull Type getType() {
        return laivynpc().getVersion().getEntityHorseType(this);
    }
    public void setType(@NotNull Type type) {
        laivynpc().getVersion().setEntityHorseType(this, type);
    }

    @Override
    public @NotNull HorseClass getClassExecutor() {
        return (HorseClass) laivynpc().getVersion().getClassExec("Entity:Horse");
    }

    public static class HorseClass extends AgeableLivingEntityClass {
        public HorseClass(@NotNull String className) {
            super(className);
        }
    }

    public enum Type {
        HORSE(EntityType.HORSE, 0),
        DONKEY(EntityType.HORSE_DONKEY, 1),
        MULE(EntityType.HORSE_MULE, 2),
        ZOMBIE(EntityType.HORSE_ZOMBIE, 3),
        SKELETON(EntityType.HORSE_SKELETON, 4),
        ;

        private final @NotNull EntityType type;
        private final int id;

        Type(@NotNull EntityType type, int id) {
            this.type = type;
            this.id = id;
        }

        public @NotNull EntityType getType() {
            return type;
        }

        public int getId() {
            return id;
        }

        public static @NotNull Type getById(int id) {
            for (Type type : values()) {
                if (type.getId() == id) {
                    return type;
                }
            }
            throw new NullPointerException("Couldn't found this HorseType '" + id + "'");
        }
    }
}
