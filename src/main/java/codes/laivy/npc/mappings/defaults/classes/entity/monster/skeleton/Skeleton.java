package codes.laivy.npc.mappings.defaults.classes.entity.monster.skeleton;

import codes.laivy.npc.mappings.Version;
import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumSkeletonTypeEnum;
import codes.laivy.npc.mappings.versions.V1_10_R1;
import codes.laivy.npc.mappings.versions.V1_8_R1;
import codes.laivy.npc.mappings.versions.V1_9_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Skeleton extends EntityLiving {
    public Skeleton(@Nullable Object value) {
        super(value);
    }

    public @NotNull Skeleton.Type getType() {
        return laivynpc().getVersion().getEntitySkeletonType(this);
    }

    @Override
    public @NotNull SkeletonClass getClassExecutor() {
        return (SkeletonClass) laivynpc().getVersion().getClassExec("Entity:Skeleton");
    }

    public static class SkeletonClass extends EntityLivingClass {
        public SkeletonClass(@NotNull String className) {
            super(className);
        }
    }

    public enum Type {
        NORMAL(V1_8_R1.class, EntityType.SKELETON),
        WITHER(V1_8_R1.class, EntityType.SKELETON_WITHER),
        STRAY(V1_10_R1.class, EntityType.SKELETON_STRAY),
        ;

        private final @NotNull Class<? extends Version> since;
        private final @NotNull EntityType entityType;

        Type(@NotNull Class<? extends Version> since, @NotNull EntityType entityType) {
            this.since = since;
            this.entityType = entityType;
        }

        public @NotNull Class<? extends Version> getSince() {
            return since;
        }

        public @NotNull EnumSkeletonTypeEnum.EnumSkeletonType getEnum() {
            if (!ReflectionUtils.isCompatible(V1_9_R1.class)) {
                throw new IllegalArgumentException("This method is only available at 1.9+");
            }

            if (this == NORMAL) {
                return EnumSkeletonTypeEnum.NORMAL();
            } else if (this == WITHER) {
                return EnumSkeletonTypeEnum.WITHER();
            } else if (this == STRAY) {
                return EnumSkeletonTypeEnum.STRAY();
            } else {
                throw new IllegalStateException("Couldn't find this skeleton type enum '" + name() + "'");
            }
        }
        public static @NotNull Skeleton.Type fromEnum(@NotNull EnumSkeletonTypeEnum.EnumSkeletonType enumSkeletonType) {
            if (enumSkeletonType.name().equals("NORMAL")) {
                return NORMAL;
            } else if (enumSkeletonType.name().equals("WITHER")) {
                return WITHER;
            } else if (enumSkeletonType.name().equals("STRAY")) {
                return STRAY;
            } else {
                throw new IllegalStateException("Couldn't find this skeleton type from enum '" + enumSkeletonType.name() + "'");
            }
        }

        public @NotNull EntityType getEntityType() {
            return entityType;
        }

        public boolean isCompatible() {
            return ReflectionUtils.isCompatible(getSince());
        }
    }
}
