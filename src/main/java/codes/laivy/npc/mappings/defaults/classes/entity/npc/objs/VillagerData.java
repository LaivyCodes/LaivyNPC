package codes.laivy.npc.mappings.defaults.classes.entity.npc.objs;

import codes.laivy.npc.mappings.defaults.classes.entity.npc.Villager;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.versions.V1_14_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class VillagerData extends ObjectExecutor {

    static {
        if (!ReflectionUtils.isCompatible(V1_14_R1.class)) {
            throw new UnsupportedOperationException("This class is only available at 1.14+");
        }
    }

    public VillagerData(@Nullable Object value) {
        super(value);
    }

    public int getLevel() {
        V1_14_R1 version = (V1_14_R1) laivynpc().getVersion();
        return version.getVillagerDataLevel(this);
    }
    public @NotNull VillagerProfessionExec getProfession() {
        V1_14_R1 version = (V1_14_R1) laivynpc().getVersion();
        return version.getVillagerDataProfession(this);
    }
    public @NotNull VillagerType getType() {
        V1_14_R1 version = (V1_14_R1) laivynpc().getVersion();
        return version.getVillagerDataType(this);
    }

    @Override
    public @NotNull VillagerDataClass getClassExecutor() {
        return (VillagerDataClass) laivynpc().getVersion().getClassExec("VillagerData");
    }

    public static class VillagerDataClass extends ClassExecutor {
        public VillagerDataClass(@NotNull String className) {
            super(className);
        }
    }
}
