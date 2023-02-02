package codes.laivy.npc.mappings.utils.classes.scoreboard;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.MethodExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class CraftScoreboard extends ObjectExecutor {
    public CraftScoreboard(@Nullable Object value) {
        super(value);
    }

    public @NotNull Scoreboard getHandle() {
        MethodExecutor method = new MethodExecutor(getClassExecutor(), laivynpc().getVersion().getClassExec("Scoreboard"), "getHandle", "Gets the NMS Scoreboard from a CraftScoreboard");
        method.load();

        return new Scoreboard(Objects.requireNonNull(method.invokeInstance(this)));
    }

    @Override
    public @NotNull CraftScoreboardClass getClassExecutor() {
        return (CraftScoreboardClass) laivynpc().getVersion().getClassExec("CraftScoreboard");
    }

    public static class CraftScoreboardClass extends ClassExecutor {
        public CraftScoreboardClass(@NotNull String className) {
            super(className);
        }
    }
}
