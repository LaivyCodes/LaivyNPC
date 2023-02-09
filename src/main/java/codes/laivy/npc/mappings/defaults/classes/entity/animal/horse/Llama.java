package codes.laivy.npc.mappings.defaults.classes.entity.animal.horse;

import codes.laivy.npc.mappings.Version;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumColorEnum;
import codes.laivy.npc.mappings.versions.V1_11_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Llama extends AbstractChestedHorse {
    public Llama(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull LlamaClass getClassExecutor() {
        return (LlamaClass) laivynpc().getVersion().getClassExec("Entity:Llama");
    }

    public @NotNull Variant getVariant() {
        return laivynpc().getVersion().getEntityLlamaVariant(this);
    }
    public void setVariant(@NotNull Variant variant) {
        laivynpc().getVersion().setEntityLlamaVariant(this, variant);
    }

    public @Nullable EnumColorEnum.EnumColor getCarpetColor() {
        return laivynpc().getVersion().getEntityLlamaCarpetColor(this);
    }
    public void setCarpetColor(@Nullable EnumColorEnum.EnumColor color) {
        laivynpc().getVersion().setEntityLlamaCarpetColor(this, color);
    }

    public static class LlamaClass extends AbstractChestedHorseClass {
        public LlamaClass(@NotNull String className) {
            super(className);
        }
    }

    public enum Variant {
        CREAMY(0, V1_11_R1.class),
        WHITE(1, V1_11_R1.class),
        BROWN(2, V1_11_R1.class),
        GRAY(3, V1_11_R1.class),
        ;

        private final int id;
        private final @NotNull Class<? extends Version> since;

        Variant(int id, @NotNull Class<? extends Version> since) {
            this.id = id;
            this.since = since;
        }

        public boolean isCompatible() {
            return ReflectionUtils.isCompatible(getSince());
        }

        public int getId() {
            return id;
        }

        public @NotNull Class<? extends Version> getSince() {
            return since;
        }

        public static @NotNull Variant getById(int id) {
            for (Variant variant : values()) {
                if (variant.id == id) {
                    return variant;
                }
            }
            throw new NullPointerException("Couldn't find a llama variant with id '" + id + "'");
        }
    }
}
