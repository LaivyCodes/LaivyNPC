package codes.laivy.npc.mappings.defaults.classes.others.objects.registry;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import org.jetbrains.annotations.NotNull;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class BuiltInRegistries extends ClassExecutor {

    public BuiltInRegistries(@NotNull String className) {
        super(className);
    }

    public static @NotNull IRegistry getCatVariantRegistry() {
        return new IRegistry(laivynpc().getVersion().getFieldExec("IRegistry:CAT_VARIANT").invokeStatic());
    }
}
