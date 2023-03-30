package codes.laivy.npc.mappings.defaults.classes.entity.player;

import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class ProfilePublicKey extends ObjectExecutor {
    public ProfilePublicKey(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull ProfilePublicKeyClass getClassExecutor() {
        return (ProfilePublicKeyClass) laivynpc().getVersion().getClassExec("ProfilePublicKey");
    }

    public static class ProfilePublicKeyClass extends ClassExecutor {
        public ProfilePublicKeyClass(@NotNull String className) {
            super(className);
        }
    }
}
