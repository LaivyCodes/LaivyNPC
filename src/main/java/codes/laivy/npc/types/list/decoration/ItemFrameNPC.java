package codes.laivy.npc.types.list.decoration;

import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import codes.laivy.npc.mappings.utils.classes.entity.decoration.ItemFrame;
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

import java.util.*;

import static codes.laivy.npc.config.Translate.translate;

public class ItemFrameNPC extends EntityNPC {

    public static @NotNull ItemFrameNPC fastInstance(@NotNull List<OfflinePlayer> player, @NotNull Location location, @Nullable Object object) {
        return new ItemFrameNPC(player, location);
    }

    public static void debug(@NotNull Location location) {
        ItemFrameNPC stand = new ItemFrameNPC(new ArrayList<>(), location);
        stand.debug();
        stand.destroy();
    }

    @Override
    protected void debug() {
        super.debug();
        setItem(getItem());
        setRotation(getRotation());
    }

    public ItemFrameNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.ITEM_FRAME, location);
        getHolograms().setDistanceFromNPC(-1.25D);
    }

    public @NotNull org.bukkit.inventory.ItemStack getItem() {
        return getEntity().getItem();
    }
    public void setItem(@NotNull org.bukkit.inventory.ItemStack item) {
        getEntity().setItem(item);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    public int getRotation() {
        return getEntity().getRotation();
    }
    public void setRotation(int rotation) {
        getEntity().setRotation(rotation);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public @NotNull ItemFrame getEntity() {
        return (ItemFrame) super.getEntity();
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();
        list.add(new NPCConfiguration("item", "/laivynpc config item") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                ItemFrameNPC itemFrameNPC = (ItemFrameNPC) npc;

                ItemStack item = sender.getInventory().getItemInHand();
                if (item == null || item.getType() == Material.AIR) {
                    sender.sendMessage(translate(sender, "npc.commands.item_frame.item.holding"));
                    return;
                }

                itemFrameNPC.setItem(item);
                sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
            }
        });
        list.add(new NPCConfiguration("rotate", "/laivynpc config rotate") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                ItemFrameNPC itemFrameNPC = (ItemFrameNPC) npc;
                itemFrameNPC.setRotation(itemFrameNPC.getRotation() + 1);
                sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
            }
        });
        return list;
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("ItemFrameNPC Configuration", new HashMap<String, Object>() {{
            put("Item", getItem().serialize());
            put("Rotation", getRotation());
        }});

        return map;
    }

    public static @NotNull ItemFrameNPC deserialize(@NotNull ConfigurationSection section) {
        ItemFrameNPC npc = (ItemFrameNPC) EntityNPC.deserialize(section);

        section = section.getConfigurationSection("ItemFrameNPC Configuration");
        npc.setItem(ItemStack.deserialize(section.getConfigurationSection("Item").getValues(true)));
        npc.setRotation(section.getInt("Rotation"));

        return npc;
    }
    //

}
