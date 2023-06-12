package codes.laivy.npc.mappings.defaults.classes.entity;

import codes.laivy.npc.mappings.defaults.classes.nbt.tags.NBTTagCompound;
import codes.laivy.npc.mappings.versions.V1_12_R1;
import codes.laivy.npc.types.player.Shoulder;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EntityHuman extends EntityLiving {
    public EntityHuman(@Nullable Object value) {
        super(value);
    }

    public @Nullable NBTTagCompound getEntityShoulder(@NotNull Shoulder shoulder) {
        if (!ReflectionUtils.isCompatible(V1_12_R1.class)) {
            throw new NullPointerException("Shoulder entities are only available since 1.12+");
        }
        V1_12_R1 version = (V1_12_R1) laivynpc().getVersion();
        return version.getEntityShoulder(this, shoulder);
    }
    public void setEntityShoulder(@NotNull Shoulder shoulder, @Nullable NBTTagCompound entity) {
        if (!ReflectionUtils.isCompatible(V1_12_R1.class)) {
            throw new NullPointerException("Shoulder entities are only available since 1.12+");
        }
        V1_12_R1 version = (V1_12_R1) laivynpc().getVersion();
        version.setEntityShoulder(this, shoulder, entity);
    }

    @Override
    public @NotNull EntityHumanClass getClassExecutor() {
        return (EntityHumanClass) laivynpc().getVersion().getClassExec("Entity:Human");
    }

    public static class EntityHumanClass extends EntityLivingClass {
        public EntityHumanClass(@NotNull String className) {
            super(className);
        }
    }
}
