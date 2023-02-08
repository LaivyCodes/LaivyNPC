package codes.laivy.npc.mappings.defaults.classes.enums;

import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.defaults.classes.java.EnumObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EnumZombieTypeEnum extends EnumExecutor {

    public static @NotNull EnumZombieType NORMAL() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumZombieType");
        String name = laivynpc().getVersion().getText("EnumZombieType:NORMAL");

        return new EnumZombieType(enumExec.valueOf(name).getValue());
    }
    public static @NotNull EnumZombieType VILLAGER_FARMER() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumZombieType");
        String name = laivynpc().getVersion().getText("EnumZombieType:VILLAGER_FARMER");

        return new EnumZombieType(enumExec.valueOf(name).getValue());
    }
    public static @NotNull EnumZombieType VILLAGER_LIBRARIAN() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumZombieType");
        String name = laivynpc().getVersion().getText("EnumZombieType:VILLAGER_LIBRARIAN");

        return new EnumZombieType(enumExec.valueOf(name).getValue());
    }
    public static @NotNull EnumZombieType VILLAGER_PRIEST() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumZombieType");
        String name = laivynpc().getVersion().getText("EnumZombieType:VILLAGER_PRIEST");

        return new EnumZombieType(enumExec.valueOf(name).getValue());
    }
    public static @NotNull EnumZombieType VILLAGER_SMITH() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumZombieType");
        String name = laivynpc().getVersion().getText("EnumZombieType:VILLAGER_SMITH");

        return new EnumZombieType(enumExec.valueOf(name).getValue());
    }
    public static @NotNull EnumZombieType VILLAGER_BUTCHER() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumZombieType");
        String name = laivynpc().getVersion().getText("EnumZombieType:VILLAGER_BUTCHER");

        return new EnumZombieType(enumExec.valueOf(name).getValue());
    }
    public static @NotNull EnumZombieType HUSK() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumZombieType");
        String name = laivynpc().getVersion().getText("EnumZombieType:HUSK");

        return new EnumZombieType(enumExec.valueOf(name).getValue());
    }

    public EnumZombieTypeEnum(@NotNull ClassExecutor classExecutor) {
        super(classExecutor);
    }

    public static final class EnumZombieType extends EnumObjExec {
        public EnumZombieType(@Nullable Enum<?> value) {
            super(value);
        }
    }

    public static final class EnumZombieTypeClass extends ClassExecutor {
        public EnumZombieTypeClass(@NotNull String className) {
            super(className);
        }
    }
}
