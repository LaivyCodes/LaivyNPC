package codes.laivy.npc.mappings.defaults.classes.others.inventories;

import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.versions.V1_14_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class InventorySubcontainer extends ObjectExecutor {

    static {
        if (!ReflectionUtils.isCompatible(V1_14_R1.class)) {
            throw new UnsupportedOperationException("This class is only available at 1.14+");
        }
    }

    public InventorySubcontainer(@Nullable Object value) {
        super(value);
    }

    public void setItem(int slot, @NotNull ItemStack itemStack) {
        V1_14_R1 version = (V1_14_R1) laivynpc().getVersion();
        version.inventorySubcontainerSetItem(this, slot, codes.laivy.npc.mappings.defaults.classes.others.objects.ItemStack.getNMSItemStack(itemStack));
    }
    public @NotNull ItemStack getItem(int slot) {
        V1_14_R1 version = (V1_14_R1) laivynpc().getVersion();
        return version.inventorySubcontainerGetItem(this, slot).getCraftItemStack().getItemStack();
    }

    @Override
    public @NotNull InventorySubcontainerClass getClassExecutor() {
        return (InventorySubcontainerClass) laivynpc().getVersion().getClassExec("InventorySubcontainer");
    }

    public static final class InventorySubcontainerClass extends ClassExecutor {
        public InventorySubcontainerClass(@NotNull String className) {
            super(className);
        }
    }
}
