package codes.laivy.npc.mappings.defaults.classes.entity.animal.horse;

import codes.laivy.npc.mappings.Version;
import codes.laivy.npc.mappings.defaults.classes.entity.AgeableEntityLiving;
import codes.laivy.npc.mappings.defaults.classes.others.inventories.InventorySubcontainer;
import codes.laivy.npc.mappings.versions.V1_11_R1;
import codes.laivy.npc.mappings.versions.V1_14_R1;
import codes.laivy.npc.mappings.versions.V1_8_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public abstract class AbstractHorse extends AgeableEntityLiving {
    public AbstractHorse(@Nullable Object value) {
        super(value);
    }

    @ApiStatus.Internal
    public @NotNull InventorySubcontainer getInventory() {
        if (!ReflectionUtils.isCompatible(V1_14_R1.class)) {
            throw new UnsupportedOperationException("This method is only available at 1.14+");
        }

        V1_14_R1 version = (V1_14_R1) laivynpc().getVersion();
        return version.getHorseInventory(this);
    }

    public @NotNull Type getType() {
        return laivynpc().getVersion().getEntityAbstractHorseType(this);
    }

    @Override
    public @NotNull AgeableEntityLiving.AgeableEntityLivingClass getClassExecutor() {
        if (ReflectionUtils.isCompatible(V1_11_R1.class)) {
            return (AbstractHorseClass) laivynpc().getVersion().getClassExec("Entity:Horse:Abstract");
        } else {
            return (AgeableEntityLivingClass) laivynpc().getVersion().getClassExec("Entity:Horse");
        }
    }

    public static class AbstractHorseClass extends AgeableEntityLivingClass {
        public AbstractHorseClass(@NotNull String className) {
            super(className);
        }
    }

    public enum Type {
        HORSE(EntityType.HORSE, 0, V1_8_R1.class),
        DONKEY(EntityType.HORSE_DONKEY, 1, V1_8_R1.class),
        MULE(EntityType.HORSE_MULE, 2, V1_8_R1.class),
        ZOMBIE(EntityType.HORSE_ZOMBIE, 3, V1_8_R1.class),
        SKELETON(EntityType.HORSE_SKELETON, 4, V1_8_R1.class),
        LLAMA(EntityType.LLAMA, V1_11_R1.class),
        ;

        private final @NotNull Class<? extends Version> since;

        private final @NotNull EntityType type;
        private final int id;

        Type(@NotNull EntityType type, @NotNull Class<? extends Version> since) {
            this(type, -1, since);
        }
        Type(@NotNull EntityType type, int id, @NotNull Class<? extends Version> since) {
            this.type = type;
            this.id = id;
            this.since = since;
        }

        public @NotNull EntityType getEntityType() {
            return type;
        }

        public @NotNull Class<? extends Version> getSince() {
            return since;
        }

        public boolean isCompatible() {
            return ReflectionUtils.isCompatible(getSince());
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
