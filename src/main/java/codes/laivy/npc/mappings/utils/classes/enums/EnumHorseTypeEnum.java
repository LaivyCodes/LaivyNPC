package codes.laivy.npc.mappings.utils.classes.enums;

import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.utils.classes.java.EnumObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EnumHorseTypeEnum extends EnumExecutor {
    public static @NotNull EnumHorseType HORSE() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumHorseType");
        String name = laivynpc().getVersion().getText("EnumHorseType:HORSE");

        return new EnumHorseType(enumExec.getEnums().get(name).getValue());
    }

    public static @NotNull EnumHorseType DONKEY() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumHorseType");
        String name = laivynpc().getVersion().getText("EnumHorseType:DONKEY");

        return new EnumHorseType(enumExec.getEnums().get(name).getValue());
    }

    public static @NotNull EnumHorseType MULE() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumHorseType");
        String name = laivynpc().getVersion().getText("EnumHorseType:MULE");

        return new EnumHorseType(enumExec.getEnums().get(name).getValue());
    }

    public static @NotNull EnumHorseType ZOMBIE() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumHorseType");
        String name = laivynpc().getVersion().getText("EnumHorseType:ZOMBIE");

        return new EnumHorseType(enumExec.getEnums().get(name).getValue());
    }

    public static @NotNull EnumHorseType SKELETON() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumHorseType");
        String name = laivynpc().getVersion().getText("EnumHorseType:SKELETON");

        return new EnumHorseType(enumExec.getEnums().get(name).getValue());
    }

    public EnumHorseTypeEnum(@NotNull ClassExecutor classExecutor) {
        super(classExecutor);
    }

    public static class EnumHorseType extends EnumObjExec {
        public EnumHorseType(@Nullable Enum<?> value) {
            super(value);
        }
    }

    public static class EnumHorseTypeClass extends ClassExecutor {
        public EnumHorseTypeClass(@NotNull String className) {
            super(className);
        }
    }
}
