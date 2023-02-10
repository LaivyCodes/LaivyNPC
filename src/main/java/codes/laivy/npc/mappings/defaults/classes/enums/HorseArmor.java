package codes.laivy.npc.mappings.defaults.classes.enums;

import org.jetbrains.annotations.NotNull;

public enum HorseArmor {
    NONE(0),
    IRON(1),
    GOLD(2),
    DIAMOND(3),
    ;

    private final int data;

    HorseArmor(int value) {
        this.data = value;
    }

    public int getData() {
        return data;
    }

    public static @NotNull HorseArmor getByData(int data) {
        for (HorseArmor armor : values()) {
            if (armor.getData() == data) {
                return armor;
            }
        }
        throw new NullPointerException("Couldn't find a horse armor with data '" + data + "'");
    }
}
