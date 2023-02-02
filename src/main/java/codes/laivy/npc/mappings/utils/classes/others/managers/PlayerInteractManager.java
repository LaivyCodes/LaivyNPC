package codes.laivy.npc.mappings.utils.classes.others.managers;

import codes.laivy.npc.LaivyNPC;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerInteractManager extends ObjectExecutor {
    public PlayerInteractManager(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull PlayerInteractManagerClass getClassExecutor() {
        return (PlayerInteractManagerClass) LaivyNPC.laivynpc().getVersion().getClassExec("PlayerInteractManager");
    }

    public static class PlayerInteractManagerClass extends ClassExecutor {
        public PlayerInteractManagerClass(@NotNull String className) {
            super(className);
        }
    }
}
