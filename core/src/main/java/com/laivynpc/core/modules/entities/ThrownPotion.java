package com.laivynpc.core.modules.entities;

import com.laivynpc.core.modules.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface ThrownPotion extends Entity {
    @NotNull Collection<PotionEffect> getEffects();

    @NotNull ItemStack getItem();

    void setItem(@NotNull ItemStack item);
}
