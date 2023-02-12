package codes.laivy.npc.mappings.defaults.classes.others.location;

import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import org.bukkit.Location;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Vec3D extends ObjectExecutor {
    public Vec3D(@Nullable Object value) {
        super(value);
    }

    public final double getX() {
        //noinspection DataFlowIssue
        return (double) laivynpc().getVersion().getMethodExec("Vec3D:getX").invokeInstance(this);
    }
    public final double getY() {
        //noinspection DataFlowIssue
        return (double) laivynpc().getVersion().getMethodExec("Vec3D:getY").invokeInstance(this);
    }
    public final double getZ() {
        //noinspection DataFlowIssue
        return (double) laivynpc().getVersion().getMethodExec("Vec3D:getZ").invokeInstance(this);
    }

    public final @NotNull Location toLocation(@NotNull World world) {
        return new Location(world, getX(), getY(), getZ());
    }

    @Override
    public @NotNull Vec3DClass getClassExecutor() {
        return (Vec3DClass) laivynpc().getVersion().getClassExec("Vec3D");
    }

    public static class Vec3DClass extends ClassExecutor {
        public Vec3DClass(@NotNull String className) {
            super(className);
        }
    }
}
