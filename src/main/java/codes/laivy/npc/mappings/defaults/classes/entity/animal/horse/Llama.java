package codes.laivy.npc.mappings.defaults.classes.entity.animal.horse;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Llama extends AbstractChestedHorse {
    public Llama(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull LlamaClass getClassExecutor() {
        return (LlamaClass) laivynpc().getVersion().getClassExec("Entity:Llama");
    }

    public static class LlamaClass extends AbstractChestedHorseClass {
        public LlamaClass(@NotNull String className) {
            super(className);
        }
    }
}
