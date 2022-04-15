package codes.laivy.npc.reflection.packets;

import codes.laivy.npc.exceptions.LaivyNPCException;
import codes.laivy.npc.reflection.ReflectionUtils;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static codes.laivy.npc.reflection.ReflectionUtils.getVersion;

public interface LaivyNPCPacket {

    Object getPacket();

    default void send(Player... players) {
        try {
            for (Player player : players) {
                Method send = ReflectionUtils.getPlayerConnection(player).getClass().getMethod(getVersion().PlayerConnection_sendPacket, Class.forName(getVersion().Packet));
                send.invoke(ReflectionUtils.getPlayerConnection(player), getPacket());
            }
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            throw new LaivyNPCException(e);
        }
    }

}
