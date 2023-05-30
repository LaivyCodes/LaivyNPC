package codes.laivy.npc.mappings.defaults.classes.others.objects;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.defaults.classes.packets.Packet;
import codes.laivy.npc.mappings.versions.V1_19_R3;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class PlayerConnection extends ObjectExecutor {
    public PlayerConnection(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull PlayerConnectionClass getClassExecutor() {
        return (PlayerConnectionClass) laivynpc().getVersion().getClassExec("PlayerConnection");
    }

    public void sendPacket(@NotNull Packet... packets) {
        for (Packet packet : packets) {
            laivynpc().getVersion().sendPacket(packet, this);
        }
    }

    @NotNull
    public NetworkManager getNetworkManager() {
        if (ReflectionUtils.isCompatible(V1_19_R3.class)) {
            return new NetworkManager(laivynpc().getVersion().getFieldExec("PlayerConnection:getNetworkManager").invokeInstance(this));
        } else {
            return new NetworkManager(laivynpc().getVersion().getMethodExec("PlayerConnection:getNetworkManager").invokeInstance(this));
        }
    }

    public static class PlayerConnectionClass extends ClassExecutor {
        public PlayerConnectionClass(@NotNull String className) {
            super(className);
        }
    }
}
