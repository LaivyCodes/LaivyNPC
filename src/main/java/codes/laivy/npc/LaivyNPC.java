package codes.laivy.npc;

import codes.laivy.npc.api.GlowingStatus;
import codes.laivy.npc.api.player.PlayerNPC;
import codes.laivy.npc.api.player.PlayerNPCSkin;
import codes.laivy.npc.api.workers.NPCAnimation;
import codes.laivy.npc.reflection.ReflectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;

public class LaivyNPC extends JavaPlugin implements Listener {

    public static LaivyNPC plugin() {
        return getPlugin(LaivyNPC.class);
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().log(Level.INFO, "Esse plugin é compatível com sua versão " + ReflectionUtils.getVersion() + ". Bom uso :)");
    }

    @Override
    public void onDisable() {
        for (Map.Entry<Player, List<PlayerNPC>> map : PlayerNPC.getPlayerNPCs().entrySet()) {
            for (PlayerNPC npc : map.getValue()) {
                npc.getController().despawn();
            }
        }
    }

    private final Map<Player, PlayerNPC> npcMap = new HashMap<>();

    @EventHandler
    private void chat(AsyncPlayerChatEvent e) {
        Bukkit.getScheduler().runTask(this, () -> {
            if (!npcMap.containsKey(e.getPlayer())) {
                npcMap.put(e.getPlayer(), new PlayerNPC(e.getPlayer(), "", null, e.getPlayer().getLocation()));
            }
            PlayerNPC npc = npcMap.get(e.getPlayer());

            npc.getController().setViewDistance(10);

            npc.getController().spawn();
            npc.getAppearance().setSkin(new PlayerNPCSkin("ewogICJ0aW1lc3RhbXAiIDogMTY0OTYwNzU3MTcyNSwKICAicHJvZmlsZUlkIiA6ICJhYzM2YmVkZGQxNGQ0YjVmYmQyYzc5OThlMWMwOTg3ZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJtYWlzYWthIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2RjZGNiOTFlN2RjYTdlNTM3NzFjZGZhMDJmN2E3ZThiMmJmMTdhOTJmMmNhMDI4ZGYyYzgxNmJiOGUwNzE2MTYiCiAgICB9CiAgfQp9", "CAWXCOoWGbqkt5f3w7nPfPi9EvMn+FSXqw87bWfO/RXkXSh6LCN1H6HJym77x/adS8HhPq9dqmT281CDPOdh80Uynp2w8phsKFQny7YxufvC6Hqmi7LkEaSGrYbjCHs7UQ/3AP5z2u6QqH9zd8ZdRhEXf+0IU/OZ4ePRoRr0mi0ZYpS1l/iNK49BNOXwXqAqJXqqqHnhzXso0M3y/XpG4RuTSsiiF96cX68DdaMnCfP4rFuAozNllPVC6HU8ygAXhr3WgSmcZvsfS4nLYiCpT8X6cRhd/CAayvlZenVFN3F7HexQc67Ls8cA32j15nuwwwX+5irv7uFkVB9i+eASqxFVq1hY+ab15fpHxMR7passuE6MO0aPmGQIq5ak1WIBxDYta+ES/jlM+8YINN/2X9w/kmoWLq1lCL5V18RbURSHfk2JCFCuBjBZcZcNbuZTSRp9itx7VC07k0xBfPDVTwiHQLWkT1PQgek1KSiAiIC3e5F1pyeJqR05hpWTW9/7v7mvWQSt1I5Pf6L5HcHT4Qy7wPGD2KVSii9h4Gp7xNoK/Eaw8elgEq7RZIxjJM58UhShR857CfvnIQJA02Cr3WhIMSA5yeLeboLAYoT+REyUMEQuCps3dB6LBiJT8RzuiARaYghrhPivTEM1LkOl5gpT81Jb0/Gae/hW04fQ7w0="));

            npc.getAppearance().setOnFire(true);

            npc.getAnimation().setHeadRotationType(NPCAnimation.HeadRotationType.FOLLOW_NEAREST_ENTITY, 1);

            Bukkit.getScheduler().runTaskTimer(plugin(), () -> {
                npc.getAppearance().setGlowingStatus(new GlowingStatus(GlowingStatus.GlowingColor.values()[new Random().nextInt(GlowingStatus.GlowingColor.values().length)], true));
            }, 1, 1);

            Bukkit.getScheduler().runTaskLater(plugin(), () -> {
                npc.getAppearance().setSkin(PlayerNPCSkin.DEFAULT_SKIN);
            }, 100);

            Bukkit.getScheduler().runTaskLater(plugin(), () -> {
                npc.getAppearance().setSkin(new PlayerNPCSkin("ewogICJ0aW1lc3RhbXAiIDogMTY0OTI5NzYwNTA1NywKICAicHJvZmlsZUlkIiA6ICJjYmFkZmRmNTRkZTM0N2UwODQ3MjUyMDIyYTFkNGRkZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJNeHJuaW5nIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2I3OTY4MTllZTY2YzQ1Njg2ZmJmZGI3OGQ1N2U3ZDliMGE4YzQwYzY1ZTI3NTE4ZGY0ZGViMTJhMjA1OWVkYjQiCiAgICB9CiAgfQp9", "QVBXp7w5PvhSCK3P7cSQIa5WFTPhxVJewixw9O6Z52z0obsy8pYsjQ4Tc162yIi6brT+X0rwqqn9mOhcxp8HjeZ8fSV/eCyjoZR3WBY4uILCXRy3WM6+ShomRNujiZ399i/CwEuUKE2lF74/yM1eR/N069ryELTeKwRW3chV4fEyHY+8qDgcy2FWsjA8jusli+8lTQFl4HLu1+ArX6ltJag7VNmjpMP56KHs/wT/lhgZBOteNzxbFpRPxgaW7hXdT8rt5+Uog0YiyodagJ6zeePsD8d8GTsU802KkHW90lzGyn0yukEm+4WeKJ21v+23MWGvjEZKTkQKCpYOwMN4xsc5AXhMvqyJdNnrDI2AQcDQtMm37BCvyQwEJmJrzEFT1ibl7oNeO/RpBti8qCDNFlb8Wx6g6guGiDeay2i76BfK2rttzGpWcroFxgVOLA+SW7mUGBx0S0ZeCrCZwBVSJ+VqMiUeAXxDUGIWHyR0ERBiqDIsOYcrO97b6smva71CQ/+eTJe3U0MOTNRIt3HGRONaQWqm8WUH/ONkffC6LNuuX31G62klHeEWLVN5btktArwaWdZycaEwYQjGasKhfd3J9ShmsGOMxtJQriVn4jC5Tura/v+DCt9njcAlJ52CohXwUSjl8G7t0Rw++fGprtHC82VGPt++9JfCh+RHjKE="));
            }, 150);
        });
    }

}
