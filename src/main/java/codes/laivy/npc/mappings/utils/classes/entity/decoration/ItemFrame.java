package codes.laivy.npc.mappings.utils.classes.entity.decoration;

import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import codes.laivy.npc.mappings.utils.classes.java.IntegerObjExec;
import codes.laivy.npc.mappings.utils.classes.others.objects.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class ItemFrame extends Entity {
    public ItemFrame(@Nullable Object value) {
        super(value);
    }

    public @NotNull org.bukkit.inventory.ItemStack getItem() {
        return new ItemStack(laivynpc().getVersion().getMethodExec("Entity:ItemFrame:getItem").invokeInstance(this)).getCraftItemStack().getItemStack();
    }
    public void setItem(@NotNull org.bukkit.inventory.ItemStack item) {
        laivynpc().getVersion().getMethodExec("Entity:ItemFrame:setItem").invokeInstance(this, ItemStack.getNMSItemStack(item));
    }

    public int getRotation() {
        //noinspection DataFlowIssue
        return (int) laivynpc().getVersion().getMethodExec("Entity:ItemFrame:getRotation").invokeInstance(this);
    }
    public void setRotation(int rotation) {
        laivynpc().getVersion().getMethodExec("Entity:ItemFrame:setRotation").invokeInstance(this, new IntegerObjExec(rotation));
    }

    @Override
    public @NotNull ItemFrameClass getClassExecutor() {
        return (ItemFrameClass) laivynpc().getVersion().getClassExec("Entity:ItemFrame");
    }

    public static class ItemFrameClass extends EntityClass {
        public ItemFrameClass(@NotNull String className) {
            super(className);
        }
    }
}
