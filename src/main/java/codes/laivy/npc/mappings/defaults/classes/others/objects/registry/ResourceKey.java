package codes.laivy.npc.mappings.defaults.classes.others.objects.registry;

import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class ResourceKey extends ObjectExecutor {

    public ResourceKey(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull ResourceKeyClass getClassExecutor() {
        return (ResourceKeyClass) laivynpc().getVersion().getClassExec("ResourceKey");
    }

    public static final class ResourceKeyClass extends ClassExecutor {
        public ResourceKeyClass(@NotNull String className) {
            super(className);
        }
    }
}
