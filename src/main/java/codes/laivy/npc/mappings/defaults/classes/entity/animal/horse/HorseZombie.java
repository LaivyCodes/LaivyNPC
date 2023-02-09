package codes.laivy.npc.mappings.defaults.classes.entity.animal.horse;

import codes.laivy.npc.mappings.versions.V1_11_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class HorseZombie extends Horse {
    public HorseZombie(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull HorseClass getClassExecutor() {
        if (ReflectionUtils.isCompatible(V1_11_R1.class)) {
            return (HorseZombieClass) laivynpc().getVersion().getClassExec("Entity:Horse:Zombie");
        } else {
            return (HorseClass) laivynpc().getVersion().getClassExec("Entity:Horse");
        }
    }

    public static class HorseZombieClass extends HorseClass {
        public HorseZombieClass(@NotNull String className) {
            super(className);
        }
    }
}
