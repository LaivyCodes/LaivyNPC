package codes.laivy.npc.types.commands;

import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.clicks.DeluxeMenusClickAction;
import com.extendedclip.deluxemenus.menu.Menu;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

import static codes.laivy.npc.config.Translate.translate;

public class DeluxeMenusConfiguration extends NPCConfiguration {
    public DeluxeMenusConfiguration() {
        super("deluxemenu", "/laivynpc config deluxemenu (name)");
    }

    @Override
    public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
        if (args.length == 2) {
            NPC.ClickType[] types;
            try {
                types = new NPC.ClickType[] {
                        NPC.ClickType.valueOf(args[0].toUpperCase())
                };
            } catch (IllegalArgumentException ignore) {
                if (args[0].equalsIgnoreCase("all")) {
                    types = NPC.ClickType.values();
                } else {
                    sender.performCommand("laivynpc config click-command");
                    return;
                }
            }

            String menuName = args[1];
            @Nullable Menu menu = Menu.getMenu(menuName);

            if (menu == null) {
                sender.sendMessage(translate(sender, "npc.commands.deluxemenus.main.unknown", menuName));
                return;
            }

            Map<NPC.ClickType, Menu> map = new LinkedHashMap<>();
            if (npc.getClickAction() instanceof DeluxeMenusClickAction) {
                DeluxeMenusClickAction old = (DeluxeMenusClickAction) npc.getClickAction();
                map.putAll(old.getMenus());
            }

            for (NPC.ClickType type : types) {
                map.put(type, menu);
            }

            DeluxeMenusClickAction action = new DeluxeMenusClickAction(map);
            npc.setClickAction(action);
            return;
        }

        StringBuilder types = new StringBuilder("§6ALL");
        for (NPC.ClickType type : NPC.ClickType.values()) {
            types.append("§c, §6").append(type);
        }

        sender.sendMessage("Use " + getSyntax() + " (type) (menu name)");
        sender.sendMessage(translate(sender, "npc.commands.click.command.types") + ": " + types);
    }
}
