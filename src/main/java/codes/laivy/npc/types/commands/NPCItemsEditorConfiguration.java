package codes.laivy.npc.types.commands;

import codes.laivy.npc.developers.events.NPCClickEvent;
import codes.laivy.npc.mappings.utils.classes.enums.EnumItemSlot;
import codes.laivy.npc.types.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static codes.laivy.npc.LaivyNPC.laivynpc;
import static codes.laivy.npc.config.Translate.translate;

public class NPCItemsEditorConfiguration extends NPCConfiguration implements Listener {

    public final Map<UUID, NPC> EQUIPMENT_EDITOR = new HashMap<>();

    public NPCItemsEditorConfiguration() {
        super("items", "/laivynpc config items");

        if (laivynpc().isEnabled()) {
            Bukkit.getPluginManager().registerEvents(this, laivynpc());
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    private void npcClickEvent(NPCClickEvent e) {
        if (EQUIPMENT_EDITOR.containsKey(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);

            ItemStack item = e.getPlayer().getItemInHand();

            if (item == null || item.getType() == Material.AIR) {
                for (EnumItemSlot slot : EnumItemSlot.values()) {
                    e.getNPC().setItem(slot, null);
                }
                e.getPlayer().sendMessage(translate(e.getPlayer(), "npc.commands.items.cleared"));
            } else {
                if (e.getClickType().isRightClick()) {
                    if (!e.getClickType().isShiftClick()) {
                        for (EnumItemSlot slot : EnumItemSlot.values()) {
                            String contains;

                            if (EnumItemSlot.FEET == slot) contains = "_BOOTS";
                            else if (EnumItemSlot.LEGS == slot) contains = "_LEGGINGS";
                            else if (EnumItemSlot.CHEST == slot) contains = "_CHESTPLATE";
                            else if (EnumItemSlot.HEAD == slot) contains = "_HELMET";
                            else continue;

                            if (item.getType().name().contains(contains)) {
                                e.getNPC().setItem(slot, item);
                                return;
                            }
                        }
                    }

                    e.getNPC().setItem(EnumItemSlot.MAINHAND, item);
                } else {
                    e.getNPC().setItem(EnumItemSlot.OFFHAND, item);
                }
            }
        }
    }

    @Override
    public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
        if (EQUIPMENT_EDITOR.containsKey(sender.getUniqueId()) && EQUIPMENT_EDITOR.get(sender.getUniqueId()) == npc) {
            EQUIPMENT_EDITOR.remove(sender.getUniqueId());
            sender.sendMessage(translate(sender, "npc.commands.items.left"));
            return;
        }

        EQUIPMENT_EDITOR.put(sender.getUniqueId(), npc);

        sender.sendMessage(translate(sender, "npc.commands.items"));
    }
}
