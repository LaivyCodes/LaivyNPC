package codes.laivy.npc.types.clicks;

import codes.laivy.npc.types.NPC;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CommandClickAction implements NPC.ClickAction {

    private final @NotNull Map<NPC.@NotNull ClickType, @NotNull String> commands;

    public CommandClickAction() {
        this(new HashMap<>());
    }
    public CommandClickAction(@NotNull Map<NPC.ClickType, @NotNull String> commands) {
        this.commands = commands;
    }

    public @NotNull Map<NPC.@NotNull ClickType, @NotNull String> getCommands() {
        return commands;
    }

    @Override
    public void run(@NotNull Player clickedPlayer, NPC.@NotNull ClickType type) {
        if (getCommands().containsKey(type)) {
            clickedPlayer.performCommand(getCommands().get(type));
        }
    }

    public @NotNull Map<@NotNull String, @Nullable Object> serialize() {
        Map<String, Object> map = new HashMap<>();

        for (NPC.ClickType type : NPC.ClickType.values()) {
            map.put(type.name(), getCommands().getOrDefault(type, ""));
        }

        return map;
    }
    public static @NotNull CommandClickAction deserialize(@NotNull Map<@NotNull String, @Nullable Object> map) {
        CommandClickAction action = new CommandClickAction(new HashMap<>());

        for (NPC.ClickType type : NPC.ClickType.values()) {
            if (map.containsKey(type.name())) {
                action.getCommands().put(type, (String) Objects.requireNonNull(map.get(type.name())));
            }
        }

        return action;
    }

}
