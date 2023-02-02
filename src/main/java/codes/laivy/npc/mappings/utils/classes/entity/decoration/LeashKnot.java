package codes.laivy.npc.mappings.utils.classes.entity.decoration;

import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class LeashKnot extends Entity {
    public LeashKnot(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull LeashKnotClass getClassExecutor() {
        return (LeashKnotClass) laivynpc().getVersion().getClassExec("Entity:LeashKnot");
    }

    public static class LeashKnotClass extends EntityClass {
        public LeashKnotClass(@NotNull String className) {
            super(className);
        }
    }
}
