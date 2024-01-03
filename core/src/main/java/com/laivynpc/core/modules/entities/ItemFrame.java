package com.laivynpc.core.modules.entities;

import com.laivynpc.core.modules.Entity;
import org.bukkit.Rotation;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface ItemFrame extends Entity {
    @NotNull ItemStack getItemStack();
    void setItemStack(@NotNull ItemStack item);

    @NotNull Rotation getRotation();
    void setRotation(@NotNull Rotation rotation);
}
