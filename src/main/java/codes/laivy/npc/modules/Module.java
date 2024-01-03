package codes.laivy.npc.modules;

import codes.laivy.npc.utils.Version;
import org.jetbrains.annotations.NotNull;

/**
 * The Module interface is responsible for translating inventories, signs, chats, items, and entity names.
 */
public interface Module {

    @NotNull ClassLoader getClassLoader();

    @NotNull String @NotNull [] getCompatibleVersions();

    @NotNull Version getVersion();

}
