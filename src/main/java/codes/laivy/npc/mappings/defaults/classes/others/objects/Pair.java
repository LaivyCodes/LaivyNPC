package codes.laivy.npc.mappings.defaults.classes.others.objects;

import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.versions.V1_16_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Pair extends ObjectExecutor {

    static {
        if (!ReflectionUtils.isCompatible(V1_16_R1.class)) {
            throw new UnsupportedOperationException("This class is only available at 1.16+");
        }
    }

    public Pair(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull PairClass getClassExecutor() {
        return (PairClass) laivynpc().getVersion().getClassExec("Pair");
    }

    public static class PairClass extends ClassExecutor {

        public PairClass(@NotNull String className) {
            super(className);
        }
    }
}
