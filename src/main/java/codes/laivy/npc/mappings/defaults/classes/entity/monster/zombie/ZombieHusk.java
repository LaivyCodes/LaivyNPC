package codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie;

import codes.laivy.npc.mappings.versions.V1_11_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class ZombieHusk extends Zombie {
    public ZombieHusk(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull ZombieClass getClassExecutor() {
        if (ReflectionUtils.isCompatible(V1_11_R1.class)) {
            return (ZombieHuskClass) laivynpc().getVersion().getClassExec("Entity:Zombie:Husk");
        } else {
            return (ZombieClass) laivynpc().getVersion().getClassExec("Entity:Zombie");
        }
    }

    public static class ZombieHuskClass extends ZombieClass {
        public ZombieHuskClass(@NotNull String className) {
            super(className);
        }
    }
}
