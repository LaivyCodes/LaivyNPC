package codes.laivy.npc.mappings.defaults.classes.scoreboard;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumChatFormatEnum;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumNameTagVisibilityEnum;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumTeamPushEnum;
import codes.laivy.npc.mappings.defaults.classes.java.StringObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class ScoreboardTeam extends ObjectExecutor {
    public ScoreboardTeam(@Nullable Object value) {
        super(value);
    }

    @NotNull
    public List<String> getPlayerNameSet() {
        //noinspection unchecked
        return new ArrayList<>((Collection<String>) Objects.requireNonNull(laivynpc().getVersion().getMethodExec("ScoreboardTeam:getPlayerNameSet").invokeInstance(this)));
    }
    public void setNameTagVisibility(@NotNull EnumNameTagVisibilityEnum.EnumNameTagVisibility nameTagVisibility) {
        laivynpc().getVersion().getMethodExec("ScoreboardTeam:setNameTagVisibity").invokeInstance(this, nameTagVisibility);
    }
    public void setColor(@NotNull EnumChatFormatEnum.EnumChatFormat color) {
        laivynpc().getVersion().getMethodExec("ScoreboardTeam:setColor").invokeInstance(this, color);
    }
    public void setPrefix(@NotNull String prefix) {
        laivynpc().getVersion().getMethodExec("ScoreboardTeam:setPrefix").invokeInstance(this, new StringObjExec(prefix));
    }
    @NotNull
    public String getName() {
        return (String) Objects.requireNonNull(laivynpc().getVersion().getMethodExec("ScoreboardTeam:getName").invokeInstance(this));
    }

    public EnumTeamPushEnum.@NotNull EnumTeamPush getCollision() {
        return new EnumTeamPushEnum.EnumTeamPush((Enum<?>) laivynpc().getVersion().getMethodExec("ScoreboardTeam:getCollision").invokeInstance(this));
    }
    public void setCollision(EnumTeamPushEnum.@NotNull EnumTeamPush collision) {
        laivynpc().getVersion().getMethodExec("ScoreboardTeam:setCollision").invokeInstance(this, collision);
    }

    @Override
    public @NotNull ScoreboardTeamClass getClassExecutor() {
        return (ScoreboardTeamClass) laivynpc().getVersion().getClassExec("ScoreboardTeam");
    }

    public static class ScoreboardTeamClass extends ClassExecutor {
        public ScoreboardTeamClass(@NotNull String className) {
            super(className);
        }
    }
}
