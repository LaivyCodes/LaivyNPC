package codes.laivy.npc.mappings.defaults.classes.others.server;

import codes.laivy.npc.LaivyNPC;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MinecraftServer extends ObjectExecutor {
    public MinecraftServer(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull MinecraftServerClass getClassExecutor() {
        return (MinecraftServerClass) LaivyNPC.laivynpc().getVersion().getClassExec("MinecraftServer");
    }

    public static class MinecraftServerClass extends ClassExecutor {
        public MinecraftServerClass(@NotNull String className) {
            super(className);
        }
    }
}
