package codes.laivy.npc.mappings.defaults.classes.entity.animal;

import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.versions.V1_13_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Dolphin extends EntityLiving {

    public static @NotNull DataWatcherObject FISH_METADATA() {
        if (ReflectionUtils.isCompatible(V1_13_R1.class)) {
            return new DataWatcherObject(laivynpc().getVersion().getFieldExec("Entity:Dolphin:DataWatcher:hasFish").invokeStatic());
        } else {
            throw new IllegalStateException("This metadata object is compatible only with 1.13+");
        }
    }

    public Dolphin(@Nullable Object value) {
        super(value);
    }

    public boolean hasFish() {
        return laivynpc().getVersion().hasEntityDolphinFish(this);
    }
    @ApiStatus.Experimental
    public void setFish(boolean fish) {
        laivynpc().getVersion().setEntityDolphinFish(this, fish);
    }

    @Override
    public @NotNull DolphinClass getClassExecutor() {
        return (DolphinClass) laivynpc().getVersion().getClassExec("Entity:Dolphin");
    }

    public static class DolphinClass extends EntityLivingClass {
        public DolphinClass(@NotNull String className) {
            super(className);
        }
    }
}
