package codes.laivy.npc.mappings.defaults.classes.entity.npc;

import codes.laivy.npc.mappings.Version;
import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.defaults.classes.entity.npc.objs.VillagerType;
import codes.laivy.npc.mappings.versions.V1_14_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static codes.laivy.npc.LaivyNPC.laivynpc;

// TODO: 10/02/2023 Villager type (taiga, jungle, plains, ect...)
public class Villager extends EntityLiving {

    public static @NotNull DataWatcherObject DATA_METADATA() {
        if (ReflectionUtils.isCompatible(V1_14_R1.class)) {
            return new DataWatcherObject(laivynpc().getVersion().getFieldExec("Metadata:Villager:Data").invokeStatic());
        } else {
            throw new IllegalStateException("This metadata object is compatible only with 1.14+");
        }
    }

    public Villager(@Nullable Object value) {
        super(value);
    }

    public @NotNull VillagerProfession getProfession() {
        return laivynpc().getVersion().getEntityVillagerProfession(this);
    }
    public void setProfession(@NotNull VillagerProfession profession) {
        laivynpc().getVersion().setEntityVillagerProfession(this, profession);
    }

    // 1.14+ ONLY!!
    public @NotNull Villager.Type getType() {
        return laivynpc().getVersion().getEntityVillagerType(this);
    }
    // 1.14+ ONLY!!
    public void setType(@NotNull Villager.Type type) {
        laivynpc().getVersion().setEntityVillagerType(this, type);
    }

    public static class VillagerClass extends EntityLivingClass {
        public VillagerClass(@NotNull String className) {
            super(className);
        }
    }

    public enum Type {
        DESERT(VillagerType.DESERT(), V1_14_R1.class),
        JUNGLE(VillagerType.JUNGLE(), V1_14_R1.class),
        PLAINS(VillagerType.PLAINS(), V1_14_R1.class),
        SAVANNA(VillagerType.SAVANNA(), V1_14_R1.class),
        SNOW(VillagerType.SNOW(), V1_14_R1.class),
        SWAMP(VillagerType.SWAMP(), V1_14_R1.class),
        TAIGA(VillagerType.TAIGA(), V1_14_R1.class),
        ;

        private final @NotNull VillagerType villagerType;
        private final @NotNull Class<? extends Version> since;

        Type(@NotNull VillagerType villagerType, @NotNull Class<? extends Version> since) {
            this.villagerType = villagerType;
            this.since = since;
        }

        public @NotNull VillagerType getVillagerType() {
            return villagerType;
        }

        public @NotNull Class<? extends Version> getSince() {
            return since;
        }

        public boolean isCompatible() {
            return ReflectionUtils.isCompatible(getSince());
        }

        public static @NotNull Villager.Type fromVillagerType(@NotNull VillagerType villagerType) {
            for (Type type : values()) {
                if (Objects.equals(type.getVillagerType().getValue(), villagerType.getValue())) {
                    return type;
                }
            }
            throw new NullPointerException("Couldn't find a enum for this villager type '" + villagerType + "'");
        }
    }
}
