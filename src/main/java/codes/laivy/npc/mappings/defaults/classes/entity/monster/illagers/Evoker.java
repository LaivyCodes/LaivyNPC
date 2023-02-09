package codes.laivy.npc.mappings.defaults.classes.entity.monster.illagers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Evoker extends IllagerWizard {
    public Evoker(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull EvokerClass getClassExecutor() {
        return (EvokerClass) laivynpc().getVersion().getClassExec("Entity:Evoker");
    }

    public static class EvokerClass extends IllagerWizardClass {
        public EvokerClass(@NotNull String className) {
            super(className);
        }
    }
}
