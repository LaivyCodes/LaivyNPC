package codes.laivy.npc.reflection.packets;

import codes.laivy.npc.exceptions.LaivyNPCException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static codes.laivy.npc.reflection.ReflectionUtils.getVersion;

public class ScoreboardTeamPacket implements LaivyNPCPacket {

    private final Object packet;

    public ScoreboardTeamPacket(Object scoreboardTeam, boolean b) {
        try {
            if (getVersion().versionCode >= 17) {
                Class<?> PacketPlayOutScoreboardTeamClass = Class.forName(getVersion().PacketPlayOutScoreboardTeam);
                Method method = PacketPlayOutScoreboardTeamClass.getMethod("a", scoreboardTeam.getClass(), boolean.class);

                this.packet = method.invoke(null, scoreboardTeam, b);
            } else if (getVersion().versionCode >= 8) {
                Constructor<?> PacketPlayOutScoreboardTeamConstructor = Class.forName(getVersion().PacketPlayOutScoreboardTeam).getConstructor(Class.forName(getVersion().ScoreboardTeam), int.class);
                this.packet = PacketPlayOutScoreboardTeamConstructor.newInstance(scoreboardTeam, (b ? 0 : 2));
            } else {
                throw new LaivyNPCException(new ClassNotFoundException("Esse pacote não é compatível com essa versão"));
            }
        } catch (InstantiationException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new LaivyNPCException(e);
        }
    }

    @Override
    public Object getPacket() {
        return packet;
    }
}
