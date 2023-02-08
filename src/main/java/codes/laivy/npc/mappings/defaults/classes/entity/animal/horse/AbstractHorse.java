package codes.laivy.npc.mappings.defaults.classes.entity.animal.horse;

import codes.laivy.npc.mappings.defaults.classes.entity.AgeableLivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AbstractHorse extends AgeableLivingEntity {
    public AbstractHorse(@Nullable Object value) {
        super(value);
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
