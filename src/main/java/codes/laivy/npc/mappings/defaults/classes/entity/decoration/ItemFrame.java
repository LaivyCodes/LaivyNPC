package codes.laivy.npc.mappings.defaults.classes.entity.decoration;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.java.IntegerObjExec;
import codes.laivy.npc.mappings.defaults.classes.others.objects.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class ItemFrame extends Entity {
    public ItemFrame(@Nullable Object value) {
        super(value);
    }

    public @NotNull org.bukkit.inventory.ItemStack getItem() {
        return laivynpc().getVersion().getEntityItemFrameItem(this);
    }
    public void setItem(@NotNull org.bukkit.inventory.ItemStack item) {
        laivynpc().getVersion().setEntityItemFrameItem(this, item);
    }

    public int getRotation() {
        return laivynpc().getVersion().getEntityItemFrameRotation(this);
    }
    public void setRotation(int rotation) {
        laivynpc().getVersion().setEntityItemFrameRotation(this, rotation);
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
