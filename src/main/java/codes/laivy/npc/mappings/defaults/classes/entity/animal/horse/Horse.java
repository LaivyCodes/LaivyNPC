package codes.laivy.npc.mappings.defaults.classes.entity.animal.horse;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Horse extends AbstractHorse {
    public Horse(@Nullable Object value) {
        super(value);
    }

    public @NotNull Type getType() {
        return laivynpc().getVersion().getEntityHorseType(this);
    }

    @Override
    public @NotNull HorseClass getClassExecutor() {
        return (HorseClass) laivynpc().getVersion().getClassExec("Entity:Horse");
    }

    public static class HorseClass extends AgeableLivingEntityClass {
        public HorseClass(@NotNull String className) {
            super(className);
        }
    }

}
