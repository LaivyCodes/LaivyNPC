package codes.laivy.npc.reflection.packets;

import codes.laivy.npc.exceptions.LaivyNPCException;
import codes.laivy.npc.utils.Validation;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.bukkit.Bukkit;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import static codes.laivy.npc.reflection.ReflectionUtils.getVersion;

public class DataWatcherController {

    private final Object entityPlayer;
    private final Object dataWatcher;

    public DataWatcherController(Object entityPlayer) {
        Validation.notNull(entityPlayer, new NullPointerException("A entidade não pode ser nula"));

        try {
            this.entityPlayer = entityPlayer;
            this.dataWatcher = entityPlayer.getClass().getMethod(getVersion().Entity_getEntityData).invoke(entityPlayer);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new LaivyNPCException(e);
        }
    }

    public void setValue(int index, byte b) {
        if (getVersion().versionCode >= 9) {
            try {
                Class<?> dataWatcherRegistry = Class.forName(getVersion().DataWatcherRegistry);
                Object dataWatcherRegistry_a = dataWatcherRegistry.getDeclaredField(getVersion().DataWatcherRegistry_BYTE).get(dataWatcherRegistry);
                Method dataWatcherRegistry_a_method = dataWatcherRegistry_a.getClass().getMethod(getVersion().DataWatcherRegistry_BYTE_write, int.class);
                Method dataWatcherPutMethod = dataWatcher.getClass().getMethod(getVersion().DataWatcher_set, Class.forName(getVersion().DataWatcherObject), Object.class);
                dataWatcherRegistry_a_method.setAccessible(true);

                Object registry = dataWatcherRegistry_a_method.invoke(dataWatcherRegistry_a, index);
                dataWatcherPutMethod.invoke(dataWatcher, registry, b);
            } catch (InvocationTargetException | NoSuchFieldException | ClassNotFoundException | IllegalAccessException | NoSuchMethodException e) {
                throw new LaivyNPCException(e);
            }
        } else if (getVersion().versionCode >= 8) {
                try {
                    Method method = Class.forName(getVersion().DataWatcher).getMethod(getVersion().DataWatcher_set, int.class, Object.class);
                    method.invoke(dataWatcher, index, b);
                } catch (InvocationTargetException | ClassNotFoundException | IllegalAccessException | NoSuchMethodException e) {
                    throw new LaivyNPCException(e);
                }
        } else {
            throw new LaivyNPCException(new ClassNotFoundException("O data watcher do plugin não é compatível com essa versão"));
        }
    }

    public Object getDataWatcher() {
        return dataWatcher;
    }

    public Object getEntityPlayer() {
        return entityPlayer;
    }

}
