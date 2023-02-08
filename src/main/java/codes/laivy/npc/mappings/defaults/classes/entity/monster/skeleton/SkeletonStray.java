package codes.laivy.npc.mappings.defaults.classes.entity.monster.skeleton;

import codes.laivy.npc.mappings.versions.V1_11_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class SkeletonStray extends Skeleton {
    public SkeletonStray(@Nullable Object value) {
        super(value);
        if (!ReflectionUtils.isCompatible(V1_11_R1.class)) {
            throw new IllegalStateException("This skeleton type is compatible only with 1.11+");
        }
    }

    @Override
    public @NotNull SkeletonClass getClassExecutor() {
        if (ReflectionUtils.isCompatible(V1_11_R1.class)) {
            return (SkeletonStrayClass) laivynpc().getVersion().getClassExec("Entity:Skeleton:Stray");
        } else {
            return (SkeletonClass) laivynpc().getVersion().getClassExec("Entity:Skeleton");
        }
    }

    public static class SkeletonStrayClass extends SkeletonClass {
        public SkeletonStrayClass(@NotNull String className) {
            super(className);
        }
    }
}
