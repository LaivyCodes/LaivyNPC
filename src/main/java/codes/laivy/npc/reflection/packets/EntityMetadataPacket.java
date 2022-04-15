package codes.laivy.npc.reflection.packets;

import codes.laivy.npc.exceptions.LaivyNPCException;
import codes.laivy.npc.reflection.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class EntityMetadataPacket implements LaivyNPCPacket{

    private final Object packet;

    public EntityMetadataPacket(Object entityPlayer, Object dataWatcher, boolean b) {
        try {
            if (ReflectionUtils.getVersion().versionCode >= 8) {
                Constructor<?> metadataPacketConstructor = Class.forName(ReflectionUtils.getVersion().PacketPlayOutEntityMetadata).getConstructor(int.class, Class.forName(ReflectionUtils.getVersion().DataWatcher), boolean.class);
                this.packet = metadataPacketConstructor.newInstance(
                        ReflectionUtils.getEntityId(entityPlayer),
                        dataWatcher,
                        b
                );
            } else {
                throw new LaivyNPCException(new ClassNotFoundException("Esse pacote não é compatível com essa versão"));
            }
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new LaivyNPCException(e);
        }
    }

    @Override
    public Object getPacket() {
        return packet;
    }
}
