package codes.laivy.npc.mappings.defaults.classes.entity.animal;

import codes.laivy.npc.mappings.defaults.classes.entity.TameableEntityLiving;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Parrot extends TameableEntityLiving {

    public Parrot(@Nullable Object value) {
        super(value);
    }

    public @NotNull Variant getVariant() {
        return laivynpc().getVersion().getEntityParrotVariant(this);
    }
    public void setVariant(@NotNull Variant variant) {
        laivynpc().getVersion().setEntityParrotVariant(this, variant);
    }

    @Override
    public @NotNull ParrotClass getClassExecutor() {
        return (ParrotClass) laivynpc().getVersion().getClassExec("Entity:Parrot");
    }

    public static class ParrotClass extends TameableEntityLivingClass {
        public ParrotClass(@NotNull String className) {
            super(className);
        }
    }

    public enum Variant {
        RED(0),
        BLUE(1),
        GREEN(2),
        LIGHT_BLUE(3),
        GRAY(4);
        ;

        private final int data;

        Variant(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public static @NotNull Variant getByData(int data) {
            for (Variant variant : values()) {
                if (variant.getData() == data) {
                    return variant;
                }
            }
            throw new NullPointerException("Couldn't find a parrot variant with data '" + data + "'");
        }
    }
}
