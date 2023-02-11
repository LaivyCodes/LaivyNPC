package codes.laivy.npc.mappings.defaults.classes.entity.npc.objs;

import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.versions.V1_14_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class VillagerProfessionExec extends ObjectExecutor {

    static {
        if (!ReflectionUtils.isCompatible(V1_14_R1.class)) {
            throw new UnsupportedOperationException("This class is only available at 1.14+");
        }
    }

    public VillagerProfessionExec(@Nullable Object value) {
        super(value);
    }

    public @NotNull String getName() {
        return (String) Objects.requireNonNull(laivynpc().getVersion().getFieldExec("VillagerProfession:Name").invokeInstance(this));
    }

    @Override
    public @NotNull VillagerProfessionExec.VillagerProfessionExecClass getClassExecutor() {
        return (VillagerProfessionExecClass) laivynpc().getVersion().getClassExec("VillagerProfession");
    }

    public static class VillagerProfessionExecClass extends ClassExecutor {
        public VillagerProfessionExecClass(@NotNull String className) {
            super(className);
        }
    }
}
