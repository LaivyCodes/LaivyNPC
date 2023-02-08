package codes.laivy.npc.types.commands;

import codes.laivy.npc.developers.events.NPCClickEvent;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumItemSlotEnum;
import codes.laivy.npc.mappings.versions.V1_9_R1;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.utils.ReflectionUtils;
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
                for (@NotNull EnumItemSlotEnum.EnumItemSlot slot : EnumItemSlotEnum.EnumItemSlot.values()) {
                    e.getNPC().setItem(slot, null);
                }
                e.getPlayer().sendMessage(translate(e.getPlayer(), "npc.commands.items.cleared"));
            } else {
                if (e.getClickType().isRightClick()) {
                    if (!e.getClickType().isShiftClick()) {
                        for (@NotNull EnumItemSlotEnum.EnumItemSlot slot : EnumItemSlotEnum.EnumItemSlot.values()) {
                            String contains;

                            if (EnumItemSlotEnum.EnumItemSlot.FEET.equals(slot)) contains = "_BOOTS";
                            else if (EnumItemSlotEnum.EnumItemSlot.LEGS.equals(slot)) contains = "_LEGGINGS";
                            else if (EnumItemSlotEnum.EnumItemSlot.CHEST.equals(slot)) contains = "_CHESTPLATE";
                            else if (EnumItemSlotEnum.EnumItemSlot.HEAD.equals(slot)) contains = "_HELMET";
                            else continue;

                            if (item.getType().name().contains(contains)) {
                                e.getNPC().setItem(slot, item);
                                return;
                            }
                        }
                    }

                    e.getNPC().setItem(EnumItemSlotEnum.EnumItemSlot.MAINHAND, item);
                } else {
                    if (ReflectionUtils.isCompatible(V1_9_R1.class)) {
                        e.getNPC().setItem(EnumItemSlotEnum.EnumItemSlot.OFFHAND, item);
                    } else {
                        e.getPlayer().sendMessage(translate(e.getPlayer(), "npc.commands.items.offhand_incompatible"));
                    }
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
