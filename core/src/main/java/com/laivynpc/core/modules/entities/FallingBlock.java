package com.laivynpc.core.modules.entities;

import com.laivynpc.core.modules.Entity;
import org.bukkit.Material;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;

public interface FallingBlock extends Entity {
    @NotNull Material getMaterial();
    void setMaterial(@NotNull Material material);
}
