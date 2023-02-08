package codes.laivy.npc.mappings.defaults.classes.others.location;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.MethodExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.defaults.classes.java.IntegerObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class World extends ObjectExecutor {
    public World(@Nullable Object value) {
        super(value);
    }

    public @NotNull CraftWorld getCraftWorld() {
        MethodExecutor method = new MethodExecutor(getClassExecutor(), laivynpc().getVersion().getClassExec("CraftWorld"), "getWorld", "Gets the CraftWorld of a NMS World");
        method.load();

        return new CraftWorld(method.invokeInstance(this));
    }

    public @Nullable Object getEntityById(int id) {
        return laivynpc().getVersion().getMethodExec("World:getEntityById").invokeInstance(this, new IntegerObjExec(id));
    }

    @Override
    public @NotNull WorldClass getClassExecutor() {
        return (WorldClass) laivynpc().getVersion().getClassExec("World");
    }

    public static class WorldClass extends ClassExecutor {
        public WorldClass(@NotNull String className) {
            super(className);
        }
    }

}
