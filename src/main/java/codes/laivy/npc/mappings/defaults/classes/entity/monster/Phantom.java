package codes.laivy.npc.mappings.defaults.classes.entity.monster;

import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.versions.V1_9_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Phantom extends EntityLiving {

    public static @NotNull DataWatcherObject SIZE_METADATA() {
        if (ReflectionUtils.isCompatible(V1_9_R1.class)) {
            return new DataWatcherObject(laivynpc().getVersion().getFieldExec("Entity:Phantom:Size").invokeStatic());
        } else {
            throw new IllegalStateException("Metadata objects is compatible only at 1.9+");
        }
    }

    public Phantom(@Nullable Object value) {
        super(value);
    }

    public int getSize() {
        return laivynpc().getVersion().getEntityPhantomSize(this);
    }
    public void setSize(int size) {
        laivynpc().getVersion().setEntityPhantomSize(this, size);
    }

    @Override
    public @NotNull PhantomClass getClassExecutor() {
        return (PhantomClass) laivynpc().getVersion().getClassExec("Entity:Phantom");
    }

    public static class PhantomClass extends EntityLivingClass {
        public PhantomClass(@NotNull String className) {
            super(className);
        }
    }
}
