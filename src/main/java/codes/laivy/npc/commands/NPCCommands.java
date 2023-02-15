package codes.laivy.npc.commands;

import codes.laivy.npc.debug.Debug;
import codes.laivy.npc.debug.DebugLog;
import codes.laivy.npc.developers.events.NPCClickEvent;
import codes.laivy.npc.developers.events.NPCSelectEvent;
import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.commands.NPCConfiguration;
import codes.laivy.npc.types.player.PlayerNPC;
import codes.laivy.npc.types.utils.NPCHologramText;
import codes.laivy.npc.utils.LaivyNPCUpdater;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static codes.laivy.npc.LaivyNPC.laivynpc;
import static codes.laivy.npc.config.Translate.translate;

public class NPCCommands implements CommandExecutor, Listener {

    private static final @NotNull Map<@NotNull UUID, @NotNull NPC> SELECTED_NPCS = new HashMap<>();

    public NPCCommands() {
        Bukkit.getPluginManager().registerEvents(this, laivynpc());
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    private void interact(NPCClickEvent e) {
        if (SELECTED_NPCS.containsKey(e.getPlayer().getUniqueId()) && SELECTED_NPCS.get(e.getPlayer().getUniqueId()) == e.getNPC()) {
            return;
        }

        if (e.getPlayer().hasPermission("laivynpc.admin.select") && e.getPlayer().getItemInHand() != null) {
            if (e.getPlayer().getItemInHand().getType() == Material.STICK) {
                selectNPC(e.getPlayer(), e.getNPC());
                e.setCancelled(true);
            }
        }
    }

    public static boolean selectNPC(@NotNull Player player, @NotNull NPC npc) {
        NPCSelectEvent event = new NPCSelectEvent(npc);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return false;

        SELECTED_NPCS.put(player.getUniqueId(), npc);
        player.sendMessage(translate(player, "npc.general_command.selected", npc.getId()));
        return true;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("laivynpc")) {
            Player player = null;
            if (sender instanceof Player) {
                player = (Player) sender;
            }
            
            if (!sender.hasPermission("laivynpc.user.command")) {
                sender.sendMessage(translate(player, "npc.general_command.no_permission"));
                return true;
            }

            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("help")) {
                    StringBuilder authors = new StringBuilder();
                    List<String> authorsList = laivynpc().getDescription().getAuthors();
                    for (String author : authorsList) {
                        authors.append("§6").append(author);
                        if (authorsList.indexOf(author) + 1 != authorsList.size()) {
                            authors.append("§f, ");
                        }
                    }

                    sender.sendMessage("§8/ -----=--- LaivyNPC ----=----- /");
                    sender.sendMessage("§8 - §a" + translate(player, "npc.general_command.details.version") + ": §6" + laivynpc().getDescription().getVersion());
                    sender.sendMessage("§8 - §a" + translate(player, "npc.general_command.details.website") + ": §6" + laivynpc().getDescription().getWebsite());
                    sender.sendMessage("§8 - §a" + translate(player, "npc.general_command.details.authors") + ": §6" + authors);
                    sender.sendMessage("");
                    sender.sendMessage("§8 - " + (LaivyNPCUpdater.checkUpdates() && !LaivyNPCUpdater.errorCheckUpdates() ? (LaivyNPCUpdater.hasNewVersion() ? translate(player, "npc.general_command.version.new_available") : translate(player, "npc.general_command.version.you_are_updated")) : translate(player, "npc.general_command.version.cannot_search")));
                    sender.sendMessage("§8/ ----=--- " + translate(player, "npc.general_command.permissions") + " ----=---- /");
                    sender.sendMessage("§8 - §6laivynpc.user §5" + translate(player, "npc.general_command.permissions.default_users"));
                    sender.sendMessage("§8 - §6laivynpc.user.command §a" + translate(player, "npc.general_command.permissions.command"));
                    sender.sendMessage("");
                    sender.sendMessage("§8 - §6laivynpc.admin §5" + translate(player, "npc.general_command.permissions.default_operators"));
                    sender.sendMessage("§8 - §6laivynpc.admin.create §a" + translate(player, "npc.general_command.permissions.create"));
                    sender.sendMessage("§8 - §6laivynpc.admin.delete §a" + translate(player, "npc.general_command.permissions.delete"));
                    sender.sendMessage("§8 - §6laivynpc.admin.select §a" + translate(player, "npc.general_command.permissions.select"));
                    sender.sendMessage("§8 - §6laivynpc.admin.config §a" + translate(player, "npc.general_command.permissions.config"));
                    sender.sendMessage("§8/ -------=--- NPCs ----=------- /");
                    sender.sendMessage(translate(player, "npc.general_command.how_to.select"));
                    sender.sendMessage(translate(player, "npc.general_command.how_to.config"));
                    sender.sendMessage("§8/ ----------=-------=---------- /");
                    sender.sendMessage("");
                } else if (args[0].equalsIgnoreCase("delete")) {
                    if (!sender.hasPermission("laivynpc.admin.delete")) {
                        sender.sendMessage(translate(player, "npc.general_command.no_permission"));
                    } else if (args.length < 2) {
                        sender.sendMessage("§cUse /laivynpc delete (id)");
                    } else {
                        try {
                            int id = Integer.parseInt(args[1]);
                            if (NPC.NPCS_ID.containsKey(id)) {
                                NPC npc = NPC.NPCS_ID.get(id);
                                npc.destroy();
                                sender.sendMessage(translate(player, "npc.general_command.npc.deleted", id));
                                return true;
                            }
                        } catch (NumberFormatException ignore) {
                        }

                        sender.sendMessage(translate(player, "npc.general_command.npc.id_not_found"));
                    }
                } else if (args[0].equalsIgnoreCase("create")) {
                    if (!sender.hasPermission("laivynpc.admin.create")) {
                        sender.sendMessage(translate(player, "npc.general_command.no_permission"));
                    } else if (!(sender instanceof Player)) {
                        sender.sendMessage(translate(null, "npc.general_command.only_players"));
                    } else {
                        if (args.length >= 3) {
                            // Name
                            StringBuilder name = new StringBuilder();
                            for (int row = 2; row < args.length; row++) {
                                name.append(args[row]);
                                if (row + 1 != args.length) name.append(" ");
                            }
                            //

                            final String fName = ChatColor.translateAlternateColorCodes('&', name.toString());

                            NPC npc;

                            // Type
                            String type = args[1].toUpperCase();
                            if (type.equalsIgnoreCase("PLAYER")) {
                                npc = new PlayerNPC(new ArrayList<>(), player.getLocation());
                            } else {
                                try {
                                    Entity.EntityType entityType = Entity.EntityType.valueOf(type);
                                    if (laivynpc().getVersion().isEntityTypeSupported(entityType)) {
                                        if (entityType.canFastInstance()) {
                                            npc = entityType.fastInstance(new ArrayList<>(), player.getLocation());
                                        } else {
                                            player.sendMessage(translate(player, "npc.general_command.npc.only_api"));
                                            return true;
                                        }
                                    } else {
                                        player.sendMessage(translate(player, "npc.general_command.npc.incompatible", entityType.getSince().getSimpleName()));
                                        return true;
                                    }
                                } catch (IllegalArgumentException ignore) {
                                    player.sendMessage(translate(player, "npc.general_command.npc.unknown_type"));
                                    return true;
                                }
                            }
                            //

                            npc.getHolograms().setLine(0, new NPCHologramText(fName, npc));
                            npc.spawn();
                        } else {
                            player.sendMessage("§cUse /laivynpc create (type) (name)");
                        }
                    }
                } else if (args[0].equalsIgnoreCase("types")) {
                    StringBuilder entities = new StringBuilder();
                    entities.append("§aPLAYER");

                    for (Entity.EntityType entityType : Entity.EntityType.values()) {
                        String color = "§a";
                        if (!entityType.canFastInstance()) color = "§c";
                        else if (!laivynpc().getVersion().isEntityTypeSupported(entityType)) color = "§6";

                        entities.append("§f, ").append(color).append(entityType.name());
                    }

                    sender.sendMessage(translate(player, "npc.general_command.npc.details.available"));
                    sender.sendMessage(translate(player, "npc.general_command.npc.details.available_list") + entities);
                } else if (args[0].equalsIgnoreCase("list")) {
                    if (!sender.hasPermission("laivynpc.admin.list")) {
                        sender.sendMessage(translate(player, "npc.general_command.no_permission"));
                        return true;
                    }

                    StringBuilder entities = new StringBuilder();

                    int row = 0;
                    for (@NotNull NPC npc : NPC.NPCS_ID.values()) {
                        if (npc.isHidden()) continue;

                        entities.append("§6").append(npc.getClass().getSimpleName()).append(" '").append(npc.getId()).append("'§8. (x:").append(npc.getLocation().getBlockX()).append(",y:").append(npc.getLocation().getBlockY()).append(",z:").append(npc.getLocation().getBlockZ()).append(")");
                        row++;
                        if (row != NPC.NPCS_ID.size()) entities.append("§f, ");
                    }

                    sender.sendMessage(translate(player, "npc.general_command.npc.details.created"));
                    sender.sendMessage(entities.toString());
                } else if (args[0].equalsIgnoreCase("select")) {
                    if (!sender.hasPermission("laivynpc.admin.select")) {
                        sender.sendMessage(translate(player, "npc.general_command.no_permission"));
                    } else if (!(sender instanceof Player)) {
                        sender.sendMessage(translate(null, "npc.general_command.only_players"));
                    } else {
                        if (args.length > 1) {
                            try {
                                int id = Integer.parseInt(args[1]);
                                if (NPC.NPCS_ID.containsKey(id)) {
                                    NPC npc = NPC.NPCS_ID.get(id);
                                    if (!selectNPC((Player) sender, npc)) {
                                        player.sendMessage(translate(player, "npc.general_command.unavailable_select", id));
                                    }
                                    return true;
                                }
                            } catch (NumberFormatException ignore) {
                            }

                            sender.sendMessage(translate(player, "npc.general_command.npc.id_not_found"));
                        } else {
                            sender.sendMessage("§cUse /laivynpc select (id)");
                            sender.sendMessage(translate(player, "npc.general_command.npc.tip.see_id"));
                        }
                    }
                } else if (args[0].equalsIgnoreCase("config")) {
                    if (!sender.hasPermission("laivynpc.admin.config")) {
                        sender.sendMessage(translate(player, "npc.general_command.no_permission"));
                    } else if (!(sender instanceof Player)) {
                        sender.sendMessage(translate(null, "npc.general_command.only_players"));
                    } else {
                        if (args.length > 1) {
                            String[] arguments = new String[args.length - 2];
                            System.arraycopy(args, 2, arguments, 0, args.length - 2);

                            if (SELECTED_NPCS.containsKey(player.getUniqueId())) {
                                NPC npc = SELECTED_NPCS.get(player.getUniqueId());

                                for (NPCConfiguration configuration : npc.getByCommandConfigurations()) {
                                    if (configuration.getName().equalsIgnoreCase(args[1])) {
                                        configuration.execute(npc, player, arguments);
                                        return true;
                                    }
                                }
                                player.sendMessage(translate(player, "npc.general_command.npc.config.unknown_config"));
                            } else {
                                player.sendMessage(translate(player, "npc.general_command.npc.config.select_first"));
                            }
                        } else {
                            player.sendMessage(translate(player, "npc.general_command.npc.config.configs_list"));
                        }
                    }
                } else if (args[0].equalsIgnoreCase("debug")) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(translate(null, "npc.general_command.only_players"));
                    } else {
                        Debug debug = new Debug(player);
                    }
                } else {
                    sender.sendMessage("§cWrong arguments!");
                }
            } else {
                sender.sendMessage("§8/ ----------=-------=---------- /");
                sender.sendMessage("§6/laivynpc help §7- §b" + translate(player, "npc.general_command.npc.config.info.help"));
                sender.sendMessage("§6/laivynpc types §7- §b" + translate(player, "npc.general_command.npc.config.info.types"));
                sender.sendMessage("§6/laivynpc create (type) (name) §7- §b" + translate(player, "npc.general_command.npc.config.info.create"));
                sender.sendMessage("§6/laivynpc delete (id) §7- §b" + translate(player, "npc.general_command.npc.config.info.delete"));
                sender.sendMessage("§6/laivynpc list §7- §b" + translate(player, "npc.general_command.npc.config.info.list"));
                sender.sendMessage("§6/laivynpc update §7- §b" + translate(player, "npc.general_command.npc.config.info.update"));
                sender.sendMessage("§6/laivynpc debug §7- §b" + translate(player, "npc.general_command.npc.config.info.debug"));
                sender.sendMessage("");
                sender.sendMessage("§6/laivynpc select (id) §7- §b" + translate(player, "npc.general_command.npc.config.info.select"));
                sender.sendMessage("§8/ ----------=-------=---------- /");

                if (sender instanceof Player) {
                    sender.sendMessage("§7 " + translate(player, "npc.general_command.npc.config.configurations"));
                    if (SELECTED_NPCS.containsKey(player.getUniqueId())) {
                        NPC npc = SELECTED_NPCS.get(player.getUniqueId());

                        for (NPCConfiguration configuration : npc.getByCommandConfigurations()) {
                            sender.sendMessage("§c" + configuration.getSyntax());
                        }
                    } else {
                        sender.sendMessage(translate(player, "npc.general_command.npc.config.select_first"));
                    }
                    sender.sendMessage("§8/ ----------=-------=---------- /");
                    sender.sendMessage("");
                }
            }
        }
        return true;
    }
}
