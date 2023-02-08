package codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie;

import codes.laivy.npc.mappings.Version;
import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumZombieTypeEnum;
import codes.laivy.npc.mappings.versions.V1_10_R1;
import codes.laivy.npc.mappings.versions.V1_8_R1;
import codes.laivy.npc.mappings.versions.V1_9_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Zombie extends EntityLiving {
    public Zombie(@Nullable Object value) {
        super(value);
    }

    public @Nullable Zombie.Type getType() {
        if (ReflectionUtils.isCompatible(V1_9_R1.class)) {
            return laivynpc().getVersion().getEntityZombieType(this);
        } else {
            return null;
        }
    }

    @Override
    public @NotNull Zombie.ZombieClass getClassExecutor() {
        return (ZombieClass) laivynpc().getVersion().getClassExec("Entity:Zombie");
    }

    public static class ZombieClass extends EntityLivingClass {
        public ZombieClass(@NotNull String className) {
            super(className);
        }
    }

    public enum Type {
        NORMAL(V1_8_R1.class, -1, EntityType.ZOMBIE),
        VILLAGER(V1_8_R1.class, 0, EntityType.ZOMBIE_VILLAGER),
        HUSK(V1_10_R1.class, 5, EntityType.ZOMBIE_HUSK),
        ;

        private final @NotNull EntityType entityType;

        private final @NotNull Class<? extends Version> since;
        private final int id;

        Type(@NotNull Class<? extends Version> since, int id, @NotNull EntityType entityType) {
            this.since = since;
            this.id = id;
            this.entityType = entityType;
        }

        public @NotNull EntityType getEntityType() {
            return entityType;
        }

        public @NotNull Class<? extends Version> getSince() {
            return since;
        }

        public int getId() {
            return id;
        }

        public boolean isVillager() {
            return this == VILLAGER;
        }

        public boolean isCompatible() {
            return ReflectionUtils.isCompatible(getSince());
        }

        public @NotNull EnumZombieTypeEnum.EnumZombieType getEnum() {
            if (!ReflectionUtils.isCompatible(V1_9_R1.class)) {
                throw new IllegalArgumentException("This method is only available at 1.9+");
            }

            if (this == NORMAL) {
                return EnumZombieTypeEnum.NORMAL();
            } else if (this == VILLAGER) {
                return EnumZombieTypeEnum.VILLAGER_FARMER();
            } else if (this == HUSK) {
                return EnumZombieTypeEnum.HUSK();
            } else {
                throw new IllegalStateException("Couldn't find this zombie type enum '" + name() + "'");
            }
        }
        public static @NotNull Zombie.Type fromEnum(@NotNull EnumZombieTypeEnum.EnumZombieType enumZombieType) {
            if (enumZombieType.name().equals("NORMAL")) {
                return NORMAL;
            } else if (enumZombieType.name().equals("VILLAGER_FARMER")) {
                return VILLAGER;
            } else if (enumZombieType.name().equals("VILLAGER_LIBRARIAN")) {
                return VILLAGER;
            } else if (enumZombieType.name().equals("VILLAGER_PRIEST")) {
                return VILLAGER;
            } else if (enumZombieType.name().equals("VILLAGER_SMITH")) {
                return VILLAGER;
            } else if (enumZombieType.name().equals("VILLAGER_BUTCHER")) {
                return VILLAGER;
            } else if (enumZombieType.name().equals("HUSK")) {
                return HUSK;
            } else {
                throw new IllegalStateException("Couldn't find this zombie type from enum '" + enumZombieType.name() + "'");
            }
        }

        public static @Nullable Zombie.Type getById(int id) {
            for (Type type : Type.values()) {
                if (type.isCompatible() && type.getId() == id) {
                    return type;
                }
            }
            return null;
        }
    }

}
