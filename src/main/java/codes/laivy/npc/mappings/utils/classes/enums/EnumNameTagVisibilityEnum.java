package codes.laivy.npc.mappings.utils.classes.enums;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.utils.classes.java.EnumObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EnumNameTagVisibilityEnum extends EnumExecutor {

    public static @NotNull EnumNameTagVisibility NEVER() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("EnumNameTagVisibilityEnum:EnumNameTagVisibility");
        String name = laivynpc().getVersion().getText("ScoreboardTeamBase:EnumNameTagVisibility:NEVER");

        return new EnumNameTagVisibility(enumExec.getEnums().get(name).getValue());
    }

    public EnumNameTagVisibilityEnum(@NotNull ClassExecutor classExecutor) {
        super(classExecutor);
    }

    public static class EnumNameTagVisibility extends EnumObjExec {
        public EnumNameTagVisibility(@Nullable Enum<?> value) {
            super(value);
        }
    }

    public static class EnumNameTagVisibilityClass extends ClassExecutor {
        public EnumNameTagVisibilityClass(@NotNull String className) {
            super(className);
        }
    }
}
