package com.laivynpc.core.api;

import com.laivynpc.core.modules.Module;
import com.laivynpc.core.utils.Version;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface NpcApi {

    @Nullable Module getModule();

    @NotNull Version getVersion();

    // Status management

    void load();

    void unload();

    boolean isLoaded();
}
