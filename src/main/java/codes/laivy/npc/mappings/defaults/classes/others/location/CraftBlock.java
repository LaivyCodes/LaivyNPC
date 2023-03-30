package codes.laivy.npc.mappings.defaults.classes.others.location;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class CraftBlock extends ObjectExecutor {

    public static @NotNull CraftBlock get(@NotNull Block block) {
        return new CraftBlock(block);
    }

    // ---/-/--- //

    public CraftBlock(@Nullable Object value) {
        super(value);
    }

    public @NotNull codes.laivy.npc.mappings.defaults.classes.others.objects.Block getNMSBlock() {
        return laivynpc().getVersion().getNMSBlock(this);
    }

    @Override
    public @NotNull CraftBlockClass getClassExecutor() {
        return (CraftBlockClass) laivynpc().getVersion().getClassExec("CraftBlock");
    }

    public static class CraftBlockClass extends ClassExecutor {
        public CraftBlockClass(@NotNull String className) {
            super(className);
        }
    }
}
