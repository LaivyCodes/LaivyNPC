package codes.laivy.npc.mappings.utils.classes.others.server;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.MethodExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import org.bukkit.Server;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class CraftServer extends ObjectExecutor {

    @NotNull
    public static CraftServer getCraftServer(@NotNull Server server) {
        return new CraftServer(laivynpc().getVersion().getClassExec("CraftServer").cast(server));
    }

    // ---/-/--- //

    public CraftServer(@Nullable Object value) {
        super(value);
    }

    public MinecraftServer getServer() {
        MethodExecutor method = new MethodExecutor(laivynpc().getVersion().getClassExec("CraftServer"), laivynpc().getVersion().getClassExec("MinecraftServer"), "getServer", "Gets the NMS MinecraftServer from a CraftServer");
        method.load();

        return new MinecraftServer(method.invokeInstance(this));
    }

    @Override
    public @NotNull CraftServerClass getClassExecutor() {
        return (CraftServerClass) laivynpc().getVersion().getClassExec("CraftServer");
    }

    public static class CraftServerClass extends ClassExecutor {
        public CraftServerClass(@NotNull String className) {
            super(className);
        }
    }
}
