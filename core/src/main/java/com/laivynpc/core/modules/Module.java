package com.laivynpc.core.modules;

import com.laivynpc.core.utils.Version;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.jetbrains.annotations.NotNull;

public interface Module {

    @NotNull ClassLoader getClassLoader();

    @NotNull String @NotNull [] getCompatibleVersions();

    @NotNull Version getVersion();

    @NotNull EntityType getEntiyType(@NotNull Object object);

    @NotNull Entity create(@NotNull Location location, @NotNull EntityType type);

}
