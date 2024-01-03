package com.laivynpc.core.modules.entities;

import com.laivynpc.core.modules.Entity;
import org.bukkit.entity.Horse.Variant;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.entity.Horse.*;

public interface Horse extends Entity {
    @NotNull Variant getVariant();
    void setVariant(@NotNull Variant variant);

    @NotNull Color getColor();
    void setColor(@NotNull Color color);

    @NotNull Style getStyle();
    void setStyle(@NotNull Style style);

    boolean isCarryingChest();
    void setCarryingChest(boolean chest);
}
