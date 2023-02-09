package codes.laivy.npc.mappings.defaults.classes.enums;

import codes.laivy.npc.mappings.defaults.classes.java.EnumObjExec;
import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.versions.V1_12_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EnumSpellEnum extends EnumExecutor {

    public static @NotNull EnumSpellEnum getInstance() {
        if (!ReflectionUtils.isCompatible(V1_12_R1.class)) {
            throw new IllegalStateException("The EnumSpell enums is only available at 1.12+");
        }

        return (EnumSpellEnum) laivynpc().getVersion().getEnumExec("Entity:IllagerWizard:Spell");
    }

    public static @NotNull EnumSpellExec NONE() {
        if (!ReflectionUtils.isCompatible(V1_12_R1.class)) {
            throw new IllegalStateException("The EnumSpell enums is only available at 1.12+");
        }
        return new EnumSpellExec(getInstance().getEnums().get("NONE").getValue());
    }
    public static @NotNull EnumSpellExec SUMMON_VEX() {
        if (!ReflectionUtils.isCompatible(V1_12_R1.class)) {
            throw new IllegalStateException("The EnumSpell enums is only available at 1.12+");
        }
        return new EnumSpellExec(getInstance().getEnums().get("SUMMON_VEX").getValue());
    }
    public static @NotNull EnumSpellExec FANGS() {
        if (!ReflectionUtils.isCompatible(V1_12_R1.class)) {
            throw new IllegalStateException("The EnumSpell enums is only available at 1.12+");
        }
        return new EnumSpellExec(getInstance().getEnums().get("FANGS").getValue());
    }
    public static @NotNull EnumSpellExec WOLOLO() {
        if (!ReflectionUtils.isCompatible(V1_12_R1.class)) {
            throw new IllegalStateException("The EnumSpell enums is only available at 1.12+");
        }
        return new EnumSpellExec(getInstance().getEnums().get("WOLOLO").getValue());
    }
    public static @NotNull EnumSpellExec DISAPPEAR() {
        if (!ReflectionUtils.isCompatible(V1_12_R1.class)) {
            throw new IllegalStateException("The EnumSpell enums is only available at 1.12+");
        }
        return new EnumSpellExec(getInstance().getEnums().get("DISAPPEAR").getValue());
    }
    public static @NotNull EnumSpellExec BLINDNESS() {
        if (!ReflectionUtils.isCompatible(V1_12_R1.class)) {
            throw new IllegalStateException("The EnumSpell enums is only available at 1.12+");
        }
        return new EnumSpellExec(getInstance().getEnums().get("BLINDNESS").getValue());
    }

    public EnumSpellEnum(@NotNull ClassExecutor classExecutor) {
        super(classExecutor);
    }

    public enum Spell {
        NONE(0),
        SUMMON_VEX(1),
        FANGS(2),
        WOLOLO(3),
        DISAPPEAR(4),
        BLINDNESS(5),
        ;

        private final int value;

        Spell(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public @NotNull EnumSpellExec getEnum() {
            if (this == NONE) {
                return NONE();
            } else if (this == SUMMON_VEX) {
                return SUMMON_VEX();
            } else if (this == FANGS) {
                return FANGS();
            } else if (this == WOLOLO) {
                return WOLOLO();
            } else if (this == DISAPPEAR) {
                return DISAPPEAR();
            } else if (this == BLINDNESS) {
                return BLINDNESS();
            } else {
                throw new IllegalArgumentException("Couldn't find this slot's enum '" + this.name() + "'");
            }
        }
        public static @NotNull Spell fromEnum(@NotNull EnumSpellExec spellEnum) {
            if (spellEnum.name().equals("NONE")) {
                return NONE;
            } else if (spellEnum.name().equals("SUMMON_VEX")) {
                return SUMMON_VEX;
            } else if (spellEnum.name().equals("FANGS")) {
                return FANGS;
            } else if (spellEnum.name().equals("WOLOLO")) {
                return WOLOLO;
            } else if (spellEnum.name().equals("DISAPPEAR")) {
                return DISAPPEAR;
            } else if (spellEnum.name().equals("BLINDNESS")) {
                return BLINDNESS;
            } else {
                throw new IllegalArgumentException("Couldn't find this spell named '" + spellEnum.name() + "'");
            }
        }

        public static @NotNull Spell getByValue(int value) {
            for (Spell spell : values()) {
                if (spell.getValue() == value) {
                    return spell;
                }
            }
            throw new NullPointerException("Couldn't find a spell with value '" + value + "'");
        }
    }

    public static class EnumSpellExec extends EnumObjExec {
        public EnumSpellExec(@NotNull Enum<?> value) {
            super(value);
        }
    }

    public static class EnumSpellClass extends ClassExecutor {
        public EnumSpellClass(@NotNull String className) {
            super(className);
        }
    }

}
