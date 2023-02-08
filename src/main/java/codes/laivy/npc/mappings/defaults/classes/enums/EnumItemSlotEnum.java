package codes.laivy.npc.mappings.defaults.classes.enums;

import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.defaults.classes.java.EnumObjExec;
import codes.laivy.npc.mappings.versions.V1_9_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EnumItemSlotEnum extends EnumExecutor {

    public static @NotNull EnumItemSlotEnum getInstance() {
        return (EnumItemSlotEnum) laivynpc().getVersion().getEnumExec("EnumItemSlot");
    }

    public static @NotNull EnumItemSlotEnum.EnumItemSlotExec MAINHAND() {
        if (!ReflectionUtils.isCompatible(V1_9_R1.class)) {
            throw new IllegalStateException("The EnumItemSlot enums is only available at 1.9+");
        }

        String name = laivynpc().getVersion().getText("EnumItemSlot:MAINHAND");
        return new EnumItemSlotExec(Objects.requireNonNull(getInstance().getEnums().get(name).getValue()));
    }
    public static @NotNull EnumItemSlotEnum.EnumItemSlotExec OFFHAND() {
        if (!ReflectionUtils.isCompatible(V1_9_R1.class)) {
            throw new IllegalStateException("The EnumItemSlot enums is only available at 1.9+");
        }

        String name = laivynpc().getVersion().getText("EnumItemSlot:OFFHAND");
        return new EnumItemSlotExec(Objects.requireNonNull(getInstance().getEnums().get(name).getValue()));
    }
    public static @NotNull EnumItemSlotEnum.EnumItemSlotExec HEAD() {
        if (!ReflectionUtils.isCompatible(V1_9_R1.class)) {
            throw new IllegalStateException("The EnumItemSlot enums is only available at 1.9+");
        }

        String name = laivynpc().getVersion().getText("EnumItemSlot:HEAD");
        return new EnumItemSlotExec(Objects.requireNonNull(getInstance().getEnums().get(name).getValue()));
    }
    public static @NotNull EnumItemSlotEnum.EnumItemSlotExec CHEST() {
        if (!ReflectionUtils.isCompatible(V1_9_R1.class)) {
            throw new IllegalStateException("The EnumItemSlot enums is only available at 1.9+");
        }

        String name = laivynpc().getVersion().getText("EnumItemSlot:CHEST");
        return new EnumItemSlotExec(Objects.requireNonNull(getInstance().getEnums().get(name).getValue()));
    }
    public static @NotNull EnumItemSlotEnum.EnumItemSlotExec LEGS() {
        if (!ReflectionUtils.isCompatible(V1_9_R1.class)) {
            throw new IllegalStateException("The EnumItemSlot enums is only available at 1.9+");
        }

        String name = laivynpc().getVersion().getText("EnumItemSlot:LEGS");
        return new EnumItemSlotExec(Objects.requireNonNull(getInstance().getEnums().get(name).getValue()));
    }
    public static @NotNull EnumItemSlotEnum.EnumItemSlotExec FEET() {
        if (!ReflectionUtils.isCompatible(V1_9_R1.class)) {
            throw new IllegalStateException("The EnumItemSlot enums is only available at 1.9+");
        }

        String name = laivynpc().getVersion().getText("EnumItemSlot:FEET");
        return new EnumItemSlotExec(Objects.requireNonNull(getInstance().getEnums().get(name).getValue()));
    }

    public EnumItemSlotEnum(@NotNull ClassExecutor classExecutor) {
        super(classExecutor);
    }

    public enum EnumItemSlot {
        MAINHAND(0),
        OFFHAND(-999), // Offhand doesn't have an id.
        FEET(1),
        LEGS(2),
        CHEST(3),
        HEAD(4),
        ;

        private final int id;

        EnumItemSlot(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public @NotNull EnumItemSlotExec getEnum() {
            if (this == MAINHAND) {
                return MAINHAND();
            } else if (this == OFFHAND) {
                if (ReflectionUtils.isCompatible(V1_9_R1.class)) {
                    return OFFHAND();
                } else {
                    throw new IllegalStateException("The offhand equipment slot is available only at 1.9+");
                }
            } else if (this == FEET) {
                return FEET();
            } else if (this == LEGS) {
                return LEGS();
            } else if (this == CHEST) {
                return CHEST();
            } else if (this == HEAD) {
                return HEAD();
            } else {
                throw new IllegalArgumentException("Couldn't find this slot's enum '" + this.name() + "'");
            }
        }
    }

    public static class EnumItemSlotExec extends EnumObjExec {
        public EnumItemSlotExec(@NotNull Enum<?> value) {
            super(value);
        }
    }

    public static class EnumItemSlotClass extends ClassExecutor {
        public EnumItemSlotClass(@NotNull String className) {
            super(className);
        }
    }

}
