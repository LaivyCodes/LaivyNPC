package codes.laivy.npc.api;

import codes.laivy.npc.modules.Module;
import codes.laivy.npc.utils.Version;
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
