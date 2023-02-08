package codes.laivy.npc.mappings.defaults.classes.enums;

import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.defaults.classes.java.EnumObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EnumTeamPushEnum extends EnumExecutor {

    public static @NotNull EnumTeamPush ALWAYS() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("ScoreboardTeam:EnumTeamPush");
        String name = laivynpc().getVersion().getText("ScoreboardTeam:EnumTeamPush:ALWAYS");

        return new EnumTeamPush(enumExec.valueOf(name).getValue());
    }
    public static @NotNull EnumTeamPush NEVER() {
        EnumExecutor enumExec = laivynpc().getVersion().getEnumExec("ScoreboardTeam:EnumTeamPush");
        String name = laivynpc().getVersion().getText("ScoreboardTeam:EnumTeamPush:NEVER");

        return new EnumTeamPush(enumExec.valueOf(name).getValue());
    }

    public EnumTeamPushEnum(@NotNull ClassExecutor classExecutor) {
        super(classExecutor);
    }

    public static class EnumTeamPush extends EnumObjExec {
        public EnumTeamPush(@Nullable Enum<?> value) {
            super(value);
        }
    }

    public static class EnumTeamPushClass extends ClassExecutor {
        public EnumTeamPushClass(@NotNull String className) {
            super(className);
        }
    }
}
