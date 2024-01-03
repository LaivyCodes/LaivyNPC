package com.laivynpc.core.modules.entities;

import com.laivynpc.core.modules.Entity;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.entity.Ocelot.*;

public interface Ocelot extends Entity {
    @NotNull Type getCatType();
    void setCatType(@NotNull Type type);

    boolean isSitting();

    void setSitting(boolean sitting);
}
