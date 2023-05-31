package codes.laivy.npc.types.list.monster;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.Spider;
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

public class SpiderNPC extends EntityLivingNPC {

    public static @NotNull SpiderNPC fastInstance(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new SpiderNPC(id, players, location);
    }

    public static void debug(@NotNull Location location) {
        SpiderNPC spiderNPC = new SpiderNPC(new ArrayList<>(), location);
        spiderNPC.debug();
        spiderNPC.destroy();
    }

    @Override
    protected void debug() {
        super.debug();
        setClimbing(!isClimbing());
    }

    protected SpiderNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType type, @NotNull Location location) {
        super(id, players, type, location);
    }
    public SpiderNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(NPC.getNextNpcId(), players, location);
    }
    public SpiderNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(id, players, Entity.EntityType.SPIDER, location);
    }

    public boolean isClimbing() {
        return getEntity().isClimbing();
    }
    public void setClimbing(boolean flag) {
        getEntity().setClimbing(flag);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();

        list.add(new NPCConfiguration("climbing", "/laivynpc config climbing") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                SpiderNPC spiderNPC = (SpiderNPC) npc;
                spiderNPC.setClimbing(!spiderNPC.isClimbing());
                sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
            }
        });

        return list;
    }

    @Override
    public @NotNull Spider getEntity() {
        return (Spider) super.getEntity();
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("SpiderNPC Configuration", new HashMap<String, Object>() {{
            put("Climbing", isClimbing());
        }});

        return map;
    }

    public static @NotNull SpiderNPC deserialize(@NotNull ConfigurationSection section) {
        SpiderNPC npc = (SpiderNPC) EntityLivingNPC.deserialize(section);

        section = section.getConfigurationSection("SpiderNPC Configuration");
        npc.setClimbing(section.getBoolean("Climbing"));

        return npc;
    }
    //
}
