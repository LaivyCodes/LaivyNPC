package codes.laivy.npc.mappings.defaults.classes.enums;

import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.defaults.classes.java.EnumObjExec;
import org.jetbrains.annotations.NotNull;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EnumZombieTypeEnum extends EnumExecutor {

    public static @NotNull EnumZombieType NORMAL() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumZombieType");
        return new EnumZombieType(enumExec.valueOf("NORMAL").getValue());
    }
    public static @NotNull EnumZombieType VILLAGER_FARMER() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumZombieType");
        return new EnumZombieType(enumExec.valueOf("VILLAGER_FARMER").getValue());
    }
    public static @NotNull EnumZombieType HUSK() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumZombieType");
    return new EnumZombieType(enumExec.valueOf("HUSK").getValue());
    }

    public EnumZombieTypeEnum(@NotNull ClassExecutor classExecutor) {
        super(classExecutor);
    }

    public static final class EnumZombieType extends EnumObjExec {
        public EnumZombieType(@NotNull Enum<?> value) {
            super(value);
        }
    }

    public static final class EnumZombieTypeClass extends ClassExecutor {
        public EnumZombieTypeClass(@NotNull String className) {
            super(className);
        }
    }
}
