package codes.laivy.npc.mappings.defaults.classes.entity.animal.fish;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Cod extends Fish {
    public Cod(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull CodClass getClassExecutor() {
        return (CodClass) laivynpc().getVersion().getClassExec("Entity:Cod");
    }

    public static class CodClass extends Fish.FishClass {
        public CodClass(@NotNull String className) {
            super(className);
        }
    }
}
