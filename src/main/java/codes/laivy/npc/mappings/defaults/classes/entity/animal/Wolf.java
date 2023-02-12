package codes.laivy.npc.mappings.defaults.classes.entity.animal;

import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.defaults.classes.entity.TameableEntityLiving;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumColorEnum;
import codes.laivy.npc.mappings.versions.V1_9_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Wolf extends TameableEntityLiving {

    public static @NotNull DataWatcherObject ANGRY_METADATA() {
        if (ReflectionUtils.isCompatible(V1_9_R1.class)) {
            return new DataWatcherObject(laivynpc().getVersion().getFieldExec("Metadata:Wolf:Angry").invokeStatic());
        } else {
            throw new IllegalStateException("Metadata objects is compatible only at 1.9+");
        }
    }
    public static @NotNull DataWatcherObject COLLAR_COLOR_METADATA() {
        if (ReflectionUtils.isCompatible(V1_9_R1.class)) {
            return new DataWatcherObject(laivynpc().getVersion().getFieldExec("Metadata:Wolf:CollarColor").invokeStatic());
        } else {
            throw new IllegalStateException("Metadata objects is compatible only at 1.9+");
        }
    }

    public Wolf(@Nullable Object value) {
        super(value);
    }

    public @NotNull EnumColorEnum.EnumColor getCollarColor() {
        return laivynpc().getVersion().getEntityWolfCollarColor(this);
    }
    public void setCollarColor(@NotNull EnumColorEnum.EnumColor color) {
        laivynpc().getVersion().setEntityWolfCollarColor(this, color);
    }

    public boolean isAngry() {
        return laivynpc().getVersion().isEntityWolfAngry(this);
    }
    public void setAngry(boolean flag) {
        laivynpc().getVersion().setEntityWolfAngry(this, flag);
    }

    @Override
    public @NotNull WolfClass getClassExecutor() {
        return (WolfClass) laivynpc().getVersion().getClassExec("Entity:Wolf");
    }

    public static class WolfClass extends TameableEntityLivingClass {
        public WolfClass(@NotNull String className) {
            super(className);
        }
    }

}
