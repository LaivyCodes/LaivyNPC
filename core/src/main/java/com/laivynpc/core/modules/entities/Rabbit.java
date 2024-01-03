package com.laivynpc.core.modules.entities;

import com.laivynpc.core.modules.Entity;
import org.bukkit.entity.Rabbit.Type;
import org.jetbrains.annotations.NotNull;

public interface Rabbit extends Entity {
    @NotNull Type getRabbitType();
    void setRabbitType(@NotNull Type type);
}
