package com.laivynpc.core.api;

import com.laivynpc.core.modules.Module;
import com.laivynpc.core.utils.Version;
import org.jetbrains.annotations.NotNull;

public interface NpcApi {

    @NotNull Module getModule();

    @NotNull Version getVersion();

    // Status management

    void load();

    void unload();

    boolean isLoaded();
}
