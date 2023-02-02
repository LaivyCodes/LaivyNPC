package codes.laivy.npc.mappings.utils.classes.entity.animal;

import codes.laivy.npc.mappings.utils.classes.entity.AgeableLivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Chicken extends AgeableLivingEntity {
    public Chicken(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull ChickenClass getClassExecutor() {
        return (ChickenClass) laivynpc().getVersion().getClassExec("Entity:Chicken");
    }

    public static class ChickenClass extends AgeableLivingEntityClass {
        public ChickenClass(@NotNull String className) {
            super(className);
        }
    }
}
