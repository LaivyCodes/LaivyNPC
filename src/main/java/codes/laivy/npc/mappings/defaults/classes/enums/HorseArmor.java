package codes.laivy.npc.mappings.defaults.classes.enums;

import codes.laivy.npc.mappings.Version;
import codes.laivy.npc.mappings.versions.V1_8_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;

public enum HorseArmor {
    NONE(0, V1_8_R1.class),
    // TODO: Leather horse armor type
    // LEATHER(-1, V1_14_R1.class),
    IRON(1, V1_8_R1.class),
    GOLD(2, V1_8_R1.class),
    DIAMOND(3, V1_8_R1.class),
    ;

    private final int data;
    private final @NotNull Class<? extends Version> since;

    HorseArmor(int data, @NotNull Class<? extends Version> since) {
        this.data = data;
        this.since = since;
    }

    public int getData() {
        return data;
    }

    public @NotNull Class<? extends Version> getSince() {
        return since;
    }

    public boolean isCompatible() {
        return ReflectionUtils.isCompatible(getSince());
    }

    public static @NotNull HorseArmor getByData(int data) {
        for (HorseArmor armor : values()) {
            if (armor.isCompatible() && armor.getData() == data) {
                return armor;
            }
        }
        throw new NullPointerException("Couldn't find a horse armor with data '" + data + "'");
    }
}
