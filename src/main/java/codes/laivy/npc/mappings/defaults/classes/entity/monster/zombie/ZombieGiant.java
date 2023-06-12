package codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie;

import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class ZombieGiant extends EntityLiving {
    public ZombieGiant(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull ZombieGiantClass getClassExecutor() {
        return (ZombieGiantClass) laivynpc().getVersion().getClassExec("Entity:Zombie:Giant");
    }

    public static class ZombieGiantClass extends EntityLivingClass {
        public ZombieGiantClass(@NotNull String className) {
            super(className);
        }
    }
}
