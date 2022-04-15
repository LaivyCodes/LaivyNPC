package codes.laivy.npc.reflection.packets;

import codes.laivy.npc.exceptions.LaivyNPCException;
import codes.laivy.npc.reflection.ReflectionUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class EntityDestroyPacket implements LaivyNPCPacket {

    private final Object packet;

    public EntityDestroyPacket(Object entityPlayer) {
        try {
            if (ReflectionUtils.getVersion().versionCode >= 8) {
                Object packet;

                try {
                    Object entityPlayerArray = Array.newInstance(int.class, 1);
                    Array.set(entityPlayerArray, 0, ReflectionUtils.getEntityId(entityPlayer));

                    Constructor<?> destroyEntityConstructor = Class.forName(ReflectionUtils.getVersion().PacketPlayOutEntityDestroy).getConstructor(entityPlayerArray.getClass());
                    //noinspection JavaReflectionInvocation
                    packet = destroyEntityConstructor.newInstance(entityPlayerArray);
                } catch (NoSuchMethodException ignore) {
                    Constructor<?> destroyEntityConstructor = Class.forName(ReflectionUtils.getVersion().PacketPlayOutEntityDestroy).getConstructor(int.class);
                    packet = destroyEntityConstructor.newInstance(ReflectionUtils.getEntityId(entityPlayer));
                }

                this.packet = packet;
            } else {
                throw new LaivyNPCException(new ClassNotFoundException("Esse pacote não é compatível com essa versão"));
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new LaivyNPCException(e);
        }
    }

    @Override
    public Object getPacket() {
        return packet;
    }
}
