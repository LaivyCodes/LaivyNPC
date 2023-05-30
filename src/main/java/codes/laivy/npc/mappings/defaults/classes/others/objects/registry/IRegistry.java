package codes.laivy.npc.mappings.defaults.classes.others.objects.registry;

import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.versions.V1_19_R2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class IRegistry extends ObjectExecutor {

    public IRegistry(@Nullable Object value) {
        super(value);
    }

    public @Nullable Object get(@NotNull ResourceKey key) {
        V1_19_R2 version = (V1_19_R2) laivynpc().getVersion();
        return version.getResourceKeyValue(this, key);
    }

    @Override
    public @NotNull IRegistryClass getClassExecutor() {
        return (IRegistryClass) laivynpc().getVersion().getClassExec("IRegistry");
    }

    public static final class IRegistryClass extends ClassExecutor {
        public IRegistryClass(@NotNull String className) {
            super(className);
        }
    }
}
