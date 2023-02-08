package codes.laivy.npc.mappings.defaults.classes.datawatcher;

import org.jetbrains.annotations.Nullable;

public interface VersionedDataWatcherObject {

    @Nullable Object get();
    void set(@Nullable Object object);

}
