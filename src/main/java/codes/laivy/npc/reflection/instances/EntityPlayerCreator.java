package codes.laivy.npc.reflection.instances;

import codes.laivy.npc.exceptions.LaivyNPCException;
import org.bukkit.Location;
import org.bukkit.Server;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static codes.laivy.npc.reflection.ReflectionUtils.*;

public class EntityPlayerCreator {

    private final Object entityPlayer;

    public EntityPlayerCreator(Server server, Object gameProfile, Location l) {
        if (getVersion().versionCode >= 17) {
            try {
                Constructor<?> entityPlayerConstructor = Class.forName(getVersion().EntityPlayer).getConstructor(Class.forName(getVersion().MinecraftServer), getNMSWorld(l.getWorld()).getClass(), gameProfile.getClass());
                Object entityPlayer = entityPlayerConstructor.newInstance(getMinecraftServer(server), getNMSWorld(l.getWorld()), gameProfile);
                entityPlayer.getClass().getMethod(getVersion().Entity_absMoveTo, double.class, double.class, double.class, float.class, float.class).invoke(entityPlayer, l.getX(), l.getY(), l.getZ(), l.getYaw(), l.getPitch());

                this.entityPlayer = entityPlayer;
            } catch (InstantiationException | InvocationTargetException | ClassNotFoundException | IllegalAccessException | NoSuchMethodException e) {
                throw new LaivyNPCException(e);
            }
        } else if (getVersion().versionCode >= 14) {
            try {
                Constructor<?> entityPlayerConstructor = Class.forName(getVersion().EntityPlayer).getConstructor(Class.forName(getVersion().MinecraftServer), getNMSWorld(l.getWorld()).getClass(), gameProfile.getClass(), Class.forName(getVersion().EntityPlayer_PlayerInteractManager));
                Constructor<?> playerInteractManagerConstructor = Class.forName(getVersion().EntityPlayer_PlayerInteractManager).getConstructor(Class.forName(getVersion().WorldServer));
                Object entityPlayer = entityPlayerConstructor.newInstance(getMinecraftServer(server), getNMSWorld(l.getWorld()), gameProfile, playerInteractManagerConstructor.newInstance(getNMSWorld(l.getWorld())));
                entityPlayer.getClass().getMethod(getVersion().Entity_absMoveTo, double.class, double.class, double.class, float.class, float.class).invoke(entityPlayer, l.getX(), l.getY(), l.getZ(), l.getYaw(), l.getPitch());

                this.entityPlayer = entityPlayer;
            } catch (InstantiationException | InvocationTargetException | ClassNotFoundException | IllegalAccessException | NoSuchMethodException e) {
                throw new LaivyNPCException(e);
            }
        } else if (getVersion().versionCode >= 8) {
            try {
                Constructor<?> entityPlayerConstructor = Class.forName(getVersion().EntityPlayer).getConstructor(Class.forName(getVersion().MinecraftServer), getNMSWorld(l.getWorld()).getClass(), gameProfile.getClass(), Class.forName(getVersion().EntityPlayer_PlayerInteractManager));
                Constructor<?> playerInteractManagerConstructor = Class.forName(getVersion().EntityPlayer_PlayerInteractManager).getConstructor(Class.forName(getVersion().World));
                Object entityPlayer = entityPlayerConstructor.newInstance(getMinecraftServer(server), getNMSWorld(l.getWorld()), gameProfile, playerInteractManagerConstructor.newInstance(getNMSWorld(l.getWorld())));
                entityPlayer.getClass().getMethod(getVersion().Entity_absMoveTo, double.class, double.class, double.class, float.class, float.class).invoke(entityPlayer, l.getX(), l.getY(), l.getZ(), l.getYaw(), l.getPitch());

                this.entityPlayer = entityPlayer;
            } catch (InstantiationException | InvocationTargetException | ClassNotFoundException | IllegalAccessException | NoSuchMethodException e) {
                throw new LaivyNPCException(e);
            }
        } else {
            throw new LaivyNPCException(new ClassNotFoundException("O criador de entidades do plugin não é compatível com essa versão"));
        }
    }

    public Object getEntityPlayer() {
        return entityPlayer;
    }

}
