package codes.laivy.npc.mappings.defaults.classes.entity.animal;

import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.instances.FieldExecutor;
import codes.laivy.npc.mappings.versions.V1_14_R1;
import codes.laivy.npc.mappings.versions.V1_19_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

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

    public @NotNull Cat.Variant getVariant() {
        return laivynpc().getVersion().getEntityCatVariant(this);
    }
    public void setVariant(@NotNull Cat.Variant variant) {
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

    public enum Variant {
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

        Variant(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public @NotNull Object getEnum() {
            FieldExecutor executor = laivynpc().getVersion().getFieldExec("Cat:Variant:" + name().toLowerCase());
            return Objects.requireNonNull(executor.invokeStatic());
        }

        public static @NotNull Cat.Variant getById(int id) {
            for (Variant variant : values()) {
                if (variant.getId() == id) {
                    return variant;
                }
            }
            throw new NullPointerException("Couldn't get this cat variant id '" + id + "'");
        }
        public static @NotNull Cat.Variant getByEnum(@NotNull Object catVariant) {
            if (!ReflectionUtils.isCompatible(V1_19_R1.class)) {
                throw new UnsupportedOperationException("This method is only available at 1.19+");
            }

            for (Variant variant : values()) {
                if (variant.getEnum().equals(catVariant)) {
                    return variant;
                }
            }
            throw new NullPointerException("Couldn't get this cat variant enum '" + catVariant + "'");
        }
    }
}
