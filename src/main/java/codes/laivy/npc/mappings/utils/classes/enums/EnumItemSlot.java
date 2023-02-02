package codes.laivy.npc.mappings.utils.classes.enums;

public enum EnumItemSlot {
    MAINHAND(0),
    OFFHAND(0),

    HEAD(4),
    CHEST(3),
    LEGS(2),
    FEET(1);

    private final int slot;

    EnumItemSlot(int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }
}
