package codes.laivy.npc.mappings.utils.classes.others.bukkit;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BukkitConfigurationSection extends ObjectExecutor {
    public BukkitConfigurationSection(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull BukkitConfigurationSection.BukkitConfigurationSectionClass getClassExecutor() {
        return new BukkitConfigurationSectionClass(ConfigurationSection.class.getName());
    }

    public static class BukkitConfigurationSectionClass extends ClassExecutor {
        public BukkitConfigurationSectionClass(@NotNull String className) {
            super(className);
        }
    }
}
