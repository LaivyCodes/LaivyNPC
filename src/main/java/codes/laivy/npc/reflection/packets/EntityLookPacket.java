package codes.laivy.npc.reflection.packets;

import codes.laivy.npc.exceptions.LaivyNPCException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static codes.laivy.npc.reflection.ReflectionUtils.getEntityId;
import static codes.laivy.npc.reflection.ReflectionUtils.getVersion;

public class EntityLookPacket implements LaivyNPCPacket {

    private final Object packet;

    public EntityLookPacket(Object entity, double yaw, double pitch) {
        try {
            if (getVersion().versionCode >= 8) {
                Constructor<?> headRotationConstructor = Class.forName(getVersion().PacketPlayOutEntity_PacketPlayOutEntityLook).getConstructor(int.class, byte.class, byte.class, boolean.class);
                this.packet = headRotationConstructor.newInstance(getEntityId(entity), (byte)(yaw * 256 / 360), (byte)(pitch * 256 / 360), true);
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
