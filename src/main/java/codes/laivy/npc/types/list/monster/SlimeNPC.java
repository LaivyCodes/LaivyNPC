package codes.laivy.npc.types.list.monster;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.Slime;
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

public class SlimeNPC extends EntityLivingNPC {

    public static @NotNull SlimeNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new SlimeNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        SlimeNPC slimeNPC = new SlimeNPC(new ArrayList<>(), location);
        slimeNPC.debug();
        slimeNPC.destroy();
    }

    @Override
    protected void debug() {
        super.debug();
        setSize(getSize());
    }

    public SlimeNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.SLIME, location);
    }

    public int getSize() {
        return getEntity().getSize();
    }
    public void setSize(int size) {
        getEntity().setSize(size);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();
        list.add(new NPCConfiguration("size", "/laivynpc config size (size)") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                SlimeNPC slimeNPC = (SlimeNPC) npc;

                if (args.length > 0) {
                    int size;
                    try {
                        size = Integer.parseInt(args[0].replace(",", "."));
                    } catch (NumberFormatException ignore) {
                        sender.performCommand("laivynpc config " + getName());
                        return;
                    }

                    slimeNPC.setSize(size);
                    sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                    return;
                }

                sender.sendMessage("§cUse " + getSyntax());
            }
        });
        return list;
    }

    @Override
    public @NotNull Slime getEntity() {
        return (Slime) super.getEntity();
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();

        map.put("SlimeNPC Configuration", new HashMap<String, Object>() {{
            put("Size", getSize());
        }});

        return map;
    }

    public static @NotNull SlimeNPC deserialize(@NotNull ConfigurationSection section) {
        SlimeNPC npc = (SlimeNPC) EntityLivingNPC.deserialize(section);

        section = section.getConfigurationSection("SlimeNPC Configuration");
        npc.setSize(section.getInt("Size"));

        return npc;
    }
    //
}
