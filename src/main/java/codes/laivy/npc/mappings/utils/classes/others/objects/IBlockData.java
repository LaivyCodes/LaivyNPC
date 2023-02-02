package codes.laivy.npc.mappings.utils.classes.others.objects;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class IBlockData extends ObjectExecutor {
    public IBlockData(@Nullable Object value) {
        super(value);
    }

    public @NotNull Block getBlock() {
        return new Block(laivynpc().getVersion().getMethodExec("IBlockData:getBlock").invokeInstance(this));
    }

    @Override
    public @NotNull IBlockDataClass getClassExecutor() {
        return (IBlockDataClass) laivynpc().getVersion().getClassExec("IBlockData");
    }

    public static class IBlockDataClass extends ClassExecutor {
        public IBlockDataClass(@NotNull String className) {
            super(className);
        }
    }
}
