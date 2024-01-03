package com.laivynpc.core.modules.entities;

import com.laivynpc.core.modules.Entity;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;

public interface Enderman extends Entity {
    @NotNull MaterialData getCarry();
    void setCarry(@NotNull MaterialData carry);
}
