package codes.laivy.npc.mappings.defaults.classes.enums;

import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.defaults.classes.java.EnumObjExec;
import codes.laivy.npc.mappings.versions.V1_11_R1;
import codes.laivy.npc.mappings.versions.V1_9_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EnumHorseTypeEnum extends EnumExecutor {

    public static @NotNull EnumHorseTypeEnum getInstance() {
        if (ReflectionUtils.isCompatible(V1_11_R1.class) || !ReflectionUtils.isCompatible(V1_9_R1.class)) {
            throw new IllegalStateException("This enum isn't available at this version");
        }

        return (EnumHorseTypeEnum) laivynpc().getVersion().getEnumExec("EnumHorseType");
    }

    public static @NotNull EnumHorseType HORSE() {
        String name = laivynpc().getVersion().getText("EnumHorseType:HORSE");
        return new EnumHorseType(getInstance().getEnums().get(name).getValue());
    }
    public static @NotNull EnumHorseType DONKEY() {
        String name = laivynpc().getVersion().getText("EnumHorseType:DONKEY");
        return new EnumHorseType(getInstance().getEnums().get(name).getValue());
    }
    public static @NotNull EnumHorseType MULE() {
        String name = laivynpc().getVersion().getText("EnumHorseType:MULE");
        return new EnumHorseType(getInstance().getEnums().get(name).getValue());
    }
    public static @NotNull EnumHorseType ZOMBIE() {
        String name = laivynpc().getVersion().getText("EnumHorseType:ZOMBIE");
        return new EnumHorseType(getInstance().getEnums().get(name).getValue());
    }
    public static @NotNull EnumHorseType SKELETON() {
        String name = laivynpc().getVersion().getText("EnumHorseType:SKELETON");
        return new EnumHorseType(getInstance().getEnums().get(name).getValue());
    }

    public EnumHorseTypeEnum(@NotNull ClassExecutor classExecutor) {
        super(classExecutor);
    }

    public static class EnumHorseType extends EnumObjExec {
        public EnumHorseType(@NotNull Enum<?> value) {
            super(value);
        }
    }

    public static class EnumHorseTypeClass extends ClassExecutor {
        public EnumHorseTypeClass(@NotNull String className) {
            super(className);
        }
    }
}
