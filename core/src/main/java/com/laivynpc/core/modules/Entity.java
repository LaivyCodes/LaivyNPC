package com.laivynpc.core.modules;

import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

public interface Entity {

    @NotNull Object getHandle();

    @NotNull EntityType getType();

}
