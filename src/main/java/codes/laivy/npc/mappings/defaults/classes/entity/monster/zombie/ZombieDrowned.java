package codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class ZombieDrowned extends Zombie {
    public ZombieDrowned(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull ZombieDrownedClass getClassExecutor() {
        return (ZombieDrownedClass) laivynpc().getVersion().getClassExec("Entity:Zombie:Drowned");
    }

    public static class ZombieDrownedClass extends ZombieClass {
        public ZombieDrownedClass(@NotNull String className) {
            super(className);
        }
    }
}
