package codes.laivy.npc.mappings.utils.classes.entity.animal;

import codes.laivy.npc.mappings.utils.classes.entity.AgeableLivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Cow extends AgeableLivingEntity {
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