package codes.laivy.npc.reflection.packets;

import codes.laivy.npc.exceptions.LaivyNPCException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static codes.laivy.npc.reflection.ReflectionUtils.getVersion;

public class HeadRotationPacket implements LaivyNPCPacket {

    private final Object packet;

    public HeadRotationPacket(Object entity, double yaw) {
        try {
            if (getVersion().versionCode >= 8) {
                Constructor<?> headRotationConstructor = Class.forName(getVersion().PacketPlayOutEntityHeadRotation).getConstructor(Class.forName(getVersion().Entity), byte.class);
                this.packet = headRotationConstructor.newInstance(entity, (byte) (yaw * 256 / 360));
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
