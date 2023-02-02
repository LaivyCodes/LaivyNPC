package codes.laivy.npc.mappings.utils.classes.scoreboard;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import codes.laivy.npc.mappings.utils.classes.java.StringObjExec;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Scoreboard extends ObjectExecutor {

    public static @NotNull Scoreboard getFrom(@NotNull Player player) {
        return laivynpc().getVersion().getScoreboardFrom(player.getScoreboard());
    }

    public Scoreboard(@Nullable Object value) {
        super(value);
    }

    @Nullable
    public ScoreboardTeam getTeam(@NotNull String team) {
        Object scoreboardTeam = laivynpc().getVersion().getMethodExec("Scoreboard:getTeam").invokeInstance(this, new StringObjExec(team));

        if (scoreboardTeam == null) {
            return null;
        } else {
            return new ScoreboardTeam(scoreboardTeam);
        }
    }

    public boolean addToTeam(@NotNull ScoreboardTeam team, @NotNull Entity player) {
        return laivynpc().getVersion().addToTeam(this, team, player);
    }

    @Override
    public @NotNull ScoreboardClass getClassExecutor() {
        return (ScoreboardClass) laivynpc().getVersion().getClassExec("Scoreboard");
    }

    public static class ScoreboardClass extends ClassExecutor {
        public ScoreboardClass(@NotNull String className) {
            super(className);
        }
    }
}
