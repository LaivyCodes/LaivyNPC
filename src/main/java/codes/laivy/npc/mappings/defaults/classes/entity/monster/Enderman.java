package codes.laivy.npc.mappings.defaults.classes.entity.monster;

import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.versions.V1_9_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Enderman extends EntityLiving {

    public static @NotNull DataWatcherObject SCREAMING_METADATA() {
        if (ReflectionUtils.isCompatible(V1_9_R1.class)) {
            return new DataWatcherObject(laivynpc().getVersion().getFieldExec("Metadata:Enderman:screaming").invokeStatic());
        } else {
            throw new IllegalStateException("Metadata objects is compatible only at 1.9+");
        }
    }

    public Enderman(@Nullable Object value) {
        super(value);
    }

    public boolean isCarrying() {
        return getCarrying() != null;
    }
    public @Nullable Material getCarrying() {
        return laivynpc().getVersion().getEntityEndermanCarryingMaterial(this);
    }
    public void setCarrying(@Nullable Material material) {
        laivynpc().getVersion().setEntityEndermanCarriedMaterial(this, material);
    }

    public boolean isScreaming() {
        return laivynpc().getVersion().isEntityEndermanScreaming(this);
    }
    public void setScreaming(boolean flag) {
        laivynpc().getVersion().setEntityEndermanScreaming(this, flag);
    }

    @Override
    public @NotNull EndermanClass getClassExecutor() {
        return (EndermanClass) laivynpc().getVersion().getClassExec("Entity:Enderman");
    }

    public static class EndermanClass extends EntityLivingClass {
        public EndermanClass(@NotNull String className) {
            super(className);
        }
    }
}
