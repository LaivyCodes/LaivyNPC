package com.laivynpc.core.modules.entities;

import com.laivynpc.core.modules.Entity;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.jetbrains.annotations.NotNull;

public interface Skeleton extends Entity {
    @NotNull SkeletonType getSkeletonType();
    void setSkeletonType(@NotNull SkeletonType type);
}
