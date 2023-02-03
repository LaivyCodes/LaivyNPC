package codes.laivy.npc.mappings.utils.classes.enums;

import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.utils.classes.java.EnumObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EnumItemSlotEnum extends EnumExecutor {

    public EnumItemSlotEnum(@NotNull ClassExecutor classExecutor) {
        super(classExecutor);
    }

    public static @NotNull EnumItemSlot MAINHAND() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumItemSlot");
        String name = laivynpc().getVersion().getText("EnumItemSlot:MAINHAND");

        return new EnumItemSlot(Objects.requireNonNull(enumExec.getEnums().get(name).getValue()));
    }
    public static @NotNull EnumItemSlot OFFHAND() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumItemSlot");
        String name = laivynpc().getVersion().getText("EnumItemSlot:OFFHAND");

        return new EnumItemSlot(Objects.requireNonNull(enumExec.getEnums().get(name).getValue()));
    }
    public static @NotNull EnumItemSlot HEAD() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumItemSlot");
        String name = laivynpc().getVersion().getText("EnumItemSlot:HEAD");

        return new EnumItemSlot(Objects.requireNonNull(enumExec.getEnums().get(name).getValue()));
    }
    public static @NotNull EnumItemSlot CHEST() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumItemSlot");
        String name = laivynpc().getVersion().getText("EnumItemSlot:CHEST");

        return new EnumItemSlot(Objects.requireNonNull(enumExec.getEnums().get(name).getValue()));
    }
    public static @NotNull EnumItemSlot LEGS() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumItemSlot");
        String name = laivynpc().getVersion().getText("EnumItemSlot:LEGS");

        return new EnumItemSlot(Objects.requireNonNull(enumExec.getEnums().get(name).getValue()));
    }
    public static @NotNull EnumItemSlot FEET() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumItemSlot");
        String name = laivynpc().getVersion().getText("EnumItemSlot:FEET");

        return new EnumItemSlot(Objects.requireNonNull(enumExec.getEnums().get(name).getValue()));
    }
    
    public static class EnumItemSlot extends EnumObjExec {

        private final int slot;

        public EnumItemSlot(@NotNull Enum<?> value) {
            super(value);

            if (value.name().equals("FEET")) {
                this.slot = 1;
            } else if (value.name().equals("LEGS")) {
                this.slot = 2;
            } else if (value.name().equals("CHEST")) {
                this.slot = 3;
            } else if (value.name().equals("HEAD")) {
                this.slot = 4;
            } else if (value.name().equals("OFFHAND")) {
                this.slot = 0;
            } else if (value.name().equals("MAINHAND")) {
                this.slot = 0;
            } else {
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
