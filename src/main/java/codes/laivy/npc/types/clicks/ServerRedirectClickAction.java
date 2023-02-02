package codes.laivy.npc.types.clicks;

import codes.laivy.npc.types.NPC;
import codes.laivy.npc.utils.BungeeUtils;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class ServerRedirectClickAction implements NPC.ClickAction {

    private final @NotNull Map<NPC.@NotNull ClickType, @NotNull String> servers;

    public ServerRedirectClickAction() {
        this(new HashMap<>());
    }
    public ServerRedirectClickAction(@NotNull Map<NPC.@NotNull ClickType, @NotNull String> servers) {
        this.servers = servers;
    }

    @Override
    public void run(@NotNull Player clickedPlayer, NPC.@NotNull ClickType type) {
        if (getServers().containsKey(type)) {
            BungeeUtils.redirectToServer(clickedPlayer, getServers().get(type));
        }
    }

    public @NotNull Map<NPC.@NotNull ClickType, @NotNull String> getServers() {
        return servers;
    }

    @NotNull
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();

        for (NPC.ClickType type : NPC.ClickType.values()) {
            map.put(type.name(), getServers().getOrDefault(type, ""));
        }

        return map;
    }
    public static @NotNull ServerRedirectClickAction deserialize(@NotNull Map<String, Object> map) {
        ServerRedirectClickAction action = new ServerRedirectClickAction(new HashMap<>());

        for (NPC.ClickType type : NPC.ClickType.values()) {
            if (map.containsKey(type.name())) {
                action.getServers().put(type, (String) map.get(type.name()));
            }
        }

        return action;
    }
}
