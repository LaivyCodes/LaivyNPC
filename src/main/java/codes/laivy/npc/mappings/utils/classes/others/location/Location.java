package codes.laivy.npc.mappings.utils.classes.others.location;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Location extends ObjectExecutor {
    public Location(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull LocationClass getClassExecutor() {
        return (LocationClass) laivynpc().getVersion().getClassExec("Location");
    }

    public static class LocationClass extends ClassExecutor {
        public LocationClass(@NotNull String className) {
            super(className);
        }
    }
}
