package codes.laivy.npc.types.list.monster;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.CaveSpider;
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

public class CaveSpiderNPC extends SpiderNPC {

    public static @NotNull CaveSpiderNPC fastInstance(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new CaveSpiderNPC(id, players, location);
    }

    public static void debug(@NotNull Location location) {
        CaveSpiderNPC caveSpiderNPC = new CaveSpiderNPC(new ArrayList<>(), location);
        caveSpiderNPC.debug();
        caveSpiderNPC.destroy();
    }

    protected CaveSpiderNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType type, @NotNull Location location) {
        super(id, players, type, location);
    }
    public CaveSpiderNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(NPC.getNextNpcId(), players, location);
    }
    public CaveSpiderNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(id, players, Entity.EntityType.CAVE_SPIDER, location);
    }

}
