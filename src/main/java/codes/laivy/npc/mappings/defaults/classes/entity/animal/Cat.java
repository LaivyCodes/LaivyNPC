package codes.laivy.npc.mappings.defaults.classes.entity.animal;

import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.versions.V1_14_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Cat extends Ocelot {

    public static @NotNull DataWatcherObject VARIANT_METADATA() {
        if (ReflectionUtils.isCompatible(V1_14_R1.class)) {
            return new DataWatcherObject(laivynpc().getVersion().getFieldExec("Metadata:Cat:Type").invokeStatic());
        } else {
            throw new IllegalStateException("This metadata object is compatible only with 1.14+");
        }
    }

    public Cat(@Nullable Object value) {
        super(value);
    }

    public @NotNull CatVariant getVariant() {
        return laivynpc().getVersion().getEntityCatVariant(this);
    }
    public void setVariant(@NotNull CatVariant variant) {
        laivynpc().getVersion().setEntityCatVariant(this, variant);
    }

    @Override
    public @NotNull Ocelot.OcelotClass getClassExecutor() {
        if (ReflectionUtils.isCompatible(V1_14_R1.class)) {
            return (CatClass) laivynpc().getVersion().getClassExec("Entity:Cat");
        } else {
            return (Ocelot.OcelotClass) laivynpc().getVersion().getClassExec("Entity:Ocelot");
        }
    }

    public static class CatClass extends Ocelot.OcelotClass {
        public CatClass(@NotNull String className) {
            super(className);
        }
    }

    public enum CatVariant {
        TABBY(0),
        BLACK(1),
        RED(2),
        SIAMESE(3),
        BRITISH_SHORTHAIR(4),
        CALICO(5),
        PERSIAN(6),
        RAGDOLL(7),
        WHITE(8),
        JELLIE(9),
        ALL_BLACK(10),
        ;

        private final int id;

        CatVariant(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public static @NotNull CatVariant getById(int id) {
            for (CatVariant variant : values()) {
                if (variant.getId() == id) {
                    return variant;
                }
            }
            throw new NullPointerException("Couldn't get this cat variant id '" + id + "'");
        }
    }
}
