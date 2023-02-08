package codes.laivy.npc.mappings.defaults.classes.entity.monster.skeleton;

import codes.laivy.npc.mappings.versions.V1_11_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class SkeletonWither extends Skeleton {
    public SkeletonWither(@Nullable Object value) {
        super(value);
        if (!ReflectionUtils.isCompatible(V1_11_R1.class)) {
            throw new IllegalStateException("This skeleton type is compatible only with 1.11+");
        }
    }

    @Override
    public @NotNull SkeletonClass getClassExecutor() {
        if (ReflectionUtils.isCompatible(V1_11_R1.class)) {
            return (SkeletonStray.SkeletonStrayClass) laivynpc().getVersion().getClassExec("Entity:Skeleton:Wither");
        } else {
            return (SkeletonClass) laivynpc().getVersion().getClassExec("Entity:Skeleton");
        }
    }

    public static class SkeletonWitherClass extends SkeletonClass {
        public SkeletonWitherClass(@NotNull String className) {
            super(className);
        }
    }
}
