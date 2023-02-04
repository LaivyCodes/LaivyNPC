package codes.laivy.npc.mappings.utils.classes.entity.monster;

import codes.laivy.npc.mappings.Version;
import codes.laivy.npc.mappings.utils.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.utils.classes.enums.EnumSkeletonTypeEnum;
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

    public @NotNull SkeletonType getType() {
        return laivynpc().getVersion().getEntitySkeletonType(this);
    }
    public void setType(@NotNull SkeletonType type) {
        laivynpc().getVersion().setEntitySkeletonType(this, type);
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

    public enum SkeletonType {
        NORMAL(V1_8_R1.class),
        WITHER(V1_8_R1.class),
        STRAY(V1_10_R1.class),
        ;

        private final @NotNull Class<? extends Version> since;

        SkeletonType(@NotNull Class<? extends Version> since) {
            this.since = since;
        }

        public @NotNull Class<? extends Version> getSince() {
            return since;
        }

        public @NotNull EnumSkeletonTypeEnum.EnumSkeletonType getEnum() {
            if (!ReflectionUtils.isCompatible(V1_9_R1.class)) {
                throw new IllegalArgumentException("This method is only available at 1.9+");
            }
        }

        public boolean isCompatible() {
            return ReflectionUtils.isCompatible(getSince());
        }
    }
}
