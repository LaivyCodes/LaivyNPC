package codes.laivy.npc.mappings.defaults.classes.entity.animal;

import codes.laivy.npc.mappings.defaults.classes.entity.TameableLivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Ocelot extends TameableLivingEntity {

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

    public Ocelot(@Nullable Object value) {
        super(value);
    }

    public @NotNull CatVariant getVariant() {
        return laivynpc().getVersion().getEntityCatVariant(this);
    }
    public void setVariant(@NotNull CatVariant variant) {
        laivynpc().getVersion().setEntityCatVariant(this, variant);
    }

    @Override
    public @NotNull OcelotClass getClassExecutor() {
        return (OcelotClass) laivynpc().getVersion().getClassExec("Entity:Ocelot");
    }

    public static class OcelotClass extends TameableLivingEntityClass {
        public OcelotClass(@NotNull String className) {
            super(className);
        }
    }
}
