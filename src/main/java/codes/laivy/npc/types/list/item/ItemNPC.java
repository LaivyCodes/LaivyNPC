package codes.laivy.npc.types.list.item;

import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import codes.laivy.npc.mappings.utils.classes.entity.item.Item;
import codes.laivy.npc.types.EntityNPC;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.commands.NPCConfiguration;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static codes.laivy.npc.config.Translate.translate;

public class ItemNPC extends EntityNPC {

    public static @NotNull ItemNPC fastInstance(@NotNull List<OfflinePlayer> player, @NotNull Location location, @Nullable Object object) {
        return new ItemNPC(player, location);
    }

    public static void debug(@NotNull Location location) {
        ItemNPC itemNPC = new ItemNPC(new ArrayList<>(), location);
        itemNPC.debug();
        itemNPC.destroy();
    }

    public ItemNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.ITEM, location);
    }

    public @NotNull ItemStack getItemStack() {
        return getEntity().getItemStack();
    }
    public void setItemStack(@NotNull ItemStack item) {
        getEntity().setItemStack(item);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();
        list.add(new NPCConfiguration("item", "/laivynpc config item") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                ItemNPC itemNPC = (ItemNPC) npc;

                ItemStack item = sender.getInventory().getItemInHand();
                if (item == null || item.getType() == Material.AIR) {
                    sender.sendMessage(translate(sender, "npc.commands.item_frame.item.holding"));
                    return;
                }

                itemNPC.setItemStack(item);
                sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
            }
        });
        return list;
    }

    @Override
    public @NotNull Item getEntity() {
        return (Item) super.getEntity();
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("ItemNPC Configuration", new HashMap<String, Object>() {{
            put("Item", getItemStack());
        }});

        return map;
    }

    public static @NotNull ItemNPC deserialize(@NotNull ConfigurationSection section) {
        ItemNPC npc = (ItemNPC) EntityNPC.deserialize(section);

        section = section.getConfigurationSection("ItemNPC Configuration");
        npc.setItemStack(ItemStack.deserialize(section.getConfigurationSection("Item").getValues(true)));

        return npc;
    }
    //
}
