package codes.laivy.npc.mappings.defaults.classes.entity.animal.horse;

import codes.laivy.npc.mappings.versions.V1_11_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class HorseDonkey extends AbstractChestedHorse {
    public HorseDonkey(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull AbstractHorseClass getClassExecutor() {
        if (ReflectionUtils.isCompatible(V1_11_R1.class)) {
            return (HorseDonkeyClass) laivynpc().getVersion().getClassExec("Entity:Horse:Donkey");
        } else {
            return (AbstractHorseClass) laivynpc().getVersion().getClassExec("Entity:Horse");
        }
    }

    public static class HorseDonkeyClass extends AbstractHorseClass {
        public HorseDonkeyClass(@NotNull String className) {
            super(className);
        }
    }
}
