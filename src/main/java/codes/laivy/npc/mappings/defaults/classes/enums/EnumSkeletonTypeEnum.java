package codes.laivy.npc.mappings.defaults.classes.enums;

import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.defaults.classes.java.EnumObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EnumSkeletonTypeEnum extends EnumExecutor {

    public static @NotNull EnumSkeletonType NORMAL() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumSkeletonType");
        String name = laivynpc().getVersion().getText("EnumSkeletonType:NORMAL");

        return new EnumSkeletonType(enumExec.valueOf(name).getValue());
    }
    public static @NotNull EnumSkeletonType WITHER() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumSkeletonType");
        String name = laivynpc().getVersion().getText("EnumSkeletonType:WITHER");

        return new EnumSkeletonType(enumExec.valueOf(name).getValue());
    }
    public static @NotNull EnumSkeletonType STRAY() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumSkeletonType");
        String name = laivynpc().getVersion().getText("EnumSkeletonType:STRAY");

        return new EnumSkeletonType(enumExec.valueOf(name).getValue());
    }

    public EnumSkeletonTypeEnum(@NotNull ClassExecutor classExecutor) {
        super(classExecutor);
    }

    public static final class EnumSkeletonType extends EnumObjExec {
        public EnumSkeletonType(@Nullable Enum<?> value) {
            super(value);
        }
    }

    public static final class EnumSkeletonTypeClass extends ClassExecutor {
        public EnumSkeletonTypeClass(@NotNull String className) {
            super(className);
        }
    }
}
