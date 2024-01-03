package com.laivynpc.core.modules.entities;

import com.laivynpc.core.modules.Entity;
import org.bukkit.inventory.meta.FireworkMeta;
import org.jetbrains.annotations.NotNull;

public interface Firework extends Entity {
    @NotNull FireworkMeta getFireworkMeta();
    void setFireworkMeta(@NotNull FireworkMeta meta);

    void detonate();
}
