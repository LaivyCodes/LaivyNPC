package codes.laivy.npc.mappings.defaults.classes.entity.animal.horse;

import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.defaults.classes.enums.HorseArmor;
import codes.laivy.npc.mappings.versions.V1_9_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Horse extends AbstractHorse {

    public static @NotNull DataWatcherObject ARMOR_METADATA() {
        if (ReflectionUtils.isCompatible(V1_9_R1.class)) {
            return new DataWatcherObject(laivynpc().getVersion().getFieldExec("Metadata:Horse:DataWatcher:Armor").invokeStatic());
        } else {
            throw new IllegalStateException("Metadata objects is compatible only at 1.9+");
        }
    }

    public Horse(@Nullable Object value) {
        super(value);
    }

    public @NotNull HorseArmor getArmor() {
        return laivynpc().getVersion().getEntityHorseArmor(this);
    }
    public void setArmor(@NotNull HorseArmor armor) {
        laivynpc().getVersion().setEntityHorseArmor(this, armor);
    }

    @ApiStatus.Experimental
    public int getVariant() {
        return laivynpc().getVersion().getEntityHorseVariant(this);
    }
    @ApiStatus.Experimental
    public void setVariant(int variant) {
        laivynpc().getVersion().setEntityHorseVariant(this, variant);
    }

}
