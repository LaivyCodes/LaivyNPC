package codes.laivy.npc.mappings.defaults.classes.entity.animal.fish;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Salmon extends Fish {
    public Salmon(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull SalmonClass getClassExecutor() {
        return (SalmonClass) laivynpc().getVersion().getClassExec("Entity:Salmon");
    }

    public static class SalmonClass extends FishClass {
        public SalmonClass(@NotNull String className) {
            super(className);
        }
    }
}
