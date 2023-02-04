package codes.laivy.npc.mappings.utils.classes.entity.monster;

import codes.laivy.npc.mappings.Version;
import codes.laivy.npc.mappings.utils.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.utils.classes.enums.EnumZombieTypeEnum;
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

    public boolean isVillager() {
        //noinspection DataFlowIssue
        return (boolean) laivynpc().getVersion().getMethodExec("Entity:Zombie:isVillager").invokeInstance(this);
    }

    public @Nullable Zombie.Type getType() {
        return laivynpc().getVersion().getEntityZombieType(this);
    }
    public void setType(@Nullable Zombie.Type type) {
        laivynpc().getVersion().setEntityZombieType(this, type);
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
        NORMAL(V1_8_R1.class, -1, false),
        VILLAGER_FARMER(V1_8_R1.class, 0, true),
        VILLAGER_LIBRARIAN(V1_8_R1.class, 1, true),
        VILLAGER_PRIEST(V1_8_R1.class, 2, true),
        VILLAGER_SMITH(V1_8_R1.class, 3, true),
        VILLAGER_BUTCHER(V1_8_R1.class, 4, true),
        ;

        private final @NotNull Class<? extends Version> since;

        private final int id;

        private final boolean villager;

        Type(@NotNull Class<? extends Version> since, int id, boolean villager) {
            this.since = since;
            this.id = id;
            this.villager = villager;
        }

        public @NotNull Class<? extends Version> getSince() {
            return since;
        }

        public int getId() {
            return id;
        }

        public boolean isVillager() {
            return villager;
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
            } else if (this == VILLAGER_FARMER) {
                return EnumZombieTypeEnum.VILLAGER_FARMER();
            } else if (this == VILLAGER_LIBRARIAN) {
                return EnumZombieTypeEnum.VILLAGER_LIBRARIAN();
            } else if (this == VILLAGER_PRIEST) {
                return EnumZombieTypeEnum.VILLAGER_PRIEST();
            } else if (this == VILLAGER_SMITH) {
                return EnumZombieTypeEnum.VILLAGER_SMITH();
            } else if (this == VILLAGER_BUTCHER) {
                return EnumZombieTypeEnum.VILLAGER_BUTCHER();
            } else {
                throw new IllegalStateException("Couldn't find this skeleton type enum '" + name() + "'");
            }
        }
        public static @NotNull Zombie.Type fromEnum(@NotNull EnumZombieTypeEnum.EnumZombieType enumZombieType) {
            if (enumZombieType.name().equals("NORMAL")) {
                return NORMAL;
            } else if (enumZombieType.name().equals("VILLAGER_FARMER")) {
                return VILLAGER_FARMER;
            } else if (enumZombieType.name().equals("VILLAGER_LIBRARIAN")) {
                return VILLAGER_LIBRARIAN;
            } else if (enumZombieType.name().equals("VILLAGER_PRIEST")) {
                return VILLAGER_PRIEST;
            } else if (enumZombieType.name().equals("VILLAGER_SMITH")) {
                return VILLAGER_SMITH;
            } else if (enumZombieType.name().equals("VILLAGER_BUTCHER")) {
                return VILLAGER_BUTCHER;
            } else {
                throw new IllegalStateException("Couldn't find this skeleton type from enum '" + enumZombieType.name() + "'");
            }
        }

        public static @NotNull Zombie.Type getById(int id) {
            for (Type type : Type.values()) {
                if (type.isCompatible() && type.getId() == id) {
                    return type;
                }
            }
            throw new NullPointerException("Couldn't find a zombie type with id '" + id + "'");
        }
    }

}
