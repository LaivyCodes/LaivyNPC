package codes.laivy.npc.mappings.defaults.classes.others.location;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class BlockPosition extends ObjectExecutor {

    public static @NotNull BlockPosition create(int x, int y, int z) {
        return laivynpc().getVersion().createBlockPosition(x, y, z);
    }

    // ---/-/--- //

    public BlockPosition(@Nullable Object value) {
        super(value);
    }

    public int getX() {
        //noinspection DataFlowIssue
        return (int) laivynpc().getVersion().getMethodExec("BlockPosition:getX").invokeInstance(this);
    }
    public int getY() {
        //noinspection DataFlowIssue
        return (int) laivynpc().getVersion().getMethodExec("BlockPosition:getY").invokeInstance(this);
    }
    public int getZ() {
        //noinspection DataFlowIssue
        return (int) laivynpc().getVersion().getMethodExec("BlockPosition:getZ").invokeInstance(this);
    }

    @Override
    public @NotNull BlockPositionClass getClassExecutor() {
        return (BlockPositionClass) laivynpc().getVersion().getClassExec("BlockPosition");
    }

    public static class BlockPositionClass extends ClassExecutor {
        public BlockPositionClass(@NotNull String className) {
            super(className);
        }
    }
}
