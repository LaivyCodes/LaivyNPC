package codes.laivy.npc.mappings.defaults.classes.entity.animal.fish;

import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.versions.V1_13_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class PufferFish extends Fish {

    public static @NotNull DataWatcherObject PUFF_STATE_METADATA() {
        if (ReflectionUtils.isCompatible(V1_13_R1.class)) {
            return new DataWatcherObject(laivynpc().getVersion().getFieldExec("Entity:PufferFish:DataWatcher:PuffState").invokeStatic());
        } else {
            throw new IllegalStateException("This metadata object is compatible only with 1.13+");
        }
    }

    public PufferFish(@Nullable Object value) {
        super(value);
    }

    public int getPuffState() {
        return laivynpc().getVersion().getEntityPufferFishPuff(this);
    }
    public void setPuffState(int puffState) {
        laivynpc().getVersion().setEntityPufferFishPuff(this, puffState);
    }

    @Override
    public @NotNull PufferFish.PufferFishClass getClassExecutor() {
        return (PufferFishClass) laivynpc().getVersion().getClassExec("Entity:PufferFish");
    }

    public static class PufferFishClass extends FishClass {
        public PufferFishClass(@NotNull String className) {
            super(className);
        }
    }
}
