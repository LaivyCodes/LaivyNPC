package com.laivynpc.module;

import com.laivynpc.core.modules.Entity;
import com.laivynpc.core.modules.Module;
import com.laivynpc.core.modules.entities.DroppedItem;
import com.laivynpc.core.utils.Version;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public final class Main implements Module {

    public static void main(String[] args) {
    }

    private final @NotNull ClassLoader classLoader;

    public Main(@NotNull ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public @NotNull ClassLoader getClassLoader() {
        return classLoader;
    }

    @Override
    public @NotNull String @NotNull [] getCompatibleVersions() {
        return new String[] { "v1_8_R1" };
    }

    @Override
    public @NotNull Version getVersion() {
        @NotNull InputStream stream = Objects.requireNonNull(Main.class.getResourceAsStream("/properties.yml"));
        @NotNull InputStreamReader reader = new InputStreamReader(stream);
        @NotNull YamlConfiguration configuration = YamlConfiguration.loadConfiguration(reader);
        @NotNull String[] version = configuration.getString("version").split("\\.");

        return Version.of(configuration.getString("vendor"), Integer.parseInt(version[0]), Integer.parseInt(version[1]));
    }

    @Override
    public @NotNull EntityType getEntiyType(@NotNull Object object) {
        return null;
    }

    @Override
    public @NotNull Entity create(@NotNull Location location, @NotNull EntityType type) {
        return EntityCreator.create(location, type);
    }

}