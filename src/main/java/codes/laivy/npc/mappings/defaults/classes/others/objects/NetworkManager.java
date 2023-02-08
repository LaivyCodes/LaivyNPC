package codes.laivy.npc.mappings.defaults.classes.others.objects;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import io.netty.channel.Channel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class NetworkManager extends ObjectExecutor {
    public NetworkManager(@Nullable Object value) {
        super(value);
    }

    @NotNull
    public Channel getChannel() {
        return (Channel) Objects.requireNonNull(laivynpc().getVersion().getFieldExec("NetworkManager:channel").invokeInstance(this));
    }

    @Override
    public @NotNull NetworkManagerClass getClassExecutor() {
        return (NetworkManagerClass) laivynpc().getVersion().getClassExec("NetworkManager");
    }

    public static class NetworkManagerClass extends ClassExecutor {
        public NetworkManagerClass(@NotNull String className) {
            super(className);
        }
    }

}
