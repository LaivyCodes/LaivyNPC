package codes.laivy.npc.mappings.utils.classes.entity.ambient;

import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Egg extends Entity {
    public Egg(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull EggClass getClassExecutor() {
        return (EggClass) laivynpc().getVersion().getClassExec("Entity:Egg");
    }

    public static class EggClass extends Entity.EntityClass {
        public EggClass(@NotNull String className) {
            super(className);
        }
    }
}
