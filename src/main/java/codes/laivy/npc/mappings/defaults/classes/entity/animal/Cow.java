package codes.laivy.npc.mappings.defaults.classes.entity.animal;

import codes.laivy.npc.mappings.defaults.classes.entity.AgeableEntityLiving;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Cow extends AgeableEntityLiving {
    public Cow(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull CowClass getClassExecutor() {
        return (CowClass) laivynpc().getVersion().getClassExec("Entity:Cow");
    }

    public static class CowClass extends AgeableLivingEntityClass {
        public CowClass(@NotNull String className) {
            super(className);
        }
    }
}
