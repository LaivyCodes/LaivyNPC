package codes.laivy.npc.mappings.utils.classes.others.location;

import codes.laivy.npc.LaivyNPC;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WorldServer extends World {
    public WorldServer(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull WorldServerClass getClassExecutor() {
        return (WorldServerClass) LaivyNPC.laivynpc().getVersion().getClassExec("WorldServer");
    }

    public static class WorldServerClass extends WorldClass {
        public WorldServerClass(@NotNull String className) {
            super(className);
        }
    }

}
