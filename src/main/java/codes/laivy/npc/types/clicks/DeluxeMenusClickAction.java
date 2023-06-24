package codes.laivy.npc.types.clicks;

import codes.laivy.npc.types.NPC;
import com.extendedclip.deluxemenus.menu.Menu;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class DeluxeMenusClickAction implements NPC.ClickAction {
    private final @NotNull Map<NPC.ClickType, @NotNull Object> menus;

    public DeluxeMenusClickAction(@NotNull Map<NPC.ClickType, @NotNull Menu> menus) {
        this.menus = menus;
    }

    public @NotNull Map<NPC.ClickType, @NotNull Menu> getMenus() {
        return menus;
    }

    @Override
    public void run(@NotNull Player clickedPlayer, NPC.ClickType type) {
        if (getMenus().containsKey(type)) {
            @NotNull Menu menu = getMenus().get(type);
            menu.openMenu(clickedPlayer);
        }
    }

    public @NotNull Map<@NotNull String, @Nullable Object> serialize() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("Menus", new LinkedHashMap<String, Object>() {{
            for (NPC.ClickType type : NPC.ClickType.values()) {
                if (getMenus().containsKey(type)) {
                    put(type.name(), getMenus().get(type));
                }
            }
        }});

        return map;
    }
    public static @NotNull DeluxeMenusClickAction deserialize(@NotNull Map<@NotNull String, @Nullable Object> map) {
        @NotNull ConfigurationSection commands = (ConfigurationSection) Objects.requireNonNull(map.get("Menus"));

        DeluxeMenusClickAction action = new DeluxeMenusClickAction(new LinkedHashMap<>());

        for (NPC.ClickType type : NPC.ClickType.values()) {
            for (String key : commands.getKeys(false)) {
                if (key.equalsIgnoreCase(type.name())) {
                    Menu menu = Menu.getMenu((String) commands.get(type.name()));

                    if (menu == null) {
                        throw new NullPointerException("Couldn't find a DeluxeMenu menu with name '" + commands.get(type.name()) + "'");
                    }

                    action.getMenus().put(type, menu);
                }
            }
        }

        return action;
    }
}
