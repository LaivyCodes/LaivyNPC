package codes.laivy.npc.mappings.defaults.classes.entity.npc.objs;

import codes.laivy.npc.mappings.defaults.classes.entity.npc.Villager;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.versions.V1_14_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class VillagerType extends ObjectExecutor {

    static {
        if (!ReflectionUtils.isCompatible(V1_14_R1.class)) {
            throw new UnsupportedOperationException("This class is only available at 1.14+");
        }
    }

    public static @NotNull VillagerType DESERT() {
        return new VillagerType(laivynpc().getVersion().getFieldExec("VillagerType:Desert").invokeStatic());
    }
    public static @NotNull VillagerType JUNGLE() {
        return new VillagerType(laivynpc().getVersion().getFieldExec("VillagerType:Jungle").invokeStatic());
    }
    public static @NotNull VillagerType PLAINS() {
        return new VillagerType(laivynpc().getVersion().getFieldExec("VillagerType:Plains").invokeStatic());
    }
    public static @NotNull VillagerType SAVANNA() {
        return new VillagerType(laivynpc().getVersion().getFieldExec("VillagerType:Savanna").invokeStatic());
    }
    public static @NotNull VillagerType SNOW() {
        return new VillagerType(laivynpc().getVersion().getFieldExec("VillagerType:Snow").invokeStatic());
    }
    public static @NotNull VillagerType SWAMP() {
        return new VillagerType(laivynpc().getVersion().getFieldExec("VillagerType:Swamp").invokeStatic());
    }
    public static @NotNull VillagerType TAIGA() {
        return new VillagerType(laivynpc().getVersion().getFieldExec("VillagerType:Taiga").invokeStatic());
    }

    public VillagerType(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull VillagerTypeClass getClassExecutor() {
        return (VillagerTypeClass) laivynpc().getVersion().getClassExec("VillagerType");
    }

    public static class VillagerTypeClass extends ClassExecutor {
        public VillagerTypeClass(@NotNull String className) {
            super(className);
        }
    }
}
