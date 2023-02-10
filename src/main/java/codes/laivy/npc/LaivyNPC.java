package codes.laivy.npc;

import codes.laivy.npc.commands.NPCCommands;
import codes.laivy.npc.listeners.InjectionListener;
import codes.laivy.npc.listeners.NPCListener;
import codes.laivy.npc.mappings.Version;
import codes.laivy.npc.mappings.defaults.classes.packets.listeners.InjectionUtils;
import codes.laivy.npc.metrics.Metrics;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.utils.LaivyNPCUpdater;
import codes.laivy.npc.utils.ReflectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.*;

import static codes.laivy.npc.config.Translate.translate;

public class LaivyNPC extends JavaPlugin {

    public static void main(String[] args) {
        System.out.println();
    }

    public static @NotNull LaivyNPC laivynpc() {
        return getPlugin(LaivyNPC.class);
    }

    public boolean hasMultiplesLanguagesSupport() {
        return Bukkit.getPluginManager().isPluginEnabled("LvMultiplesLanguages");
    }

    private final @NotNull File databaseFile;

    private boolean successfullyLoaded = true;

    private boolean debug = true;

    public LaivyNPC() {
        //noinspection ResultOfMethodCallIgnored
        getDataFolder().mkdirs();

        try {
            this.databaseFile = new File(getDataFolder(), "data.yml");
            //noinspection ResultOfMethodCallIgnored
            this.databaseFile.getParentFile().createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Database file exception", e);
        }
    }

    public boolean isDebug() {
        return debug;
    }

    public @NotNull File getDatabaseFile() {
        return databaseFile;
    }

    //
    // Plugin Area
    @Override
    public void onEnable() {
        saveDefaultConfig();

        try {
            log(translate(null, "plugin.trying_load", ReflectionUtils.getVersionName()));
            //noinspection unchecked
            Class<Version> clazz = (Class<Version>) ReflectionUtils.getNullableClass("codes.laivy.npc.mappings.versions." + ReflectionUtils.getVersionName().toUpperCase());
            if (clazz == null) {
                throw new NullPointerException("Couldn't find this server version's properties (" + ReflectionUtils.getVersionName() + ")");
            }

            Constructor<Version> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);

            Version version = constructor.newInstance();

            Version.LOADED_VERSIONS.put(ReflectionUtils.getVersionName(), version);
        } catch (Throwable e) {
            throw new RuntimeException("Version loading", e);
        }

        // Check if the version is compatible
        if (!ReflectionUtils.isCompatible()) {
            log(translate(null, "plugin.incompatible'", ReflectionUtils.getVersionName()));
            setEnabled(false);
            return;
        } else {
            log(translate(null, "plugin.compatible", ReflectionUtils.getVersionName()));
        }
        //

        getCommand("laivynpc").setExecutor(new NPCCommands());

        Bukkit.getPluginManager().registerEvents(new InjectionListener(), this);
        Bukkit.getPluginManager().registerEvents(new NPCListener(), this);

        new Metrics(this, 17155);
        new Thread(new LaivyNPCUpdater()).start();

        // Injection
        Bukkit.getScheduler().runTaskLater(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                try {
                    InjectionUtils.injectPlayer(player);
                } catch (Throwable e) {
                    if (e instanceof IllegalArgumentException) {
                        if (e.getMessage().contains("Duplicate handler name")) {
                            Bukkit.getScheduler().runTaskLater(this, () -> {
                                try {
                                    InjectionUtils.injectPlayer(player);
                                } catch (Throwable e2) {
                                    throw new RuntimeException("Player's packet_handler injection", e2);
                                }
                            }, 100);
                            return;
                        }
                    }
                    throw new RuntimeException("Player's packet_handler injection", e);
                }
            }
        }, 40);

        // Load the NPCs
        YamlConfiguration config = YamlConfiguration.loadConfiguration(getDatabaseFile());
        if (config.contains("npcs")) {
            for (String key : config.getConfigurationSection("npcs").getKeys(false)) {
                try {
                    NPC.loadFromConfig(config.getConfigurationSection("npcs." + key));
                } catch (Exception e) {
                    e.printStackTrace();
                    log(translate(null, "npc.cannot_load", key));
                }
            }
        }
        //

        successfullyLoaded = true;
    }

    @Override
    public void onDisable() {
        try {
            for (Player player : Bukkit.getOnlinePlayers()) {
                InjectionUtils.removePlayer(player);
            }
            if (!successfullyLoaded) return;

            for (Map.Entry<@NotNull Integer, @NotNull NPC> map : NPC.NPCS_ID.entrySet()) {
                map.getValue().despawn();
            }

            YamlConfiguration config = YamlConfiguration.loadConfiguration(getDatabaseFile());
            config.set("npcs", null);
            int amount = 0;
            boolean error = false;
            for (NPC npc : NPC.PUBLIC_NPCS) {
                if (npc.isSaveable()) {
                    try {
                        config.set("npcs." + amount, npc.serialize());
                    } catch (Exception e) {
                        error = true;
                        e.printStackTrace();
                        log(translate(null, "npc.cannot_save", npc.getId(), npc.getClass().getSimpleName()));
                    }
                    amount++;
                }
            }
            if (!error) {
                config.save(getDatabaseFile());
            } else {
                log(translate(null, "npc.data_not_saved"));
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    public Version getVersion() {
        if (ReflectionUtils.isCompatible()) {
            return Version.LOADED_VERSIONS.get(ReflectionUtils.getVersionName());
        } else {
            throw new IllegalStateException("The plugin isn't compatible with that version '" + ReflectionUtils.getVersionName() + "' yet!");
        }
    }

    // Plugin Area

    public void log(@NotNull Object message) {
        getServer().getConsoleSender().sendMessage("§8[§6" + getDescription().getName() + "§8]§7" + " " + message);
    }

}
