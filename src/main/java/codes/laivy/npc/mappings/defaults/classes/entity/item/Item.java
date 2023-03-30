package codes.laivy.npc.mappings.defaults.classes.entity.item;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Item extends Entity {
    public Item(@Nullable Object value) {
        super(value);
    }

    public @NotNull ItemStack getItemStack() {
        return laivynpc().getVersion().getEntityItemItemStack(this);
    }
    public void setItemStack(@NotNull ItemStack item) {
        laivynpc().getVersion().setEntityItemItemStack(this, item);
    }

    @Override
    public @NotNull ItemClass getClassExecutor() {
        return (ItemClass) laivynpc().getVersion().getClassExec("Entity:Item");
    }

    public static class ItemClass extends EntityClass {
        public ItemClass(@NotNull String className) {
            super(className);
        }
    }
}
