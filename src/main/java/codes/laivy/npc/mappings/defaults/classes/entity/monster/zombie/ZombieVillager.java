package codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie;

import codes.laivy.npc.mappings.versions.V1_11_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class ZombieVillager extends Zombie {
    public ZombieVillager(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull ZombieClass getClassExecutor() {
        if (ReflectionUtils.isCompatible(V1_11_R1.class)) {
            return (ZombieVillagerClass) laivynpc().getVersion().getClassExec("Entity:Zombie:Villager");
        } else {
            return (ZombieClass) laivynpc().getVersion().getClassExec("Entity:Zombie");
        }
    }

    public static class ZombieVillagerClass extends ZombieClass {
        public ZombieVillagerClass(@NotNull String className) {
            super(className);
        }
    }
}
