package codes.laivy.npc.mappings.defaults.classes.others.location;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.MethodExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.versions.V1_8_R1;
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
        return new WorldServer(laivynpc().getVersion().getMethodExec("CraftWorld:getHandle").invokeInstance(this));
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
