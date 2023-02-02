package codes.laivy.npc.mappings.utils.classes.others.objects;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Block extends ObjectExecutor {
    public Block(@Nullable Object value) {
        super(value);
    }

    public @NotNull IBlockData getData() {
        return new IBlockData(laivynpc().getVersion().getMethodExec("Block:getData").invokeInstance(this));
    }
    public @NotNull Material getMaterial() {
        return (Material) Objects.requireNonNull(laivynpc().getVersion().getMethodExec("CraftMagicNumbers:getMaterial").invokeStatic(this));
    }

    @Override
    public @NotNull BlockClass getClassExecutor() {
        return (BlockClass) laivynpc().getVersion().getClassExec("Block");
    }

    public static class BlockClass extends ClassExecutor {
        public BlockClass(@NotNull String className) {
            super(className);
        }
    }
}
