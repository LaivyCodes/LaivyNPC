package codes.laivy.npc.mappings.defaults.classes.entity.animal.fish;

import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.versions.V1_13_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Pufferfish extends Fish {

    public static @NotNull DataWatcherObject PUFF_STATE_METADATA() {
        if (ReflectionUtils.isCompatible(V1_13_R1.class)) {
            return new DataWatcherObject(laivynpc().getVersion().getFieldExec("Entity:PufferFish:PuffState").invokeStatic());
        } else {
            throw new IllegalStateException("This metadata object is compatible only with 1.13+");
        }
    }

    public Pufferfish(@Nullable Object value) {
        super(value);
    }

    public int getPuffState() {
        return laivynpc().getVersion().getEntityPufferFishPuff(this);
    }
    public void setPuffState(int puffState) {
        laivynpc().getVersion().setEntityPufferFishPuff(this, puffState);
    }

    @Override
    public @NotNull Pufferfish.PufferfishClass getClassExecutor() {
        return (PufferfishClass) laivynpc().getVersion().getClassExec("Entity:PufferFish");
    }

    public static class PufferfishClass extends FishClass {
        public PufferfishClass(@NotNull String className) {
            super(className);
        }
    }
}
