package codes.laivy.npc.mappings.defaults.classes.entity.animal;

import codes.laivy.npc.mappings.defaults.classes.entity.AgeableLivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Rabbit extends AgeableLivingEntity {
    public Rabbit(@Nullable Object value) {
        super(value);
    }

    public @NotNull Rabbit.Variant getVariant() {
        return laivynpc().getVersion().getEntityRabbitType(this);
    }
    public void setVariant(@NotNull Rabbit.Variant variant) {
        laivynpc().getVersion().setEntityRabbitType(this, variant);
    }

    @Override
    public @NotNull RabbitClass getClassExecutor() {
        return (RabbitClass) laivynpc().getVersion().getClassExec("Entity:Rabbit");
    }

    public static class RabbitClass extends AgeableLivingEntityClass {
        public RabbitClass(@NotNull String className) {
            super(className);
        }
    }

    public enum Variant {
        TOAST(0),
        WHITE(1),
        BLACK(2),
        SALT_AND_PEPPER(3),
        GOLD(4),
        BROWN_WITH_WHITE(5),
        KILLER(99),
        ;

        private final int id;

        Variant(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public static @NotNull Rabbit.Variant getById(int id) {
            for (Variant variant : values()) {
                if (variant.getId() == id) {
                    return variant;
                }
            }
            throw new NullPointerException("Couldn't found this Rabbit Variant '" + id + "'");
        }
    }
}
