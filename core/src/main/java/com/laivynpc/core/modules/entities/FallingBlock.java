package com.laivynpc.core.modules.entities;

import com.laivynpc.core.modules.Entity;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;

public interface FallingBlock extends Entity {
    @NotNull MaterialData getMaterial();
    void setMaterial(@NotNull MaterialData material);
}
