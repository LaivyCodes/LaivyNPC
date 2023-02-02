package codes.laivy.npc.types.commands;

import codes.laivy.npc.types.NPC;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class NPCConfiguration {

    private final String name;
    private final String syntax;

    public NPCConfiguration(@NotNull String name, @NotNull String syntax) {
        this.name = name;
        this.syntax = syntax;
    }

    @NotNull
    public String getName() {
        return name;
    }
    @NotNull
    public String getSyntax() {
        return syntax;
    }

    public abstract void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args);

}
