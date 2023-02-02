package codes.laivy.npc.mappings.utils.classes.gameprofile;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class PropertyMap extends ObjectExecutor {
    public PropertyMap(@Nullable Object value) {
        super(value);
    }

    public boolean put(@NotNull String key, @NotNull Property property) {
        return laivynpc().getVersion().putInPropertyMap(this, key, property);
    }

    @Override
    public @NotNull PropertyMapClass getClassExecutor() {
        return (PropertyMapClass) laivynpc().getVersion().getClassExec("PropertyMap");
    }

    public static class PropertyMapClass extends ClassExecutor {
        public PropertyMapClass(@NotNull String className) {
            super(className);
        }
    }
}
