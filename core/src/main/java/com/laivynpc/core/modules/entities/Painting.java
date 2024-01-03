package com.laivynpc.core.modules.entities;

import com.laivynpc.core.modules.Entity;
import org.bukkit.Art;
import org.jetbrains.annotations.NotNull;

public interface Painting extends Entity {
    @NotNull Art getArt();
    boolean setArt(@NotNull Art art);
}
