package codes.laivy.npc.mappings.utils.classes.others.location;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.MethodExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class CraftWorld extends ObjectExecutor {

    public static CraftWorld getCraftWorld(@NotNull org.bukkit.World world) {
        return new CraftWorld(laivynpc().getVersion().getClassExec("CraftWorld").cast(world));
    }

    // ---/-/--- //

    public CraftWorld(@Nullable Object value) {
        super(value);
    }

    public WorldServer getHandle() {
        MethodExecutor method = new MethodExecutor(laivynpc().getVersion().getClassExec("CraftWorld"), laivynpc().getVersion().getClassExec("WorldServer"), "getHandle", "Gets the NMS WorldServer from a CraftWorld");
        method.load();

        return new WorldServer(method.invokeInstance(this));
    }

    @Override
    public @NotNull CraftWorldClass getClassExecutor() {
        return (CraftWorldClass) laivynpc().getVersion().getClassExec("CraftWorld");
    }

    public static class CraftWorldClass extends ClassExecutor {
        public CraftWorldClass(@NotNull String className) {
            super(className);
        }
    }
}
