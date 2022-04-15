package codes.laivy.npc.reflection.packets;

import codes.laivy.npc.exceptions.LaivyNPCException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static codes.laivy.npc.reflection.ReflectionUtils.getVersion;

public class EntitySpawnPacket implements LaivyNPCPacket {

    private final Object packet;

    public EntitySpawnPacket(Object entityPlayer) {
        try {
            if (getVersion().versionCode >= 8) {
                Constructor<?> spawnEntityConstructor = Class.forName(getVersion().PacketPlayOutNamedEntitySpawn).getConstructor(Class.forName(getVersion().EntityHuman));
                this.packet = spawnEntityConstructor.newInstance(entityPlayer);
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
