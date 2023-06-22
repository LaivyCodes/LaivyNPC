package codes.laivy.npc.types.clicks;

import codes.laivy.npc.types.NPC;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class CommandClickAction implements NPC.ClickAction {

    private final @NotNull Executor executor;
    private final @NotNull Map<NPC.@NotNull ClickType, @NotNull String> commands;

    public CommandClickAction(@NotNull Executor executor) {
        this(executor, new LinkedHashMap<>());
    }
    public CommandClickAction(@NotNull Executor executor, @NotNull Map<NPC.ClickType, @NotNull String> commands) {
        this.executor = executor;
        this.commands = commands;
    }

    public @NotNull Executor getExecutor() {
        return executor;
    }

    public @NotNull Map<NPC.@NotNull ClickType, @NotNull String> getCommands() {
        return commands;
    }

    @Override
    public void run(@NotNull Player clickedPlayer, NPC.@NotNull ClickType type) {
        if (getCommands().containsKey(type)) {
            if (getExecutor() == Executor.PLAYER) {
                clickedPlayer.performCommand(getCommands().get(type).replace("%player%", clickedPlayer.getName()));
            } else if (getExecutor() == Executor.CONSOLE) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), getCommands().get(type).replace("%player%", clickedPlayer.getName()));
            } else {
                throw new IllegalArgumentException("Unknown executor type '" + getExecutor().name() + "'");
            }
        }
    }

    public @NotNull Map<@NotNull String, @Nullable Object> serialize() {
        Map<String, Object> map = new LinkedHashMap<>();

        map.put("Executor", getExecutor().name());
        map.put("Commands", new LinkedHashMap<String, Object>() {{
            for (NPC.ClickType type : NPC.ClickType.values()) {
                if (getCommands().containsKey(type)) {
                    put(type.name(), getCommands().get(type));
                }
            }
        }});

        return map;
    }
    public static @NotNull CommandClickAction deserialize(@NotNull Map<@NotNull String, @Nullable Object> map) {
        if (map.containsKey("Executor")) {
            @NotNull Executor executor = Executor.valueOf(((String) Objects.requireNonNull(map.get("Executor"))).toUpperCase());
            @NotNull ConfigurationSection commands = (ConfigurationSection) Objects.requireNonNull(map.get("Commands"));

            CommandClickAction action = new CommandClickAction(executor, new LinkedHashMap<>());

            for (NPC.ClickType type : NPC.ClickType.values()) {
                for (String key : commands.getKeys(false)) {
                    if (key.equalsIgnoreCase(type.name())) {
                        action.getCommands().put(type, (String) commands.get(type.name()));
                    }
                }
            }

            return action;
        } else {
            CommandClickAction action = new CommandClickAction(Executor.PLAYER, new LinkedHashMap<>());

            for (NPC.ClickType type : NPC.ClickType.values()) {
                if (map.containsKey(type.name())) {
                    action.getCommands().put(type, (String) Objects.requireNonNull(map.get(type.name())));
                }
            }

            return action;
        }
    }

    public enum Executor {
        CONSOLE,
        PLAYER
    }

}
