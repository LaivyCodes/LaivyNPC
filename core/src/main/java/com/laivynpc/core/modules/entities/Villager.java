package com.laivynpc.core.modules.entities;

import com.laivynpc.core.modules.Entity;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.entity.Villager.*;

public interface Villager extends Entity {
    @NotNull Profession getProfession();
    void setProfession(@NotNull Profession profession);
}
