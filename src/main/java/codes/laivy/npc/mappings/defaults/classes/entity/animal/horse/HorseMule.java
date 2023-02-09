package codes.laivy.npc.mappings.defaults.classes.entity.animal.horse;

import codes.laivy.npc.mappings.versions.V1_11_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class HorseMule extends AbstractChestedHorse {
    public HorseMule(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull AbstractHorseClass getClassExecutor() {
        if (ReflectionUtils.isCompatible(V1_11_R1.class)) {
            return (HorseMuleClass) laivynpc().getVersion().getClassExec("Entity:Horse:Mule");
        } else {
            return (AbstractHorseClass) laivynpc().getVersion().getClassExec("Entity:Horse");
        }
    }

    public static class HorseMuleClass extends AbstractHorseClass {
        public HorseMuleClass(@NotNull String className) {
            super(className);
        }
    }
}
