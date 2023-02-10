package codes.laivy.npc.mappings.defaults.classes.others.chat;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.*;

public class IChatBaseComponent extends ObjectExecutor {

    public static @NotNull IChatBaseComponent convert(@NotNull String string) {
        return laivynpc().getVersion().stringToBaseComponent(string);
    }
    public static @NotNull String convert(@NotNull IChatBaseComponent iChatBaseComponent) {
        return laivynpc().getVersion().baseComponentToString(iChatBaseComponent);
    }

    public IChatBaseComponent(@Nullable Object value) {
        super(value);
    }

    public @NotNull String getString() {
        return convert(this);
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
