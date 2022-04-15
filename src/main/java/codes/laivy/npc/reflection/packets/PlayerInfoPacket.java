package codes.laivy.npc.reflection.packets;

import codes.laivy.npc.exceptions.LaivyNPCException;
import codes.laivy.npc.reflection.ReflectionUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static codes.laivy.npc.reflection.ReflectionUtils.getVersion;

public class PlayerInfoPacket implements LaivyNPCPacket {

    public static final Enum<?> ADD_PLAYER_ENUM;
    public static final Enum<?> REMOVE_PLAYER_ENUM;

    static {
        try {
            ADD_PLAYER_ENUM = ReflectionUtils.getEnum(Class.forName(getVersion().PacketPlayOutPlayerInfo_EnumPlayerInfoAction), getVersion().PacketPlayOutPlayerInfo_EnumPlayerInfoAction_ADD_PLAYER);
            REMOVE_PLAYER_ENUM = ReflectionUtils.getEnum(Class.forName(getVersion().PacketPlayOutPlayerInfo_EnumPlayerInfoAction), getVersion().PacketPlayOutPlayerInfo_EnumPlayerInfoAction_REMOVE_PLAYER);
        } catch (ClassNotFoundException e) {
            throw new LaivyNPCException(e);
        }
    }

    private final Object packet;

    public PlayerInfoPacket(Action action, Object entityPlayer) {
        try {
            if (getVersion().versionCode >= 8) {
                Object entityPlayerArray = Array.newInstance(Class.forName(getVersion().EntityPlayer), 1);
                Array.set(entityPlayerArray, 0, entityPlayer);

                Constructor<?> playerInfoConstructor = Class.forName(getVersion().PacketPlayOutPlayerInfo).getConstructor(Class.forName(getVersion().PacketPlayOutPlayerInfo_EnumPlayerInfoAction), entityPlayerArray.getClass());

                this.packet = playerInfoConstructor.newInstance(action.actionEnum, entityPlayerArray);
            } else {
                throw new LaivyNPCException(new ClassNotFoundException("Esse pacote não é compatível com essa versão"));
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new LaivyNPCException(e);
        }
    }

    public enum Action {

        ADD_PLAYER(ADD_PLAYER_ENUM),
        REMOVE_PLAYER(REMOVE_PLAYER_ENUM);

        private final Enum<?> actionEnum;

        Action(Enum<?> actionEnum) {
            this.actionEnum = actionEnum;
        }

        public Enum<?> getActionEnum() {
            return actionEnum;
        }
    }

    @Override
    public Object getPacket() {
        return packet;
    }
}
