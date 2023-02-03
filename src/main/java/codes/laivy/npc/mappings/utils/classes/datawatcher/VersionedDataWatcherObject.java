package codes.laivy.npc.mappings.utils.classes.datawatcher;

import org.jetbrains.annotations.Nullable;

public interface VersionedDataWatcherObject {

    @Nullable Object get();
    void set(@Nullable Object object);

}
