package codes.laivy.npc.types.list.monster;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.Phantom;
import codes.laivy.npc.types.EntityLivingNPC;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.commands.NPCConfiguration;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static codes.laivy.npc.config.Translate.translate;

public class PhantomNPC extends EntityLivingNPC {

    public static @NotNull PhantomNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new PhantomNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        PhantomNPC phantomNPC = new PhantomNPC(new ArrayList<>(), location);
        phantomNPC.debug();
        phantomNPC.destroy();
    }

    @Override
    protected void debug() {
        super.debug();
        setSize(getSize());
    }

    protected PhantomNPC(@NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType type, @NotNull Location location) {
        super(players, type, location);
    }
    public PhantomNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.PHANTOM, location);
    }

    public int getSize() {
        return getEntity().getSize();
    }
    public void setSize(int size) {
        getEntity().setSize(size);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public @NotNull Phantom getEntity() {
        return (Phantom) super.getEntity();
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();

        list.add(new NPCConfiguration("size", "/laivynpc config size (size)") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                PhantomNPC phantomNPC = (PhantomNPC) npc;

                if (args.length > 0) {
                    int peek;
                    try {
                        peek = Integer.parseInt(args[0].replace(",", "."));
                        phantomNPC.setSize(peek);
                        sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                        return;
                    } catch (NumberFormatException ignore) {
                    }
                }

                sender.sendMessage("§cUse " + getSyntax());
            }
        });

        return list;
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("PhantomNPC Configuration", new HashMap<String, Object>() {{
            put("Size", getSize());
        }});

        return map;
    }

    public static @NotNull PhantomNPC deserialize(@NotNull ConfigurationSection section) {
        PhantomNPC npc = (PhantomNPC) EntityLivingNPC.deserialize(section);

        section = section.getConfigurationSection("PhantomNPC Configuration");
        npc.setSize(section.getInt("Size"));

        return npc;
    }
    //
}
