package codes.laivy.npc.mappings.utils.classes.others.chat;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.*;

public class IChatBaseComponent extends ObjectExecutor {

    @NotNull
    public static IChatBaseComponent convert(@NotNull String string) {
        return laivynpc().getVersion().stringToBaseComponent(string);
    }

    public IChatBaseComponent(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull IChatBaseComponentClass getClassExecutor() {
        return (IChatBaseComponentClass) laivynpc().getVersion().getClassExec("IChatBaseComponent");
    }

    public static class IChatBaseComponentClass extends ClassExecutor {
        public IChatBaseComponentClass(@NotNull String className) {
            super(className);
        }
    }
    public static class ChatSerializerClass extends ClassExecutor {
        public ChatSerializerClass(@NotNull String className) {
            super(className);
        }
    }
}
