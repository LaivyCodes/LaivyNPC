package com.laivynpc.core.modules.entities;

import com.laivynpc.core.modules.Entity;
import org.bukkit.DyeColor;
import org.jetbrains.annotations.NotNull;

public interface Wolf extends Entity {
    boolean isAngry();

    void setAngry(boolean angry);

    boolean isSitting();

    void setSitting(boolean sitting);

    @NotNull DyeColor getCollarColor();

    void setCollarColor(@NotNull DyeColor color);
}
