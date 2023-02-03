package codes.laivy.npc.mappings.utils.classes.enums;

import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.utils.classes.java.EnumObjExec;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EnumItemSlotEnum extends EnumExecutor {

    public EnumItemSlotEnum(@NotNull ClassExecutor classExecutor) {
        super(classExecutor);
    }

    public static @NotNull EnumItemSlot MAINHAND() {
        if (!laivynpc().getVersion().getEnums().containsKey("EnumItemSlot")) {
            return new EnumItemSlot(0);
        }

        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumItemSlot");
        String name = laivynpc().getVersion().getText("EnumItemSlot:MAINHAND");

        return new EnumItemSlot(Objects.requireNonNull(enumExec.getEnums().get(name).getValue()));
    }
    public static @NotNull EnumItemSlot OFFHAND() {
        if (!laivynpc().getVersion().getEnums().containsKey("EnumItemSlot")) {
            return new EnumItemSlot(0);
        }

        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumItemSlot");
        String name = laivynpc().getVersion().getText("EnumItemSlot:OFFHAND");

        return new EnumItemSlot(Objects.requireNonNull(enumExec.getEnums().get(name).getValue()));
    }
    public static @NotNull EnumItemSlot HEAD() {
        if (!laivynpc().getVersion().getEnums().containsKey("EnumItemSlot")) {
            return new EnumItemSlot(4);
        }

        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumItemSlot");
        String name = laivynpc().getVersion().getText("EnumItemSlot:HEAD");

        return new EnumItemSlot(Objects.requireNonNull(enumExec.getEnums().get(name).getValue()));
    }
    public static @NotNull EnumItemSlot CHEST() {
        if (!laivynpc().getVersion().getEnums().containsKey("EnumItemSlot")) {
            return new EnumItemSlot(3);
        }

        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumItemSlot");
        String name = laivynpc().getVersion().getText("EnumItemSlot:CHEST");

        return new EnumItemSlot(Objects.requireNonNull(enumExec.getEnums().get(name).getValue()));
    }
    public static @NotNull EnumItemSlot LEGS() {
        if (!laivynpc().getVersion().getEnums().containsKey("EnumItemSlot")) {
            return new EnumItemSlot(2);
        }

        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumItemSlot");
        String name = laivynpc().getVersion().getText("EnumItemSlot:LEGS");

        return new EnumItemSlot(Objects.requireNonNull(enumExec.getEnums().get(name).getValue()));
    }
    public static @NotNull EnumItemSlot FEET() {
        if (!laivynpc().getVersion().getEnums().containsKey("EnumItemSlot")) {
            return new EnumItemSlot(1);
        }

        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumItemSlot");
        String name = laivynpc().getVersion().getText("EnumItemSlot:FEET");

        return new EnumItemSlot(Objects.requireNonNull(enumExec.getEnums().get(name).getValue()));
    }
    
    public static class EnumItemSlot extends EnumObjExec {

        private final int slot;

        public EnumItemSlot(int slot) {
            super(null);
            this.slot = slot;
        }
        public EnumItemSlot(@NotNull Enum<?> value) {
            super(value);

            switch (value.name()) {
                case "FEET":
                    this.slot = 1;
                    break;
                case "LEGS":
                    this.slot = 2;
                    break;
                case "CHEST":
                    this.slot = 3;
                    break;
                case "HEAD":
                    this.slot = 4;
                    break;
                case "OFFHAND":
                case "MAINHAND":
                    this.slot = 0;
                    break;
                default:
                    throw new IllegalArgumentException("Couldn't find this value name '" + value.name() + "'");
            }
        }

        public int getSlot() {
            return slot;
        }
    }

    public static class EnumItemSlotClass extends ClassExecutor {
        public EnumItemSlotClass(@NotNull String className) {
            super(className);
        }
    }
    
}
