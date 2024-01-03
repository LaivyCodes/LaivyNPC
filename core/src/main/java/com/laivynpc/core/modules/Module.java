package com.laivynpc.core.modules;

import com.laivynpc.core.utils.Version;
import org.jetbrains.annotations.NotNull;

/**
 * The Module interface is responsible for translating inventories, signs, chats, items, and entity names.
 */
public interface Module {

    @NotNull ClassLoader getClassLoader();

    @NotNull String @NotNull [] getCompatibleVersions();

    @NotNull Version getVersion();

}
