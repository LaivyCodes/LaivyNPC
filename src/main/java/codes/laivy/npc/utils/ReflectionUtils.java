package codes.laivy.npc.utils;

import codes.laivy.npc.mappings.Version;
import codes.laivy.npc.mappings.defaults.classes.packets.Packet;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static codes.laivy.npc.LaivyNPC.laivynpc;
import static org.bukkit.Bukkit.getServer;

public class ReflectionUtils {

    public static boolean isCompatible(@NotNull Class<? extends Version> version) {
        return version.isAssignableFrom(laivynpc().getVersion().getClass());
    }

    @NotNull
    public static String getVersionName() {
        final String[] packageName = getServer().getClass().getPackage().getName().split("\\.");
        for (String p : packageName) if (p.contains("v1_")) {
            return p;
        }
        throw new NullPointerException("Cannot identify the version's name");
    }

    public static boolean isCompatible() {
        return Version.LOADED_VERSIONS.containsKey(getVersionName());
    }

    @Nullable
    public static Class<?> getNullableClass(@NotNull String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException | NullPointerException ignore) {
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    @NotNull
    public static Class<?> getClass(@NotNull String name) {
        try {
            return Class.forName(name);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível encontrar a classe '" + name + "'", e);
        }
    }

    public static void sendPacketToPlayer(@NotNull List<@NotNull Packet> packets, @NotNull UUID... players) {
        for (UUID uuid : players) {
            Player player = Bukkit.getPlayer(uuid);

            if (player == null || !player.isOnline()) {
                continue;
            }

            for (Packet packet : packets) {
                packet.send(player.getPlayer());
            }
        }
    }
    public static void sendPacketToPlayer(@NotNull List<@NotNull Packet> packets, @NotNull Player... players) {
        Set<UUID> uuids = new LinkedHashSet<>();
        for (Player player : players) {
            uuids.add(player.getUniqueId());
        }
        sendPacketToPlayer(packets, uuids.toArray(new UUID[0]));
    }

}
