package com.laivynpc.core;

import com.laivynpc.core.api.NpcApi;
import com.laivynpc.core.api.provider.NpcApiProvider;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Painting;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

public class LaivyNPC extends JavaPlugin {

    // Static initializers

    public static @NotNull LaivyNPC laivynpc() {
        return getPlugin(LaivyNPC.class);
    }

    // Object

    private @UnknownNullability NpcApi api;

    public LaivyNPC() {
    }

    @Override
    public void onLoad() {
        this.api = new NpcApiProvider(this);
    }

    public @NotNull NpcApi getApi() {
        if (api == null) {
            throw new IllegalStateException("You cannot access the api before the LaivyNPC plugin loads.");
        }

        return api;
    }
    public void setApi(@NotNull NpcApi api) {
        this.api = api;
    }

    @Override
    public void onEnable() {
    }
    @Override
    public void onDisable() {
    }

}
