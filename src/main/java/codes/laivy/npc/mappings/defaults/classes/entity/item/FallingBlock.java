package codes.laivy.npc.mappings.defaults.classes.entity.item;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class FallingBlock extends Entity {
    public FallingBlock(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull FallingBlockClass getClassExecutor() {
        return (FallingBlockClass) laivynpc().getVersion().getClassExec("Entity:FallingBlock");
    }

    public static class FallingBlockClass extends EntityClass {
        public FallingBlockClass(@NotNull String className) {
            super(className);
        }
    }
}
