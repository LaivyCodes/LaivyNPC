package codes.laivy.npc.mappings.defaults.classes.entity.animal;

import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.defaults.classes.entity.AgeableEntityLiving;
import codes.laivy.npc.mappings.versions.V1_13_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Turtle extends AgeableEntityLiving {

    public static @NotNull DataWatcherObject EGG_METADATA() {
        if (ReflectionUtils.isCompatible(V1_13_R1.class)) {
            return new DataWatcherObject(laivynpc().getVersion().getFieldExec("Entity:Turtle:DataWatcher:Egg").invokeStatic());
        } else {
            throw new IllegalStateException("This metadata object is only compatible at 1.13+");
        }
    }

    public Turtle(@Nullable Object value) {
        super(value);
    }

    public boolean hasEgg() {
        return laivynpc().getVersion().hasEntityTurtleEgg(this);
    }
    public void setEgg(boolean flag) {
        laivynpc().getVersion().setEntityTurtleEgg(this, flag);
    }

    @Override
    public @NotNull TurtleClass getClassExecutor() {
        return (TurtleClass) laivynpc().getVersion().getClassExec("Entity:Turtle");
    }

    public static class TurtleClass extends AgeableLivingEntityClass {
        public TurtleClass(@NotNull String className) {
            super(className);
        }
    }
}
