package codes.laivy.npc.reflection;

import codes.laivy.npc.exceptions.LaivyNPCException;
import codes.laivy.npc.reflection.instances.EntityPlayerCreator;
import codes.laivy.npc.reflection.packets.LaivyNPCPacket;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ReflectionUtils {

    public static Class<?> getClass(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new LaivyNPCException(e);
        }
    }
    public static Enum<?> getEnum(Class<?> c, String name) {
        //noinspection unchecked,rawtypes
        return Enum.valueOf((Class<Enum>) c, name);
    }

    public static Object getEntityPlayer(Player player) {
        try {
            return Class.forName(getVersion().CraftPlayer).getMethod("getHandle").invoke(player);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new LaivyNPCException(e);
        }
    }
    public static int getEntityId(Object entity) {
        try {
            return (int) entity.getClass().getMethod(getVersion().Entity_getEntityId).invoke(entity);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new LaivyNPCException(e);
        }
    }

    public static Object construct(Class<?> c, Object... constructorParameters) {
        try {
            return c.getConstructor(objectsToClasses(constructorParameters)).newInstance(constructorParameters);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new LaivyNPCException(e);
        }
    }

    public static Object executeMethod(Object object, String method, Object... values) {
        try {
            return object.getClass().getMethod(method, objectsToClasses(values)).invoke(object, values);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new LaivyNPCException(e);
        }
    }

    private static Class<?>[] objectsToClasses(Object... objects) {
        Class<?>[] classes = new Class[objects.length];
        int row = 0;
        for (Object v : objects) {
            classes[row] = v.getClass();
            row++;
        }
        return classes;
    }

    public static Object getPlayerConnection(Player player) {
        try {
            Method handle = player.getClass().getMethod("getHandle");
            Object nmsPlayer = handle.invoke(player);
            Field connection = nmsPlayer.getClass().getField(getVersion().EntityPlayer_connection);
            return connection.get(nmsPlayer);
        } catch (NoSuchFieldException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new LaivyNPCException(e);
        }
    }

    public static void sendPacketToPlayer(Player player, LaivyNPCPacket... packets) {
        sendPacketToPlayer(player, Arrays.asList(packets));
    }
    public static void sendPacketToPlayer(Player player, List<LaivyNPCPacket> packets) {
        for (LaivyNPCPacket packet : packets) {
            packet.send(player);
        }
    }

    public static VersionEnum getVersion() {
        final String[] packageName = Bukkit.getServer().getClass().getPackage().getName().split("\\.");
        String version = null;

        for (String p : packageName) {
            if (p.contains("v1_")) {
                version = p;

                try {
                    return VersionEnum.valueOf(p);
                } catch (IllegalArgumentException ignore) {
                }
            }
        }
        throw new IllegalStateException("Essa versão não é compatível com o plugin! (Sua versão: " + version + ")");
    }

    public static Object getMinecraftServer(Server server) {
        try {
            Class<?> craftServerClass = Class.forName(getVersion().CraftServer);
            Class<?> minecraftServerClass = Class.forName(getVersion().MinecraftServer);

            return minecraftServerClass.cast(craftServerClass.getMethod("getServer").invoke(server));
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            throw new LaivyNPCException(e);
        }
    }
    public static Object getNMSWorld(World world) {
        try {
            return Class.forName(getVersion().CraftWorld).getMethod("getHandle").invoke(world);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new LaivyNPCException(e);
        }
    }
    public static Object createGameProfile(UUID uuid, String name) {
        try {
            return Class.forName(getVersion().GameProfile).getConstructor(UUID.class, String.class).newInstance(uuid, name);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new LaivyNPCException(e);
        }
    }

    public static Object createEntityPlayer(Server server, Object gameProfile, Location l) {
        return new EntityPlayerCreator(server, gameProfile, l).getEntityPlayer();
    }

}
