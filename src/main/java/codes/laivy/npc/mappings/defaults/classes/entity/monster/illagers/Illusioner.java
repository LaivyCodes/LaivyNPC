package codes.laivy.npc.mappings.defaults.classes.entity.monster.illagers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Illusioner extends IllagerWizard {
    public Illusioner(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull IllusionerClass getClassExecutor() {
        return (IllusionerClass) laivynpc().getVersion().getClassExec("Entity:Illusioner");
    }

    public static class IllusionerClass extends IllagerWizardClass {
        public IllusionerClass(@NotNull String className) {
            super(className);
        }
    }
}
