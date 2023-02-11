package codes.laivy.npc.mappings.defaults.classes.entity.animal.fish;

import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.versions.V1_13_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Tropicalfish extends Fish {

    public static @NotNull DataWatcherObject VARIANT_METADATA() {
        if (ReflectionUtils.isCompatible(V1_13_R1.class)) {
            return new DataWatcherObject(laivynpc().getVersion().getFieldExec("Entity:TropicalFish:Variant").invokeStatic());
        } else {
            throw new IllegalStateException("This metadata object is compatible only with 1.13+");
        }
    }

    public Tropicalfish(@Nullable Object value) {
        super(value);
    }

    public int getVariant() {
        return laivynpc().getVersion().getEntityTropicalFishVariant(this);
    }
    public void setVariant(int variant) {
        laivynpc().getVersion().setEntityTropicalFishVariant(this, variant);
    }

    @Override
    public @NotNull Tropicalfish.TropicalfishClass getClassExecutor() {
        return (TropicalfishClass) laivynpc().getVersion().getClassExec("Entity:TropicalFish");
    }

    public static class TropicalfishClass extends FishClass {
        public TropicalfishClass(@NotNull String className) {
            super(className);
        }
    }
}
