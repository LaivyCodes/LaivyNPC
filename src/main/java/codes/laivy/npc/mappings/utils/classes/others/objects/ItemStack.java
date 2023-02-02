package codes.laivy.npc.mappings.utils.classes.others.objects;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.MethodExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.utils.classes.java.ItemStackObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static codes.laivy.npc.LaivyNPC.*;

public class ItemStack extends ObjectExecutor {
    @NotNull
    public static ItemStack getNMSItemStack(@NotNull org.bukkit.inventory.ItemStack item) {
        MethodExecutor method = new MethodExecutor(laivynpc().getVersion().getClassExec("CraftItemStack"), laivynpc().getVersion().getClassExec("ItemStack"), "asNMSCopy", "Gets a NMS ItemStack from a Craft ItemStack", ClassExecutor.ITEMSTACK);
        method.load();

        return new ItemStack(method.invokeStatic(new ItemStackObjExec(item)));
    }

    public ItemStack(@Nullable Object value) {
        super(value);
    }

    public @NotNull CraftItemStack getCraftItemStack() {
        return new CraftItemStack(Objects.requireNonNull(laivynpc().getVersion().getMethodExec("CraftItemStack:asCraftMirror").invokeStatic(this)));
    }

    @Override
    public @NotNull ItemStackClass getClassExecutor() {
        return (ItemStackClass) laivynpc().getVersion().getClassExec("ItemStack");
    }

    public static class ItemStackClass extends ClassExecutor {
        public ItemStackClass(@NotNull String className) {
            super(className);
        }
    }
}
