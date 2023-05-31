package codes.laivy.npc.mappings.defaults.classes.gameprofile;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class GameProfile extends ObjectExecutor {
    public GameProfile(@Nullable Object value) {
        super(value);
    }

    @NotNull
    public PropertyMap getProperties() {
        return new PropertyMap(Objects.requireNonNull(laivynpc().getVersion().getMethodExec("GameProfile:getProperties").invokeInstance(this)));
    }

    public @NotNull String getName() {
        return (String) Objects.requireNonNull(laivynpc().getVersion().getMethodExec("GameProfile:getName").invokeInstance(this));
    }
    public @NotNull UUID getUniqueId() {
        return (UUID) Objects.requireNonNull(laivynpc().getVersion().getFieldExec("GameProfile:id").invokeInstance(this));
    }

    @Override
    public @NotNull GameProfileClass getClassExecutor() {
        return (GameProfileClass) laivynpc().getVersion().getClassExec("GameProfile");
    }

    public static class GameProfileClass extends ClassExecutor {
        public GameProfileClass(@NotNull String className) {
            super(className);
        }
    }
}
