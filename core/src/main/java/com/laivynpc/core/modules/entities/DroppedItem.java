package com.laivynpc.core.modules.entities;

import com.laivynpc.core.modules.Entity;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface DroppedItem extends Entity {
    @NotNull ItemStack getItemStack();
    void setItemStack(@NotNull ItemStack item);
}
